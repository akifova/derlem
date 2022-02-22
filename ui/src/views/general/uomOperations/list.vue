<template>
  <div>
    <h2 class="content-block">{{$t('app.menu.general.uom.text')}}</h2>
    <div class="content-block">
      <div class="dx-card responsive-paddings">
          <DxForm
              :form-data="formData"
              :read-only="false"
              :show-colon-after-label="true"
              :show-validation-summary="true"
              validation-group="uomValidationGroup"
              @initialized="formInitialized"
          >
            <DxGroupItem :col-count="4" :caption="caption">
              <DxGroupItem :col-span="4" :col-count="4">
                <DxSimpleItem data-field="unit" :label="{text: $t('app.general.uom.unit')}"
                              :editor-options="{maxLength:20}">
                  <DxRequiredRule :message="$t('validation.uom.required-unit')"></DxRequiredRule>
                </DxSimpleItem>
                <DxSimpleItem data-field="shortName" :label="{text: $t('app.general.uom.shortName')}"
                              :editor-options="{maxLength:10}">
                  <DxRequiredRule :message="$t('validation.uom.required-shortName')"></DxRequiredRule>
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
            :data-source="uomDataSource"
            :columns-auto-width="true"
            :show-borders="true"
            @row-updating="onUomUpdating"
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
              data-field="unit"
              :caption="$t('app.general.uom.unit')"/>
          <DxColumn
              data-field="shortName"
              data-type="string"
              :caption="$t('app.general.uom.shortName')"
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
import {CustomStoreCreator} from "@/app/common/utils/CustomStoreCreator";
import _ from "lodash";

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
    DxEditing
  },
  data() {
    return {
      formData: {},
      formInstance: null,
      gridInstance: null,
      uomDataSource: CustomStoreCreator().createStore('/common/uom/'),
      saveBtnOptions: {
        type: 'default',
        text: i18n.global.t('common.save'),
        useSubmitBehavior: true,
        validationGroup: "uomValidationGroup",
        onClick: (e) => {
          let validationResult = e.validationGroup.validate();
          if (validationResult.isValid) {
            this.uomDataSource.insert(this.formData).then(() => {
              this.formInstance.resetValues();
              this.gridInstance.refresh(true);
            })
          }
        }
      },
      caption: i18n.global.t('app.menu.general.uom.add'),
    }
  },
  methods: {
    formInitialized: function (e) {
      this.formInstance = e.component
    },
    gridInitialized: function (e) {
      this.gridInstance = e.component
    },
    onUomUpdating: function (data) {
      data.newData = _.extend({}, data.oldData, data.newData);
    },
  }
}
</script>
