<template>
  <DxTabPanel
      height="50%"
      :selected-index="0"
      :loop="false"
      :animation-enabled="false"
      :swipe-enabled="false"
  >
  <DxItem
      title="Kalite"
      template="quality-tab"
  ></DxItem>
    <template #quality-tab>
      <QualityTab
        :creel-id="templateData.id"/>
    </template>
  </DxTabPanel>
</template>

<script>
export default {
  props: {
    templateData: {
      type: Object,
      default: () => {
      }
    }
  },
  data() {
    console.log(this.templateData)
  }
}
</script>

<style scoped>

</style>