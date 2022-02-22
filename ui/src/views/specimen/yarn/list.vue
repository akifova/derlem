<template>
  <div>
    <h2 class="content-block">{{$t('app.menu.specimen.yarn.text')}}</h2>
    <div class="content-block">
      <div class="dx-card responsive-paddings">
          <DxForm
              :form-data="formData"
              :read-only="false"
              :show-colon-after-label="true"
              :show-validation-summary="true"
              validation-group="specimenYarnValidationGroup"
              @initialized="formInitialized"
          >
            <DxGroupItem :col-count="5" :caption="caption">
              <DxGroupItem :col-span="5" :col-count="5">
                <DxSimpleItem data-field="code" :label="{text: $t('app.specimen.yarn.code')}">
                  <DxRequiredRule :message="$t('validation.specimen.yarn.required-code')"></DxRequiredRule>
                </DxSimpleItem>
                <DxSimpleItem data-field="yarnNumber" :label="{text: $t('app.specimen.yarn.yarnNumber')}">
                  <DxRequiredRule :message="$t('validation.specimen.yarn.required-yarnNumber')"></DxRequiredRule>
                </DxSimpleItem>
                <DxSimpleItem data-field="manufacturerCode" :label="{text: $t('app.specimen.yarn.manufacturerCode')}">
                  <DxRequiredRule :message="$t('validation.specimen.yarn.required-manufacturerCode')"></DxRequiredRule>
                </DxSimpleItem>
                <DxSimpleItem data-field="supplier" :label="{text: $t('app.specimen.yarn.supplier')}" editor-type="dxSelectBox"
                              :editor-options="supplier">
                  <DxRequiredRule :message="$t('validation.specimen.yarn.required-supplier')"></DxRequiredRule>
                </DxSimpleItem>
                <DxSimpleItem data-field="colorCode" :label="{text: $t('app.specimen.yarn.colorCode')}" editor-type="dxColorBox"
                              :editor-options="{ editAlphaChannel:true }">
                  <DxRequiredRule :message="$t('validation.specimen.yarn.required-colorCode')"></DxRequiredRule>
                </DxSimpleItem>
              </DxGroupItem>
              <DxGroupItem :col-span="5" :col-count="5">
                <DxSimpleItem data-field="colorName" :label="{text: $t('app.specimen.yarn.colorName')}">
                  <DxRequiredRule :message="$t('validation.specimen.yarn.required-colorName')"></DxRequiredRule>
                </DxSimpleItem>
                <DxSimpleItem data-field="spin" :label="{text: $t('app.specimen.yarn.spin')}" editor-type="dxNumberBox">
                  <DxRequiredRule :message="$t('validation.specimen.yarn.required-spin')"></DxRequiredRule>
                </DxSimpleItem>
                <DxSimpleItem data-field="cipsPercentage" :label="{text: $t('app.specimen.yarn.cipsPercentage')}" editor-type="dxNumberBox">
                  <DxRequiredRule :message="$t('validation.specimen.yarn.required-cipsPercentage')"></DxRequiredRule>
                </DxSimpleItem>
                <DxSimpleItem data-field="uvType" :label="{text: $t('app.specimen.yarn.uvType')}" editor-type="dxSelectBox"
                              :editor-options="{displayExpr: 'name', valueExpr: 'id', dataSource:uvType}">
                  <DxRequiredRule :message="$t('validation.specimen.yarn.required-uvType')"></DxRequiredRule>
                </DxSimpleItem>
                <DxSimpleItem data-field="weightUnit" :label="{text: $t('app.specimen.yarn.weightUnit')}" editor-type="dxSelectBox"
                              :editor-options="weightUnit">
                  <DxRequiredRule :message="$t('validation.specimen.yarn.required-weightUnit')"></DxRequiredRule>
                </DxSimpleItem>
              </DxGroupItem>
              <DxGroupItem :col-span="5" :col-count="5">
                <DxSimpleItem data-field="weight" :label="{text: $t('app.specimen.yarn.weight')}">
                  <DxRequiredRule :message="$t('validation.specimen.yarn.required-weight')"></DxRequiredRule>
                </DxSimpleItem>
                <DxSimpleItem data-field="fixedType" :label="{text: $t('app.specimen.yarn.fixedType')}">
                  <DxRequiredRule :message="$t('validation.specimen.yarn.required-fixedType')"></DxRequiredRule>
                </DxSimpleItem>
                <DxSimpleItem data-field="fiberType" :label="{text: $t('app.specimen.yarn.fiberType')}">
                  <DxRequiredRule :message="$t('validation.specimen.yarn.required-fiberType')"></DxRequiredRule>
                </DxSimpleItem>
                <DxSimpleItem data-field="fiberFineness" :label="{text: $t('app.specimen.yarn.fiberFineness')}">
                  <DxRequiredRule :message="$t('validation.specimen.yarn.required-fiberFineness')"></DxRequiredRule>
                </DxSimpleItem>
                <DxSimpleItem data-field="filamentQuantity" :label="{text: $t('app.specimen.yarn.filamentQuantity')}" editor-type="dxNumberBox">
                  <DxRequiredRule :message="$t('validation.specimen.yarn.required-filamentQuantity')"></DxRequiredRule>
                </DxSimpleItem>
                <DxSimpleItem data-field="yarnGroup" :label="{text: $t('app.specimen.yarn.yarnGroup')}" editor-type="dxSelectBox" :editor-options="yarn">
                  <DxRequiredRule :message="$t('validation.specimen.yarn.required-yarnGroup')"></DxRequiredRule>
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
            :data-source="specimenYarnDataSource"
            :columns-auto-width="true"
            :show-borders="true"
            @row-updating="onSpecimenYarnUpdating"
            @initialized="gridInitialized"
        >
          <DxEditing
            :allow-updating="true"
            :allow-deleting="true"
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
              :caption="$t('app.specimen.yarn.code')"
          />
          <DxColumn
              data-field="yarnNumber"
              data-type="string"
              :caption="$t('app.specimen.yarn.yarnNumber')"
          />
          <DxColumn
              data-field="yarnGroup"
              data-type="string"
              :caption="$t('app.specimen.yarn.yarnGroup')"
              :calculate-display-value="calculateYarnGroupValue"
              edit-cell-template="yarnGroupLookupTemplate"
          >
          </DxColumn>
          <DxColumn
              :visible="false"
              data-field="manufacturerCode"
              data-type="string"
              :caption="$t('app.specimen.yarn.manufacturerCode')"
          />
          <DxColumn
              :visible="false"
              data-field="supplier"
              data-type="string"
              :caption="$t('app.specimen.yarn.supplier')"
              edit-cell-template="supplierSelectBoxTemplate"
          />
          <DxColumn
              :visible="false"
              data-field="colorCode"
              data-type="string"
              :caption="$t('app.specimen.yarn.colorCode')"
              edit-cell-template="colorCodeTemplate"
          />
          <DxColumn
              data-field="colorName"
              data-type="string"
              :caption="$t('app.specimen.yarn.colorName')"
          />
          <DxColumn
              data-field="spin"
              data-type="string"
              :caption="$t('app.specimen.yarn.spin')"
          />
          <DxColumn
              data-field="cipsPercentage"
              data-type="string"
              :caption="$t('app.specimen.yarn.cipsPercentage')"
          />
          <DxColumn
              :visible="false"
              data-field="uvType"
              data-type="string"
              :caption="$t('app.specimen.yarn.uvType')"
          >
            <DxLookup
                :data-source="uvType"
                display-expr="name"
                value-expr="id"
            />
          </DxColumn>
          <DxColumn
              :visible="false"
              data-field="weightUnit"
              data-type="string"
              :caption="$t('app.specimen.yarn.weightUnit')"
              :calculate-display-value="calculateWeightUnitValue"
              edit-cell-template="weightUnitSelectBoxTemplate"
          />
          <DxColumn
              data-field="fixedType"
              data-type="string"
              :caption="$t('app.specimen.yarn.fixedType')"
          />
          <DxColumn
              data-field="fiberType"
              data-type="string"
              :caption="$t('app.specimen.yarn.fiberType')"
          />
          <DxColumn
              data-field="fiberFineness"
              data-type="string"
              :caption="$t('app.specimen.yarn.fiberFineness')"
          />
          <DxColumn
              data-field="filamentQuantity"
              data-type="string"
              :caption="$t('app.specimen.yarn.filamentQuantity')"
          />
          <template #yarnGroupLookupTemplate="{ data: cellInfo }">
            <KzLookupFieldEditor
                :value="cellInfo.value"
                :on-value-changed="(value) => onValueChanged(value, cellInfo)"
                data-source-url="/yarn/group/search"
                :search-fields="['yarnType.name','name']"
                :data-grid-component="cellInfo.component"
            />
          </template>
          <template #weightUnitSelectBoxTemplate="{ data: cellInfo }">
            <KzSelectBoxFieldEditor
                :value="cellInfo.value"
                :on-value-changed="(value) => onValueChanged(value, cellInfo)"
                data-source-url="/common/uom/"
                :display-fields="['shortName']"
                :data-grid-component="cellInfo.component"
            />
          </template>
          <template #supplierSelectBoxTemplate="{ data: cellInfo }">
            <KzSelectBoxFieldEditor
                :value="cellInfo.value"
                :on-value-changed="(value) => onValueChanged(value, cellInfo)"
                data-source-url="/common/firm/"
                :display-fields="['name']"
                :data-grid-component="cellInfo.component"
            />
          </template>
          <template #colorCodeTemplate="{ data: cellInfo }">
            <DxColorBox
                :edit-alpha-channel="true"
                :value="cellInfo.value"
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
  DxFilterPanel,
  DxFilterRow,
  DxGroupPanel,
  DxHeaderFilter,
  DxRequiredRule,
  DxScrolling,
  DxEditing,
  DxLookup
} from 'devextreme-vue/data-grid';
import DxColorBox from 'devextreme-vue/color-box';
import i18n from "@/app/common/utils/i18n";
import _ from "lodash";
import {CustomStoreCreator} from "@/app/common/utils/CustomStoreCreator";
import KzLookupFieldEditor from "@/app/components/dx/dataGrid/KzLookupFieldEditor";
import KzSelectBoxFieldEditor from "@/app/components/dx/dataGrid/KzSelectBoxFieldEditor";

