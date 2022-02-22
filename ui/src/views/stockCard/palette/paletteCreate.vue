<template>
  <div>
    <h2 class="content-block">{{ $t('app.menu.stock-card.palette.text') }}</h2>
    <div class="content-block">
      <div class="dx-card responsive-paddings">
        <DxForm
            :form-data="formData"
            :read-only="false"
            :show-colon-after-label="true"
            :show-validation-summary="true"
            validation-group="paletteValidationGroup"
            @initialized="formInitialized"
        >
          <DxGroupItem>
            <DxGroupItem :col-span="4" :col-count="4" :caption="$t('app.stock-card.palette.definition')">
              <DxSimpleItem data-field="code" :label="{text: $t('app.stock-card.palette.code')}">
                <DxRequiredRule :message="$t('validation.stock-card.palette.required-code')"></DxRequiredRule>
              </DxSimpleItem>
              <DxSimpleItem data-field="definition" :label="{text: $t('app.stock-card.palette.definition')}">
                <DxRequiredRule :message="$t('validation.stock-card.palette.required-definition')"></DxRequiredRule>
              </DxSimpleItem>
              <DxSimpleItem :col-span="2" :label="{text: $t('app.stock-card.palette.getColorsFromImage')}">
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
                      ref="refPaletteCreateUploader"
                      :invalid-file-extension-message="$t('common.bmp-file')"

                  />
                </template>
              </DxSimpleItem>
            </DxGroupItem>
            <DxGroupItem :col-count="1" :caption="$t('app.stock-card.palette.similar-palettes.header')">
              <DxDataGrid
                  :data-source="similarPalettesDataSource"
                  :columns-auto-width="true"
                  :show-borders="true"
                  key-expr="paletteId"
                  @initialized="similarGridInitialized"
              >
                <DxScrolling mode="virtual"/>
                <DxColumn
                    :allow-editing="false"
                    data-field="paletteCode"
                    data-type="string"
                    :caption="$t('app.stock-card.palette.code')"
                />
                <DxColumn
                    :allow-editing="false"
                    data-field="matchColorCount"
                    data-type="string"
                    :caption="$t('app.stock-card.palette.similar-palettes.match-color-count')"
                />
                <DxColumn
                    :allow-editing="false"
                    data-field="similarityRatio"
                    data-type="string"
                    :caption="$t('app.stock-card.palette.similar-palettes.similarity-ratio')"
                />
              </DxDataGrid>
            </DxGroupItem>
            <DxGroupItem :col-count="1">
              <DxGroupItem :col-span="1" :col-count="1" :caption="$t('app.stock-card.palette.yarn')">
                <DxSimpleItem :label="{visible:false}">
                  <template #default>
                    <DxDataGrid
                        :data-source="paletteLines"
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
                          :calculate-display-value="calculateColorCodeDisplayValue"
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
import {PaletteDao} from "@/views/stockCard/palette/persistence/PaletteDao";
import i18n from "@/app/common/utils/i18n";
import store from "@/store";
import restapi from "@/app/common/utils/restapi";
import {NotificationService} from "@/app/common/utils/NotificationService";
import qs from "qs";

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
    DxButtonItem
  },
  data() {
    return {
      similarPalettesDataSource: [],
      similarGridInstance: null,
      saveBtnOptions: {
        type: 'default',
        useSubmitBehavior: true,
        text: i18n.global.t('common.save'),
        validationGroup: "paletteValidationGroup",
        onClick: (e) => {
          let validationResult = e.validationGroup.validate();
          if (validationResult.isValid) {
            this.loading = true;
            if (this.paletteLines.length === 0) {
              kazu.showMessage("Desen görseli seçerek palet tanımlayınız.", 'error', 2000)
              return false
            }

            let palette = {
              "code": this.formData.code,
              "definition": this.formData.definition,
              "paletteLines": this.paletteLines
            }

            PaletteDao().paletteDataSource().insert(palette).then(() => {
              this.paletteLines = []
              this.dataGridInstance.refresh();
              this.formInstance.resetValues();
              this.$refs.refPaletteCreateUploader.instance.reset()
            })
          }
        }
      },
      dataGridInstance: null,
      formInstance: null,
      formData: {},
      paletteLines: [],
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
    calculateColorCodeDisplayValue(rowData) {
      return rowData.color.code;
    },
    similarGridInitialized(e) {
      this.similarGridInstance = e.component
    },
    formInitialized(e) {
      this.formInstance = e.component
    },
    dataGridInitialized(e) {
      this.dataGridInstance = e.component
    },
    handleDesignImageUploaded(e) {
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

        this.paletteLines.push(paletteLine);
      })

      this.dataGridInstance.refresh();

      const colorCodes = this.paletteLines.map(m=> m.color.code);
      restapi({
        method:"GET",
        url: "/scm/production/definition/stock-card/palette/find-similar",
        params: {colorCodes: colorCodes},
        paramsSerializer: params => {
        return qs.stringify(params, {arrayFormat: 'repeat'})
      }
      }).then(result => {
        console.log(result.data);
        this.similarPalettesDataSource = result.data;
        this.similarGridInstance.refresh();
        if(result.data.filter(f=>f.similarityRatio == 100).length > 0) {
          NotificationService().showToaster({
            "type": "warning",
            "message": "DİKKAT!!! %100 Uyumlu Palet Bulundu..."
          })
        }
      });
    },
  }
}
</script>
