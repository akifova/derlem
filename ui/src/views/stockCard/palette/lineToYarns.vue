<template>
  <DxDataGrid
      :data-source="paletteLines"
      :show-borders="true"
      :allow-column-resizing="true"
      @saving="addLineToYarn($event)"
      @row-inserting="onLineToYarnInserting"
      key="yarn.id"
  >
    <DxEditing
        :allow-adding="true"
        mode="popup"
    >
      <DxPopup
          :show-title="true"
          :width="500"
          :height="325"
          title="İplik Tanımla"
      />
    </DxEditing>
    <DxColumn data-field="lattePercentage" data-type="string" styling-mode="filled"
              :caption="$t('app.stock-card.palette.lattePercentage')">
      <DxNumericRule message="Numeric Rule" />
      <DxRequiredRule message="Required Rule" />
    </DxColumn>
    <DxColumn
        :width="125"
        data-field="yarn"
        :caption="$t('app.menu.yarn.text')"
        :calculate-display-value="calculateDisplayYarnName"
    >
      <DxLookup
          :data-source="yarnDataSource"
          :display-expr="displayExpr"
      />
      <DxRequiredRule message="Yarn Required Rule" />
    </DxColumn>
  </DxDataGrid>
</template>
<script>

import {
  DxColumn,
  DxDataGrid,
  DxEditing,
  DxLookup,
  DxNumericRule,
  DxPopup,
  DxRequiredRule,
} from 'devextreme-vue/data-grid';
import {NotificationService} from "@/app/common/utils/NotificationService";
import {CustomStoreCreator} from "@/app/common/utils/CustomStoreCreator";

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
    DxEditing,
    DxPopup,
    DxLookup,
    DxRequiredRule,
    DxNumericRule,
  },
  data() {
    let {lineToYarns} = this.templateData.data
    return {
      lineToYarns,
      gridInstance: null,
      yarnDataSource: CustomStoreCreator().createStore("/yarn/"),
      paletteLines: lineToYarns,
    };
  },
  methods: {
    onLineToYarnInserting(data){
      const gridInstance = data.component;
      const rowsData = gridInstance.getDataSource().items();
      let finder = rowsData.filter(f=> f.yarn.id == data.data.yarn.id);

      if(finder.length >0) {
        data.cancel = true;
        NotificationService().showToaster({"message": "Bir Palet Satırına Aynı İplik 2 defa Girilemez.", "type": "alert"})
      }
    },
    calculateDisplayYarnName(data){
      return data.yarn?.name
    },
    addLineToYarn(e) {
      delete e.changes[0].data.__KEY__

    },
    displayExpr: (e) => {
      if (e) {
        return e.name;
      } else {
        return null
      }
    },
    valueExpr(e) {
      return e && 'id:' + e.id;
    },
  }
};
</script>
