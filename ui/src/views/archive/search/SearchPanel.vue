<template>
  <h2 class="content-block">{{$t('app.menu.archive.search.text')}}</h2>
  <div class="content-block">
    <div class="dx-card responsive-paddings">
      <DxForm
          :form-data="formData"
          :read-only="false">

        <DxTabbedItem>
          <DxTab :title="$t('app.archive.search.tab.designProperties')">
            <DxGroupItem :col-count="4">
              <DxSimpleItem data-field="starRating" editor-type="dxSlider" :label="{text: $t('app.archive.design.starRating')}"
                            :editor-options="{min:0, max:5, value:0, tooltip: sliderTooltip}"/>
              <DxSimpleItem data-field="difficulty" editor-type="dxSlider" :label="{text: $t('app.archive.design.difficulty')}"
                            :editor-options="{min:0, max:100, step:5, value:0, tooltip: sliderTooltip}"/>
              <DxSimpleItem :col-span="2" :label="{text: $t('app.archive.design.designPlan')}">
                <template #default>
                  <DxLookup @value-changed="lookupValueChanged($event, 'designPlan')" :show-clear-button="true" :data-source="designPlanDataSource" :display-expr="designPlanDisplayExpr" :min-search-length="2" :search-enabled="true"/>
                </template>
              </DxSimpleItem>
              <DxSimpleItem :label="{text: $t('app.archive.design.weaveType')}">
                <template #default>
                  <DxLookup :data-source="weaveTypeDataSource" :display-expr="keyValueDisplayExpr" @value-changed="lookupValueChanged($event, 'weaveType')"/>
                </template>
              </DxSimpleItem>
              <DxSimpleItem :label="{text: $t('app.archive.design.overlockYarn')}">
                <template #default>
                  <DxLookup :data-source="yarnDataSource" :display-expr="yarnDisplayExpr" :min-search-length="2" :search-enabled="true" @value-changed="lookupValueChanged($event, 'overlockYarn')"/>
                </template>
              </DxSimpleItem>
            </DxGroupItem>
          </DxTab>
          <DxTab :title="$t('app.archive.search.tab.stockCard')">
            <StockCardFieldsLookup @data-field="stockCardFieldChanged"></StockCardFieldsLookup>
          </DxTab>
          <DxTab :title="$t('app.archive.search.tab.archive')">
            <DxGroupItem :col-count="4">
              <DxSimpleItem data-field="designName" :label="{text: $t('app.archive.design.designName')}"/>
              <DxSimpleItem data-field="tag" :label="{text: $t('app.archive.design.tag')}"/>
              <DxSimpleItem :label="{text: $t('app.archive.design.firm')}">
                <template #default>
                  <DxLookup @value-changed="lookupValueChanged($event, 'firm')" :data-source="firmDataSource" :display-expr="firmDisplayExpr" :min-search-length="2" :search-enabled="true"/>
                </template>
              </DxSimpleItem>
              <DxSimpleItem :label="{text: $t('app.archive.design.archiveStatus')}">
                <template #default>
                  <DxLookup :data-source="archiveStatusDataSource" :display-expr="keyValueDisplayExpr" @value-changed="lookupValueChanged($event, 'archiveStatus')"/>
                </template>
              </DxSimpleItem>
            </DxGroupItem>
          </DxTab>
        </DxTabbedItem>

        <DxGroupItem :col-count="5">
          <DxEmptyItem :col-span="4"/>
          <DxSimpleItem editor-type="dxButton" :editor-options="{text: 'Ara', type: 'success', onClick: searchArchive}"/>
        </DxGroupItem>
      </DxForm>
    </div>
  </div>
  <ResultGrid ref="resultGrid"/>
</template>
<script>

import {DxEmptyItem, DxForm, DxGroupItem, DxSimpleItem, DxTab, DxTabbedItem} from "devextreme-vue/form";
import {DxLookup} from "devextreme-vue/lookup";
import {CustomStoreCreator} from "@/app/common/utils/CustomStoreCreator";
import ResultGrid from "./ResultGrid"
import StockCardFieldsLookup from "@/app/components/archive/StockCardFieldsLookup";


export default {
  components: {
    DxForm,
    DxTabbedItem,
    DxTab,
    DxGroupItem,
    DxSimpleItem,
    DxLookup,
    DxEmptyItem,
    ResultGrid,
    StockCardFieldsLookup
  },
  data: function() {
    return {
      formData:{},
      sliderTooltip: { enabled: true, showMode: 'always', position: 'bottom'},
      firmDataSource: CustomStoreCreator().createLookupStore('/common/firm/search', ['name','tradeName']),
      designPlanDataSource: CustomStoreCreator().createLookupStore('/design/plan/search', ['title']),
      weaveTypeDataSource: CustomStoreCreator().createLoadStore('/archive/weave-types/', 'key'),
      yarnDataSource: CustomStoreCreator().createLookupStore('/yarn/search', ['name', 'mark']),
      archiveStatusDataSource: CustomStoreCreator().createLoadStore('/archive/statuses', 'key')
    };
  },
  methods: {
    keyValueDisplayExpr: function (keyValueDto) {
      return keyValueDto ? keyValueDto.value : "";
    },
    firmDisplayExpr: function (firmDto) {
      return firmDto ? firmDto.name : "";
    },
    designPlanDisplayExpr: function (designPlanDto) {
      return designPlanDto ? designPlanDto.title : "";
    },
    yarnDisplayExpr: function (yarnDto) {
      return yarnDto ? yarnDto.name : '';
    },
    lookupValueChanged: function (e, dataField) {
      this.formData[dataField] = e.value;
    },
    stockCardFieldChanged: function (data) {
      this.formData[data.field] = data.value;
    },
    searchArchive(){
      this.$refs.resultGrid.searchArchive(this.formData);
    }
  }
};
</script>
