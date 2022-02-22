<template>
    <DxDataGrid
        :data-source="yarnPropertyDataSource"
        :show-borders="true"
        :allow-column-resizing="true"
        @toolbar-preparing="onToolbarPreparing($event)"
    >
      <DxMasterDetail
          :enabled="true"
          template="propertyLine"
      />
      <template #propertyLine="{ data }">
        <propertyLine
            :template-data="data"
        />
      </template>
      <DxColumn data-field="yarnPropertyLine.yarnProperty.name" :caption="$t('app.yarn.property-line.attribute')">
      </DxColumn>
      <template #lineTitle>
        <div class="informer">
          <span style="font-weight: bold">{{ name }} {{ $t('app.yarn.property-line.title') }}</span>
        </div>
      </template>
    </DxDataGrid>
</template>
<script>

import {DxColumn, DxDataGrid, DxMasterDetail} from 'devextreme-vue/data-grid';
import {CustomStoreCreator} from "@/app/common/utils/CustomStoreCreator";
import propertyLine from "@/views/yarn/yarnListLinePropertyLine";

export default {
  props: {
    templateData: {
      type: Object,
      default: () => {
      }
    }
  },
  components: {
    DxDataGrid,
    DxColumn,
    propertyLine,
    DxMasterDetail
  },
  data() {
    let {id,name} = this.templateData.data;
    return {
      name,
      yarnPropertyDataSource: CustomStoreCreator().createStore(`/yarn/to-property-line/get-by-yarn-id/${id}`),
    };
  },
  methods: {
    onToolbarPreparing(e) {
      e.toolbarOptions.items.unshift({
        location: 'before',
        template: 'lineTitle'
      });
    },
  },
};
</script>
