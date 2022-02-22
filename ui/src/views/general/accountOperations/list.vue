<template>
  <div>
    <h2 class="content-block">{{$t('app.menu.general.account.text')}}</h2>
    <div class="content-block">
      <div class="dx-card responsive-paddings">
        <DxForm
            :form-data="formData"
            :read-only="false"
            :show-colon-after-label="true"
            :show-validation-summary="true"
            validation-group="userAccountValidationGroup"
            @initialized="formInitialized"
        >
          <DxGroupItem :col-count="4" css-class="first-group" :caption="caption">
            <DxGroupItem :col-span="4" :col-count="4">
              <DxSimpleItem data-field="name" :label="{text: $t('app.general.account.name')}"
                            :editor-options="{ maxLength:100 }">
                <DxRequiredRule :message="$t('validation.account.required-name')"></DxRequiredRule>
              </DxSimpleItem>
              <DxSimpleItem data-field="surname" :label="{text: $t('app.general.account.surname')}"
                            :editor-options="{ maxLength:100 }">
                <DxRequiredRule :message="$t('validation.account.required-surname')"></DxRequiredRule>
              </DxSimpleItem>
              <DxSimpleItem data-field="email" :label="{text: $t('app.general.account.email')}"
                            :editor-options="{ maxLength:100 }">
                <DxRequiredRule :message="$t('validation.account.required-email')"></DxRequiredRule>
                <DxEmailRule :message="$t('validation.account.invalid-email')"></DxEmailRule>
              </DxSimpleItem>
              <DxSimpleItem data-field="password" :label="{text: $t('app.general.account.password')}"
                            :editor-options="{mode:'password' }">
                <DxRequiredRule :message="$t('validation.account.required-password')"></DxRequiredRule>
              </DxSimpleItem>
            </DxGroupItem>
          </DxGroupItem>
          <DxGroupItem :col-count="1">
            <DxSimpleItem data-field="authorizations" :label="{text: $t('app.general.account.role')}" editor-type="dxTagBox" :editor-options="role">
              <DxRequiredRule :message="$t('validation.account.required-role')"></DxRequiredRule>
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
    <div class="content-block">
      <div class="dx-card responsive-paddings">

        <DxPopup
            v-model:visible="popupVisible"
            :drag-enabled="false"
            :close-on-outside-click="true"
            :show-close-button="true"
            :show-title="true"
            :width="500"
            :height="350"
            container=".dx-viewport"
            :title="$t('app.general.account.reset-password')"
        >
          <DxPosition
              at="center"
              my="center"
          />
          <div class="dx-fieldset">
            <div class="dx-fieldset-header">{{ name }}</div>
            <div class="dx-field">
              <div class="dx-field-label">{{ $t('app.general.account.newPassword') }}</div>
              <DxTextBox
                  mode="password"
                  class="dx-field-value"
                  v-model:value="newPassword"
              />
            </div>
            <div class="dx-field">
              <div class="dx-field-label">{{ $t('app.general.account.confirmPassword') }}</div>
              <DxTextBox
                  mode="password"
                  class="dx-field-value"
                  v-model:value="confirmPassword"
              />
            </div>
            <div class="dx-field">
              <DxFormButton
                  type="success"
                  :text="$t('common.update')"
                  @click="changePassword"
              />
            </div>
          </div>
        </DxPopup>
        <DxDataGrid
            :data-source="userDataSource"
            :columns-auto-width="true"
            :show-borders="true"
            @row-updating="onUserAccountUpdating"
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
              template="rolesTemplate"
          />
          <template #rolesTemplate="{ data: selectedUser }">
            <roles
                :template-data="selectedUser"
            />
          </template>
          <DxColumn
              data-type="string"
              data-field="name"
              :caption="$t('app.general.account.name')"
          >
          </DxColumn>
          <DxColumn
              data-field="surname"
              data-type="string"
              :caption="$t('app.general.account.surname')"
          >
          </DxColumn>
          <DxColumn
              data-field="email"
              data-type="string"
              :caption="$t('app.general.account.email')"
          >
          </DxColumn>
          <DxColumn
              type="buttons"
              :width="110"
          >
            <DxButton
                hint="Şifre Sıfırla"
                icon="key"
                @click="popupResetPassword"
            />
            <DxButton name="edit"/>
            <DxButton name="delete"/>
          </DxColumn>
        </DxDataGrid>
      </div>
    </div>
  </div>
</template>
<script>
import roles from "@/views/general/accountOperations/roles";
import {DxButtonItem, DxForm, DxGroupItem, DxSimpleItem} from 'devextreme-vue/form';
import {DxTextBox} from 'devextreme-vue';
import DxFormButton from 'devextreme-vue/button'
import { DxPopup, DxPosition } from 'devextreme-vue/popup';
import {CustomStoreCreator} from "@/app/common/utils/CustomStoreCreator";
import {UserAccountService} from "@/views/general/accountOperations/business/UserAccountService";
import _ from 'lodash';
import {
  DxColumn,
  DxDataGrid,
  DxEditing,
  DxEmailRule,
  DxFilterPanel,
  DxFilterRow,
  DxGroupPanel,
  DxHeaderFilter,
  DxMasterDetail,
  DxRequiredRule,
  DxScrolling,
  DxButton
} from 'devextreme-vue/data-grid';
import i18n from "@/app/common/utils/i18n";
import restapi from "@/app/common/utils/restapi";

export default {
  components: {
    DxTextBox,
    DxPosition,
    DxPopup,
    DxButton,
    roles,
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
    DxEmailRule,
    DxMasterDetail,
    DxEditing,
    DxFormButton
  },
  data() {
    return {
      newPassword:null,
      confirmPassword:null,
      userId:null,
      popupVisible: false,
      userDataSource: CustomStoreCreator().createStore("/user/account/"),
      formData: {},
      formInstance: null,
      gridInstance: null,
      role: UserAccountService().getTagBoxParameters(),
      saveBtnOptions: {
        type: 'default',
        text: i18n.global.t("common.save"),
        useSubmitBehavior: true,
        validationGroup: "userAccountValidationGroup",
        onClick: (e) => {


          let validationResult = e.validationGroup.validate();
          if(validationResult.isValid) {
            this.loading = true;

            let userAccount = {"name": this.formData.name, "surname": this.formData.surname, "email": this.formData.email, "password": this.formData.password,
              authorizations: []}

            for (let i = 0; i < this.formData.authorizations.length; i++) {
              userAccount.authorizations.push({"role": {"id": this.formData.authorizations[i].id}, "active": true})
            }

            console.log(userAccount);

            this.userDataSource.insert(userAccount).then(() => {
              console.log('Sildi');
              this.formInstance.resetValues();
              this.gridInstance.refresh();
            });
          }
        }
      },
      showButton: false,
      caption: i18n.global.t('app.menu.general.account.add'),
    }
  },
  methods: {
    gridInitialized: function (e) {
      this.gridInstance = e.component
    },
    formInitialized: function (e) {
      this.formInstance = e.component
    },
    onUserAccountUpdating:function (data){
      data.newData = _.extend({}, data.oldData, data.newData);
    },
    popupResetPassword:function (e) {
      this.popupVisible = true
      this.userId = e.row.key
    },
    changePassword:function () {
      restapi({
        method: 'PUT',
        url: '/user/account/reset-password',
        data: {userId:this.userId,newPassword: this.newPassword, confirmPassword:this.confirmPassword }
      }).then(() => {
        this.popupVisible = false
      })
    }
  }
}
</script>
