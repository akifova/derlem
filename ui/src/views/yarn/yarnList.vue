<template>
  <div>
    <h2 class="content-block">{{$t('app.menu.yarn.list')}}</h2>
    <div class="content-block">
      <div class="dx-card responsive-paddings">
        <DxDataGrid
            :data-source="yarnDataSource"
            :columns-auto-width="true"
            :show-borders="true"
            @row-updating="onYarnUpdating"
        >
          <DxMasterDetail
              :enabled="true"
              template="yarnProperty"
          />
          <template #yarnProperty="{ data: yarnAttributes }">
            <yarnProperty
                :template-data="yarnAttributes"
            />
          </template>
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
              :caption="$t('app.yarn.definition.name')"
          >
          </DxColumn>
          <DxColumn
              data-type="string"
              data-field="minStock"
              :caption="$t('app.yarn.definition.minStock')"
          >
          </DxColumn>
          <DxColumn
              data-field="weight"
              data-type="string"
              :caption="$t('app.yarn.definition.weight')"
          >
          </DxColumn>
          <DxColumn
              data-field="weightUnit"
              data-type="string"
              :caption="$t('app.yarn.definition.weightUnit')"
              :calculate-display-value="calculateWeightUnitValue"
              edit-cell-template="weightUnitSelectBoxTemplate"
          />
          <template #weightUnitSelectBoxTemplate="{ data: cellInfo }">
            <KzSelectBoxFieldEditor
                :value="cellInfo.value"
                :on-value-changed="(value) => onValueChanged(value, cellInfo)"
                data-source-url="/common/uom/"
                :display-fields="['shortName']"
                :data-grid-component="cellInfo.component"
            />
          </template>
          <DxColumn
              data-field="yarnGroup"
              data-type="string"
              :caption="$t('app.yarn.definition.yarnGroup')"
              :calculate-display-value="calculateYarnGroupValue"
              edit-cell-template="yarnGroupLookupTemplate"
          />
          <DxColumn
                data-field="actionType"
                data-type="string"
                :caption="$t('app.yarn.definition.actionType')"
                :calculate-display-value="calculateActionTypeValue"
            >
            <DxLookup
                :data-source="actionTypeDataSource"
                display-expr="name"
                value-expr="id"
            />
          </DxColumn>
          <DxColumn
              data-field="usageType"
              data-type="string"
              :caption="$t('app.yarn.definition.usageType')"
              :calculate-display-value="calculatePropertyValue"
              :editor-options="{disabled:true}"
          />
          <template #yarnGroupLookupTemplate="{ data: cellInfo }">
            <KzLookupFieldEditor
                :value="cellInfo.value"
                :on-value-changed="(value) => onValueChanged(value, cellInfo)"
                data-source-url="/yarn/group/search"
                :search-fields="['yarnGroup.name']"
                :data-grid-component="cellInfo.component"
            />
          </template>
        </DxDataGrid>
      </div>
    </div>
  </div>
</template>
<script>
import yarnProperty from "@/views/yarn/yarnListLineProperty";
import KzLookupFieldEditor from "@/app/components/dx/dataGrid/KzLookupFieldEditor";
import {
  DxColumn,
  DxDataGrid,
  DxEditing,
  DxFilterPanel,
  DxFilterRow,
  DxGroupPanel,
  DxHeaderFilter,
  DxMasterDetail,
  DxScrolling,
  DxLookup
} from 'devextreme-vue/data-grid';
import i18n from "@/app/common/utils/i18n";
import {CustomStoreCreator} from "@/app/common/utils/CustomStoreCreator";
import _ from "lodash";
import KzSelectBoxFieldEditor from "@/app/components/dx/dataGrid/KzSelectBoxFieldEditor";
import {YarnService} from "@/views/yarn/business/YarnService";

export default {
  components: {
    KzSelectBoxFieldEditor,
    DxDataGrid,
    DxColumn,
    DxHeaderFilter,
    DxFilterRow,
    DxFilterPanel,
    DxScrolling,
    DxGroupPanel,
    DxEditing,
    DxMasterDetail,
    yarnProperty,
    KzLookupFieldEditor,
    DxLookup
  },
  data() {
    return {
      actionTypeDataSource: YarnService().getActionType().dataSource,
      yarnDataSource: CustomStoreCreator().createStore('/yarn/'),
      caption: i18n.global.t('app.menu.stock-card.quality.add'),
    }
  },
  methods: {
    calculatePropertyValue(val){
      return i18n.global.t('common.' + val.usageType)
    },
    calculateYarnGroupValue(rowData) {
      return rowData.yarnGroup?.name;
    },
    calculateWeightUnitValue(rowData){
      return rowData.weightUnit.shortName;
    },
    calculateActionTypeValue(rowData){
      return rowData.actionType.toUpperCase()
    },
    onValueChanged(value, cellInfo) {
      cellInfo.setValue(value);
      cellInfo.component.updateDimensions();
    },
    onYarnUpdating: function (data) {
      data.newData = _.extend({}, data.oldData, data.newData);
    },
  }
}
</script>
