<template>
  <DxSelectBox
      :id="id"
      :data-source="dataSource"
      :display-expr="displayExpr"
      :value-expr="valueExpr"
      @Value-changed="$emit('callBack', $event)"
      styling-mode="filled"
      :placeholder="placeHolder"
      :show-clear-button="true"
  />
</template>
<script>
import DxSelectBox from 'devextreme-vue/select-box';
import CustomStore from "devextreme/data/custom_store";
import restapi from "@/app/common/utils/restapi";

export default {
  components: {
    DxSelectBox
  },
  data(){
    const {id,url,placeHolder} = this.items
    return{
      placeHolder,
      id,
      url,
      val: '',
      dataSource: new CustomStore({
        key: 'id',
        load: () => {
          let url = this.url
          return restapi({
            method: 'GET',
            url,
          })
        },
        byKey: function (key) {
          return key
        },
      }),
    }
  },
  props: {
    items: {
      type: Object,
      default: () => {
      }
    }
  },
  methods:{
    displayExpr(item) {
      if (item) {
        if (item.code && item.definition){
          return `${item.code} / ${item.definition}`;
        } else if(item.name) {
          return item.name
        } else {
          return item.value
        }
      } else {
        return null
      }
    },
    valueExpr: (e) => {
      if (e) {
        if (e.id) {
          return {'id': e.id};
        } else {
          return {'id': e.key};
        }
      } else {
        return null
      }
    }
  }
};
</script>
