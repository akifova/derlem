<template>
  <div>
    <h2 class="content-block">{{$t('app.menu.yarn.property.text')}}</h2>
    <div class="content-block">
      <div class="dx-card responsive-paddings">
          <DxForm
              :form-data="formData"
              :read-only="false"
              :show-colon-after-label="true"
              :show-validation-summary="true"
              validation-group="yarnPropertyValidationGroup"
              @initialized="formInitialized"
          >
            <DxGroupItem :col-count="3" :caption="caption">
              <DxGroupItem :col-span="3" :col-count="3">
                <DxSimpleItem data-field="name" :label="{text: $t('app.yarn.property.name')}">
                  <DxRequiredRule :message="$t('validation.yarn.yarn-property-type.required-name')"></DxRequiredRule>
                </DxSimpleItem>
                <DxSimpleItem data-field="yarnPropertyType" :label="{text: $t('app.yarn.property.yarn-property-type.text')}" :editor-options="propertyType" editor-type="dxSelectBox">
                  <DxRequiredRule :message="$t('validation.yarn.yarn-property-type.required-yarnpropertytype')"></DxRequiredRule>
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
            :data-source="yarnPropertyDataSource"
            :columns-auto-width="true"
            :show-borders="true"
            @row-updating="onYarnPropertyUpdating"
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
          <DxMasterDetail
              :enabled="true"
              template="yarnPropertyLine"
          />
          <template #yarnPropertyLine="{ data: yarnPropertyLine }">
            <yarnPropertyLine
                :template-data="yarnPropertyLine"
            />
          </template>
          <DxColumn
              data-type="string"
              data-field="name"
              :caption="$t('app.yarn.property.name')"
          >
          </DxColumn>
          <DxColumn
              data-type="string"
              data-field="yarnPropertyType"
              :caption="$t('app.yarn.property.yarn-property-type.text')"
              :calculate-display-value="calculatePropertyValue"
          >
            <DxLookup
                :data-source="propertyTypeDataSource"
                value-expr="id"
                display-expr="name"
            />
          </DxColumn>
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
  DxLookup,
  DxMasterDetail,
  DxRequiredRule,
  DxScrolling
} from 'devextreme-vue/data-grid';
import yarnPropertyLine from "./yarnPropertyLine"
import i18n from "@/app/common/utils/i18n";
import _ from "lodash";
import {YarnPropertyService} from "@/views/yarn/property/business/YarnPropertyService";
import {CustomStoreCreator} from "@/app/common/utils/CustomStoreCreator";

export default {
  components: {
    DxLookup,
    DxEditing,
    DxMasterDetail,
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
    yarnPropertyLine
  },
  data() {
    return {
      formData: {},
      formInstance: null,
      gridInstance: null,
      propertyTypeDataSource: YarnPropertyService().getPropertyTypeDataSource(),
      propertyType: YarnPropertyService().getSelectBoxPropertyParameters(),
      yarnPropertyDataSource: CustomStoreCreator().createStore('/yarn/property/'),
      saveBtnOptions: {
        type: 'default',
        useSubmitBehavior: true,
        text: i18n.global.t('common.save'),
        validationGroup: "yarnPropertyValidationGroup",
        onClick: (e) => {
          let validationResult = e.validationGroup.validate();
          if (validationResult.isValid) {
            this.yarnPropertyDataSource.insert(this.formData).then(() => {
              this.formInstance.resetValues();
              this.gridInstance.refresh();
            })
          }
        }
      },
      caption: i18n.global.t('app.menu.yarn.property.add'),
    }
  },
  methods: {
    formInitialized: function (e) {
      this.formInstance = e.component
    },
    gridInitialized: function (e) {
      this.gridInstance = e.component;
    },
    calculatePropertyValue(val){
      return i18n.global.t('app.yarn.property.yarn-property-type.' + val.yarnPropertyType)
    },
    onYarnPropertyUpdating: function (data) {
      data.newData = _.extend({}, data.oldData, data.newData);
    },
  }
}
</script>
