<template>
    <DxCheckBox :value="checkboxVal" :disabled="true"/>
</template>
<script>
import DxCheckBox from 'devextreme-vue/check-box';

export default {
  components: {
    DxCheckBox
  },
  data(){
    return{
      checkboxVal: this.cellData
    }
  },
  props: {
    cellData: {
      type: Object,
      default: () => {
      }
    }
  },
};
</script>
