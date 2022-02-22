<template>
  <DxSelectBox
      v-model:value="currentValue"
      :data-source="selectBoxDataSource"
      :on-value-changed="(e) => onValueChanged(e.value)"
      :on-selection-changed="onSelectionChanged"
      :display-expr="selectBoxDisplayExpression"
  >
  </DxSelectBox>
</template>

<script>
import {DxSelectBox} from "devextreme-vue";
import {CustomStoreCreator} from "@/app/common/utils/CustomStoreCreator";

export default {
  name: "KzSelectBoxFieldEditor",
  props: {
    value: {
      type: Object,
      default: () => {},
    },
    dataSourceUrl: {
      type: String,
      default: () => ''
    },
    dataSourceKey: {
      type: String,
      default: () => 'id'
    },
    storeType: {
      type: String,
      default: () => 'load'
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
    }
  },
  data() {
    return {
      currentValue: this.value,
      selectBoxDataSource: CustomStoreCreator().createLoadStore(this.dataSourceUrl)
    }
  },
  methods: {
    selectBoxDisplayExpression(item) {
      return item ? this.displayFields.map(m => item[m]).join("- ") : '';
    },
    onSelectionChanged() {
      this.dataGridComponent.updateDimensions();
    }
  },
  components: {
    DxSelectBox
  }
}
</script>