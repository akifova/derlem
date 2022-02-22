<template>
  <div>
    <h2 class="content-block">{{ $t('app.menu.archive.designList.text') }}</h2>
    <div class="content-block">
      <div class="dx-card responsive-paddings">
        <DxForm
            :form-data="formData"
            :read-only="false"
            :show-colon-after-label="true"
            :show-validation-summary="true"
            validation-group="archiveDesignValidationGroup"
            @initialized="formInitialized"
        >
          <DxGroupItem :col-count="3">
            <DxGroupItem :col-span="1" :col-count="1">
              <DxSimpleItem data-field="design" :label="{text: $t('app.archive.design.design')}">
                <template #default>
                  <DxFileUploader
                      :multiple="false"
                      :allowed-file-extensions="['.bmp']"
                      :select-button-text="$t('app.archive.design.designFile')"
                      upload-mode="useForm"
                      @value-changed="designFilesUpload"
                      ref="refAddDesignUploader"
                      accept="image/bmp"
                      :invalid-file-extension-message="$t('common.bmp-file')"

                  />
                </template>
              </DxSimpleItem>
              <DxSimpleItem data-field="empty" :label="{text: $t('app.archive.design.empty')}">
                <template #default>
                  <DxFileUploader
                      :multiple="true"
                      :allowed-file-extensions="['.bmp']"
                      :select-button-text="$t('app.archive.design.emptyFile')"
                      upload-mode="useForm"
                      @value-changed="emptyFilesUpload"
                      accept="image/bmp"
                      ref="refEmptyUploader"
                      :invalid-file-extension-message="$t('common.bmp-file')"

                  />
                </template>
              </DxSimpleItem>
              <DxSimpleItem data-field="scanning" :label="{text: $t('app.archive.design.scanning')}">
                <template #default>
                  <DxFileUploader
                      :multiple="true"
                      :allowed-file-extensions="['.bmp']"
                      :select-button-text="$t('app.archive.design.scanningFile')"
                      upload-mode="useForm"
                      @value-changed="scanningFilesUpload"
                      accept="image/bmp"
                      ref="refScanningUploader"
                      :invalid-file-extension-message="$t('common.bmp-file')"

                  />
                </template>
              </DxSimpleItem>
            </DxGroupItem>
            <DxGroupItem :col-span="2">
              <DxGroupItem :col-count="1">
                <DxSimpleItem editor-type="dxButton" :editor-options="selectArchiveButtonOptions"/>
              </DxGroupItem>
              <DxGroupItem :col-count="2">
                <DxSimpleItem data-field="designPlan.title" :label="{text: $t('app.archive.design.designPlan')}" :editor-options="{disabled: true}"/>
                <DxSimpleItem data-field="creel.sampleName" :label="{text: $t('app.archive.design.creelPalette')}" :editor-options="{disabled: true}"/>
                <DxSimpleItem data-field="quality.definition" :label="{text: $t('app.archive.design.quality')}" :editor-options="{disabled: true}"/>
                <DxSimpleItem data-field="collection.definition" :label="{text: $t('app.archive.design.collection')}" :editor-options="{disabled: true}"/>
                <DxSimpleItem data-field="designName" :label="{text: $t('app.archive.design.designName')}" :editor-options="{disabled: true}"/>

                <DxSimpleItem data-field="size" :label="{text: $t('app.archive.design.size')}" editor-type="dxLookup"
                              :editor-options="sizeSelectBoxOptions">
                  <DxRequiredRule :message="$t('validation.archive.design.required-size')"></DxRequiredRule>
                </DxSimpleItem>
                <DxSimpleItem data-field="shape" :label="{text: $t('app.archive.design.shape')}"
                              editor-type="dxLookup"
                              :editor-options="shapeSelectBoxOptions">
                  <DxRequiredRule :message="$t('validation.archive.design.required-shape')"></DxRequiredRule>
                </DxSimpleItem>
                <DxSimpleItem data-field="edge" :label="{text: $t('app.archive.design.edge')}" editor-type="dxLookup"
                              :editor-options="edgeSelectBoxOptions">
                  <DxRequiredRule :message="$t('validation.archive.design.required-edge')"></DxRequiredRule>
                </DxSimpleItem>
                <DxSimpleItem data-field="customer" :label="{text: $t('app.archive.design.customer')}"
                              editor-type="dxLookup"
                              :editor-options="customerSelectBoxOptions">
                </DxSimpleItem>
                <DxSimpleItem data-field="designer" :label="{text: $t('app.archive.design.designer')}"
                              editor-type="dxLookup"
                              :editor-options="designerSelectBoxOptions">
                  <DxRequiredRule :message="$t('validation.archive.design.required-designer')"></DxRequiredRule>
                </DxSimpleItem>
              </DxGroupItem>
            </DxGroupItem>
          </DxGroupItem>
          <DxGroupItem :col-count="5">
            <DxButtonItem
                :button-options="saveBtnOptions"
            />
          </DxGroupItem>
        </DxForm>
        <FindAndSelectArchivePopUp ref="findAndSelectArchive" @selected-archive="popUpArchiveSelected"/>
      </div>
    </div>
  </div>
