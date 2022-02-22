<template>
  <DxForm>
    <DxGroupItem :col-count="4">
      <DxSimpleItem :label="{text: $t('app.archive.design.quality')}">
        <template #default>
          <DxLookup :data-source="qualityDataSource" :display-expr="codeDefinitionDisplayExpr" :min-search-length="2" :search-enabled="true" @value-changed="stockCardLookupValueChanged($event, 'quality')"/>
        </template>
      </DxSimpleItem>
      <DxSimpleItem :label="{text: $t('app.archive.design.collection')}">
        <template #default>
          <DxLookup :data-source="collectionDataSource" :display-expr="codeDefinitionDisplayExpr" :min-search-length="2" :search-enabled="true" @value-changed="stockCardLookupValueChanged($event, 'collection')"/>
        </template>
      </DxSimpleItem>
      <DxSimpleItem :label="{text: $t('app.archive.design.designCode')}">
        <template #default>
          <DxLookup :data-source="designDataSource" :display-expr="codeDisplayExpr" :min-search-length="2" :search-enabled="true" @value-changed="stockCardLookupValueChanged($event, 'design')"/>
        </template>
      </DxSimpleItem>
      <DxSimpleItem :label="{text: $t('app.archive.design.palette')}">
        <template #default>
          <DxLookup :data-source="paletteDataSource" :display-expr="codeDefinitionDisplayExpr" :min-search-length="2" :search-enabled="true" @value-changed="stockCardLookupValueChanged($event, 'palette')"/>
        </template>
      </DxSimpleItem>
      <DxSimpleItem :label="{text: $t('app.archive.design.size')}">
        <template #default>
          <DxLookup :data-source="sizeDataSource" :display-expr="codeDefinitionDisplayExpr" :min-search-length="2" :search-enabled="true" @value-changed="stockCardLookupValueChanged($event, 'size')"/>
        </template>
      </DxSimpleItem>
      <DxSimpleItem :label="{text: $t('app.archive.design.shape')}">
        <template #default>
          <DxLookup :data-source="shapeDataSource" :display-expr="codeDefinitionDisplayExpr" @value-changed="stockCardLookupValueChanged($event, 'shape')"/>
        </template>
      </DxSimpleItem>
      <DxSimpleItem :label="{text: $t('app.archive.design.edge')}">
        <template #default>
          <DxLookup :data-source="edgeDataSource" :display-expr="codeDefinitionDisplayExpr" @value-changed="stockCardLookupValueChanged($event, 'edge')"/>
        </template>
      </DxSimpleItem>
      <DxSimpleItem :label="{text: $t('app.archive.design.sideEdge')}">
        <template #default>
          <DxLookup :data-source="edgeDataSource" :display-expr="codeDefinitionDisplayExpr" @value-changed="stockCardLookupValueChanged($event, 'sideEdge')"/>
        </template>
      </DxSimpleItem>
    </DxGroupItem>
  </DxForm>
</template>

<script>
import {CustomStoreCreator} from "@/app/common/utils/CustomStoreCreator";
import {DxForm, DxGroupItem, DxSimpleItem} from "devextreme-vue/form";
import {DxLookup} from "devextreme-vue/lookup";

export default {
  name: "StockCardFieldsLookup",
  components: {
    DxGroupItem,
    DxSimpleItem,
    DxLookup,
    DxForm
  },
  data() {
    return {
      qualityDataSource: CustomStoreCreator().createLookupStore('/scm/production/definition/stock-card/quality/search', ['code', 'definition']),
      collectionDataSource: CustomStoreCreator().createLookupStore('/scm/production/definition/stock-card/collection/search', ['code', 'definition']),
      designDataSource: CustomStoreCreator().createLookupStore('/scm/production/definition/stock-card/design/search', ['code']),
      paletteDataSource: CustomStoreCreator().createLookupStore('/scm/production/definition/stock-card/palette/search', ['code', 'definition']),
      sizeDataSource: CustomStoreCreator().createLookupStore('/scm/production/definition/stock-card/measure/search', ['code', 'definition']),
      shapeDataSource: CustomStoreCreator().createLoadStore('/scm/production/definition/stock-card/shape/'),
      edgeDataSource: CustomStoreCreator().createLoadStore('/scm/production/definition/stock-card/edge/'),
    }
  },
  methods: {
    codeDefinitionDisplayExpr: function (anyDto) {
      return anyDto ? anyDto.code + ' - ' + anyDto.definition : '';
    },
    codeDisplayExpr: function (anyDto) {
      return anyDto ? anyDto.code : '';
    },
    stockCardLookupValueChanged: function (e, dataField) {
      this.$emit("dataField", {"field": dataField, "value": e.value});
      //this.formData[dataField] = e.value;
    }
  }
}
</script>