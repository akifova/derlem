<template>
  <div>
    <h2 class="content-block">{{$t('app.menu.general.firm.text')}}</h2>
    <div class="content-block">
      <div class="dx-card responsive-paddings">
          <DxForm
              @initialized="formInitialized"
              :form-data="formData"
              :read-only="false"
              :show-colon-after-label="true"
              :show-validation-summary="true"
              validation-group="firmValidationGroup"
          >

            <DxGroupItem :col-count="4" :caption="caption">
              <DxGroupItem :col-span="4" :col-count="4">
                <DxSimpleItem data-field="name" :label="{text: $t('app.general.firm.name')}">
                  <DxRequiredRule :message="$t('validation.firm.required-name')"></DxRequiredRule>
                </DxSimpleItem>
                <DxSimpleItem data-field="tradeName" :label="{text: $t('app.general.firm.tradeName')}">
                  <DxRequiredRule :message="$t('validation.firm.required-tradeName')"></DxRequiredRule>
                </DxSimpleItem>
                <DxSimpleItem data-field="taxOffice" :label="{text: $t('app.general.firm.taxOffice')}">
                  <DxRequiredRule :message="$t('validation.firm.required-taxOffice')"></DxRequiredRule>
                </DxSimpleItem>
                <DxSimpleItem data-field="taxNumber" :label="{text: $t('app.general.firm.taxNumber')}"
                              :editor-options="{ mask:'0000000000' }">
                  <DxRequiredRule :message="$t('validation.firm.required-taxNumber')"></DxRequiredRule>
                </DxSimpleItem>
              </DxGroupItem>
              <DxGroupItem :col-span="4" :col-count="4">
                <DxSimpleItem data-field="supplier" editor-type="dxCheckBox" :label="{text: $t('app.general.firm.supplier')}">
                </DxSimpleItem>
                <DxSimpleItem data-field="customer" editor-type="dxCheckBox" :label="{text: $t('app.general.firm.customer')}">
                </DxSimpleItem>
                <DxSimpleItem data-field="outsourcer" editor-type="dxCheckBox" :label="{text: $t('app.general.firm.outsourcer')}">
                </DxSimpleItem>
                <DxSimpleItem data-field="isPersonCompany" editor-type="dxCheckBox" :label="{text: $t('app.general.firm.isPersonCompany')}"
                              :editor-options="{ onValueChanged:isPersonCompanyVal }">
                </DxSimpleItem>
              </DxGroupItem>
            </DxGroupItem>
            <DxGroupItem :col-count="3" css-class="second-group">
              <DxGroupItem :col-span="3" :col-count="3">
                <DxSimpleItem :col-span="2" data-field="address.address" :label="{text: $t('app.general.firm.address')}">
                  <DxRequiredRule :message="$t('validation.firm.required-address')"></DxRequiredRule>
                </DxSimpleItem>
                <DxSimpleItem data-field="address.county" :label="{text:$t('app.general.firm.county')}">
                  <template #default>
                    <DxLookup
                        v-model:value="countyValue"
                        :data-source="countyDataSource"
                        :display-expr="getDisplayExpr"
                        placeholder="Şehir Seçiniz..."
                        :min-search-length="3"
                        :search-enabled=true
                        @item-click="changeLookupValue($event)"
                    >
                    </DxLookup>
                  </template>
                </DxSimpleItem>
              </DxGroupItem>
            </DxGroupItem>
            <DxGroupItem :col-count="3" css-class="second-group">
              <DxGroupItem :col-span="3" :col-count="3">
                <DxSimpleItem data-field="address.webUrl" :label="{text: $t('app.general.firm.weburl')}">
                  <DxRequiredRule :message="$t('validation.firm.required-weburl')"></DxRequiredRule>
                </DxSimpleItem>
                <DxSimpleItem data-field="address.mails[0].mailAddress" :label="{text: $t('app.general.firm.mailAddress')}">
                  <DxRequiredRule :message="$t('validation.firm.required-email')"></DxRequiredRule>
                  <DxEmailRule :message="$t('validation.firm.invalid-email')"></DxEmailRule>
                </DxSimpleItem>
                <DxSimpleItem data-field="address.phones[0].phoneNumber" :label="{text: $t('app.general.firm.phoneNumber')}"
                              :editor-options="{ mask:'(000) 000 00 00'}">
                  <DxRequiredRule :message="$t('validation.firm.required-phoneNumber')"></DxRequiredRule>
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
            :ref="dataGridRefName"
            id="gridContainer"
            :data-source="firmDataSource"
            :columns-auto-width="true"
            :show-borders="true"
            @row-updating="onFirmUpdating"
            @initialized="gridInitialized"
        >
          <DxFilterRow :visible="true"/>
          <DxFilterPanel :visible="true"/>
          <DxHeaderFilter :visible="true"/>
          <DxScrolling mode="infinite"/>
          <DxGroupPanel :visible="true"/>
          <DxMasterDetail
              :enabled="true"
              template="addressDetail"
          />
          <template #addressDetail="{ data: address }">
            <addressDetail
                :template-data="address"
            />
          </template>
          <DxEditing
              :allow-updating="true"
              :allow-deleting="true"
              mode="popup"
          />
          <DxColumn
              data-type="string"
              data-field="name"
              :caption="$t('app.general.firm.name')"
          />
          <DxColumn
              data-field="tradeName"
              data-type="string"
              :caption="$t('app.general.firm.tradeName')"
          />
          <DxColumn
              :visible="false"
              data-field="taxOffice"
              data-type="string"
              :caption="$t('app.general.firm.taxOffice')"
          />
          <DxColumn
              :visible="false"
              data-field="taxNumber"
              data-type="string"
              :caption="$t('app.general.firm.taxNumber')"
          />
          <DxColumn
              data-field="supplier"
              data-type="boolean"
              :caption="$t('app.general.firm.supplier')"
          />
          <DxColumn
              data-field="customer"
              data-type="boolean"
              :caption="$t('app.general.firm.customer')"
          />
          <DxColumn
              data-field="outsourcer"
              data-type="boolean"
              :caption="$t('app.general.firm.outsourcer')"
          />
          <DxColumn
              data-field="isPersonCompany"
              data-type="boolean"
              :caption="$t('app.general.firm.isPersonCompany')"
          />
        </DxDataGrid>
      </div>
    </div>
  </div>
