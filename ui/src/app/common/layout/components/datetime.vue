<template>
  <div>
    {{deadLine}}
  </div>
</template>
<script>

import kazu from "@/app/common/utils/kazu";

export default {
  components: {

  },
  data(){
    return{
      deadLine: kazu.timestampToDate(this.cellData.deadLine)
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