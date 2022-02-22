<template>
    <DxDataGrid
        :ref="dataGridRefName"
        :data-source="yarnGroupLine"
        :show-borders="true"
        :allow-column-resizing="true"
        @toolbar-preparing="onToolbarPreparing($event)"

    >
      <DxColumn data-field="yarnProperty.name" :caption="$t('app.yarn.property-line.attribute')">
      </DxColumn>
      <template #lineTitle>
        <div class="informer">
          <span style="font-weight: bold">{{ name }} {{ $t('app.yarn.property-line.title') }}</span>
        </div>
      </template>
    </DxDataGrid>
</template>
<script>

import {DxColumn, DxDataGrid} from 'devextreme-vue/data-grid';
import {YarnGroupLineDao} from "@/views/yarn/group/persistence/YarnGroupLineDao";

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
    let {id,name} = this.templateData.data;
    return {
      name,
      dataGridRefName,
      yarnGroupLine: YarnGroupLineDao().yarnGroupLineDataSource(id)
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
  computed: {
    dataGrid: function () {
      return this.$refs[dataGridRefName].instance;
    }
  },
};
</script>
<style>
#data-grid-demo {
  min-height: 700px;
}
</style>
