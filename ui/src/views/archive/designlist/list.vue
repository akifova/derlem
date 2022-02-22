<template>
  <div>
    <h2 class="content-block">{{$t('app.archive.designList.convert-to-sample')}}</h2>
    <div class="content-block">
      <div class="dx-card responsive-paddings">
          <DxForm
              :form-data="formData"
              :read-only="false"
              :show-colon-after-label="true"
              :show-validation-summary="true"
              validation-group="designListValidationGroup"
              @initialized="formInitialized"
          >
            <DxGroupItem :col-count="1">
              <DxSimpleItem data-field="code" :label="{visible:false}">
                <template #default>
                  <DxDataGrid
                      id="gridContainer"
                      :data-source="dataGridSource"
                      :columns-auto-width="true"
                      :show-borders="true"
                      @initialized="gridInitialized"
                  >
                    <DxFilterRow :visible="true"/>
                    <DxFilterPanel :visible="true"/>
                    <DxHeaderFilter :visible="true"/>
                    <DxScrolling mode="infinite"/>
                    <DxGroupPanel :visible="true"/>
                    <DxMasterDetail
                        :enabled="true"
                        template="plainline"
                    />
                    <template #plainline="{ data: list }">
                      <plainline
                          :template-data="list"
                          @callbackDoubleClick="doubleClick"
                      />
                    </template>
                    <DxColumn
                        data-field="title"
                        data-type="string"
                        :caption="$t('app.specimen.creel.designPlan')">
                    </DxColumn>
                  </DxDataGrid>
                </template>
              </DxSimpleItem>
            </DxGroupItem>
            <DxGroupItem :col-count="1">
              <DxGroupItem :col-count="4" :caption="$t('app.archive.designList.general-definition')">
                <DxSimpleItem data-field="designName" :label="{text: $t('app.archive.design.designName')}"
                              :editor-options="{ disabled:true}">
                </DxSimpleItem>
                <DxSimpleItem data-field="versionNumber" :label="{text: $t('common.versionNumber')}"
                              :editor-options="{ disabled:true}" />
                <DxSimpleItem data-field="customer" :label="{text: $t('app.archive.design.customer')}" editor-type="dxLookup"
                              :editor-options="customer">
                </DxSimpleItem>
                <DxSimpleItem data-field="designer" :label="{text: $t('app.archive.design.designer')}" editor-type="dxLookup"
                              :editor-options="designer">
                  <DxRequiredRule :message="$t('validation.archive.design.required-designer')"></DxRequiredRule>
                </DxSimpleItem>
                <DxSimpleItem data-field="tag" :label="{text: $t('app.archive.designList.tag')}" editor-type="dxTagBox"
                              :editor-options="{ acceptCustomValue:true}">
                  <DxRequiredRule :message="$t('validation.design.required-tag')"></DxRequiredRule>
                </DxSimpleItem>
                <DxSimpleItem data-field="overlockYarn" :label="{text: $t('app.archive.design.overlockYarn')}" editor-type="dxLookup"
                              :editor-options="overlockYarn">
                </DxSimpleItem>
                <DxSimpleItem data-field="sideEdge" :label="{text: $t('app.archive.design.sideEdge')}" editor-type="dxLookup"
                              :editor-options="sideEdge">
                  <DxRequiredRule :message="$t('validation.archive.design.required-sideEdge')"></DxRequiredRule>
                </DxSimpleItem>
                <DxSimpleItem data-field="weaveType" :label="{text: $t('app.archive.design.weaveType')}" editor-type="dxLookup"
                              :editor-options="weaveType">
                  <DxRequiredRule :message="$t('validation.archive.design.required-weaveType')"></DxRequiredRule>
                </DxSimpleItem>

                <DxSimpleItem data-field="difficulty" :label="{text: $t('app.archive.design.difficulty')}"
                              editor-type="dxSlider" :editor-options="difficultySlideOptions"/>
                <DxSimpleItem data-field="starRating" :label="{text: $t('app.archive.design.starRating')}"
                  editor-type="dxSlider" :editor-options="starRatingSlideOptions"/>
                <DxSimpleItem data-field="makingRare" :label="{text: $t('app.archive.design.makingRare')}" :editor-options="makingRare" editor-type="dxSwitch">
                </DxSimpleItem>
                <DxSimpleItem data-field="makingRarePaletteIndexes" :label="{text: $t('app.archive.design.makingRarePaletteIndexes')}"
                              :editor-options="{placeholder: $t('app.archive.design.making-rare-placeholder')}"
                />
              </DxGroupItem>
            </DxGroupItem>
            <DxGroupItem :caption="$t('app.archive.designList.stock-card')">
              <DxGroupItem :col-count="3">
                <DxSimpleItem data-field="collection" :label="{text: $t('app.archive.design.collection')}" editor-type="dxLookup"
                              :editor-options="collection">
                  <DxRequiredRule :message="$t('validation.archive.design.required-collection')"></DxRequiredRule>
                </DxSimpleItem>
                <DxSimpleItem data-field="quality" :label="{text: $t('app.archive.design.quality')}" editor-type="dxLookup"
                              :editor-options="quality">
                  <DxRequiredRule :message="$t('validation.archive.design.required-quality')"></DxRequiredRule>
                </DxSimpleItem>
                <DxSimpleItem data-field="design" :label="{text:  $t('app.archive.design.design')}" editor-type="dxLookup"
                              :editor-options="design">
                  <DxRequiredRule :message="$t('validation.archive.design.required-design')"></DxRequiredRule>
                </DxSimpleItem>
                <DxSimpleItem data-field="size" :label="{text: $t('app.archive.design.size')}" editor-type="dxLookup"
                              :editor-options="size">
                  <DxRequiredRule :message="$t('validation.archive.design.required-size')"></DxRequiredRule>
                </DxSimpleItem>
                <DxSimpleItem data-field="edge" :label="{text: $t('app.archive.design.edge')}" editor-type="dxLookup"
                              :editor-options="edge">
                  <DxRequiredRule :message="$t('validation.archive.design.required-edge')"></DxRequiredRule>
                </DxSimpleItem>
                <DxSimpleItem data-field="shape" :label="{text: $t('app.archive.design.shape')}" editor-type="dxLookup"
                              :editor-options="shape">
                  <DxRequiredRule :message="$t('validation.archive.design.required-shape')"></DxRequiredRule>
                </DxSimpleItem>
                <DxSimpleItem data-field="palette" :label="{text: $t('app.archive.design.palette')}" editor-type="dxLookup"
                              :editor-options="palette">
                  <DxRequiredRule :message="$t('validation.archive.design.required-palette')"></DxRequiredRule>
                </DxSimpleItem>
                <DxSimpleItem data-field="bordure" :label="{text: $t('app.archive.design.bordure')}" editor-type="dxLookup"
                              :editor-options="bordure">
                </DxSimpleItem>
                <DxSimpleItem data-field="floor" :label="{text: $t('app.archive.design.floor')}" editor-type="dxLookup"
                              :editor-options="floor">
                </DxSimpleItem>
              </DxGroupItem>
            </DxGroupItem>
            <DxGroupItem :col-count="4">
              <DxGroupItem :col-count="2" :col-span="1">
                <DxButtonItem
                    :button-options="addBtnOptions"
                    horizontal-alignment="left"
                />
              </DxGroupItem>
            </DxGroupItem>
          </DxForm>
      </div>
    </div>
  </div>
