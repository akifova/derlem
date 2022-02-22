<template>
    <DxDataGrid
        :data-source="yarnPropertyLineDataSource"
        :show-borders="true"
        :allow-column-resizing="true"
    >
      <DxColumn data-field="attribute" :caption="$t('app.yarn.property-line.attribute')">
      </DxColumn>
    </DxDataGrid>
</template>
<script>

import {DxColumn, DxDataGrid} from 'devextreme-vue/data-grid';

const dataGridRefName = "dataGridRefName";

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
  },
  data() {
    let {yarnPropertyLine} = this.templateData.data;
    console.log(yarnPropertyLine)
    return {
      name,
      dataGridRefName,
      yarnPropertyLineDataSource:[yarnPropertyLine]
    };
  },
};
</script>
