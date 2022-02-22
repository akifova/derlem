<template>
  <DxPopup
      v-model:visible="isPopupVisible"
      :drag-enabled="false"
      :close-on-outside-click="false"
      :show-close-button="true"
      :show-title="false"
      width="50%"
      height="80%"
      @option-changed="popupChanged"
      @initialized="onPopUpInitialized"
  >
    <DxPosition
        at="center"
        my="center"
        v-model:of="positionOf"
    />
    <DxToolbarItem
        widget="dxButton"
        toolbar="bottom"
        location="after"
        :options="closeButtonOptions"
    />
    <template #content>
      <DxScrollView
          width="100%"
          height="100%"
          direction="both"
          show-scrollbar="always"
          :use-native="false"
      >
        <img :src="imageData" style="object-fit: contain; width: 100%; height: 100%;">
      </DxScrollView>
    </template>

  </DxPopup>
</template>

<script>
import {DxPopup, DxPosition, DxToolbarItem} from 'devextreme-vue/popup';
import {DxScrollView} from 'devextreme-vue/scroll-view';
import i18n from "@/app/common/utils/i18n";
import restapi from "@/app/common/utils/restapi";

export default {
  components: {
    DxPopup,
    DxPosition,
    DxToolbarItem,
    DxScrollView
  },
  data() {
    return {
      currentEmployee: {},
      isPopupVisible: this.popupVisible,
      popUpInstance: null,
      positionOf: '',
      imageData: '',
      closeButtonOptions: {
        text: i18n.global.t('common.close'),
        onClick: () => {
          this.isPopupVisible = false;
          this.$emit('is-popup-visible', this.isPopupVisible);
        },
      },
    };
  },
  methods:{
    popupChanged(e){
      this.isPopupVisible = e.value;
      this.$emit('is-popup-visible', this.isPopupVisible);
    },
    onPopUpShowing(archiveId){
      restapi({
        method: 'GET',
        url: '/file/download/jpg/base64/by-archive-id/' + archiveId
      }).then((result) => {
        this.imageData = "data:image/jpg;base64," + result.data;
        this.isPopupVisible = true;
      });
    },
    onPopUpInitialized(e) {
      this.popUpInstance = e.component;
    }
  }
}
</script>

