<template>
  <div class="content-block">
    <div class="dx-card responsive-paddings">
      <DxDataGrid
          :show-borders="true"
          column-width="auto"
          :data-source="gridDataSource"
          :selection="{ mode: 'single' }"
          @initialized="gridInitialized"
          @context-menu-preparing="onGridContextMenuPreparing"
      >
        <DxFilterRow :visible="true"/>
        <DxFilterPanel :visible="true"/>
        <DxHeaderFilter :visible="true"/>
        <DxScrolling column-rendering-mode="virtual"/>
        <DxColumnChooser :enabled="true"/>
        <DxGroupPanel :visible="true"/>
        <DxColumn :caption="$t('app.archive.search.grid-column.caption.design-property')">
          <DxColumn
              :width="100"
              :allow-sorting="false"
              data-field="archiveToUserFiles"
              cell-template="thumbnailImage"
              :caption="$t('app.archive.search.grid-column.caption.design-image')"
          />
          <DxColumn
              data-type="string"
              data-field="archiveStatus"
              :caption="$t('app.archive.design.archiveStatus')"
              :calculate-cell-value="calculateStatusCell"
          />
          <DxColumn
              data-type="number"
              data-field="versionNumber"
              :caption="$t('app.archive.search.grid-column.caption.version-number')"
          />
          <DxColumn
              data-type="string"
              data-field="difficulty"
              :caption="$t('app.archive.design.difficulty')"
          />
          <DxColumn
              data-type="string"
              data-field="makingRare"
              :visible="false"
              :caption="$t('app.archive.design.makingRare')"
              :calculate-cell-value="calculateMakingRareValue"
          />
          <DxColumn
              data-type="string"
              data-field="starRating"
              :caption="$t('app.archive.design.starRating')"
          />
          <DxColumn
              data-type="string"
              data-field="tag"
              :caption="$t('app.archive.design.tag')"
          />
          <DxColumn
              data-type="string"
              data-field="version"
              :visible="false"
              :caption="$t('app.specimen.yarn.version')"
          />
          <DxColumn
              data-type="string"
              data-field="designer.nameSurname"
              :caption="$t('app.archive.design.designer')"
          />
          <DxColumn
              data-type="string"
              data-field="customer.name"
              :caption="$t('app.archive.design.customer')"
          />
        </DxColumn>
        <DxColumn :caption="$t('app.archive.search.grid-column.caption.stock-card.header')">
          <DxColumn :caption="$t('app.archive.search.grid-column.caption.stock-card.quality')">
            <DxColumn
                data-type="string"
                data-field="quality.definition"
                :caption="$t('app.archive.search.grid-column.definition')"
            />
            <DxColumn
                data-type="string"
                data-field="quality.code"
                :visible="false"
                :caption="$t('app.archive.search.grid-column.code')"
            />
          </DxColumn>
          <DxColumn :caption="$t('app.archive.search.grid-column.caption.stock-card.collection')">
            <DxColumn
                data-type="string"
                data-field="collection.definition"
                :caption="$t('app.archive.search.grid-column.definition')"
            />
            <DxColumn
                data-type="string"
                data-field="collection.code"
                :visible="false"
                :caption="$t('app.archive.search.grid-column.code')"
            />
          </DxColumn>
          <DxColumn :caption="$t('app.archive.search.grid-column.caption.stock-card.design')">
            <DxColumn
                data-type="string"
                data-field="design.code"
                :visible="true"
                :caption="$t('app.archive.search.grid-column.code')"
            />
          </DxColumn>
          <DxColumn :caption="$t('app.archive.search.grid-column.caption.stock-card.size')">
            <DxColumn
                data-type="string"
                data-field="size.definition"
                :caption="$t('app.archive.search.grid-column.definition')"
            />
            <DxColumn
                data-type="string"
                data-field="size.code"
                :visible="false"
                :caption="$t('app.archive.search.grid-column.code')"
            />
          </DxColumn>
          <DxColumn :caption="$t('app.archive.search.grid-column.caption.stock-card.edge')">
            <DxColumn
                data-type="string"
                data-field="edge.definition"
                :caption="$t('app.archive.search.grid-column.definition')"
            />
            <DxColumn
                data-type="string"
                data-field="edge.code"
                :visible="false"
                :caption="$t('app.archive.search.grid-column.code')"
            />
          </DxColumn>
          <DxColumn :caption="$t('app.archive.search.grid-column.caption.stock-card.shape')">
            <DxColumn
                data-type="string"
                data-field="shape.definition"
                :caption="$t('app.archive.search.grid-column.definition')"
            />
            <DxColumn
                data-type="string"
                data-field="shape.code"
                :visible="false"
                :caption="$t('app.archive.search.grid-column.code')"
            />
          </DxColumn>
          <DxColumn :caption="$t('app.archive.search.grid-column.caption.stock-card.side-edge')">
            <DxColumn
                data-type="string"
                data-field="sideEdge.definition"
                :caption="$t('app.archive.search.grid-column.definition')"
            />
            <DxColumn
                data-type="string"
                data-field="sideEdge.code"
                :visible="false"
                :caption="$t('app.archive.search.grid-column.code')"
            />
          </DxColumn>

        </DxColumn>
        <DxColumn :caption="$t('app.archive.search.grid-column.caption.design-plan.header')">
          <DxColumn
              data-type="string"
              data-field="designPlan.title"
              :caption="$t('app.archive.search.grid-column.caption.design-plan.title')"
          />
          <DxColumn
              data-type="string"
              data-field="designName"
              :caption="$t('app.archive.design.designName')"
          />
          <DxColumn
              data-type="string"
              data-field="weaveType"
              :caption="$t('app.archive.design.weaveType')"
          />
        </DxColumn>
        <template #thumbnailImage="{ data }">
          <img :src="getThumbnailImage(data.data)">
        </template>
      </DxDataGrid>
      <show-archive-image ref="archiveImagePopUp"/>
    </div>
  </div>
