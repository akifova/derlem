<template>
  <DxLookup :search-enabled="true"
            :min-search-length="2"
            :data-source="designerLookupDataSource"
            display-expr="nameSurname"
            @value-changed="onLookupChanged"
  />
</template>

<script>
import {reactive, toRefs} from 'vue';
import {DxLookup} from "devextreme-vue";
import {CustomStoreCreator} from "@/app/common/utils/CustomStoreCreator";

export default {
  setup(props, context) {
    //const exports = {};

    let {fieldName} = toRefs(props);
    const designerLookupDataSource = reactive(CustomStoreCreator().createLookupStore('/user/account/get/search', ['name', 'surname']));

    const onLookupChanged = (e) => {
      fieldName = e.value;
    }

    context.emit('fieldName', fieldName)

    return {designerLookupDataSource, fieldName, onLookupChanged};
  },
  components: {
    DxLookup
  }
}
</script>