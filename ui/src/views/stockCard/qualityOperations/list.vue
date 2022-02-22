<template>
  <div>
    <h2 class="content-block">{{ $t('app.menu.stock-card.quality.text') }}</h2>
    <div class="content-block">
      <div class="dx-card responsive-paddings">
        <DxForm
            :form-data="formData"
            :read-only="false"
            :show-colon-after-label="true"
            :show-validation-summary="true"
            validation-group="qualityValidationGroup"
            @initialized="formInitialized"
        >
          <DxGroupItem :col-count="4" css-class="first-group" :caption="caption">
            <DxGroupItem :col-span="4" :col-count="4">
              <DxSimpleItem data-field="code" :label="{text: $t('app.stock-card.quality.code')}">
                <DxRequiredRule :message="$t('validation.stock-card.quality.required-code')"></DxRequiredRule>
                <DxStringLengthRule :min="1" :max="5" :message="$t('validation.stock-card.quality.code-string-length-rule')"/>
              </DxSimpleItem>
              <DxSimpleItem data-field="definition" :label="{text: $t('app.stock-card.quality.definition')}"
                            :editor-options="{maxLength:'100'}">
                <DxRequiredRule :message="$t('validation.stock-card.quality.required-definition')"></DxRequiredRule>
              </DxSimpleItem>
              <DxSimpleItem data-field="woof" :label="{text: $t('app.stock-card.quality.woof')}"
                            editor-type="dxNumberBox">
                <DxRequiredRule :message="$t('validation.stock-card.quality.required-woof')"></DxRequiredRule>
                <DxRangeRule :min="10" :max="200" :message="$t('validation.stock-card.quality.min-woof-range-rule')"/>
              </DxSimpleItem>
              <DxSimpleItem data-field="crest" :label="{text: $t('app.stock-card.quality.crest')}"
                            editor-type="dxNumberBox">
                <DxRequiredRule :message="$t('validation.stock-card.quality.required-crest')"></DxRequiredRule>
                <DxRangeRule :min="10" :max="200" :message="$t('validation.stock-card.quality.min-crest-range-rule')"/>
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
            @initialized="gridInitialized"
            :data-source="qualityDataSource"
            :columns-auto-width="true"
            :show-borders="true"
            @row-updating="onQualityUpdating"
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
              alignment="left"
              data-type="string"
              data-field="code"
              :caption="$t('app.stock-card.quality.quality-code')"
          >
          </DxColumn>
          <DxColumn
              alignment="left"
              data-field="definition"
              data-type="string"
              :caption="$t('app.stock-card.quality.quality-definition')"
          >
          </DxColumn>
          <DxColumn
              alignment="left"
              data-field="woof"
              data-type="number"
              :caption="$t('app.stock-card.quality.woof-number')"
          >
          </DxColumn>
          <DxColumn
              alignment="left"
              data-field="crest"
              data-type="number"
              :caption="$t('app.stock-card.quality.crest-number')"
          >
          </DxColumn>
        </DxDataGrid>
      </div>
    </div>
  </div>
</template>
<script>
import {DxButtonItem, DxForm, DxGroupItem, DxSimpleItem,DxRangeRule,DxStringLengthRule} from 'devextreme-vue/form';
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
    DxEditing,
    DxRangeRule,
    DxStringLengthRule
  },
  data() {
    return {
      formData: {},
      formInstance: null,
      gridInstance: null,
      qualityDataSource: CustomStoreCreator().createStore('/scm/production/definition/stock-card/quality/'),
      saveBtnOptions: {
        type: 'default',
        text: i18n.global.t('common.save'),
        validationGroup: "qualityValidationGroup",
        onClick: (e) => {
          let validationResult = e.validationGroup.validate();
          if (validationResult.isValid) {
            this.qualityDataSource.insert(this.formData).then(() => {
              this.formInstance.resetValues();
              this.gridInstance.refresh();
            })
          }
        }
      },
      caption: i18n.global.t('app.menu.stock-card.quality.add'),
    }
  },
  methods: {
    formInitialized: function (e) {
      this.formInstance = e.component
    },
    gridInitialized: function (e) {
      this.gridInstance = e.component
    },
    onQualityUpdating: function (data) {
      data.newData = _.extend({}, data.oldData, data.newData);
    },
  }
}
</script>