</template>
<script>
import {DxButtonItem, DxForm, DxGroupItem, DxRequiredRule, DxSimpleItem} from 'devextreme-vue/form';
import restapi from "@/app/common/utils/restapi";
import kazu from "@/app/common/utils/kazu";
import plainline from "./plainline"
import {
  DxColumn,
  DxDataGrid,
  DxFilterPanel,
  DxFilterRow,
  DxGroupPanel,
  DxHeaderFilter,
  DxMasterDetail,
  DxScrolling,
} from 'devextreme-vue/data-grid';
import CustomStore from "devextreme/data/custom_store";
import i18n from "@/app/common/utils/i18n";
import {DxTagBox} from "devextreme-vue/tag-box";
import {CustomStoreCreator} from "@/app/common/utils/CustomStoreCreator";

const yarns = []
export default {
  components: {
    DxForm,
    DxSimpleItem,
    DxGroupItem,
    DxButtonItem,
    DxDataGrid,
    DxColumn,
    DxHeaderFilter,
    DxFilterRow,
    DxFilterPanel,
    DxScrolling,
    DxGroupPanel,
    DxRequiredRule,
    DxMasterDetail,
    plainline,
    // eslint-disable-next-line vue/no-unused-components
    DxTagBox,
  },
  data() {
    return {
      formInstance:null,
      gridInstance: null,
      yarns,
      isFloorOpened:null,
      isBordureOpened:null,
      paletteId:null,
      floorValue:null,
      borderValue:null,
      difficultySlideOptions: {
        "min": 10, "max": 100, "step":10, "tooltip": { enabled: true, showMode: 'always', position: 'bottom'}
      },
      starRatingSlideOptions: {
        "min": 1, "max": 5, "step":1, "tooltip": { enabled: true, showMode: 'always', position: 'bottom'}
      },
      customer: {
        dataSource: CustomStoreCreator().createLookupStore('/common/firm/search', ['name']),
        searchEnabled: true,
        minSearchLength: 2,
        displayExpr: (e) => {
          if (e) {
            return e.name;
          } else {
            return null
          }
        }
      },
      designPlan: {
        dataSource: CustomStoreCreator().createStore('/design/plan/actives/'),
        searchEnabled: true,
        minSearchLength: 2,
        displayExpr: (e) => {
          if (e) {
            return e.title;
          } else {
            return null
          }
        }
      },
      quality: {
        dataSource: CustomStoreCreator().createLookupStore('/scm/production/definition/stock-card/quality/search', ['code', 'definition']),
        searchEnabled: true,
        minSearchLength: 2,
        displayExpr: (e) => {
          if (e) {
            return `[${e.code}] ${e.definition}`;
          } else {
            return null
          }
        }
      },
      palette: {
        dataSource: CustomStoreCreator().createLookupStore('/scm/production/definition/stock-card/palette/search', ['code', 'definition']),
        searchEnabled: true,
        minSearchLength: 2,
        displayExpr: (e) => {
          if (e) {
            return `[${e.code}] ${e.definition}`;
          } else {
            return null
          }
        },
        onValueChanged:(e)=>{
          if(e.value) {
            console.log(e);
            this.formInstance.getEditor("bordure").reset()
            this.formInstance.getEditor("floor").reset()
            this.paletteId = e.value.id
            restapi({
              method: 'GET',
              url: '/scm/production/definition/stock-card/palette/get-yarns/' + e.value.id,
            }).then((e) => {
              this.yarns = e.data
              this.formInstance.getEditor("bordure").getDataSource().load()
              this.formInstance.getEditor("floor").getDataSource().load()
            })
          }
        },
      },
      bordure: {
        searchEnabled: true,
        minSearchLength: 2,
        dataSource: new CustomStore({
          key:'id',
          load:()=>{
            return this.yarns
          },
          byKey:(e)=>{
            return e;
          }
        }),
        displayExpr: (e) => {
          if (e) {
            return e.name;
          } else {
            return null
          }
        },
        valueExpr: (e) => {
          if (e) {
            return {'id': e.id};
          } else {
            return null
          }
        },
      },
      floor: {
        searchEnabled: true,
        minSearchLength: 2,
        dataSource: new CustomStore({
          key:'id',
          load:()=>{
            return this.yarns
          },
          byKey:(e)=>{
            return e;
          }
        }),
        displayExpr: (e) => {
          if (e) {
            return e.name;
          } else {
            return null
          }
        },
        valueExpr: (e) => {
          if (e) {
            return {'id': e.id};
          } else {
            return null
          }
        },
      },
      design: {
        dataSource: CustomStoreCreator().createLookupStore('/scm/production/definition/stock-card/design/search', ['code']),
        searchEnabled: true,
        minSearchLength: 2,
        displayExpr: (e) => {
          if (e) {
            return e.code;
          } else {
            return null
          }
        }
      },
      size: {
        dataSource: CustomStoreCreator().createLookupStore('/scm/production/definition/stock-card/measure/search', ['code', 'definition']),
        searchEnabled: true,
        minSearchLength: 2,
        displayExpr: (e) => {
          if (e) {
            return `[${e.code}] ${e.definition}`;
          } else {
            return null
          }
        }
      },
      shape: {
        dataSource: CustomStoreCreator().createLookupStore('/scm/production/definition/stock-card/shape/search', ['code', 'definition']),
        searchEnabled: true,
        minSearchLength: 2,
        displayExpr: (e) => {
          if (e) {
            return `[${e.code}] ${e.definition}`;
          } else {
            return null
          }
        }
      },
      edge: {
        dataSource: CustomStoreCreator().createLookupStore('/scm/production/definition/stock-card/edge/search', ['code', 'definition']),
        searchEnabled: true,
        minSearchLength: 2,
        displayExpr: (e) => {
          if (e) {
            return `[${e.code}] ${e.definition}`;
          } else {
            return null
          }
        }
      },
      makingRare: {
        onValueChanged:(e) =>{
          if (e.value){
            this.formData.makingRare = true
          }else {
            this.formData.printOnBarcode = false
          }
        },
      },
      overlockYarn: {
        dataSource: CustomStoreCreator().createLookupStore('/yarn/search', ['name']),
        searchEnabled: true,
        minSearchLength: 2,
        displayExpr: (e) => {
          if (e) {
            return e.name;
          } else {
            return null
          }
        }
      },
      sideEdge: {
        dataSource: CustomStoreCreator().createLookupStore('/scm/production/definition/stock-card/edge/search', ['code', 'definition']),
        searchEnabled: true,
        minSearchLength: 2,
        displayExpr: (e) => {
          if (e) {
            return `[${e.code}] ${e.definition}`;
          } else {
            return null
          }
        }
      },
      weaveType: {
        dataSource: [
          {id:'FLAT',  name:i18n.global.t('app.archive.design.flat')},
          {id:'CROSS',  name:i18n.global.t('app.archive.design.cross')},
        ],
        displayExpr: 'name',
        valueExpr: 'id',

      },
      collection: {
        dataSource: CustomStoreCreator().createLookupStore('/scm/production/definition/stock-card/collection/search', ['code', 'definition']),
        searchEnabled: true,
        minSearchLength: 2,
        displayExpr: (e) => {
          if (e) {
            return `[${e.code}] ${e.definition}`;
          } else {
            return null
          }
        }
      },
      designer: {
        dataSource: CustomStoreCreator().createLookupStore('/user/account/get/search', ['name', 'surname'], 'email'),
        searchEnabled: true,
        minSearchLength: 2,
        displayExpr: (e) => {
          if (e) {
            return e.nameSurname;
          } else {
            return null
          }
        }
      },
      formData: {
        designName:'',
        versionNumber:'',
        bordure:{
          id:null
        },
        floor:{
          id:null
        },
        creel:{
          id:null,
          versionNumber:null
        }
      },
      dataGridSource:new CustomStore({
        key: 'id',
        load: () => {
          let url = '/design/plan/actives'
          return restapi({
            method: 'GET',
            url
          }).then(data => {
            return {
              data: data.data,
              totalCount: data.data.length,
            };
          });
        },
      }),
      addBtnOptions: {
        text: i18n.global.t('common.save'),
        type: 'default',
        useSubmitBehavior: false,
        validationGroup: "designListValidationGroup",
        onClick: (e) => {
          let validationResult = e.validationGroup.validate();
          if (validationResult.isValid) {

            let saveData = {...this.formData}
            saveData.tag = this.formData.tag.join();

            kazu.RestService('/archive/sample/convert-to-sample/', saveData,'PUT').then(() => {
              this.formInstance.beginUpdate();
              this.formInstance.resetValues();
              this.gridInstance.refresh()
              this.formInstance.endUpdate();
            })
          }
        }
      },
      caption: i18n.global.t('app.menu.specimen.creel.add'),
    }
  },
  methods: {
    formInitialized:function (e) {
      this.formInstance = e.component
    },
    gridInitialized: function (e) {
      this.gridInstance = e.component
    },
    bordureClick(e){
      this.borderValue = `İplik Sıra No-> ${e.rotationNumber}`
      this.formData.bordure.id = e.id
      this.isBordureOpened = false;
    },
    doubleClick(e) {
      this.formInstance.beginUpdate();
      this.formData=e;
      this.formInstance.endUpdate();
    }
  }
}
</script>