</template>

<script>
import {
  DxColumn,
  DxColumnChooser,
  DxDataGrid,
  DxFilterPanel,
  DxFilterRow,
  DxGroupPanel,
  DxHeaderFilter,
  DxScrolling
} from 'devextreme-vue/data-grid';
import restapi from "@/app/common/utils/restapi";
import i18n from "@/app/common/utils/i18n";
import kazu from "@/app/common/utils/kazu";
import store from "@/store";
import ShowArchiveImage from "@/app/components/archive/ShowArchiveImage";

export default {
  components: {
    DxDataGrid,
    DxColumn,
    DxHeaderFilter,
    DxFilterRow,
    DxFilterPanel,
    DxScrolling,
    DxGroupPanel,
    DxColumnChooser,
    ShowArchiveImage
  },
  data(){
    return{
      gridInstance: null,
      gridDataSource: [],
      isPopUpVisible: false,
      selectedArchiveId: ''
    }
  },
  methods: {
    calculateStatusCell: function (rowData) {
      return i18n.global.t('app.archive.design.status.' + rowData.archiveStatus);
    },
    calculateMakingRareValue: function (rowData) {
      return i18n.global.t('app.archive.search.grid-column.makingRare.' + rowData.makingRare );
    },
    getThumbnailImage: function(archiveDto) {
      let originalFile = archiveDto.archiveToUserFiles.filter(f=> f.fileType === "original")[0].userFile;
      return kazu.thumbnailPicture(originalFile.url,originalFile.fileName);
    },
    onGridContextMenuPreparing: function (e) {
      e.component.clearSelection();
      e.component.selectRowsByIndexes([e.rowIndex]).then(()=> {
        let items = [];
        let downloadItems = [];
        let changeStatusItems = [];

        const userRoles = store.getters._getCurrentUser?.roles;
        const selectedItem = e.component.getSelectedRowsData()[0];
        const haveAdminRole = userRoles.indexOf("ADMIN") > -1;
        const fileName = selectedItem.archiveToUserFiles.filter(f=> f.fileType === "original")[0].userFile.originalFileName.replace(/\.[^/.]+$/, "");


        items.push({
          text: selectedItem.design ? selectedItem.design.code + ' [' + selectedItem.designName + ']' : selectedItem.designName,
          disabled: true
        });

        if(haveAdminRole || userRoles.indexOf("DOWNLOAD_JPG") > -1) {
          items.push({
            text: i18n.global.t('app.show-archive-image.view'),
            disabled: false,
            onClick: () => { this.$refs.archiveImagePopUp.onPopUpShowing(selectedItem.id); }
          });

          downloadItems.push({
            text: i18n.global.t('app.archive.search.grid-column.context-menu.download.jpg'),
            disabled: false,
            onClick: () => { this.downloadJpg(selectedItem.id, fileName) }
          });
        }

        if(haveAdminRole || userRoles.indexOf("DOWNLOAD_DESIGN") > -1) {
          downloadItems.push({
            text: i18n.global.t('app.archive.search.grid-column.context-menu.download.bmp'),
            disabled: false,
            onClick: () => { this.downloadBmp(selectedItem.id, fileName) }
          });
          downloadItems.push({
            text: i18n.global.t('app.archive.search.grid-column.context-menu.download.zip'),
            disabled: false,
            onClick: () => { this.downloadZip(selectedItem.id, fileName) }
          })
        }

        if(selectedItem.archiveStatus !== "RD") {
          if (haveAdminRole || userRoles.indexOf("ARCHIVE_WRITE") > -1) {
            changeStatusItems.push({
              text: i18n.global.t('app.archive.search.grid-column.context-menu.convert-status.convert-to-archive'),
              disabled: false,
              onClick: () => {
                this.convertStatusToArchive(selectedItem)
              }
            })
          }

          if (haveAdminRole || userRoles.indexOf("ARCHIVE_STOCK") > -1) {
            changeStatusItems.push({
              text: i18n.global.t('app.archive.search.grid-column.context-menu.convert-status.convert-to-stock'),
              disabled: false,
              onClick: () => {
                this.convertStatusToStock(selectedItem)
              }
            })
          }
          if (haveAdminRole || userRoles.indexOf("ARCHIVE_UNUSABLE") > -1) {
            changeStatusItems.push({
              text: i18n.global.t('app.archive.search.grid-column.context-menu.convert-status.convert-to-unusable'),
              disabled: false,
              onClick: () => {
                this.convertStatusToUnusable(selectedItem)
              }
            })
          }
          if (selectedItem.archiveStatus === 'PRODUCT' && (haveAdminRole || userRoles.indexOf("ARCHIVE_RETIRED") > -1)) {
            changeStatusItems.push({
              text: i18n.global.t('app.archive.search.grid-column.context-menu.convert-status.convert-to-retired'),
              disabled: false,
              onClick: () => {
                this.convertStatusToRetired(selectedItem)
              }
            })
          }
        }

        if(downloadItems.length > 0) {
          items.push({
            text: i18n.global.t('app.archive.search.grid-column.context-menu.download.header'),
            disabled: false,
            items: downloadItems
          })
        }

        if(changeStatusItems.length > 0) {
          items.push({
            text: i18n.global.t('app.archive.search.grid-column.context-menu.convert-status.header'),
            disabled: false,
            items: changeStatusItems
          })
        }

        e.items = items;
      })
    },
    convertStatusToArchive: function (archiveDto) {
      this.changeStatusRequest('/archive/convert-to-archive/' + archiveDto.id, archiveDto, 'PRODUCT');
    },
    convertStatusToStock: function (archiveDto) {
      this.changeStatusRequest('/archive/shelve/convert-to-stock/' + archiveDto.id, archiveDto, 'STOCK');
    },
    convertStatusToUnusable: function (archiveDto) {
      this.changeStatusRequest('/archive/shelve/convert-to-unusable/' + archiveDto.id, archiveDto, 'UNUSABLE');
    },
    convertStatusToRetired: function (archiveDto) {
      this.changeStatusRequest('/archive/shelve/convert-to-retired/' + archiveDto.id, archiveDto, 'RETIRED');
    },
    changeStatusRequest: function (url, archiveDto, newStatus) {
      restapi({
        url: url,
        method: 'PUT'
      }).then(()=>{
        archiveDto.archiveStatus = newStatus;
      })
    },
    downloadJpg: function (archiveId, fileName) {
      kazu.downloadImage('/file/download/jpg/by-archive-id/' + archiveId, fileName + '.jpg')
    },
    downloadZip: function (archiveId, fileName) {
      kazu.downloadImage('/file/download/bmp/supplementary/by-archive-id/' + archiveId, fileName + '.zip')
    },
    downloadBmp: function (archiveId, fileName) {
      kazu.downloadImage('/file/download/bmp/by-archive-id/' + archiveId, fileName + '.bmp')
    },
    searchArchive: function (formData) {
      let predicates = [];
      for(let field in formData) {
        if(typeof formData[field] === 'object') {
          if(formData[field].id) {
            predicates.push({field: field + '.id', type: "==", value: formData[field]['id']})
          } else if(formData[field].key) {
            predicates.push({field: field, type: "==", value: formData[field]['key']})
          }
        } else if(typeof formData[field] == 'string') {
          predicates.push({field: field, type: "==", value: `*${formData[field]}*`})
        } else if(Number.isInteger(formData[field])) {
          predicates.push({field: field, type: "=ge=", value: formData[field]})
        }
      }
      let query = "";
      if(predicates.length > 0) {
        query = predicates.map(function (predicate) {
          return predicate.field + predicate.type + predicate.value;
        }).join(";")
      }

      if(query !== "") {
        restapi({
          method: "GET",
          url: `/archive/search?sp=${query}`
        }).then((result) => {
          this.gridDataSource = result.data;
          this.gridInstance.refresh();
        })
      }
    },
    gridInitialized(e) {
      this.gridInstance = e.component
    }
  },

}
</script>

<style scoped>
img {
  height: 100px;
  display: block;
}
</style>