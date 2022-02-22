<template>
  <div>
    <h2 class="content-block">{{$t('app.pattern-code.field-definition')}}</h2>
    <div class="content-block">
      <div class="dx-card responsive-paddings">
          <DxForm
              :form-data="formData"
              :read-only="false"
              :show-colon-after-label="true"
              :show-validation-summary="true"
              validation-group="patternCodeValidationGroup"
              @initialized="formInitialized"
          >
            <DxGroupItem :col-count="4" :caption="caption">
              <DxGroupItem :col-span="4" :col-count="4">
                <DxSimpleItem data-field="code" data-type="string" :label="{text: $t('app.pattern-code.code')}"
                              :editor-options="{maxLength:4}">
                  <DxRequiredRule :message="$t('validation.pattern-code.required')"></DxRequiredRule>
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
            :data-source="patternCodeDataSource"
            :columns-auto-width="true"
            :show-borders="true"
            @row-updating="onPatternCodeUpdating"
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
              data-field="code"
              :caption="$t('app.pattern-code.code')"
          />
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
    DxButtonItem,
    DxDataGrid,
    DxColumn,
    DxHeaderFilter,
    DxFilterRow,
    DxFilterPanel,
    DxScrolling,
    DxGroupPanel
  },
  data() {
    return {
      formData: {},
      formInstance: null,
      gridInstance: null,
      patternCodeDataSource: CustomStoreCreator().createStore('/scm/production/definition/stock-card/design/'),
      saveBtnOptions: {
        type: 'default',
        text: i18n.global.t('common.save'),
        useSubmitBehavior: true,
        validationGroup: "patternCodeValidationGroup",
        onClick: (e) => {
          let validationResult = e.validationGroup.validate();
          if (validationResult.isValid) {
            this.patternCodeDataSource.insert(this.formData).then(() => {
              this.formInstance.resetValues();
              this.gridInstance.refresh();
            })
          }
        }
      },
      caption: i18n.global.t('app.menu.stock-card.pattern-code.add'),
    }
  },
  methods: {
    formInitialized: function (e) {
      this.formInstance = e.component
    },
    gridInitialized: function (e) {
      this.gridInstance = e.component
    },
    onPatternCodeUpdating: function (data) {
      data.newData = _.extend({}, data.oldData, data.newData);
    }
  }
}
</script>
