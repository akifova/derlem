<template>
    <DxDataGrid
        :ref="dataGridRefName"
        :data-source="propertyLineData"
        :show-borders="true"
        :allow-column-resizing="true"
        @toolbar-preparing="onToolbarPreparing($event)"
        @editorPreparing="editorPreparing"
    >
      <DxColumn data-field="paletteLine.rotationNumber" data-type="string" :caption="$t('app.stock-card.palette.rotationNumber')"/>
      <DxColumn data-field="rotationNumber" data-type="string" :caption="$t('app.stock-card.palette.yarnNumber')"/>
      <DxColumn data-field="lattePercentage" data-type="string" :caption="$t('app.stock-card.palette.lattePercentage')"/>
      <DxColumn data-field="color.code" cell-template="colorbox" data-type="string" :caption="$t('app.stock-card.palette.colorCode')"/>
      <template #colorbox="{ data: options }">
        <colorbox
            :cell-data="options.displayValue"
        />
      </template>
      <DxColumn data-field="color.rgbRed" data-type="string" :caption="$t('app.stock-card.palette.rgbRed')"/>
      <DxColumn data-field="color.rgbGreen" data-type="string" :caption="$t('app.stock-card.palette.rgbGreen')"/>
      <DxColumn data-field="color.rgbBlue" data-type="string" :caption="$t('app.stock-card.palette.rgbBlue')"/>
      <template #lineTitle>
        <div class="informer">
          <span style="font-weight: bold">{{ rotationNumber }} {{ $t('app.stock-card.palette.latte') }}</span>
        </div>
      </template>
    </DxDataGrid>
</template>
<script>

import {DxColumn, DxDataGrid,} from 'devextreme-vue/data-grid';
import kazu from "@/app/common/utils/kazu";
import colorbox from "@/app/common/layout/components/colorbox";

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
    colorbox
  },
  data() {
    let {id,rotationNumber} = this.templateData.data;
    return {
      rotationNumber,
      dataGridRefName,
      propertyLineData: this.lineData(id)
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
    lineData: (id) => {
      return kazu.CustomStoreService('/scm/production/definition/stock-card/palette-line-latte/search?sp=paletteLine.id=="'+id+'"','id')
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
