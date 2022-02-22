<template>
  <DxPopup
      v-model:visible="popupVisible"
      :drag-enabled="true"
      :close-on-outside-click="false"
      :show-close-button="true"
      :show-title="true"
      container=".dx-viewport"
      :title="$t('app.archive.search.title')"
  >
    <template #content>
      <DxScrollView
          width="100%"
          height="100%"
      >
        <DxToolbarItem
            widget="dxButton"
            toolbar="top"
            location="before"
            :options="selectButtonOptions"
        />
        <div class="content-block">
          <StockCardFieldsLookup @data-field="stockCardFieldSelected"></StockCardFieldsLookup>
          <DxButton text="Ara" type="success" @click="search"></DxButton>
        </div>
        <div class="content-block">
          <ArchiveBasicGrid ref="archiveBasicGrid" @selected-data="gridDataSelected"></ArchiveBasicGrid>
        </div>
      </DxScrollView>
    </template>
  </DxPopup>
</template>

<script>
import StockCardFieldsLookup from "@/app/components/archive/StockCardFieldsLookup";
import ArchiveBasicGrid from "@/app/components/archive/ArchiveBasicGrid";
import i18n from "@/app/common/utils/i18n";
import {DxPopup} from "devextreme-vue";
import {DxToolbarItem} from "devextreme-vue/popup";
import {DxButton} from "devextreme-vue/button";
import {DxScrollView} from 'devextreme-vue/scroll-view';

export default {
  name: "FindAndSelectArchivePopUp",
  data() {
    return {
      searchData: {},
      popupVisible: false,
      selectButtonOptions: {
        text: i18n.global.t('common.select'),
        onClick: () => {
          this.popupVisible = false;
        }
      },
      closeButtonOptions: {
        text: i18n.global.t('common.close'),
        onClick: () => {
          this.popupVisible = false;
        },
      }
    }
  },
  methods: {
    stockCardFieldSelected(data) {
      this.searchData[data.field] = data.value;
    },
    showPopUp() {
      this.popupVisible = true;
    },
    search() {
      this.$refs.archiveBasicGrid.searchArchive(this.searchData)
    },
    gridDataSelected(archiveDto) {
      this.$emit("selected-archive", archiveDto);
      this.popupVisible = false;
    }
  },
  components: {
    StockCardFieldsLookup,
    ArchiveBasicGrid,
    DxPopup,
    DxToolbarItem,
    DxButton,
    DxScrollView
  }
}
</script>