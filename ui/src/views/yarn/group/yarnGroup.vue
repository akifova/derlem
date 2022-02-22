<template>
  <div>
    <h2 class="content-block">{{$t('app.menu.yarn.group.text')}}</h2>
    <div class="content-block">
      <div class="dx-card responsive-paddings">
          <DxForm
              :form-data="formData"
              :read-only="false"
              :show-colon-after-label="true"
              :show-validation-summary="true"
              validation-group="yarnGroupValidationGroup"
              @initialized="formInitialized"
          >
            <DxGroupItem :col-count="3" :caption="caption">
              <DxGroupItem :col-span="3" :col-count="3">
                <DxSimpleItem data-field="name" :label="{text: $t('app.yarn.group.name')}">
                  <DxRequiredRule :message="$t('validation.yarn.group.required-name')"></DxRequiredRule>
                </DxSimpleItem>
                <DxSimpleItem data-field="yarnType" :label="{text: $t('app.yarn.group.yarn-type')}" :editor-options="yarnType" editor-type="dxSelectBox">
                  <DxRequiredRule :message="$t('validation.yarn.group.required-yarn-type')"></DxRequiredRule>
                </DxSimpleItem>
                <DxSimpleItem data-field="properties" :label="{text: $t('app.yarn.group.property')}" editor-type="dxTagBox" :editor-options="yarnProperties">
                  <DxRequiredRule :message="$t('validation.yarn.group.required-property')"></DxRequiredRule>
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
            :data-source="yarnGroupDataSource"
            :columns-auto-width="true"
            :show-borders="true"
            @row-updating="onYarnGroupUpdating"
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
              template="yarnGroupLine"
          />
          <template #yarnGroupLine="{ data: yarnGroupLine }">
            <yarnGroupLine
                :template-data="yarnGroupLine"
            />
          </template>
          <DxColumn
              data-type="string"
              data-field="name"
              :caption="$t('app.yarn.group.name')">
          </DxColumn>
          <DxColumn
              data-field="yarnType"
              :caption="$t('app.yarn.group.yarn-type')"
              :calculate-display-value= "displayYarnType"
              edit-cell-template="yarnTypeTemplate"
          />
          <template #yarnTypeTemplate="{ data: cellInfo }">
            <KzSelectBoxFieldEditor
                :value="cellInfo.value"
                :on-value-changed="(value) => onValueChanged(value, cellInfo)"
                data-source-url="/yarn/type/"
                :display-fields="['name']"
                :data-grid-component="cellInfo.component"
            />
          </template>
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
  DxMasterDetail,
  DxRequiredRule,
  DxScrolling
} from 'devextreme-vue/data-grid';
import {DxTagBox} from 'devextreme-vue/tag-box';
import yarnGroupLine from "./yarnGroupLine"
import i18n from "@/app/common/utils/i18n";
import _ from "lodash";
import {YarnPropertyService} from "@/views/yarn/property/business/YarnPropertyService";
import {YarnTypeService} from "@/views/yarn/type/business/YarnTypeService";
import {CustomStoreCreator} from "@/app/common/utils/CustomStoreCreator";
import KzSelectBoxFieldEditor from "@/app/components/dx/dataGrid/KzSelectBoxFieldEditor";

export default {
  components: {
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
    // eslint-disable-next-line vue/no-unused-components
    DxTagBox,
    yarnGroupLine,
    KzSelectBoxFieldEditor
  },
  data() {
    return {
      formData: {properties:[]},
      yarnGroupDataSource: CustomStoreCreator().createStore('/yarn/group/'),
      formInstance: null,
      gridInstance: null,
      saveBtnOptions: {
        type: 'default',
        useSubmitBehavior: true,
        text: i18n.global.t('common.save'),
        validationGroup: "yarnGroupValidationGroup",
        onClick: (e) => {
          let validationResult = e.validationGroup.validate();
          if (validationResult.isValid) {
            let tempData = {
              "name" : this.formData.name,
              "yarnType":{
                "id":this.formData.yarnType.id
              },
              properties:[]
            }
            for (let property of this.formData.properties){
              tempData.properties.push({'yarnProperty':property})
            }
            this.yarnGroupDataSource.insert(tempData).then(() => {
              this.formInstance.resetValues();
              this.gridInstance.refresh();
            })
          }
        }
      },
      yarnProperties: YarnPropertyService().getSelectBoxParameters(),
      yarnType: YarnTypeService().getSelectBoxParameters(),
      yarnTypeDataSource: CustomStoreCreator().createStore('/yarn/type/'),
      caption: i18n.global.t('app.menu.yarn.group.add'),
    }
  },
  methods: {
    formInitialized: function (e) {
      this.formInstance = e.component
    },
    gridInitialized: function (e) {
      this.gridInstance = e.component
    },
    onYarnGroupUpdating: function (data) {
      data.newData = _.extend({}, data.oldData, data.newData);
    },
    displayYarnType(data) {
      return data.yarnType.name;
    },
    onValueChanged(value, cellInfo) {
      cellInfo.setValue(value);
      cellInfo.component.updateDimensions();
    }
  }
}
</script>
