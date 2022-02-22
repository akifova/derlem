<template>
  <DxDataGrid
      :data-source="propertyLineDataSource"
      :show-borders="true"
      :allow-column-resizing="true"
      :column-min-width="50"
      @init-new-row="initNewRow"
      @row-updating="dataGridRowUpdate"
      @toolbar-preparing="onToolbarPreparing($event)"
      @initialized="lineGridInitialized"

  >
    <DxEditing
        :allow-updating="true"
        :allow-deleting="true"
        :allow-adding="true"
        mode="popup"
    />
    <DxColumn data-field="attribute" :caption="$t('app.yarn.property-line.attribute')"/>
    <DxColumn data-field="code" :caption="$t('app.yarn.property-line.code')"/>
    <template #lineTitle>
      <div class="informer">
        <span style="font-weight: bold">{{ name }} {{ $t('app.yarn.property-line.title') }}</span>
      </div>
    </template>
  </DxDataGrid>
</template>
<script>

import {DxColumn, DxDataGrid, DxEditing} from 'devextreme-vue/data-grid';
import {YarnPropertyLineDao} from "@/views/yarn/property/persistence/YarnPropertyLineDao";

const _ = require('lodash');

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
    DxEditing
  },
  data() {
    let {id, name, yarnPropertyType} = this.templateData.data;
    return {
      lineGridInstance: null,
      propertyLineDataSource: YarnPropertyLineDao().yarnPropertyLineDataSource(id),
      id,
      name,
      formData: {
        attribute: null,
        code: null,
        yarnProperty: {
          id,
          yarnPropertyType
        }
      },
    };
  },
  methods: {
    initNewRow: function (e) {
      e.data.yarnProperty = {"id": this.id}
    },
    lineGridInitialized: function (e) {
      this.lineGridInstance = e.component
    },
    onToolbarPreparing(e) {
      e.toolbarOptions.items.unshift({
        location: 'before',
        template: 'lineTitle'
      });
    },
    dataGridRowUpdate(data) {
      data.newData = _.merge(data.oldData, data.newData,{yarnProperty: {id: this.id}});
    }
  }
};
</script>
<style>
#data-grid-demo {
  min-height: 700px;
}
</style>
