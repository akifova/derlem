<template>
  <div>
    <h2 class="content-block">{{$t('app.menu.license.add')}}</h2>
    <div class="content-block">
      <div class="dx-card responsive-paddings">
        <form @submit="handleSubmit">
          <DxForm
              :ref="formDataRef"
              :form-data="formData"
              :read-only="false"
              :show-colon-after-label="true"
              :show-validation-summary="true"
              validation-group="customerData"
              :disabled="loading"
          >
            <DxGroupItem css-class="first-group" :caption="caption">
              <DxGroupItem :col-span="4" :col-count="4">
                <DxSimpleItem data-field="endDate" :label="{text: $t('app.menu.license.endDate')}" editor-type="dxDateBox"
                              :editor-options="{pickerType:'rollers' ,min:minDateValue ,dateSerializationFormat:'yyyy-MM-dd'}">
                  <DxRequiredRule :message="$t('validation.license.endDate')"></DxRequiredRule>
                </DxSimpleItem>
                <DxSimpleItem data-field="macAddress" :label="{text: $t('app.menu.license.mac-address')}"
                              :editor-options="{disabled:true}">
                  <DxRequiredRule :message="$t('app.menu.license.mac-address')"></DxRequiredRule>
                </DxSimpleItem>
                <DxSimpleItem data-field="processorId" :label="{text: $t('app.menu.license.processorId')}"
                              :editor-options="{disabled:true}">
                  <DxRequiredRule :message="$t('app.menu.license.processorId')"></DxRequiredRule>
                </DxSimpleItem>
                <DxSimpleItem data-field="baseBoardSerialNumber" :label="{text: $t('app.menu.license.baseBoardSerialNumber')}"
                              :editor-options="{disabled:true}">
                  <DxRequiredRule :message="$t('validation.license.baseBoardSerialNumber')"></DxRequiredRule>
                </DxSimpleItem>
              </DxGroupItem>
              <DxGroupItem>
                <DxSimpleItem data-field="license" :label="{text: $t('app.menu.license.text')}"  editor-type="dxTextArea"
                              :editor-options="{height:90}">
                  <DxRequiredRule :message="$t('validation.license.text')"></DxRequiredRule>
                </DxSimpleItem>
              </DxGroupItem>
            </DxGroupItem>
            <DxGroupItem :col-count="4">
              <DxGroupItem :col-count="2" :col-span="2">
                <DxButtonItem
                    :button-options="addBtnOptions"
                    :visible="!showButton"
                    horizontal-alignment="left"
                />
              </DxGroupItem>
            </DxGroupItem>
            <template #addButtonTemplate>
              <div>
                <span class="dx-button-text">
                    <dx-load-indicator v-if="loading" width="24px" height="24px" :visible="true" />
                    <span v-if="!loading">{{ $t('common.save') }}</span>
                  </span>
              </div>
            </template>
          </DxForm>
        </form>
      </div>
    </div>
  </div>
</template>
<script>
import DxLoadIndicator from "devextreme-vue/load-indicator";
import {DxButtonItem, DxForm, DxGroupItem, DxSimpleItem,DxRequiredRule} from 'devextreme-vue/form';
import kazu from "@/app/common/utils/kazu";

import i18n from "@/app/common/utils/i18n";
import DxTextArea from 'devextreme-vue/text-area';
import restapi from "@/app/common/utils/restapi";

const formData = {endDate: '', macAddress: '', processorId: '', baseBoardSerialNumber: '', license: ''}
const formDataRef = 'formDataRef';

export default {
  components: {
    DxForm,
    DxSimpleItem,
    DxGroupItem,
    DxButtonItem,
    DxLoadIndicator,
    DxRequiredRule,
    // eslint-disable-next-line vue/no-unused-components
    DxTextArea
  },
  data() {
    return {
      formDataRef,
      minDateValue: new Date(),
      loading:false,
      formData,
      addBtnOptions: {
        template: 'addButtonTemplate',
        type: 'default',
        useSubmitBehavior: true,
      },
      showButton: false,
      caption: i18n.global.t('app.menu.license.add'),
    }
  },
  created() {
    restapi({
      method: 'GET',
      url :`/license/server-license-info`
    }).then(e => {
      const macAddress = e.data.filter(f=> f.key === 'macAddress')[0].value;
      const processorId = e.data.filter(f=> f.key === 'processorId')[0].value;
      const baseBoardSerialNumber = e.data.filter(f=> f.key === 'baseBoardSerialNumber')[0].value;

      this.formData.macAddress = macAddress;
      this.formData.processorId = processorId;
      this.formData.baseBoardSerialNumber = baseBoardSerialNumber;
    })
  },
  methods: {
    handleSubmit: function (e) {
      if (!this.showButton) {
        this.loading = true
       kazu.RestService('/license/add/',this.formData,'POST').then((e) => {
         this.loading = false
         kazu.showMessage(e, 'success', 2000)
         this.formData = {}
           }).catch((e) => {
         this.loading = false
             kazu.showMessage(e, 'error', 2000)
           })
        e.preventDefault();
      } else {
        e.preventDefault();
      }
    },
  }
}
</script>