</template>
<script>
import {DxButtonItem, DxForm, DxGroupItem, DxSimpleItem,} from 'devextreme-vue/form';
import {DxLookup} from 'devextreme-vue/lookup';
import restapi from "@/app/common/utils/restapi";
import _ from "lodash";
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
  DxScrolling
} from 'devextreme-vue/data-grid';

import CustomStore from "devextreme/data/custom_store";
import i18n from "@/app/common/utils/i18n";
import {CustomStoreCreator} from "@/app/common/utils/CustomStoreCreator";
import addressDetail from "@/views/general/firmOperations/addressDetail";

const dataGridRefName = "dataGridRefName";
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
    DxEditing,
    DxLookup,
    DxEmailRule,
    DxMasterDetail,
    addressDetail
  },
  data() {
    return {
      formInstance:null,
      gridInstance:null,
      formData: {
        supplier: false,
        customer: false,
        outsourcer: false,
        isPersonCompany: 0,
        address:{
          county:{
            id:null,
            name:null,
            countyCode:null
          },
          mails:[
            {
              mailAddress: null,
              mailType: "individual"
            }],
          phones:[{
            phoneNumber: null,
            phoneType: "office"
          }]
        },
      },
      dataGridRefName,
      firmDataSource: CustomStoreCreator().createStore("/common/firm/"),
      countyDataSource: new CustomStore({
        key: 'id',
        load: (loadOptions) => {
          let url = '/common/address/county/search?sp=name==*' + loadOptions.searchValue + '* or city.name==*' + loadOptions.searchValue + '*';
          return restapi({
            method: 'GET',
            url
          })
        },
        byKey: function (key) {
          return key
        },
      }),
        countyValue:'',
      saveBtnOptions: {
        type: 'default',
        useSubmitBehavior: true,
        text: i18n.global.t('common.save'),
        validationGroup: 'firmValidationGroup',
        onClick:(e) => {
          let validationResult = e.validationGroup.validate();
          if (validationResult.isValid){
            this.firmDataSource.insert(this.formData).then(()=> {
              this.formInstance.resetValues();
              this.gridInstance.refresh();
              this.countyValue = ''
            })
          }
        }
      },
      caption: i18n.global.t('app.menu.general.firm.add'),
    }
  },
  methods: {
    formInitialized:function (e){
      this.formInstance = e.component
},
    gridInitialized: function (e){
      this.gridInstance = e.component
    },
    isPersonCompanyVal(e){
      if (e.value){
        this.formData.isPersonCompany = 1
      }else {
        this.formData.isPersonCompany = 0
      }
    },
    onFirmUpdating:function (data) {
      data.newData = _.extend({},data.oldData,data.newData)
    },
    getDisplayExpr(item) {
      return typeof item === "object" ? `${item.city.name} / ${item.name}` : item;
    },
    changeLookupValue: function (e){
      let val = e.itemData;
      this.countyValue = `${val.city.name} / ${val.name}`
      this.formData.address.county.id = val.id;
      this.formData.address.county.countyCode = val.countyCode;
      this.formData.address.county.name = val.county;
    },
  }
}
</script>
