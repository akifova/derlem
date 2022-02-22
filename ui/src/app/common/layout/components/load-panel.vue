<template>
  <DxLoadPanel
      :position="{'my': 'center', 'at': 'center', 'of': 'window'}"
      v-model:visible="store.getters._getIsLoadPanelVisible"
      shading-color="rgba(0,0,0,0.4)"
  />
</template>

<script>
import {DxLoadPanel} from "devextreme-vue/load-panel";
import store from "@/store";
export default {
  components: {
    DxLoadPanel
  },
  data(){
    return{
      store
    }
  }
}
</script>