</template>
<script>
import {DxButtonItem, DxForm, DxGroupItem, DxRequiredRule, DxSimpleItem} from 'devextreme-vue/form';
import restapi from "@/app/common/utils/restapi";
import {DxFileUploader} from 'devextreme-vue/file-uploader';
import i18n from "@/app/common/utils/i18n";
import {DesignerService} from "@/views/archive/design/business/DesignerService";
import {CustomerService} from "@/views/archive/design/business/CustomerService";
import {EdgeService} from "@/views/archive/design/business/EdgeService";
import {ShapeService} from "@/views/archive/design/business/ShapeService";
import {SizeService} from "@/views/archive/design/business/SizeService";
import FindAndSelectArchivePopUp from "@/app/components/archive/FindAndSelectArchivePopUp";

export default {
  components: {
    DxForm,
    DxSimpleItem,
    DxGroupItem,
    DxRequiredRule,
    DxButtonItem,
    DxFileUploader,
    FindAndSelectArchivePopUp
  },
  data() {
    return {
      formInstance: null,
      design: '',
      empty: '',
      scanning: '',
      designId: '',
      creelId: '',
      selectArchiveButtonOptions: {text: i18n.global.t('app.archive.search.title'), type: 'success', onClick: this.showPopUp},
      sizeSelectBoxOptions: SizeService().getSelectBoxParameters(),
      shapeSelectBoxOptions: ShapeService().getSelectBoxParameters(),
      edgeSelectBoxOptions: EdgeService().getSelectBoxParameters(),
      customerSelectBoxOptions: CustomerService().getSelectBoxParameters(),
      designerSelectBoxOptions: DesignerService().getSelectBoxParameters(),
      formData: {},
      saveBtnOptions: {
        text: i18n.global.t('common.save'),
        type: 'default',
        useSubmitBehavior: true,
        validationGroup: "archiveDesignValidationGroup",
        onClick: (e) => {
          let validationResult = e.validationGroup.validate();
          if (validationResult.isValid) {

            let formData = new FormData();
            formData.append('design', this.design[0]);
            for(let i in this.empty){
              let file = this.empty[i];
              formData.append('empties', file);
            }

            for(let i in this.scanning){
              let file = this.scanning[i];
              formData.append('scans', file);
            }
            formData.append('archiveDto', JSON.stringify(this.formData))

            restapi({
              headers: {
                'mime-type': 'multipart/form-data',
                'Content-Type': false
              },
              method: 'POST',
              url:`/archive/add-design`,
              data: formData
            }).then(() => {
              this.formInstance.resetValues();
              this.$refs.refAddDesignUploader.instance.reset()
              this.$refs.refEmptyUploader.instance.reset()
              this.$refs.refScanningUploader.instance.reset()
              formData = new FormData();
            })
          }
        }
      },
    }
  },
  methods: {
    formInitialized: function (e) {
      this.formInstance = e.component
    },
    designFilesUpload(e) {
      this.design = e.value;
    },
    emptyFilesUpload(e) {
      this.empty = e.value;
    },
    scanningFilesUpload(e) {
      this.scanning = e.value;
    },
    showPopUp() {
      this.$refs.findAndSelectArchive.showPopUp();
    },
    popUpArchiveSelected(archiveDto) {
      this.formInstance.beginUpdate();
      this.formData = archiveDto;
      this.formInstance.endUpdate();
    }
  }
}
</script>
