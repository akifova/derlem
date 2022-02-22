<template>
  <div class="content-block">
    <div class="dx-card responsive-paddings">
      <DxGallery
          id="gallery"
          :data-source="dataSource"
          :loop="loop"
          :slideshow-delay="slideshowDelay"
          :show-nav-buttons="showNavButtons"
          :show-indicator="showIndicator"
          :height="300"
      />
    </div>
  </div>
</template>

<script>
import DxGallery from 'devextreme-vue/gallery';

import restapi from "@/app/common/utils/restapi";
import i18n from "@/app/common/utils/i18n";
import kazu from "@/app/common/utils/kazu";
import store from "@/store";

export default {
  components: {
    DxGallery,
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
