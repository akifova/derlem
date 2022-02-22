<template>
  <div>
    <h2 class="content-block">{{ $t('app.menu.specimen.creel.text') }}</h2>
    <div class="content-block">
      <div class="dx-card responsive-paddings">
        <DxForm
            :form-data="formData"
            :read-only="false"
            :show-colon-after-label="true"
            :show-validation-summary="true"
            validation-group="creelPaletteValidationGroup"
            @initialized="formInitialized"
        >
          <DxGroupItem>
            <DxGroupItem :col-count="5">
              <DxSimpleItem :col-span="2" data-field="designPlan" :label="{text: $t('app.specimen.creel.designPlan')}" editor-type="dxSelectBox"
                            :editor-options="{dataSource:designPlan, displayExpr:'title', valueExpr:(e) => (e ? {'id':e.id,'name':e.name}: null)}">
                <DxRequiredRule :message="$t('validation.specimen.creel.required-designPlan')"></DxRequiredRule>
              </DxSimpleItem>
              <DxSimpleItem data-field="paletteCode" :label="{text: $t('app.specimen.creel.paletteCode')}">
                <DxRequiredRule :message="$t('validation.specimen.creel.required-paletteCode')"></DxRequiredRule>
              </DxSimpleItem>
              <DxSimpleItem data-field="qualityName" :label="{text: $t('app.specimen.creel.qualityName')}">
                <DxRequiredRule :message="$t('validation.specimen.creel.required-qualityName')"></DxRequiredRule>
              </DxSimpleItem>
              <DxSimpleItem data-field="sampleName" :label="{text: $t('app.specimen.creel.sampleName')}">
                <DxRequiredRule :message="$t('validation.specimen.creel.required-sampleName')"></DxRequiredRule>
              </DxSimpleItem>
              <DxSimpleItem data-field="raising" :label="{text: $t('app.specimen.creel.raising')}" editor-type="dxSelectBox"
                            :editor-options="{dataSource:raising, valueExpr:'id', displayExpr:'name'}">
                <DxRequiredRule :message="$t('validation.specimen.creel.required-raising')"></DxRequiredRule>
              </DxSimpleItem>
              <DxSimpleItem data-field="washing" :label="{text: $t('app.specimen.creel.washing')}" editor-type="dxSelectBox"
                            :editor-options="{ dataSource:washing, valueExpr:'id', displayExpr:'name' }">
                <DxRequiredRule :message="$t('validation.specimen.creel.required-washing')"></DxRequiredRule>
              </DxSimpleItem>
            </DxGroupItem>
            <DxGroupItem :col-count="8" :caption="$t('app.specimen.creel.qualityDesc')">
              <DxSimpleItem :col-span="2" data-field="creelQuality.woof" :label="{text: $t('app.specimen.creel.woof')}" editor-type="dxNumberBox"
                            :editor-options="{ min:0 }">
                <DxRequiredRule :message="$t('validation.specimen.creel.required-woof')"></DxRequiredRule>
              </DxSimpleItem>
              <DxSimpleItem :col-span="2" data-field="creelQuality.crest" :label="{text: $t('app.specimen.creel.crest')}" editor-type="dxNumberBox"
                            :editor-options="{ min:10 }">
                <DxRequiredRule :message="$t('validation.specimen.creel.required-crest')"></DxRequiredRule>
              </DxSimpleItem>
              <DxSimpleItem :col-span="2" data-field="creelQuality.lancet" :label="{text: $t('app.specimen.creel.lancet')}" editor-type="dxNumberBox"
                            :editor-options="{ min:5, max:50 }">
                <DxRequiredRule :message="$t('validation.specimen.creel.required-lancet')"></DxRequiredRule>
              </DxSimpleItem>
              <DxSimpleItem :col-span="2" data-field="creelQuality.knittingType" :label="{text: $t('app.specimen.creel.knittingType')}">
                <DxRequiredRule :message="$t('validation.specimen.creel.required-knittingType')"></DxRequiredRule>
              </DxSimpleItem>
            </DxGroupItem>
            <DxGroupItem :col-count="8">
              <DxSimpleItem :col-span="2" data-field="creelQuality.bottomWoofYarn" :label="{text: $t('app.specimen.creel.bottomWoofYarn')}" editor-type="dxSelectBox"
                            :editor-options="{dataSource:yarn, displayExpr:'name', valueExpr:(e) => (e ? {'id':e.id,'name':e.name}: null)}">
              </DxSimpleItem>
              <DxSimpleItem :col-span="2" data-field="creelQuality.middleWoofYarn" :label="{text: $t('app.specimen.creel.middleWoofYarn')}" editor-type="dxSelectBox"
                            :editor-options="{dataSource:yarn, displayExpr:'name', valueExpr:(e) => (e ? {'id':e.id,'name':e.name}: null)}">
              </DxSimpleItem>
              <DxSimpleItem :col-span="2" data-field="creelQuality.topWoofYarn" :label="{text: $t('app.specimen.creel.topWoofYarn')}" editor-type="dxSelectBox"
                            :editor-options="{dataSource:yarn, displayExpr:'name', valueExpr:(e) => (e ? {'id':e.id,'name':e.name}: null)}">
              </DxSimpleItem>
            </DxGroupItem>
            <DxGroupItem :col-count="1">
              <DxSimpleItem :col-span="1" data-field="explanations[0].explanation.text" :label="{text: $t('app.specimen.creel.task.title')}"
                            :editor-options="{height:'120px'}" editor-type="dxTextArea">
              </DxSimpleItem>
            </DxGroupItem>
            <DxGroupItem :col-span="4" :col-count="4" :caption="$t('app.stock-card.palette.definition')">
              <DxSimpleItem :col-span="2" :label="{text: $t('app.design.plan.picture')}">
                <template #default>
                  <DxFileUploader
                      :select-button-text="$t('app.design.plan.picture')"
                      name="file"
                      :multiple="false"
                      upload-mode="instantly"
                      :upload-url="getDesignColorUrl"
                      :upload-headers="getTokenHeader"
                      accept="image/bmp"
                      @uploaded="handleDesignImageUploaded"
                      :invalid-file-extension-message="$t('common.bmp-file')"

                  />
                </template>
              </DxSimpleItem>
            </DxGroupItem>
            <DxGroupItem :col-count="1">
              <DxGroupItem :col-span="1" :col-count="1" :caption="$t('app.stock-card.palette.yarn')">
                <DxSimpleItem :label="{visible:false}">
                  <template #default>
                    <DxDataGrid
                        :data-source="creelPaletteLinesDataSource"
                        :columns-auto-width="true"
                        :show-borders="true"
                        key-expr="rotationNumber"
                        @initialized="dataGridInitialized"
                    >
                      <DxFilterRow :visible="true"/>
                      <DxFilterPanel :visible="true"/>
                      <DxHeaderFilter :visible="true"/>
                      <DxScrolling mode="virtual"/>
                      <DxGroupPanel :visible="true"/>
                      <DxMasterDetail
                          :enabled="true"
                          template="lineToYarnsTemplate"
                      />
                      <template #lineToYarnsTemplate="{ data: lineToYarns }">
                        <lineToYarns :template-data="lineToYarns"/>
                      </template>
                      <DxColumn
                          :allow-editing="false"
                          sort-order="asc"
                          data-field="rotationNumber"
                          data-type="string"
                          :caption="$t('app.stock-card.palette.rotationNumber')"
                      />
                      <DxColumn
                          :allow-editing="false"
                          cell-template="colorbox-template"
                          data-field="color.code"
                          data-type="string"
                          :caption="$t('app.stock-card.palette.color')"
                      />
                      <template #colorbox-template="{ data:options }">
                        <colorbox
                            :cell-data="options"
                        />
                      </template>
                      <DxColumn
                          :caption="$t('app.stock-card.palette.colorCode')"
                          :allow-editing="false"
                          :calculate-display-value="calculateColorDisplayValue"
                      />
                    </DxDataGrid>
                  </template>
                </DxSimpleItem>
              </DxGroupItem>
            </DxGroupItem>
          </DxGroupItem>
          <DxGroupItem :col-count="4">
            <DxGroupItem :col-count="2" :col-span="2">
              <DxButtonItem
                  :button-options="saveBtnOptions"
              />
            </DxGroupItem>
          </DxGroupItem>
        </DxForm>
      </div>
    </div>
  </div>
