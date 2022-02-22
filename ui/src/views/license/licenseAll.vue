<template>
  <div>
    <h2 class="content-block">{{$t('app.menu.license.all')}}</h2>
    <div class="content-block">
      <div class="dx-card responsive-paddings">
        <DxDataGrid
            :ref="dataGridRefName"
            :data-source="licenseAll"
            :show-borders="true"
            :allow-column-resizing="true"
        >
          <DxFilterRow :visible="true"/>
          <DxFilterPanel :visible="true"/>
          <DxHeaderFilter :visible="true"/>
          <DxScrolling mode="infinite"/>
          <DxGroupPanel :visible="true"/>
          <DxColumn data-field="endDate" data-type="date" :caption="$t('app.menu.license.endDate')" />
          <DxColumn data-field="macAddress" data-type="string" :caption="$t('app.menu.license.mac-address')" />
          <DxColumn data-field="processorId" data-type="string" :caption="$t('app.menu.license.processorId')" />
          <DxColumn data-field="baseBoardSerialNumber" data-type="string" :caption="$t('app.menu.license.baseBoardSerialNumber')" />
          <DxColumn data-field="active" data-type="boolean" :caption="$t('app.menu.license.active')" />
        </DxDataGrid>
      </div>
    </div>
  </div>
</template>
<script>
import {licenseAll} from "@/app/common/utils/data";
import {DxColumn, DxDataGrid,DxFilterRow,DxFilterPanel,DxHeaderFilter,DxScrolling,DxGroupPanel} from 'devextreme-vue/data-grid';
const dataGridRefName = "dataGridRefName"
export default {
  components: {
    DxDataGrid,
    DxColumn,
    DxFilterRow,DxFilterPanel,DxHeaderFilter,DxScrolling,DxGroupPanel
  },
  data() {
    return {
      dataGridRefName,
      licenseAll
    };
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
