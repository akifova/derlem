<template>
  <div>
    <div>
    </div>
    <h2 class="content-block">Dashboard</h2>
    <div class="content-block">
      <div class="dx-card responsive-paddings">
        <DxBox
            direction="row"
            width="90%"
        >
          <DxItem :ratio="1">
            <template #default>
              <div class="rect demo-dark">
                <DxPieChart
                    class="pieChart"
                    :data-source="countByStatusDataSource"
                    palette="Bright"
                    title="Durum"
                >
                  <DxSize height="400"/>
                  <DxSeries
                      argument-field="status"
                      value-field="count"
                  >
                    <DxLabel :visible="true">
                      <DxConnector
                          :visible="true"
                          :width="1"
                      />
                    </DxLabel>
                  </DxSeries>
                  <DxLegend
                      :column-count="2"
                      orientation="horizontal"
                      item-text-position="right"
                      horizontal-alignment="center"
                      vertical-alignment="bottom"
                      :customize-text="formatStatusLabel"
                  />
                </DxPieChart>
              </div>
            </template>
          </DxItem>
          <DxItem :ratio="1">
            <template #default>
              <div class="rect demo-light">
                <DxPieChart
                    class="pieChart"
                    :data-source="countByStarRatingDataSource"
                    palette="Bright"
                    title="Beğenilme"
                >
                  <DxSize height="400"/>
                  <DxSeries
                      argument-field="starRating"
                      value-field="count"
                  >
                    <DxLabel :visible="true">
                      <DxConnector
                          :visible="true"
                          :width="1"
                      />
                    </DxLabel>
                  </DxSeries>
                  <DxLegend
                      :column-count="2"
                      orientation="horizontal"
                      item-text-position="right"
                      horizontal-alignment="center"
                      vertical-alignment="bottom"
                  />
                </DxPieChart>
              </div>
            </template>
          </DxItem>
          <DxItem :ratio="1">
            <template #default>
              <div class="rect demo-dark">
                <DxPieChart
                    class="pieChart"
                    :data-source="countByDifficultyDataSource"
                    palette="Bright"
                    title="Zorluk"
                >
                  <DxSize height="400"/>
                  <DxSeries
                      argument-field="difficulty"
                      value-field="count"
                  >
                    <DxLabel :visible="true">
                      <DxConnector
                          :visible="true"
                          :width="1"
                      />
                    </DxLabel>
                  </DxSeries>
                  <DxLegend
                      :column-count="2"
                      orientation="horizontal"
                      item-text-position="right"
                      horizontal-alignment="center"
                      vertical-alignment="bottom"
                  />
                </DxPieChart>
              </div>
            </template>
          </DxItem>
        </DxBox>
        <br>
        <DxChart
            id="chart"
            :data-source="countByDesignerDataSource"
        >
          <DxSeries
              argument-field="designer"
              value-field="count"
              type="bar"
              color="#ffaa66"
              name="Desinatörler"
          />
          <DxSize height="400" width="auto"/>
          <DxMargin :top="100" :right="50"/>
        </DxChart>
      </div>
    </div>
  </div>
</template>
<script>
import {DxBox, DxItem} from 'devextreme-vue/box';
import {DxChart, DxLegend, DxMargin, DxSize} from 'devextreme-vue/chart';

import DxPieChart, {DxConnector, DxLabel, DxSeries} from 'devextreme-vue/pie-chart';

import {CustomStoreCreator} from "@/app/common/utils/CustomStoreCreator";
import i18n from "@/app/common/utils/i18n";

export default {
  components: {
    DxPieChart,
    DxLabel,
    DxConnector,
    DxBox, DxItem,
    DxChart, DxSeries,
    DxLegend, DxSize,
    DxMargin
  },
  data() {
    return {
      countByStatusDataSource: CustomStoreCreator().createLoadStore("/dashboard/archive/count/by-status", "status"),
      countByStarRatingDataSource: CustomStoreCreator().createLoadStore("/dashboard/archive/count/by-star-rating","starRating"),
      countByDifficultyDataSource: CustomStoreCreator().createLoadStore("/dashboard/archive/count/by-difficulty","difficulty"),
      countByDesignerDataSource: CustomStoreCreator().createLoadStore("/dashboard/archive/count/by-designer","designer")
    };
  },
  methods: {
    formatStatusLabel(pointInfo) {
      return i18n.global.t('app.archive.design.status.' + pointInfo.pointName);
    }
  }
};
</script>
<style lang="scss">
.pieChart {
  width: 85%;
  height: 70%;
}
.logos-container {
  margin: 20px 0 40px 0;
  text-align: center;
  svg {
    display: inline-block;
  }
}

.devextreme-logo {
  width: 200px;
  height: 34px;
  margin-bottom: 17px;
}

.vue-logo {
  width: 180px;
  height: 62px;
}

.plus {
  margin: 20px 10px;
  width: 22px;
  height: 22px;
}

.screen-x-small .logos-container {
  svg {
    width: 100%;
    display: block;
    &.plus {
      margin: 0;
    }
  }
}
</style>
