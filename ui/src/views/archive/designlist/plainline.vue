<template>
    <DxDataGrid
        :ref="dataGridRefName"
        :data-source="propertyLineData"
        :show-borders="true"
        :allow-column-resizing="true"
        @editorPreparing="editorPreparing"
        @cell-dbl-click="rowDoubleClick"
        :selection="{ mode: 'single' }"
    >
      <DxColumn data-field="archiveToUserFiles" cell-template="cellTemplate" :calculate-cell-value="picture" data-type="string" :caption="$t('app.archive.design.designName')"/>
      <DxColumn data-field="archiveStatus" data-type="string" :caption="$t('app.archive.design.archiveStatus')" :calculate-display-value="calculatePropertyValue"/>
      <DxColumn data-field="insertDate" data-type="date" :caption="$t('common.insertDate')"/>
      <DxColumn data-field="savesUser" data-type="string" :caption="$t('common.savesUser')"/>
      <DxColumn data-field="versionNumber" data-type="string" :caption="$t('common.versionNumber')"/>
      <DxColumn data-field="versionDate" data-type="date" :caption="$t('common.versionDate')"/>
      <template #cellTemplate="{ data }">
        <img :src="data.value" width="100" height="100">
      </template>
    </DxDataGrid>
</template>
<script>

import {
  DxDataGrid,
  DxColumn,
} from 'devextreme-vue/data-grid';
import CustomStore from "devextreme/data/custom_store";
import restapi from "@/app/common/utils/restapi";
import kazu from "@/app/common/utils/kazu";
import i18n from "@/app/common/utils/i18n";
const dataGridRefName = "dataGridRefName";
export default {
  props: {
    templateData: {
      type: Object,
      default: () => {
      }
    }
  },
  components: {
    DxDataGrid,
    DxColumn,
  },
  data() {
    let {id} = this.templateData.data;
    return {
      dataGridRefName,
      propertyLineData: this.lineData(id)
    };
  },
  methods: {
    calculatePropertyValue(e){
      return i18n.global.t('app.archive.design.status.' + e.archiveStatus)
    },
    insertdate(data) {
       return kazu.trDateTimeFormat(data.insertDate)
    },
    versiondate(data){
      return kazu.trDateTimeFormat(data.versionDate)
    },
    picture(data){
      let files = data.archiveToUserFiles
      const file = files.filter(function (filter){
          return filter.fileType === "original"
      });
      return kazu.thumbnailPicture(file[0].userFile.url,file[0].userFile.fileName)
    },
    rowDoubleClick(e){
      this.$emit('callbackDoubleClick',e.data);
    },
    editorPreparing(e) {
      e.editorOptions.value = e.row.cells[0].displayValue
    },
    getDisplayExpr(item) {
      return item.name
    },
    lineData: (id) => {
      //return kazu.CustomStoreService('/archive/rd/get-by-plan-id/'+id,'id')
      return new CustomStore({
        key: 'id',
        load: () => {
          let url = '/archive/rd/get-by-plan-id/'+id
          return restapi({
            method: 'GET',
            url
          });
        },
      })
    },
  },
  computed: {
    dataGrid: function () {
      return this.$refs[dataGridRefName].instance;
    }
  },
};
</script>
