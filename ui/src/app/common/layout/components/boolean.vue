<template>
  <DxSwitch :id="id" :value="false" @Value-changed="$emit('callBack', $event)"/>
</template>
<script>
import {DxSwitch} from 'devextreme-vue/switch';

export default {
  components: {
    DxSwitch
  },
  data(){
    const {id} = this.items
    return{
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