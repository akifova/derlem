<template>
  <div>
    <div class="dx-fieldset">
      <div class="dx-fieldset-header">{{ name }}</div>
      <div class="dx-field">
        <div class="dx-field-label">{{ $t('app.general.firm.address') }}</div>
        <DxTextBox
            class="dx-field-value"
            v-model:value="address.address"
        />
      </div>
      <div class="dx-field">
        <div class="dx-field-label">{{ $t('app.general.firm.mailAddress') }}</div>
        <DxTextBox
            class="dx-field-value"
            v-model:value="address.mails[0].mailAddress"
        />
      </div>
      <div class="dx-field">
        <div class="dx-field-label">{{ $t('app.general.firm.phoneNumber') }}</div>
        <DxTextBox
            class="dx-field-value"
            v-model:value="address.phones[0].phoneNumber"
        />
      </div>
      <div class="dx-field">
        <div class="dx-field-label">{{ $t('app.general.firm.weburl') }}</div>
        <DxTextBox
            class="dx-field-value"
            v-model:value="address.webUrl"
        />
      </div>
      <div class="dx-field">
        <div class="dx-field-label">{{ $t('app.general.firm.county') }}</div>
        <DxLookup
            :data-source="countyDataSource"
            styling-mode="filled"
            :display-expr="getDisplayExpr"
            :value-expr="countyLookupValueExpression"
            placeholder="Şehir Seçiniz..."
            :min-search-length="3"
            :search-enabled=true
            v-model:value="address.county"
        ></DxLookup>
      </div>
      <div class="dx-field">
        <DxButton
            type="success"
            :text="$t('common.update')"
            @click="updateAddress"
        />
      </div>
    </div>
  </div>
</template>
<script>

import {DxButton, DxLookup, DxTextBox} from "devextreme-vue";
import CustomStore from "devextreme/data/custom_store";
import restapi from "@/app/common/utils/restapi";
import kazu from "@/app/common/utils/kazu";

const rolesGridRefName = "rolesGridRefName"
export default {
  props: {
    templateData: {
      type: Object,
      default: () => {
      }
    }
  },
  components: {
    DxLookup,
    DxTextBox,
    DxButton
  },
  data() {
    let {id, name,address} = this.templateData.data;
    return {
      rolesGridRefName,
      name,
      id,
      address : address ? address : {"id": "", "name": 'Firma Adresi', "address": "", "webUrl": "", "county": {}, "mails": [{"mailAddress": ""}], "phones": [{"phoneNumber": ""}]},
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
      detailInfo: `${name } adres bilgileri`
    };
  },
  methods: {
    updateAddress() {
      restapi({
        method: 'PUT',
        url: '/common/firm/update-address/' + this.id,
        data: this.address
      })
    },
    getDisplayExpr(item) {
      console.log(item)
      return typeof item === "object" && !kazu.isObjectEmpty(item) ? `${item.city.name} / ${item.name}` : "";
    },
    countyLookupValueExpression(item) {
      return item ? item : null;
    },
    changeLookupValue: function (e){
      let val = e.itemData;
      this.countyValue = `${val.city.name} / ${val.name}`
    },
    onToolbarPreparing(e) {
      e.toolbarOptions.items.unshift({
        location: 'before',
        template: 'headerTitle'
      });
    },
    displayCounty(data) {
      return `${data.county.city.name} / ${data.county.name}`
    },
    displayMailAddress(data){
      return data.mails[0].mailAddress;
    },
    displayPhoneNumber(data){
      return data.phones[0].phoneNumber;
    }
  }
};
</script>
