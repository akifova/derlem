<template>
  <div>
    <h2 class="content-block">{{$t('app.menu.yarn.type.text')}}</h2>
    <div class="content-block">
      <div class="dx-card responsive-paddings">
          <DxForm
              :form-data="formData"
              :read-only="false"
              :show-colon-after-label="true"
              :show-validation-summary="true"
              validation-group="yarnTypeValidationGroup"
              @initialized="formInitialized"
          >
            <DxGroupItem :col-count="3" css-class="first-group" :caption="caption">
              <DxGroupItem :col-span="3" :col-count="3">
                <DxSimpleItem data-field="name" :label="{text: $t('app.yarn.type.name')}">
                  <DxRequiredRule :message="$t('validation.yarn.type.required-name')"></DxRequiredRule>
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
            :data-source="yarnTypeDataSource"
            :columns-auto-width="true"
            :show-borders="true"
            @row-updating="onYarnTypeUpdating"
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
          <DxColumn
              data-type="string"
              data-field="name"
              :caption="$t('app.yarn.type.name')"
          >
          </DxColumn>
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
  DxRequiredRule,
  DxScrolling
} from 'devextreme-vue/data-grid';
import i18n from "@/app/common/utils/i18n";
import _ from "lodash";
import {CustomStoreCreator} from "@/app/common/utils/CustomStoreCreator";

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
    DxGroupPanel
  },
  data() {
    return {
      formData: {},
      yarnTypeDataSource: CustomStoreCreator().createStore('/yarn/type/'),
      formInstance: null,
      gridInstance: null,
      saveBtnOptions: {
        type: 'default',
        useSubmitBehavior: true,
        text: i18n.global.t('common.save'),
        validationGroup: "yarnTypeValidationGroup",
        onClick: (e) => {
          let validationResult = e.validationGroup.validate();
          if (validationResult.isValid) {
            this.yarnTypeDataSource.insert(this.formData).then(() => {
              this.formInstance.resetValues();
              this.gridInstance.refresh();
            })
          }
        }
      },
      caption: i18n.global.t('app.menu.yarn.type.add'),
    }
  },
  methods: {
    formInitialized: function (e) {
      this.formInstance = e.component;
    },
    gridInitialized: function (e) {
      this.gridInstance = e.component
    },
    onYarnTypeUpdating: function (data) {
      data.newData = _.extend({}, data.oldData, data.newData);
    },
  }
}
</script>