</template>
<script>
import {DxButtonItem, DxForm, DxGroupItem, DxSimpleItem} from 'devextreme-vue/form';
import kazu from "@/app/common/utils/kazu";
import {DxFileUploader} from 'devextreme-vue/file-uploader';
import colorbox from "@/app/common/layout/components/colorbox";
import lineToYarns from "@/views/stockCard/palette/lineToYarns";
import {
  DxColumn,
  DxDataGrid,
  DxFilterPanel,
  DxFilterRow,
  DxGroupPanel,
  DxHeaderFilter,
  DxMasterDetail,
  DxScrolling
} from 'devextreme-vue/data-grid';
import {DxRequiredRule} from 'devextreme-vue/validator';
import i18n from "@/app/common/utils/i18n";
import store from "@/store";
import CustomStore from "devextreme/data/custom_store";
import restapi from "@/app/common/utils/restapi";
import {CreelDao} from "@/views/specimen/creel/persistence/CreelDao";
import {NotificationService} from "@/app/common/utils/NotificationService";

export default {
  components: {
    DxForm,
    DxSimpleItem,
    DxGroupItem,
    DxRequiredRule,
    DxDataGrid,
    DxColumn,
    DxHeaderFilter,
    DxFilterRow,
    DxFilterPanel,
    DxScrolling,
    DxGroupPanel,
    DxFileUploader,
    colorbox,
    DxMasterDetail,
    lineToYarns,
    DxButtonItem,
  },
  data() {
    return {
      yarn: new CustomStore({
        key: 'id',
        load: () => {
          let url = '/yarn/'
          return restapi({
            method: 'GET',
            url,
          })
        },
        byKey: function (key) {
          return key;
        },
      }),
      raising:[
        {id:'yes',  name:'Var'},
        {id:'no',  name:'Yok'},
      ],
      washing:[
        {id:'yes',  name:'Var'},
        {id:'no',  name:'Yok'},
      ],
      designPlan: new CustomStore({
        key: 'id',
        load: () => {
          let url = '/design/plan/actives/'
          return restapi({
            method: 'GET',
            url,
          })
        },
        byKey: function (key) {
          return key;
        },
      }),
      saveBtnOptions: {
        type: 'default',
        useSubmitBehavior: true,
        text: i18n.global.t('common.save'),
        validationGroup: "creelPaletteValidationGroup",
        onClick: (e) => {
          let validationResult = e.validationGroup.validate();
          if (validationResult.isValid) {
            this.loading = true;
            if (this.creelPaletteLinesDataSource.length === 0) {
              NotificationService().showToaster({"message": "Desen görseli seçerek palet tanımlayınız.", "type": "alert"})
              return false
            }

            this.formData.creelPalette.creelPaletteLines = this.creelPaletteLinesDataSource;

            CreelDao().creelDataSource().insert(this.formData).then(() => {
              this.creelPaletteLinesDataSource = []
              this.dataGridInstance.refresh();
              this.formInstance.resetValues();
            });
          }
        }
      },
      dataGridInstance: null,
      formInstance: null,
      formData: {
        explanations:[{
          explanation:{
            text:''
          }
        }],
        creelPalette:{
          creelPaletteLines:[]
        }
      },
      creelPaletteLinesDataSource: [],
    }
  },
  computed: {
    getDesignColorUrl() {
      return process.env.BASE_URL + "image/color/";
    },
    getTokenHeader() {
      let token = store.getters._getCurrentUser?.token;
      return {"Authorization": token}
    },
  },
  methods: {
    calculateColorDisplayValue(rowData) {
      return rowData.color.code;
    },
    formInitialized(e) {
      this.formInstance = e.component
    },
    dataGridInitialized(e) {
      this.dataGridInstance = e.component
    },
    handleDesignImageUploaded(e) {
      this.creelPaletteLinesDataSource = []
      this.dataGridInstance.refresh();
      this.paletteLines = [];
      const responseData = JSON.parse(e.request.response);
      responseData.forEach((val) => {
        let color = {
          'rgbBlue': kazu.convertToTreeDigit(val.blue),
          'rgbRed': kazu.convertToTreeDigit(val.red),
          'rgbGreen': kazu.convertToTreeDigit(val.green),
          'code': kazu.convertToTreeDigit(val.red) + '.' + kazu.convertToTreeDigit(val.green) + '.' + kazu.convertToTreeDigit(val.blue),
          'definition': ''
        }
        let paletteLine = {
          "rotationNumber": val.paletteNo,
          "color": color,
          "lineToYarns": []
        }

        this.creelPaletteLinesDataSource.push(paletteLine);
      })

      this.dataGridInstance.refresh();
    },
    dxHtmlValue(e){
      this.formData.explanations[0].explanation.text = e.value
    },
  }
}
</script>
