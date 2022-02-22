<template>
  <div>
    <h2 class="content-block">{{$t('app.menu.specimen.creel.list')}}</h2>
    <div class="content-block">
      <div class="dx-card responsive-paddings">
        <DxDataGrid
            :ref="dataGridRefName"
            id="gridContainer"
            :data-source="dataSource"
            :columns-auto-width="true"
            :show-borders="true"
            @cell-dbl-click="rowDoubleClick"
            @row-updating="onCreelListUpdating"
            :selection="{ mode: 'single' }"
        >
          <DxEditing
            :allow-updating="true"
            :allow-deleting="true"
            mode="popup"
          />
          <DxFilterRow :visible="true"/>
          <DxFilterPanel :visible="true"/>
          <DxHeaderFilter :visible="true"/>
          <DxScrolling mode="infinite"/>
          <DxGroupPanel :visible="true"/>
          <DxColumn
              data-type="string"
              data-field="sampleName"
              :caption="$t('app.specimen.creel.sampleName')"
          >
          </DxColumn>
          <DxColumn
              data-type="string"
              data-field="paletteCode"
              :caption="$t('app.specimen.creel.paletteCode')"
          >
          </DxColumn>
          <DxColumn
              data-type="string"
              data-field="qualityName"
              :caption="$t('app.specimen.creel.qualityName')"
          >
          </DxColumn>
          <DxMasterDetail
              :enabled="true"
              template="creelGridDetail"
          />
          <template #creelQualityAndPalette="{ data: creel }">
            <creelGridDetail
                :template-data="creel"
            />
          </template>
        </DxDataGrid>
      </div>
    </div>
    <div class="content-block">
      <div class="dx-card responsive-paddings">
          <DxForm
              :read-only="true"
              :show-colon-after-label="true"
              :show-validation-summary="true"
              validation-group="customerData"
          >
            <DxGroupItem :col-count="4" :caption="$t('app.specimen.creel.detail')">
              <DxGroupItem :col-span="1">
                <DxSimpleItem :label="{visible:false}">
                  <template #default>
                    <DxTabPanel
                        height="50%"
                        :data-source="creelQuality"
                        :selected-index="0"
                        :loop="false"
                        :animation-enabled="false"
                        :swipe-enabled="false"
                    >
                      <template #title="{ data: line }">
                        <span>{{ line.activeVersion }}</span>
                      </template>
                      <template #item="{ data: line }">
                        <div class="tabpanel-item">
                          <div style="width: 100%;">
                            <p>
                              <b>Atkı: </b>
                              (<span>{{ line.creelQuality.woof }}</span>)
                            </p>
                            <p>
                              <b>Tarak: </b>
                              (<span>{{line.creelQuality.crest}}</span>)
                            </p>
                            <p>
                              <b>Lanset: </b>
                              (<span>{{line.creelQuality.lancet}}</span>)
                            </p>
                            <p>
                              <b>Örgü Yapısı: </b>
                              (<span>{{line.creelQuality.knittingType}}</span>)
                            </p>
                            <p>
                              <b>Atkı İpi (Alt): </b>
                              (<span>{{ line.creelQuality.bottomWoofYarn?.name }}</span>)
                            </p>
                            <p>
                              <b>Atkı İpi (Orta): </b>
                              (<span>{{line.creelQuality.middleWoofYarn?.name}}</span>)
                            </p>
                            <p>
                              <b>Atkı İpi (Üst): </b>
                              (<span>{{line.creelQuality.topWoofYarn?.name}}</span>)
                            </p>
                          </div>
                        </div>
                      </template>
                    </DxTabPanel>
                  </template>
                </DxSimpleItem>
              </DxGroupItem>
              <DxGroupItem :col-span="3">
                <DxSimpleItem :label="{visible:false}">
                  <template #default>
                    <DxTabPanel
                        height="50%"
                        :data-source="creelQuality"
                        :selected-index="0"
                        :loop="false"
                        :animation-enabled="false"
                        :swipe-enabled="false"
                    >
                      <template #title="{ data: line }">
                        <span>{{ line.activeVersion }}</span>
                      </template>
                      <template #item="{ }">
                        <DxDataGrid
                            :ref="dataGridRefName"
                            :data-source="creelPalette.creelPaletteLines"
                            :columns-auto-width="true"
                            :show-borders="true"
                        >
                          <DxFilterRow :visible="true"/>
                          <DxFilterPanel :visible="true"/>
                          <DxHeaderFilter :visible="true"/>
                          <DxScrolling mode="standard"/>
                          <DxMasterDetail
                              :enabled="true"
                              template="creelPaletteLine"
                          />
                          <template #creelPaletteLine="{ data: employee }">
                            <creelPaletteLine
                                :template-data="employee"
                            />
                          </template>
                          <DxColumn
                              data-type="string"
                              data-field="orderNo"
                              :caption="$t('app.stock-card.palette.code')"
                          >
                          </DxColumn>
                        </DxDataGrid>
                      </template>
                    </DxTabPanel>
                  </template>
                </DxSimpleItem>
              </DxGroupItem>
            </DxGroupItem>
          </DxForm>
      </div>
    </div>
  </div>
