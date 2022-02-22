<template>
    <DxNumberBox :id="id" :placeholder="placeHolder" @ValueChanged="$emit('callBack', $event)" styling-mode="filled" />
</template>
<script>
import {DxNumberBox} from 'devextreme-vue/number-box';

export default {
  components: {
    DxNumberBox
  },
  data(){
    const {id,placeHolder} = this.items
    return{
      id,
      placeHolder
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