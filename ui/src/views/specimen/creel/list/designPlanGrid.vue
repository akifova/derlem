<template>
  <div>
    <h2 class="content-block">{{$t('app.menu.specimen.creel.list')}}</h2>
    <div class="content-block">
      <div class="dx-card responsive-paddings">
        <DxDataGrid
            @initialized="gridInitialized"
            :data-source="designPlanDataSource"
            :columns-auto-width="true"
            :show-borders="true"
        >
          <DxFilterRow :visible="true"/>
          <DxFilterPanel :visible="true"/>s
          <DxHeaderFilter :visible="true"/>
          <DxScrolling mode="standard"/>
          <DxMasterDetail
              :enabled="true"
              template="creelsForDesignPlan"
          />
          <template #creelsForDesignPlan="{ data: creels }">
            <creelsForDesignPlan
                :template-data="creels"
            />
          </template>
          <DxColumn
              data-type="string"
              data-field="title"
              :caption="$t('app.specimen.creel.designPlan')"
          />
          <DxColumn
              data-type="date"
              data-field="deadLine"
              :caption="$t('app.design.plan.deadLine')"
          />
        </DxDataGrid>

      </div>
    </div>
  </div>
</template>
<script>
import {
  DxColumn,
  DxDataGrid,
  DxFilterPanel,
  DxFilterRow,
  DxHeaderFilter,
  DxMasterDetail,
  DxScrolling
} from 'devextreme-vue/data-grid';
import {CustomStoreCreator} from "@/app/common/utils/CustomStoreCreator";
import creelsForDesignPlan from "@/views/specimen/creel/list/creelsForDesignPlan";
export default {
  components: {
    DxMasterDetail,
    DxDataGrid,
    DxColumn,
    DxHeaderFilter,
    DxFilterRow,
    DxFilterPanel,
    DxScrolling,
    creelsForDesignPlan
  },
  data(){
    return{
      gridInstance: null,
      designPlanDataSource: CustomStoreCreator().createStoreFromUrl('/design/plan/actives', '/design/plan/', '/design/plan/', '/design/plan/')
    }
  },
  methods:{
    gridInitialized: function (e) {
      this.gridInstance = e.component
    },
  }
}
</script>
