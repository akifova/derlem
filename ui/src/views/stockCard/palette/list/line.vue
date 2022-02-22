<template>
    <DxDataGrid
        :ref="dataGridRefName"
        :data-source="propertyLineData"
        :show-borders="true"
        :allow-column-resizing="true"
        @toolbar-preparing="onToolbarPreparing($event)"
        @editorPreparing="editorPreparing"
    >
      <DxMasterDetail
          :enabled="true"
          template="latteline"
      />
      <template #latteline="{ data: employee }">
        <latteline
            :template-data="employee"
        />
      </template>
      <DxColumn data-field="rotationNumber" data-type="string" :caption="$t('app.stock-card.palette.rotationNumber')"/>
      <template #lineTitle>
        <div class="informer">
          <span style="font-weight: bold">{{ definition }} {{ $t('app.stock-card.palette.line') }}</span>
        </div>
      </template>
      <DxColumn data-field="color.code" cell-template="colorbox" data-type="string" :caption="$t('app.stock-card.palette.colorCode')"/>
      <template #colorbox="{ data: options }">
        <colorbox
            :cell-data="options"
        />
      </template>
      <DxColumn data-field="color.rgbRed" data-type="string" :caption="$t('app.stock-card.palette.rgbRed')"/>
      <DxColumn data-field="color.rgbGreen" data-type="string" :caption="$t('app.stock-card.palette.rgbGreen')"/>
      <DxColumn data-field="color.rgbBlue" data-type="string" :caption="$t('app.stock-card.palette.rgbBlue')"/>
    </DxDataGrid>
</template>
<script>

import {DxColumn, DxDataGrid, DxMasterDetail} from 'devextreme-vue/data-grid';
import kazu from "@/app/common/utils/kazu";
import latteline from "@/views/stockCard/palette/list/latteline";
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
    DxMasterDetail,
    latteline,
    colorbox
  },
  data() {
    let {id,definition} = this.templateData.data;
    return {
      definition,
      dataGridRefName,
      propertyLineData: this.lineData(id),
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
      return kazu.CustomStoreService(`/scm/production/definition/stock-card/palette-line/find-by-palette-id/${id}`,'id')
    },
  },
  computed: {
    dataGrid: function () {
      return this.$refs[dataGridRefName].instance;
    }
  },
};
</script>
