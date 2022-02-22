<template>
<div>
  <div class="left">
  <DxList
      :data-source="dataSource"
  >
    <template #item="{ data }">
      <CustomItem
          :item="data"
      />
    </template>
  </DxList>
  </div>
  <div class="right" v-html="text">

  </div>
</div>
</template>
<script>


import restapi from "@/app/common/utils/restapi";
import DxList from 'devextreme-vue/list';
import CustomItem from "./CustomItem"
import CustomStore from "devextreme/data/custom_store";

export default {
  props: {
    templateData: {
      type: Object,
      default: () => {
      }
    }
  },
  components: {
    DxList,
    CustomItem
  },
  data(){
    let {id} = this.templateData.data;
    return {
      id,
      dataSource:new CustomStore({
        key: 'id',
        load: () => {
          let url ='/design/plan/'+id
          return restapi({
            method: 'GET',
            url
          }).then(data => {
            this.text = data.data.task.text;
            return {
              data: data.data.designPlanToFiles
            };
          });
        },
      }),
      text:'',
    }
  },
  methods:{
  }
};
</script>
<style>
.left {
  float: left;
  width: 20%;
  height: 250px;
  padding: 20px;
  background: rgba(191, 191, 191, 0.15);
  margin-right: 30px;
}
</style>
