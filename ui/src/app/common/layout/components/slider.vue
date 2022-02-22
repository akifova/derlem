<template>
  <DxSlider
      :id="name"
      :min="min"
      :max="max"
      :value="0"
      :step="step"
      :tooltip="{
        enabled: true,
        showMode: 'always',
        position: 'bottom',
      }"
      @ValueChanged="$emit('callBack', $event)"
      styling-mode="filled"
  />
</template>
<script>
import {DxSlider} from 'devextreme-vue/slider';

export default {
  components: {
    DxSlider
  },
  data(){
    const {title, step, min, max} = this.items
    return{
      name: title,
      step: step,
      min: min,
      max: max
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