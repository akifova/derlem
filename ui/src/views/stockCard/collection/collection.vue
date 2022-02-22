<template>
  <div>
    <h2 class="content-block">{{$t('app.menu.stock-card.collection.text')}}</h2>
    <div class="content-block">
      <div class="dx-card responsive-paddings">
          <DxForm
              :form-data="formData"
              :read-only="false"
              :show-colon-after-label="true"
              :show-validation-summary="true"
              validation-group="collectionValidationGroup"
              @initialized="formInitialized"
          >
            <DxGroupItem :col-count="4" :caption="caption">
              <DxGroupItem :col-span="4" :col-count="4">
                <DxSimpleItem data-field="code" :label="{text: $t('app.stock-card.collection.code')}">
                  <DxRequiredRule :message="$t('validation.stock-card.collection.required-code')"></DxRequiredRule>
                  <DxStringLengthRule :min="2" :max="10" :message="$t('validation.stock-card.collection.code-string-length-rule')"/>
                </DxSimpleItem>
                <DxSimpleItem data-field="definition" :label="{text: $t('app.stock-card.collection.definition')}">
                  <DxRequiredRule :message="$t('validation.stock-card.collection.required-definition')"></DxRequiredRule>
                </DxSimpleItem>
                <DxSimpleItem data-field="shrinkage" :label="{text: $t('app.stock-card.collection.shrinkage')}" editor-type="dxNumberBox">
                  <DxRequiredRule :message="$t('validation.stock-card.collection.required-shrinkage')"></DxRequiredRule>
                </DxSimpleItem>
                <DxSimpleItem data-field="brand" :label="{text: $t('app.stock-card.collection.brand')}" editor-type="dxSelectBox"
                              :editor-options="brandSelectBoxOptions">
                  <DxRequiredRule :message="$t('validation.stock-card.collection.required-brand')"></DxRequiredRule>
                </DxSimpleItem>
              </DxGroupItem>
              <DxGroupItem :col-span="4" :col-count="4">
                <DxSimpleItem data-field="painted" editor-type="dxCheckBox" :label="{text: $t('app.stock-card.collection.painted')}">
                </DxSimpleItem>
                <DxSimpleItem data-field="washed" editor-type="dxCheckBox" :label="{text: $t('app.stock-card.collection.washed')}">
                </DxSimpleItem>
                <DxSimpleItem data-field="horizontalWeaving" editor-type="dxCheckBox" :label="{text: $t('app.stock-card.collection.horizontalWeaving')}">
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
            :data-source="collectionDataSource"
            :columns-auto-width="true"
            :show-borders="true"
            @row-updating="onCollectionUpdating"
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
              alignment="left"
              data-field="code"
              data-type="string"
              :caption="$t('app.stock-card.collection.code')"
          />
          <DxColumn
              alignment="left"
              data-field="definition"
              data-type="string"
              :caption="$t('app.stock-card.collection.definition')"
          />
          <DxColumn
              alignment="left"
              data-field="shrinkage"
              data-type="number"
              :caption="$t('app.stock-card.collection.shrinkage')"
          />
          <DxColumn
              alignment="left"
              data-field="brand"
              :caption="$t('app.stock-card.collection.brand')"
              :calculate-display-value= "displayBrandName"
              edit-cell-template="brandSelectBoxTemplate"
          >
          </DxColumn>
          <DxColumn
              alignment="left"
              data-field="painted"
              data-type="boolean"
              :caption="$t('app.stock-card.collection.painted')"
          />
          <DxColumn
              alignment="left"
              data-field="washed"
              data-type="boolean"
              :caption="$t('app.stock-card.collection.washed')"
          />
          <DxColumn
              alignment="left"
              data-field="horizontalWeaving"
              data-type="boolean"
              :caption="$t('app.stock-card.collection.horizontalWeaving')"
          />
          <template #brandSelectBoxTemplate="{ data: cellInfo }">
            <KzSelectBoxFieldEditor
                :value="cellInfo.value"
                :on-value-changed="(value) => onValueChanged(value, cellInfo)"
                data-source-url="/common/brand/"
                :display-fields="['name']"
                :data-grid-component="cellInfo.component"
            />
          </template>
        </DxDataGrid>
      </div>
    </div>
  </div>
</template>
<script>
import KzSelectBoxFieldEditor from "@/app/components/dx/dataGrid/KzSelectBoxFieldEditor";
import {DxButtonItem, DxForm, DxGroupItem, DxSimpleItem, } from 'devextreme-vue/form';
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
import {BrandService} from "@/views/general/brandOperations/business/BrandService";
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
    DxGroupPanel,
    KzSelectBoxFieldEditor,
  },
  data() {
    return {
      formData: {},
      formInstance: null,
      gridInstance: null,
      collectionDataSource: CustomStoreCreator().createStore('/scm/production/definition/stock-card/collection/'),
      brandSelectBoxOptions: BrandService().getSelectBoxParameters(),
      brandDataSource: CustomStoreCreator().createStore('/common/brand/'),
      saveBtnOptions: {
        type: 'default',
        useSubmitBehavior: true,
        text: i18n.global.t('common.save'),
        validationGroup: "collectionValidationGroup",
        onClick: (e) => {
          let validationResult = e.validationGroup.validate();
          if (validationResult.isValid) {
            this.collectionDataSource.insert(this.formData).then(() => {
              this.formInstance.resetValues();
              this.gridInstance.refresh();
            })
          }
        }
      },
      caption: i18n.global.t('app.menu.stock-card.collection.add'),
    }
  },
  methods: {
    formInitialized: function (e) {
      this.formInstance = e.component
    },
    gridInitialized: function (e) {
      this.gridInstance = e.component
    },
    onCollectionUpdating: function (data) {
      data.newData = _.extend({}, data.oldData, data.newData);
    },
    displayBrandName(data) {
      return data.brand?.name;
    },
    onValueChanged(value, cellInfo) {
      cellInfo.setValue(value);
      cellInfo.component.updateDimensions();
    }
  }
}
</script>
