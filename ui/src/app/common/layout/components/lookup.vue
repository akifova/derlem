<template>
  <DxLookup
      :id="id"
      v-model:value="val"
      :data-source="dataSource"
      styling-mode="filled"
      :display-expr="getDisplayExpr"
      :placeholder="placeHolder"
      :min-search-length="2"
      :search-enabled=true
      @item-click="$emit('callBack', $event)"
      :show-clear-button="true"
      :show-cancel-button="true"
  >
  </DxLookup>
</template>
<script>
import {DxLookup} from 'devextreme-vue/lookup';
import CustomStore from "devextreme/data/custom_store";
import restapi from "@/app/common/utils/restapi";

export default {
  components: {
    DxLookup
  },
  data(){
    const {id,url,searchFields,placeHolder} = this.items
    return{
      searchFields,
      id,
      placeHolder,
      url,
      val: '',
      dataSource: new CustomStore({
        key: 'id',
        load: (loadOptions) => {
          let sp = '';

          for (let i = 0; i < searchFields.length; i++) {
            if(i===0) {
              sp = searchFields[i] + '==*' + loadOptions.searchValue + '*';
            } else {
              sp = sp + ',' + searchFields[i] + '==*' + loadOptions.searchValue + '*';
            }
          }

          let u = this.url
          return restapi({
            method: 'GET',
            url:u+'?sp=' + sp,
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
    getDisplayExpr(item) {
      if (item.code){
        if(item.definition) {
          return typeof item === "object" ? `${item.code} / ${item.definition}` : item;
        } else {
          return typeof item === "object" ? `${item.code}` : item;
        }

      } else if (item.name) {
        return typeof item === "object" ? `${item.name}` : item;
      } else if (item.title) {
        return typeof item === "object" ? `${item.title}` : item;
      }
    },

  }
};
</script>
