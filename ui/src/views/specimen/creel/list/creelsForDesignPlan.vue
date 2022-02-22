<template>
  <DxDataGrid
      :data-source="creels"
      :show-borders="true"
      :allow-column-resizing="true"
      @row-expanding="onRowExpanding"
  >
    <DxColumn data-field="sampleName" data-type="string" styling-mode="filled" :caption="$t('app.specimen.creel.sampleName')"/>
    <DxColumn data-field="activeVersion" data-type="string" styling-mode="filled" :caption="$t('app.specimen.creel.activeVersion')"/>
    <DxColumn data-field="qualityName" data-type="string" styling-mode="filled" :caption="$t('app.specimen.creel.qualityName')"/>
    <DxColumn data-field="paletteCode" data-type="string" styling-mode="filled" :caption="$t('app.specimen.creel.paletteCode')"/>
    <DxColumn data-field="washing" data-type="boolean" styling-mode="filled" :caption="$t('app.specimen.creel.washing')"/>
    <DxColumn data-field="raising" data-type="boolean" styling-mode="filled" :caption="$t('app.specimen.creel.raising')"/>
  </DxDataGrid>
</template>
<script>

import {
  DxColumn,
  DxDataGrid,
} from 'devextreme-vue/data-grid';
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
    let {creels} = this.templateData.data
    return {
      creels,
    };
  },
};
</script>
