<template>
  <div class="box" :style="`background-color: ${convert(displayValue)}`"></div>
</template>
<script>

export default {
  components: {
  },
  data(){
    const {displayValue} = this.cellData;
    return{
      displayValue
    }
  },
  props: {
    cellData: {
      type: Object,
      default: () => {
      }
    }
  },
  methods:{
    convert(val){
      return `rgb(${val.replace(/[.]/gi,',')});`
    }
  }
};
</script>
<style>
.box{
  width: 20px;
  height: 20px;
  margin-top: -8.5px;
  border: 1px solid #e0e0e0;
}
</style>
