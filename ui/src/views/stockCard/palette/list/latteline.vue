<template>
    <DxDataGrid
        :ref="dataGridRefName"
        :data-source="propertyLineData"
        :show-borders="true"
        :allow-column-resizing="true"
        @toolbar-preparing="onToolbarPreparing($event)"
        @editorPreparing="editorPreparing"
    >
      <DxColumn data-field="lattePercentage" data-type="string" :caption="$t('app.stock-card.palette.lattePercentage')"/>
      <DxColumn data-field="yarn.name" data-type="string" :caption="$t('app.stock-card.palette.yarn')"/>

      <template #lineTitle>
        <div class="informer">
          <span style="font-weight: bold">{{ rotationNumber }} {{ $t('app.stock-card.palette.latte') }}</span>
        </div>
      </template>
    </DxDataGrid>
</template>
<script>

import {DxColumn, DxDataGrid,} from 'devextreme-vue/data-grid';

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
    let {yarn,rotationNumber,lineToYarns} = this.templateData.data;
    console.log(this.templateData.data)
    return {
      yarn,
      rotationNumber,
      dataGridRefName,
      propertyLineData:lineToYarns,
      yarns: []
    };
  },

  methods: {
    editorPreparing(e) {
      e.editorOptions.value = e.row.cells[0].displayValue
    },
    getDisplayExpr(item) {
      return item.name
    },
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
