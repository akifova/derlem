<template>
  <div>
    <h2 class="content-block">{{$t('app.menu.stock-card.edge.text')}}</h2>
    <div class="content-block">
      <div class="dx-card responsive-paddings">
          <DxForm
              :form-data="formData"
              :read-only="false"
              :show-colon-after-label="true"
              :show-validation-summary="true"
              validation-group="edgeValidationGroup"
              @initialized="formInitialized"
          >
            <DxGroupItem :col-count="3" :caption="caption">
              <DxGroupItem :col-span="3" :col-count="3">
                <DxSimpleItem data-field="code" :label="{text: $t('app.stock-card.edge.code')}"
                              :editor-options="{maxLength:'4'}">
                  <DxRequiredRule :message="$t('validation.stock-card.edge.required-code')"></DxRequiredRule>
                </DxSimpleItem>
                <DxSimpleItem data-field="definition" :label="{text: $t('app.stock-card.edge.definition')}"
                              :editor-options="{maxLength:'100'}">
                  <DxRequiredRule :message="$t('validation.stock-card.edge.required-definition')"></DxRequiredRule>
                </DxSimpleItem>
                <DxSimpleItem data-field="length" :label="{text: $t('app.stock-card.edge.length')}" editor-type="dxNumberBox"
                              :editor-options="{ min:'0' }">
                  <DxRequiredRule :message="$t('validation.stock-card.edge.required-length')"></DxRequiredRule>
                </DxSimpleItem>
              </DxGroupItem>
            </DxGroupItem>
            <DxGroupItem :col-count="4">
              <DxGroupItem :col-count="2" :col-span="2">
                <DxButtonItem
                    :button-options="saveBtnOptions"
                />
              </DxGroupItem>
            </DxGroupItem>
          </DxForm>
      </div>
    </div>
    <div class="content-block">
      <div class="dx-card responsive-paddings">
        <DxDataGrid
            :data-source="edgeDataSource"
            :columns-auto-width="true"
            :show-borders="true"
            @row-updating="onEdgeUpdating"
            @initialized="gridInitialized"
        >
          <DxEditing
              :allow-deleting="true"
              :allow-updating="true"
              mode="popup"
          />
          <DxFilterRow :visible="true"/>
          <DxFilterPanel :visible="true"/>
          <DxHeaderFilter :visible="true"/>
          <DxScrolling mode="infinite"/>
          <DxGroupPanel :visible="true"/>
          <DxColumn
              data-type="string"
              data-field="definition"
              :caption="$t('app.stock-card.edge.definition')"
          />
          <DxColumn
              data-field="code"
              data-type="string"
              :caption="$t('app.stock-card.edge.code')"
          />
          <DxColumn
              alignment="left"
              data-field="length"
              data-type="number"
              :caption="$t('app.stock-card.edge.length')"
          />
        </DxDataGrid>
      </div>
    </div>
  </div>
</template>
<script>
import {DxButtonItem, DxForm, DxGroupItem, DxSimpleItem,} from 'devextreme-vue/form';
import {
  DxColumn,
  DxDataGrid,
  DxEditing,
  DxFilterPanel,
  DxFilterRow,
  DxGroupPanel,
  DxHeaderFilter,
  DxRequiredRule,
  DxScrolling
} from 'devextreme-vue/data-grid';
import i18n from "@/app/common/utils/i18n";
import _ from "lodash";
import {CustomStoreCreator} from "@/app/common/utils/CustomStoreCreator";

export default {
  components: {
    DxEditing,
    DxForm,
    DxSimpleItem,
    DxGroupItem,
    DxRequiredRule,
    DxButtonItem,
    DxDataGrid,
    DxColumn,
    DxHeaderFilter,
    DxFilterRow,
    DxFilterPanel,
    DxScrolling,
    DxGroupPanel,
  },
  data() {
    return {
      formData: {},
      formInstance: null,
      gridInstance: null,
      edgeDataSource: CustomStoreCreator().createStore('/scm/production/definition/stock-card/edge/'),
      saveBtnOptions: {
        type: 'default',
        useSubmitBehavior: true,
        text: i18n.global.t('common.save'),
        validationGroup: "edgeValidationGroup",
        onClick: (e) => {
          let validationResult = e.validationGroup.validate();
          if (validationResult.isValid) {
            this.edgeDataSource.insert(this.formData).then(() => {
              this.formInstance.resetValues();
              this.gridInstance.refresh()
            })
          }
        }
      },
      caption: i18n.global.t('app.menu.stock-card.edge.add'),
    }
  },
  methods: {
    formInitialized: function (e) {
      this.formInstance = e.component
    },
    gridInitialized: function (e) {
      this.gridInstance = e.component
    },
    onEdgeUpdating: function (data) {
      data.newData = _.extend({}, data.oldData, data.newData);
    },
  }
}
</script>