</template>
<script>
import DxTabPanel from 'devextreme-vue/tab-panel';
import {DxForm, DxGroupItem, DxSimpleItem,} from 'devextreme-vue/form';
import kazu from "@/app/common/utils/kazu";
import {
  DxColumn,
  DxDataGrid,
  DxEditing,
  DxFilterPanel,
  DxFilterRow,
  DxGroupPanel,
  DxHeaderFilter,
  DxMasterDetail,
  DxScrolling
} from 'devextreme-vue/data-grid';
import {confirm} from "devextreme/ui/dialog";
import i18n from "@/app/common/utils/i18n";
import creelPaletteLine from "@/views/specimen/creel/list/creelPaletteLine";
import _ from "lodash";
import {CustomStoreCreator} from "@/app/common/utils/CustomStoreCreator";

const dataGridRefName = "dataGridRefName";
export default {
  components: {
    DxMasterDetail,
    DxDataGrid,
    DxColumn,
    DxHeaderFilter,
    DxFilterRow,
    DxFilterPanel,
    DxScrolling,
    DxGroupPanel,
    DxForm,
    DxSimpleItem,
    DxGroupItem,
    DxTabPanel,
    creelPaletteLine,
    DxEditing
  },
  data() {
    return {
      formData: {
      },
      creelQuality: {},
      creelPalette: {},
      id:'',
      dataGridRefName,
      dataSource:CustomStoreCreator().createStore('/specimen/creel/'),
      showButton: false,
      caption: i18n.global.t('app.menu.yarn.group.add'),
    }
  },
  computed: {
    dataGrid: function () {
      return this.$refs[dataGridRefName].instance;
    }
  },
  methods: {
    onCreelListUpdating: function (data) {
      data.newData = _.extend({}, data.oldData, data.newData);
    },
    rowDoubleClick(e){
      this.id = e.key
      kazu.RestService('/specimen/creel/'+this.id).then((res) => {
        this.creelQuality = res.data.creelQuality
        this.creelPalette = res.data.creelPalette.creelPaletteLines
        console.log(this.creelQuality)
        console.log(this.creelPalette)
      })
    },
    deleteBtnClick(e){
      let result = confirm(this.$t('common.delete-confirm'), this.$t('common.confirm'));
      result.then((dialogResult) => {
        if (dialogResult){
          kazu.RestService('/specimen/creel/' + e.row.key,'','DELETE').then(() => {
            this.formData = {}
            this.showButton = false
            this.dataGrid.refresh()
          })
        }
      });
    }
  }
}
</script>
<style lang="scss">

.dx-button{
  width: 100%;
}
.dx-field-button-item{
  padding: 0px;
}
</style>
