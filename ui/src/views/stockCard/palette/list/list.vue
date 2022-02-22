<template>
  <div>
    <h2 class="content-block">{{$t('app.menu.stock-card.palette.list')}}</h2>
    <div class="content-block">
      <div class="dx-card responsive-paddings">
        <DxDataGrid
            :ref="dataGridRefName"
            id="gridContainer"
            :data-source="dataSource"
            :columns-auto-width="true"
            :show-borders="true"
        >
          <DxFilterRow :visible="true"/>
          <DxFilterPanel :visible="true"/>
          <DxHeaderFilter :visible="true"/>
          <DxScrolling mode="infinite"/>
          <DxGroupPanel :visible="true"/>
          <DxMasterDetail
              :enabled="true"
              template="propertyLine"
          />
          <template #propertyLine="{ data: employee }">
            <propertyLine
                :template-data="employee"
            />
          </template>
          <DxColumn
              data-type="string"
              data-field="code"
              :caption="$t('app.stock-card.palette.code')"
          >
          </DxColumn>
          <DxColumn
              data-type="string"
              data-field="definition"
              :caption="$t('app.stock-card.palette.definition')"
          >
          </DxColumn>
          <DxColumn type="buttons">
            <DxButton
                icon="trash"
                type="default"
                @click="deleteBtnClick($event)"
            />
          </DxColumn>
        </DxDataGrid>
      </div>
    </div>
  </div>
</template>
<script>

import restapi from "@/app/common/utils/restapi";
import kazu from "@/app/common/utils/kazu";
import {
  DxButton,
  DxColumn,
  DxDataGrid,
  DxFilterPanel,
  DxFilterRow,
  DxGroupPanel,
  DxHeaderFilter,
  DxMasterDetail,
  DxScrolling
} from 'devextreme-vue/data-grid';
import propertyLine from "./line"
import CustomStore from "devextreme/data/custom_store";
import {confirm} from "devextreme/ui/dialog";
import i18n from "@/app/common/utils/i18n";

const dataGridRefName = "dataGridRefName";
export default {
  components: {
    DxMasterDetail,
    DxDataGrid,
    DxColumn,
    DxHeaderFilter,
    DxFilterRow,
    DxFilterPanel,
    DxScrolling,
    DxGroupPanel,
    DxButton,
    propertyLine
  },
  data() {
    return {
      formData: {
        properties:[]
      },
      dataGridRefName,
      dataSource:new CustomStore({
        key: 'id',
        load: () => {
          let url = '/scm/production/definition/stock-card/palette/'
          return restapi({
            method: 'GET',
            url
          }).then(data => {
            return {
              data: data.data,
              totalCount: data.data.length,
            };
          });
        },
      }),
      deleteBtnOptions: {
        text: i18n.global.t('common.cancel'),
        type: 'danger',
        useSubmitBehavior: true,
        onClick:() => {
          this.formData = {}
          this.showButton = false
          this.caption = i18n.global.t('app.menu.yarn.group.add')
        }
      },
      showButton: false,
      caption: i18n.global.t('app.menu.yarn.group.add'),
    }
  },
  computed: {
    dataGrid: function () {
      return this.$refs[dataGridRefName].instance;
    }
  },
  methods: {
    deleteBtnClick(e){
      let result = confirm(this.$t('common.delete-confirm'), this.$t('common.confirm'));
      result.then((dialogResult) => {
        if (dialogResult){
          kazu.RestService('/scm/production/definition/stock-card/palette/' + e.row.key,'','DELETE').then(() => {
            this.formData = {}
            this.showButton = false
            this.dataGrid.refresh()
          })
        }
      });
    }
  }
}
</script>
<style lang="scss">

.dx-button{
  width: 100%;
}
.dx-field-button-item{
  padding: 0px;
}
</style>
