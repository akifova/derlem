<template>
    <DxTextBox
        :id="id"
        :placeholder="placeHolder"
        @ValueChanged="$emit('callBack', $event)"
        styling-mode="filled"
        :show-clear-button="true"
    />
</template>
<script>
import DxTextBox from 'devextreme-vue/text-box';

export default {
  components: {
    DxTextBox
  },
  data(){
    const {placeHolder,id} = this.items
    return{
      placeHolder,
      id
    }
  },
  props: {
    items: {
      type: Object,
      default: () => {
      }
    }
  },
};
</script>