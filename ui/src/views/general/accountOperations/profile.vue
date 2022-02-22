<template>
  <div>
    <h2 class="content-block">{{$t('app.menu.general.account.profile')}}</h2>
    <div class="content-block">
      <div class="dx-card responsive-paddings">
        <DxForm
            :form-data="formData"
            :read-only="false"
            :show-colon-after-label="true"
            :show-validation-summary="true"
            validation-group="profileValidationGroup"
            @initialized="formInitialized"
        >
          <DxGroupItem :col-count="3">
            <DxGroupItem :col-span="3" :col-count="3">
              <DxSimpleItem data-field="name" :label="{text: $t('app.general.account.name')}" :editor-options="{disabled:true}">
              </DxSimpleItem>
              <DxSimpleItem data-field="surname" :label="{text: $t('app.general.account.surname')}" :editor-options="{disabled:true}">
              </DxSimpleItem>
              <DxSimpleItem data-field="email" :label="{text: $t('app.general.account.email')}" :editor-options="{disabled:true}">
              </DxSimpleItem>
            </DxGroupItem>
          </DxGroupItem>
          <DxGroupItem :col-count="3" caption="Şifre değiştir">
            <DxSimpleItem data-field="oldPassword" :label="{text: $t('app.general.account.oldPassword')}" :editor-options="{mode:'password'}">
              <DxRequiredRule :message="$t('validation.account.required-password')"></DxRequiredRule>
            </DxSimpleItem>
            <DxSimpleItem data-field="newPassword" :label="{text: $t('app.general.account.newPassword')}" :editor-options="{mode:'password'}">
              <DxRequiredRule :message="$t('validation.account.required-newPassword')"></DxRequiredRule>
            </DxSimpleItem>
            <DxSimpleItem data-field="confirmPassword" :label="{text: $t('app.general.account.confirmPassword')}" :editor-options="{mode:'password'}">
              <DxRequiredRule :message="$t('validation.account.required-confirmPassword')"></DxRequiredRule>
              <DxCompareRule
                  :comparison-target="passwordComparison"
                  :message="$t('validation.account.password-comparison')"
              />
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
import {DxButtonItem, DxForm, DxGroupItem, DxSimpleItem, DxRequiredRule,DxCompareRule} from 'devextreme-vue/form';
import i18n from "@/app/common/utils/i18n";
import store from "@/store";
import restapi from "@/app/common/utils/restapi";
export default {
  components: {
    DxForm,
    DxSimpleItem,
    DxGroupItem,
    DxButtonItem,
    DxRequiredRule,
    DxCompareRule
  },
  mounted(){
    console.log("test")
    restapi({
      method:'GET',
      url:`/user/account/get/${this.username}`
    }).then(res => {
      this.formData = res.data
    })
  },
  data() {
    return {
      username : store.getters._getCurrentUser?.username,
      formData:{},
      saveBtnOptions: {
        type: 'default',
        text: i18n.global.t("common.save"),
        useSubmitBehavior: true,
        validationGroup: "profileValidationGroup",
        onClick: (e) => {
          let validationResult = e.validationGroup.validate();
          if(validationResult.isValid) {
            this.loading = true;

            let formData = {
              "userId": this.formData.id,
              "oldPassword": this.formData.oldPassword,
              "newPassword": this.formData.newPassword,
              "confirmPassword": this.formData.confirmPassword
            }
            restapi({
              method:'PUT',
              url:`/user/account/change-password`,
              data:formData
            })
          }
        }
      },
    }
  },
  methods: {
    formInitialized: function (e) {
      this.formInstance = e.component
    },
    passwordComparison() {
      return this.formData.newPassword;
    },
  }
}
</script>
