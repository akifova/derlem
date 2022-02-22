<!--
Kullanım Klavuzu

Column Tanımı:
<DxColumn
              ....
              edit-cell-template="anyLookupTemplate"
          />

Template:
<template #anyLookupTemplate="{ data: cellInfo }">
            <KzLookupFieldEditor
                :value="cellInfo.value"
                :on-value-changed="(value) => onValueChanged(value, cellInfo)"
                data-source-url="/yarn/group/search"
                :search-fields="['f1','f2']"
                :display-fields="['f3','f2']"
                :data-grid-component="cellInfo.component"
            />
          </template>

import:
import KzLookupFieldEditor from "@/app/components/dx/dataGrid/KzLookupFieldEditor";

methods:
onValueChanged(value, cellInfo) {
      cellInfo.setValue(value);
      cellInfo.component.updateDimensions();
    },
-->

<template>
  <DxLookup
      :min-search-length="minSearchLength"
      :search-enabled="searchEnabled"
      v-model:value="currentValue"
      :data-source="lookUpDataSource"
      :on-value-changed="(e) => onValueChanged(e.value)"
      :on-selection-changed="onSelectionChanged"
      :display-expr="lookupDisplayExpression"
  >
  </DxLookup>
</template>

<script>
import {DxLookup} from "devextreme-vue";
import {CustomStoreCreator} from "@/app/common/utils/CustomStoreCreator";

export default {
  name: "KzLookupFieldEditor",
  props: {
    value: {
      type: Object,
      default: () => {},
    },
    dataSourceUrl: {
      type: String,
      default: () => ''
    },
    searchFields: {
      type: Array,
      default: () => ['name']
    },
    dataSourceKey: {
      type: String,
      default: () => 'id'
    },
    displayFields: {
      type: Array,
      default: () => ['name']
    },
    onValueChanged: {
      type: Function,
      default: () => function() {},
    },
    dataGridComponent: {
      type: Object,
      default: () => {},
    },
    minSearchLength: {
      type: Number,
      default: () => 2
    },
    searchEnabled: {
      type: Boolean,
      default: () => true
    }
  },
  data() {
    return {
      currentValue: this.value,
      lookUpDataSource: CustomStoreCreator().createLookupStore(this.dataSourceUrl, this.searchFields, this.dataSourceKey)
    }
  },
  methods: {
    lookupDisplayExpression(item) {
      let returnData = '';
      if(item) {
        returnData = this.displayFields.map(m => {
          return m.split('.').reduce(function (prev, curr) {
            return prev ? prev[curr] : null
          }, item || self)
        }).join("- ");
      }
      return returnData;
    },
    onSelectionChanged() {
      this.dataGridComponent.updateDimensions();
    }
  },
  components: {
    DxLookup
  }
}
</script>