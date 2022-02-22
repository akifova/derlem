<template>
  <div class="dx-card responsive-paddings">
    <DxDataGrid
        :show-borders="true"
        column-width="100%"
        :data-source="gridDataSource"
        :selection="{ mode: 'single' }"
        @initialized="gridInitialized"
        @row-dbl-click="onGridRowDblClick"
        @context-menu-preparing="onGridContextMenuPreparing"
    >
      <DxFilterRow :visible="false"/>
      <DxFilterPanel :visible="false"/>
      <DxHeaderFilter :visible="false"/>
      <DxScrolling column-rendering-mode="virtual"/>
      <DxColumnChooser :enabled="true"/>
      <DxGroupPanel :visible="false"/>

      <DxColumn
          :width="100"
          :allow-sorting="false"
          data-field="archiveToUserFiles"
          cell-template="thumbnailImage"
          :caption="$t('app.archive.search.grid-column.caption.design-image')"
      />
      <DxColumn
          data-type="number"
          data-field="versionNumber"
          :caption="$t('app.archive.search.grid-column.caption.version-number')"
      />
      <DxColumn
          data-field="archiveStatus"
          :caption="$t('app.archive.search.grid-column.caption.archive-status')"
          :calculate-display-value="calculateStatusCell"
      />
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
      <template #thumbnailImage="{ data }">
        <img :src="getThumbnailImage(data.data)">
      </template>
    </DxDataGrid>
    <show-archive-image ref="archiveImagePopUp"/>
  </div>
</template>

<script>
import kazu from "@/app/common/utils/kazu";
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
import ShowArchiveImage from "@/app/components/archive/ShowArchiveImage";
import i18n from "@/app/common/utils/i18n";
import restapi from "@/app/common/utils/restapi";
import store from "@/store";

export default {
  name: "ArchiveBasicGrid",
  data() {
    return {
      gridDataSource: []
    }
  },
  methods: {
    getThumbnailImage: function(archiveDto) {
      let originalFile = archiveDto.archiveToUserFiles.filter(f=> f.fileType === "original")[0].userFile;
      return kazu.thumbnailPicture(originalFile.url,originalFile.fileName);
    },
    calculateStatusCell: function (rowData) {
      return i18n.global.t('app.archive.design.status.' + rowData.archiveStatus);
    },
    searchArchive: function (searchData) {
      let predicates = [];
      for(let field in searchData) {
        if(typeof searchData[field] === 'object') {
          if(searchData[field].id) {
            predicates.push({field: field + '.id', type: "==", value: searchData[field]['id']})
          } else if(searchData[field].key) {
            predicates.push({field: field, type: "==", value: searchData[field]['key']})
          }
        } else if(typeof searchData[field] == 'string') {
          predicates.push({field: field, type: "==", value: searchData[field]})
        } else if(Number.isInteger(searchData[field])) {
          predicates.push({field: field, type: "=ge=", value: searchData[field]})
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
    },
    onGridContextMenuPreparing: function (e) {
      let items = [];
      e.component.clearSelection();
      e.component.selectRowsByIndexes([e.rowIndex]).then(()=> {
        const userRoles = store.getters._getCurrentUser?.roles;
        const selectedItem = e.component.getSelectedRowsData()[0];
        const haveAdminRole = userRoles.indexOf("ADMIN") > -1;

            items.push({
              text: selectedItem.design ? selectedItem.design.code + ' [' + selectedItem.designName + ']' : selectedItem.designName,
              disabled: true
            });

        items.push({
          text: i18n.global.t('common.select'),
          onClick: () => {
            this.archiveSelected(selectedItem)
          }
        });

            if(haveAdminRole || userRoles.indexOf("DOWNLOAD_JPG") > -1) {
              items.push({
                text: i18n.global.t('app.show-archive-image.view'),
                disabled: false,
                onClick: () => {
                  this.$refs.archiveImagePopUp.onPopUpShowing(selectedItem.id);
                }
              });
            }
      });
      e.items = items;
    },
    onGridRowDblClick(e) {
      this.archiveSelected(e.data);
    },
    archiveSelected(archiveDto) {
      restapi({
        method: 'GET',
        'url': '/specimen/creel/get-by-archive-id/' + archiveDto.id
      }).then(result => {
        archiveDto.creel = result.data;
        this.$emit('selected-data', archiveDto);
      });
    }
  },
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
  }
}
</script>
<style scoped>
img {
  height: 50px;
  display: block;
}
</style>