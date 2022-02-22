<template>
  <div>
    <h2 class="content-block">{{ $t('app.menu.yarn.definition.text') }}</h2>
    <div class="content-block">
      <div class="dx-card responsive-paddings">
          <DxForm
              :form-data="formData"
              :read-only="false"
              :show-colon-after-label="true"
              :show-validation-summary="true"
              validation-group="yarnValidationGroup"
              @initialized="formInitialized"
          >
            <DxGroupItem :col-count="4" :caption="caption">
              <DxSimpleItem :col-span="1" data-field="name" :label="{text: $t('app.yarn.definition.name')}">
                <DxRequiredRule :message="$t('validation.yarn.required-name')"></DxRequiredRule>
              </DxSimpleItem>
              <DxSimpleItem :col-span="1" data-field="yarnGroup" :label="{text: $t('app.yarn.definition.yarnGroup')}"
                            :editor-options="yarnGroup" editor-type="dxSelectBox">
                <DxRequiredRule :message="$t('validation.yarn.required-group')"></DxRequiredRule>
              </DxSimpleItem>
              <DxSimpleItem :col-span="1" data-field="weight" :label="{text: $t('app.yarn.definition.weight')}">
                <DxRequiredRule :message="$t('validation.yarn.required-weight')"></DxRequiredRule>
              </DxSimpleItem>
              <DxSimpleItem :col-span="1" data-field="weightUnit" :label="{text: $t('app.yarn.definition.weightUnit')}"
                            :editor-options="unitType" editor-type="dxSelectBox">
                <DxRequiredRule :message="$t('validation.yarn.required-weightUnit')"></DxRequiredRule>
              </DxSimpleItem>

            </DxGroupItem>
            <DxGroupItem :col-count="4">
              <DxSimpleItem :col-span="1" data-field="unitOfMeasure"
                            :label="{text: $t('app.yarn.definition.unitOfMeasure')}"
                            :editor-options="unitType" editor-type="dxSelectBox">
                <DxRequiredRule :message="$t('validation.yarn.required-unitOfMeasure')"></DxRequiredRule>
              </DxSimpleItem>
              <DxSimpleItem :col-span="1" data-field="minStock" :label="{text: $t('app.yarn.definition.minStock')}"
                            editor-type="dxNumberBox"
                            :editor-options="{ min:0 }">
                <DxRequiredRule :message="$t('validation.yarn.required-minStock')"></DxRequiredRule>
              </DxSimpleItem>
              <DxSimpleItem :col-span="1" data-field="usageType" :label="{text: $t('app.yarn.definition.usageType')}"
                            :editor-options="usageType" editor-type="dxSelectBox">
                <DxRequiredRule :message="$t('validation.yarn.required-usageType')"></DxRequiredRule>
              </DxSimpleItem>
              <DxSimpleItem :col-span="1" data-field="mark" :label="{text: $t('app.yarn.definition.mark')}"
                            editor-type="dxTagBox"
                            :editor-options="{ acceptCustomValue:true }">
              </DxSimpleItem>
            </DxGroupItem>
            <DxGroupItem :col-count="4">
              <DxSimpleItem :col-span="1" data-field="actionType" :label="{text: $t('app.yarn.definition.actionType')}"
                            :editor-options="actionType" editor-type="dxSelectBox">
                <DxRequiredRule :message="$t('validation.yarn.required-actionType')"></DxRequiredRule>
              </DxSimpleItem>
              <DxSimpleItem :col-span="1" data-field="salable" :label="{text: $t('app.yarn.definition.salable')}"
                            :editor-options="{ onValueChanged:salableChangedValue }"
                            editor-type="dxCheckBox"/>
              <DxSimpleItem :col-span="1" data-field="needQcApprove"
                            :label="{text: $t('app.yarn.definition.needQcApprove')}"
                            :editor-options="{ onValueChanged:needQcApproveChangedValue }"
                            editor-type="dxCheckBox"/>
              <DxSimpleItem :col-span="1" data-field="surveillance"
                            :label="{text: $t('app.yarn.definition.surveillance')}"
                            :editor-options="{ onValueChanged:surveillanceChangedValue }"
                            editor-type="dxCheckBox"/>
            </DxGroupItem>
            <DxGroupItem
                item-type="group"
                name="properties"
                :col-count="4"
            >
              <DxSimpleItem :data-field="'yarnToPropertyLines[' + index + '].yarnPropertyLine'"
                            v-for="(option , index) in yarnPropertyLineOptions"
                            :key="'ytpl' + (index + 1)"
                            :label="{visible:true, text: option.name}"
                            :editor-options="option" editor-type="dxSelectBox"
              >
              </DxSimpleItem>
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
  </div>
