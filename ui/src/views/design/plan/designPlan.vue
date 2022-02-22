<template>
  <div>
    <h2 class="content-block">{{ $t('app.menu.design.plan.text') }}</h2>
    <div class="content-block">
      <div class="dx-card responsive-paddings">
        <DxForm
            :form-data="formData"
            :read-only="false"
            :show-colon-after-label="true"
            :show-validation-summary="true"
            validation-group="designPlanValidationGroup"
            @initialized="formInitialized"
        >
          <DxGroupItem :col-count="2" :caption="caption">
            <DxGroupItem :col-span="1" :col-count="1">
              <DxSimpleItem data-field="title" :label="{text: $t('app.design.plan.title')}">
                <DxRequiredRule :message="$t('validation.design.plan.required-title')"></DxRequiredRule>
              </DxSimpleItem>
              <DxSimpleItem data-field="deadLine" :label="{text: $t('app.design.plan.deadLine')}"
                            editor-type="dxDateBox"
                            :editor-options="{pickerType:'rollers' ,min:minDateValue ,dateSerializationFormat:'yyyy-MM-dd'}">
                <DxRequiredRule :message="$t('validation.design.plan.required-deadLine')"></DxRequiredRule>
              </DxSimpleItem>
              <DxSimpleItem data-field="owner" :label="{text: $t('app.design.plan.owner')}" editor-type="dxSelectBox"
                            :editor-options="owner">
                <DxRequiredRule :message="$t('validation.design.plan.required-owner')"></DxRequiredRule>
              </DxSimpleItem>
            </DxGroupItem>
            <DxGroupItem :col-span="2" :col-count="1">
              <DxSimpleItem data-field="task.text" :label="{visible:false}">
                <template #default>
                  <DxFileUploader
                      :multiple="true"
                      :select-button-text="$t('app.design.plan.picture')"
                      upload-mode="useForm"
                      @value-changed="handleFilesUpload"
                      ref="refFileUpload"
                      accept="image/bmp"
                      :invalid-file-extension-message="$t('common.bmp-file')"

                  />
                </template>
              </DxSimpleItem>
            </DxGroupItem>
          </DxGroupItem>
          <DxGroupItem :col-count="1">
            <DxGroupItem :col-span="1" :col-count="1">
              <DxSimpleItem data-field="task.text" :label="{text: $t('app.design.plan.task.title')}"
                            :editor-options="{ height:'120px' }" editor-type="dxTextArea">
              </DxSimpleItem>
            </DxGroupItem>
          </DxGroupItem>
          <DxGroupItem :col-count="4">
            <DxGroupItem :col-count="2" :col-span="1">
              <DxButtonItem
                  :button-options="addBtnOptions"
              />
            </DxGroupItem>
          </DxGroupItem>
        </DxForm>
      </div>
    </div>
    <div class="content-block">
      <div class="dx-card responsive-paddings">
        <DxDataGrid
            :data-source="designPlanDataSource"
            :columns-auto-width="true"
            :show-borders="true"
            @initialized="gridInitialized"
        >
          <DxFilterRow :visible="true"/>
          <DxFilterPanel :visible="true"/>
          <DxHeaderFilter :visible="true"/>
          <DxScrolling mode="infinite"/>
          <DxGroupPanel :visible="true"/>
          <DxMasterDetail
              :enabled="true"
              template="propertyLine"
          />
          <template #propertyLine="{ data: employee }">
            <propertyLine
                :template-data="employee"
            />
          </template>
          <DxColumn
              data-type="string"
              data-field="title"
              :caption="$t('app.design.plan.title')">
          </DxColumn>
          <DxColumn
              data-field="deadLine"
              data-type="string"
              cell-template="deadLineTemp"
              :caption="$t('app.design.plan.deadLine')"/>
          <template #deadLineTemp="{ data: options }">
            <dateTime
                :cell-data="options.data"
            />
          </template>
          <DxColumn
              type="buttons"
              :width="110"
          >
            <DxButton
                hint="Kapat"
                icon="close"
                text="Kapat"
                :visible="isCloseButtonVisible"
                @click="closeDesignPlan"
            />
          </DxColumn>
        </DxDataGrid>
      </div>
    </div>
  </div>
</template>
<script>
import {DxButtonItem, DxForm, DxGroupItem, DxSimpleItem} from 'devextreme-vue/form';
import restapi from "@/app/common/utils/restapi";
import {DxFileUploader} from 'devextreme-vue/file-uploader';

import kazu from "@/app/common/utils/kazu";
import {
  DxColumn,
  DxDataGrid,
  DxFilterPanel,
  DxFilterRow,
  DxGroupPanel,
  DxHeaderFilter,
  DxMasterDetail,
  DxRequiredRule,
  DxScrolling,
  DxButton
} from 'devextreme-vue/data-grid';
import {confirm} from "devextreme/ui/dialog";
import i18n from "@/app/common/utils/i18n";
import dateTime from "@/app/common/layout/components/datetime";
import propertyLine from "@/views/design/plan/line";
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
    DxFileUploader,
    dateTime,
    DxMasterDetail,
    propertyLine,
    DxButton
  },
  data() {
    return {
      formInstance: null,
      gridInstance: null,
      minDateValue: new Date(),
      files: '',
      owner: {
        dataSource: kazu.CustomStoreService('/user/account/'),
        displayExpr: (e) => {
          if (e) {
            return e.name + ' ' + e.surname;
          } else {
            return null
          }
        },
        valueExpr: (e) => {
          if (e) {
            return {'id': e.id};
          } else {
            return null
          }
        },
      },
      formData: {},
      designPlanDataSource: CustomStoreCreator().createStore('/design/plan/'),
      addBtnOptions: {
        text: i18n.global.t('common.save'),
        type: 'default',
        useSubmitBehavior: true,
        validationGroup: "designPlanValidationGroup",
        onClick: (e) => {
          let validationResult = e.validationGroup.validate();
          if (validationResult.isValid) {
            let formData = new FormData();
            for (let i in this.files) {
              let file = this.files[i];
              formData.append('files', file);
            }
            formData.append('designPlanDto', JSON.stringify(this.formData))

            let url = '/design/plan/'
            restapi({
              headers: {
                'mime-type': 'multipart/form-data',
                'Content-Type': false
              },
              method: 'POST',
              url,
              data: formData
            }).then(() => {
              this.formInstance.resetValues();
              this.gridInstance.refresh();
              this.$refs.refFileUpload.instance.reset()
            })
          }
        }
      },
      caption: i18n.global.t('app.menu.design.plan.add'),
    }
  },
  methods: {
    handleFilesUpload(e) {
      this.files = e.value;
    },
    formInitialized: function (e) {
      this.formInstance = e.component
    },
    gridInitialized: function (e) {
      this.gridInstance = e.component
    },
    isCloseButtonVisible: function (e) {
      return e.row.data.active;
    },
    closeDesignPlan: function (e){
      let result = confirm(this.$t('common.close-confirm'), this.$t('common.confirm'));
      result.then((dialogResult) => {
        if (dialogResult){
          let url = '/design/plan/' + e.row.key
          return restapi({
            method: 'PUT',
            url
          }).then(() => {
          })
        }
      });
    }
  }
}
</script>
