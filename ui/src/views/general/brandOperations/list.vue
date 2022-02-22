<template>
  <div>
    <h2 class="content-block">{{ $t('app.menu.general.brand.text') }}</h2>
    <div class="content-block">
      <div class="dx-card responsive-paddings">
          <DxForm
              @initialized="formInitialized"
              :form-data="formData"
              :read-only="false"
              :show-colon-after-label="true"
              :show-validation-summary="true"
              validation-group="brandValidationGroup"
          >
            <DxGroupItem :col-count="4" :caption="caption">
              <DxGroupItem :col-span="4" :col-count="4">
                <DxSimpleItem data-field="name" :label="{text: $t('app.general.brand.name')}"
                              :editor-options="{maxLength:'50', minLength:'2'}">
                  <DxRequiredRule :message="$t('validation.brand.required-name')"></DxRequiredRule>
                  <DxStringLengthRule :min="2" :message="$t('validation.brand.name-minLength')"/>
                </DxSimpleItem>
                <DxSimpleItem data-field="code" :label="{text: $t('app.general.brand.code')}"
                              :editor-options="{maxLength:'3', minLength:'1' }">
                  <DxRequiredRule :message="$t('validation.brand.required-code')"></DxRequiredRule>
                  <DxStringLengthRule :min="2" :message="$t('validation.brand.code-minLength')"/>
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
            <template #addButtonTemplate>
              <div>
                <span class="dx-button-text">
                    <dx-load-indicator v-if="loading" width="24px" height="24px" :visible="true"/>
                    <span v-if="!loading">{{ $t('common.save') }}</span>
                  </span>
              </div>
            </template>
          </DxForm>
      </div>
    </div>
    <div class="content-block">
      <div class="dx-card responsive-paddings">
        <DxDataGrid
            :ref="dataGridRefName"
            :data-source="brandDataSource"
            :columns-auto-width="true"
            :show-borders="true"
            @row-updating="onBrandUpdating"
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
          <DxColumn
              data-type="string"
              data-field="name"
              :caption="$t('app.general.brand.name')"
          >
          </DxColumn>
          <DxColumn
              data-field="code"
              data-type="string"
              :caption="$t('app.general.brand.code')"
          >
          </DxColumn>
        </DxDataGrid>
      </div>
    </div>
  </div>
</template>
<script>
import DxLoadIndicator from "devextreme-vue/load-indicator";
import {DxButtonItem, DxForm, DxGroupItem, DxSimpleItem, DxStringLengthRule} from 'devextreme-vue/form';
import {
  DxColumn,
  DxDataGrid,
  DxEditing,
  DxFilterPanel,
  DxFilterRow,
  DxGroupPanel,
  DxHeaderFilter,
  DxRequiredRule,
  DxScrolling
} from 'devextreme-vue/data-grid';
import i18n from "@/app/common/utils/i18n";
import _ from "lodash";
import {CustomStoreCreator} from "@/app/common/utils/CustomStoreCreator";
import restapi from "@/app/common/utils/restapi";

const dataGridRefName = "dataGridRefName";

export default {
  components: {
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
    DxStringLengthRule,
    DxLoadIndicator
  },
  data() {
    return {
      loading: false,
      formData: {},
      dataGridRefName,
      formInstance: null,
      brandDataSource: CustomStoreCreator().createStore('/common/brand/'),
      saveBtnOptions: {
        type: 'default',
        useSubmitBehavior: true,
        template: 'addButtonTemplate',
        validationGroup: "brandValidationGroup",
        onClick: (e) => {
          let validationResult = e.validationGroup.validate();
          if (validationResult.isValid) {
            restapi({
              method: 'POST',
              url: '/common/brand/',
              data: this.formData
            }).then(()=>{
                  this.dataGrid.refresh()
                  this.formInstance.resetValues()
                }
            )
          }
        }
      },
      caption: i18n.global.t('app.menu.general.brand.add'),
    }
  },
  computed: {
    dataGrid: function () {
      return this.$refs[dataGridRefName].instance;
    }
  },
  methods: {
    onBrandUpdating: function (data) {
      data.newData = _.extend({}, data.oldData, data.newData);
    },
    formInitialized: function (e) {
      this.formInstance = e.component;
    }
  }
}
</script>