</template>
<script>
import {DxButtonItem, DxForm, DxGroupItem, DxRequiredRule, DxSimpleItem} from 'devextreme-vue/form';
import restapi from "@/app/common/utils/restapi";
import CustomStore from "devextreme/data/custom_store";
import i18n from "@/app/common/utils/i18n";
import {DxTagBox} from 'devextreme-vue/tag-box';
import {DxLookup} from 'devextreme-vue/lookup';
import {YarnDao} from "@/views/yarn/persistence/YarnDao";
import {YarnService} from "@/views/yarn/business/YarnService";
import {CustomStoreCreator} from "@/app/common/utils/CustomStoreCreator";

const formData = {yarnToPropertyLines: [], salable: 0, needQcApprove: 0, surveillance: 0};
let yarnToPropertyLines = formData.yarnToPropertyLines;
export default {
  components: {
    DxForm,
    DxSimpleItem,
    DxGroupItem,
    DxButtonItem,
    DxRequiredRule,
    // eslint-disable-next-line vue/no-unused-components
    DxTagBox,
    // eslint-disable-next-line vue/no-unused-components
    DxLookup
  },
  data() {
    let yarnPropertyLineOptions = this.createYtpOptions();
    return {
      formInstance: null,
      yarnPropertyLineOptions,
      yarnToPropertyLines,
      selectedIndex: 0,
      formData,
      actionType: YarnService().getActionType(),
      usageType: YarnService().getUsageType(),
      yarnGroup: {
        dataSource: CustomStoreCreator().createStore('/yarn/group/'),
        displayExpr:(data) => {
          if (!data){
            if (this.formData?.yarnGroup){
              return this.formData.yarnGroup.name
            } else {
              return ''
            }
          } else {
            return data.name;
          }
        },
        onSelectionChanged: (e) => {
          this.createYtpOptions(e.selectedItem.id);
        }
      },
      unitType: {
        dataSource: CustomStoreCreator().createStore('/common/uom/'),
        displayExpr: 'shortName',
        valueExpr: (e) => {
          if (e) {
            return {'id': e.id};
          } else {
            return null
          }
        },
      },
      saveBtnOptions: {
        type: 'default',
        useSubmitBehavior: false,
        text: i18n.global.t('common.save'),
        validationGroup: "yarnValidationGroup",
        onClick: (e) => {
          let validationResult = e.validationGroup.validate();
          if (validationResult.isValid) {
            if (this.formData.mark){
              const markValue = this.formData.mark.join();
              this.formData.mark = markValue
            }

            YarnDao().yarnDataSource().insert(this.formData).then(() => {
              this.formInstance.resetValues();
              this.yarnPropertyLineOptions = null
            })
          }
        }
      },
      caption: i18n.global.t('app.menu.yarn.definition.add'),
    }
  },
  methods: {
    formInitialized: function (e) {
      this.formInstance = e.component
    },
    createYtpOptions(groupId) {
      let url = '/yarn/group-to-property/get-by-group-id/' + groupId
      let options = [];
      restapi({
        method: 'GET',
        url,
      }).then(t => {
        this.formData.yarnToPropertyLines = [];
        for (let val of t.data) {
          this.formData.yarnToPropertyLines.push({
            yarnPropertyLine: {}
          })
          options.push({
            displayExpr: 'attribute',
            name: val.yarnProperty.name,
            dataSource: new CustomStore({
              key: 'id',
              load: () => {
                let url = '/yarn/property-line/get-by-property-id/' + val.yarnProperty.id
                return restapi({
                  method: 'GET',
                  url,
                })
              },
              byKey: function (key) {
                return key
              },
            })
          })
        }
        this.yarnPropertyLineOptions =  options;
      })
    },
    salableChangedValue(e) {
      if (e.value) {
        this.formData.salable = 1
      } else {
        this.formData.salable = 0
      }
    },
    needQcApproveChangedValue(e) {
      if (e.value) {
        this.formData.needQcApprove = 1
      } else {
        this.formData.needQcApprove = 0
      }
    },
    surveillanceChangedValue(e) {
      if (e.value) {
        this.formData.surveillance = 1
      } else {
        this.formData.surveillance = 0
      }
    }
  }
}
</script>