const dataGridRefName = "dataGridRefName";
export default {
  components: {
    DxLookup,
    KzSelectBoxFieldEditor,
    KzLookupFieldEditor,
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
    // eslint-disable-next-line vue/no-unused-components
    DxColorBox,
  },
  data() {
    return {
      specimenYarnDataSource:CustomStoreCreator().createStore('/specimen/sample-yarn/'),
      supplier: {
        dataSource: CustomStoreCreator().createStore('/common/firm/'),
        displayExpr: (e) => {
          if (e) {
            return e.name;
          } else {
            return null
          }
        },
        valueExpr: (e) => {
          if (e) {
            return {'id': e.id,'name':e.name};
          } else {
            return null
          }
        },
      },
      weightUnit: {
        dataSource: CustomStoreCreator().createStore('/common/uom/'),
        displayExpr: (e) => {
          if (e) {
            return e.shortName;
          } else {
            return null
          }
        },
        valueExpr: (e) => {
          if (e) {
            return {'id': e.id,'unit':e.unit,'shortName':e.shortName};
          } else {
            return null
          }
        },
      },
      yarnGroupDataSource: CustomStoreCreator().createStore('/yarn/group/'),
      yarn: {
        dataSource: CustomStoreCreator().createStore('/yarn/group/'),
        displayExpr: (e) => {
          if (e) {
            return e.name;
          } else {
            return null
          }
        },
        valueExpr: (e) => {
          if (e) {
            return {'id': e.id,'name':e.name};
          } else {
            return null
          }
        },
      },
      uvType:[
          {id:'yes',  name:'Var'},
          {id:'no',  name:'Yok'},
          {id:'melting',   name:'Eriyen'},
      ],
      formData: {},
      saveBtnOptions: {
        type: 'default',
        text: i18n.global.t('common.save'),
        useSubmitBehavior: true,
        validationGroup: "specimenYarnValidationGroup",
        onClick: (e) => {
          let validationResult = e.validationGroup.validate();
          if (validationResult.isValid) {
            this.specimenYarnDataSource.insert(this.formData).then(() => {
              this.formInstance.resetValues();
              this.gridInstance.refresh(true);
            })
          }
        }
      },
      caption: i18n.global.t('app.menu.specimen.yarn.add'),
    }
  },
  computed: {
    dataGrid: function () {
      return this.$refs[dataGridRefName].instance;
    }
  },
  methods: {
    calculateSupplierValue(rowData){
      return rowData.supplier?.name;
    },
    calculateWeightUnitValue(rowData){
      return rowData.weightUnit?.unit;
    },
    calculateYarnGroupValue(rowData) {
      return rowData.yarnGroup?.name;
    },
    onValueChanged(value, cellInfo) {
      cellInfo.setValue(value);
      cellInfo.component.updateDimensions();
    },
    formInitialized: function (e) {
      this.formInstance = e.component
    },
    gridInitialized: function (e) {
      this.gridInstance = e.component
    },
    onSpecimenYarnUpdating: function (data) {
      data.newData = _.extend({}, data.oldData, data.newData);
    },
  }
}
</script>
