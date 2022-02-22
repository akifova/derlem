<template>
  <div>
    <h2 class="content-block">{{ $t('app.menu.stock-card.shape.text') }}</h2>
    <div class="content-block">
      <div class="dx-card responsive-paddings">
        <DxForm
            :form-data="formData"
            :read-only="false"
            :show-colon-after-label="true"
            :show-validation-summary="true"
            validation-group="shapeValidationGroup"
            @initialized="formInitialized"
        >
          <DxGroupItem :col-count="4" css-class="first-group" :caption="caption">
            <DxGroupItem :col-span="4" :col-count="4">
              <DxSimpleItem data-field="code" :label="{text: $t('app.stock-card.shape.code')}"
                            :editor-options="{maxLength:'5'}">
                <DxRequiredRule :message="$t('validation.stock-card.shape.required-code')"></DxRequiredRule>
              </DxSimpleItem>
              <DxSimpleItem data-field="definition" :label="{text: $t('app.stock-card.shape.definition')}"
                            :editor-options="{maxLength:'100'}">
                <DxRequiredRule :message="$t('validation.stock-card.shape.required-definition')"></DxRequiredRule>
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
            :data-source="shape"
            :columns-auto-width="true"
            :show-borders="true"
            @row-updating="onShapeUpdating"
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
              data-field="code"
              :caption="$t('app.stock-card.shape.code')"
          />
          <DxColumn
              data-field="definition"
              data-type="string"
              :caption="$t('app.stock-card.shape.definition')"
          />
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
  DxScrolling,
} from 'devextreme-vue/data-grid';
import i18n from "@/app/common/utils/i18n";
import {ShapeDao} from "@/views/stockCard/shapeOperations/persistence/shape-dao";
import _ from "lodash";

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
    DxEditing
  },
  data() {
    return {
      formData: {},
      formInstance: null,
      gridInstance: null,
      shape: ShapeDao().shapeDataSource(),
      saveBtnOptions: {
        type: 'default',
        useSubmitBehavior: true,
        text: i18n.global.t('common.save'),
        validationGroup: "shapeValidationGroup",
        onClick: (e) => {
          let validationResult = e.validationGroup.validate();
          if (validationResult.isValid) {
            ShapeDao().shapeDataSource().insert(this.formData).then(() => {
              this.formInstance.resetValues();
              this.gridInstance.refresh();
            })
          }
        }
      },
      caption: i18n.global.t('app.menu.stock-card.shape.add'),
    }
  },
  methods: {
    formInitialized: function (e) {
      this.formInstance = e.component
    },
    gridInitialized: function (e) {
      this.gridInstance = e.component
    },
    onShapeUpdating: function (data) {
      data.newData = _.extend({}, data.oldData, data.newData);
    }
  }
}
</script>
