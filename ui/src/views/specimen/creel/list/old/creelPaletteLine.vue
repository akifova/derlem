<template>
  <DxDataGrid
      :data-source="dataSource"
      :columns-auto-width="true"
      :show-borders="true"
  >
    <DxFilterRow :visible="true"/>
    <DxFilterPanel :visible="true"/>
    <DxHeaderFilter :visible="true"/>
    <DxScrolling mode="infinite"/>
    <DxColumn
        data-type="string"
        data-field="lattePercentage"
        :caption="$t('app.specimen.creel.lattePercentage')"
    />
    <DxColumn
        data-type="string"
        data-field="rotationNumber"
        :caption="$t('app.specimen.creel.rotationNumber')"
    />
    <DxColumn
        data-type="string"
        data-field="rotationNumber"
        :caption="$t('app.stock-card.palette.code')"
   />
  </DxDataGrid>
</template>
<script>

import {DxColumn, DxDataGrid, DxFilterPanel, DxFilterRow, DxHeaderFilter, DxScrolling,} from 'devextreme-vue/data-grid';
import kazu from "@/app/common/utils/kazu";

export default {
  components: {
    DxDataGrid,
    DxColumn,
    DxHeaderFilter,
    DxFilterRow,
    DxFilterPanel,
    DxScrolling,
  },
  props: {
    templateData: {
      type: Object,
      default: () => {}
    }
  },
  data() {
    const { id } = this.templateData;
    return {
      id,
      name,
      dataSource:this.templateData.data.creelPaletteLineLattes
    };
  },
  methods:{
    lineData: (id) => {
      kazu.RestService('/specimen/creel/'+id)
    },
  },
};
</script>
