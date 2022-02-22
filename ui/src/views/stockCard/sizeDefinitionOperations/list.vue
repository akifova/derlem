<template>
  <div>
    <h2 class="content-block">{{ $t('app.size-definition.field-definition') }}</h2>
    <div class="content-block">
      <div class="dx-card responsive-paddings">
        <DxForm
            :form-data="formData"
            :read-only="false"
            :show-colon-after-label="true"
            :show-validation-summary="true"
            validation-group="sizeDefinitionValidationGroup"
            @initialized="formInitialized"
        >
          <DxGroupItem :col-count="3" :caption="caption">
            <DxGroupItem :col-span="3" :col-count="3">
              <DxSimpleItem data-field="code" data-type="string" :label="{text: $t('app.size-definition.code')}"
                            :editor-options="{ maxLength:'4' }">
                <DxRequiredRule :message="$t('validation.size-definition.code')"></DxRequiredRule>
              </DxSimpleItem>
              <DxSimpleItem data-field="width" data-type="number" :label="{text: $t('app.size-definition.width')}"
                            editor-type="dxNumberBox"
                            :editor-options="{min:0,max:9999}">
                <DxRequiredRule :message="$t('validation.size-definition.width')"></DxRequiredRule>
              </DxSimpleItem>
              <DxSimpleItem data-field="length" data-type="number" :label="{text: $t('app.size-definition.length')}"
                            editor-type="dxNumberBox"
                            :editor-options="{min:0,max:9999,onValueChanged:onValueChangedLength}">
                <DxRequiredRule :message="$t('validation.size-definition.length')"></DxRequiredRule>
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
            :data-source="sizeDataSource"
            :columns-auto-width="true"
            :show-borders="true"
            @row-updating="onSizeDefinitionUpdating"
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
              data-field="sizeType"
              :caption="$t('app.size-definition.size-type')"
              :editor-options="{disabled:true}"
              :calculate-display-value="calculateSizeTypeValue"
          >
          </DxColumn>
          <DxColumn
              data-field="code"
              data-type="string"
              :caption="$t('app.size-definition.code')"
          >

          </DxColumn>
          <DxColumn
              data-field="definition"
              data-type="string"
              :caption="$t('app.size-definition.definition')"
              :editor-options="{ disabled:true }"
          >

          </DxColumn>
          <DxColumn
              alignment="left"
              data-field="width"
              data-type="number"
              :caption="$t('app.size-definition.width')"
          >
          </DxColumn>
          <DxColumn
              alignment="left"
              data-field="length"
              data-type="number"
              :caption="$t('app.size-definition.length')"
          >
          </DxColumn>
          <DxColumn
              alignment="left"
              format="#0.00"
              data-field="sqm"
              data-type="number"
              :caption="$t('app.size-definition.sqm')"
              :editor-options="{disabled:true}"
          >
          </DxColumn>
          <DxColumn
              alignment="left"
              format="#0.00"
              data-field="inch"
              data-type="number"
              :caption="$t('app.size-definition.inch')"
              :editor-options="{disabled:true}"
          >
          </DxColumn>
        </DxDataGrid>
      </div>
    </div>
  </div>
</template>
<script>
import {DxButtonItem, DxForm, DxGroupItem, DxSimpleItem} from 'devextreme-vue/form';
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
    DxDataGrid,
    DxColumn,
    DxHeaderFilter,
    DxFilterRow,
    DxFilterPanel,
    DxScrolling,
    DxGroupPanel,
    DxButtonItem
  },
  data() {
    return {
      formData: {},
      formInstance: null,
      gridInstance: null,
      sizeDataSource: CustomStoreCreator().createStore('/scm/production/definition/stock-card/measure/'),
      saveBtnOptions: {
        type: 'default',
        useSubmitBehavior: true,
        text: i18n.global.t('common.save'),
        validationGroup: "sizeDefinitionValidationGroup",
        onClick: (e) => {
          let validationResult = e.validationGroup.validate();
          if (validationResult.isValid) {
            this.sizeDataSource.insert(this.formData).then(() => {
              this.formInstance.resetValues();
              this.gridInstance.refresh();
            })
          }
        }
      },
      caption: i18n.global.t('app.menu.stock-card.size-definitions.add')
    }
  },
  methods: {
    formInitialized: function (e) {
      this.formInstance = e.component
    },
    gridInitialized: function (e) {
      this.gridInstance = e.component
    },
    calculateSizeTypeValue: function (rowData) {
      return i18n.global.t('app.size-definition.type.' + rowData.sizeType)
    },
    onSizeDefinitionUpdating: function (data) {
      data.newData = _.extend({}, data.oldData, data.newData);
    },
    onValueChangedLength: function (e) {
      this.formData.definition = this.formData.width + "X" + this.formData.length
      this.formData.sqm = this.formData.width * e.value / 10.000
      this.formData.inch = this.formData.sqm / 2.54
      if (this.formData.length > 1500) {
        this.formData.sizeType = 'roll'
      } else {
        this.formData.sizeType = 'standard'
      }
    },
  }
}
</script>
