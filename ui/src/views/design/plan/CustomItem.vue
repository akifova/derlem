<template>
  <div>
    <div class="product">
      <img :src="file(item.userFile.url,item.userFile.fileName)" @click="showFile">
      <popup :user-file="item.userFile" :visible="popupVisible" @isPopupVisible="submitForm"/>
    </div>
  </div>
</template>
<script>
import kazu from "@/app/common/utils/kazu";
import popup from "@/app/components/archive/ShowArchiveImage";

export default {
  components:{
    popup
  },
  data(){
    console.log(this.item)
    return {
      popupVisible: false,
    }
  },
  props: {
    item: {
      type: Object,
      default: () => {}
    }
  },
  methods: {
    submitForm(e) {
      this.popupVisible = e
    },
    file(url,fileName) {
      return kazu.thumbnailPicture(url,fileName)
    },
    showFile(){
      this.popupVisible = true
    },
  }
};
</script>
<style scoped>
.product {
  margin: 0 auto;
  width: 100px;
}

.product > img {
  height: 100%;
  width: 95px;
  float: left;
}

.product > div {
  padding-left: 10px;
  vertical-align: top;
  line-height: 65px;
  font-size: 15px;
  float: left;
}
.popup-property-details {
  overflow: hidden;
  position: relative;
}
.large-text {
  font-size: 24px;
  color: #ff6a50;
}
.opacity {
  opacity: 0.5;
}
.images {
  font-size: 0;
}
.images > div {
  display: inline-block;
  width: 33.3%;
  vertical-align: top;
}
.images .item-content {
  position: relative;
  margin: 5px;
  color: #fff;
  cursor: pointer;
}
.images .item-content > img {
  width: 100%;
}
.item-content .item-options {
  position: absolute;
  top: 0;
  left: 0;
  height: 100%;
  width: 100%;
}
.images .item-content:hover > .item-options {
  box-shadow: inset 0 0 0 2px #f05b41;
}
.item-content .item-options > div {
  position: absolute;
  bottom: 0;
  width: 100%;
  height: 64px;
  padding-top: 4px;
  background-color: rgba(19,32,51,0.8);
  z-index: 1;
  -webkit-box-sizing: border-box;
  -moz-box-sizing: border-box;
  box-sizing: border-box;
  border-left: 2px solid transparent;
  border-right: 2px solid transparent;
}
.images .item-content:hover > .item-options > div {
  border-bottom: 2px solid #f05b41;
  border-left: 2px solid #f05b41;
  border-right: 2px solid #f05b41;
}
.item-content .item-options .address,
.item-content .item-options .price {
  max-width: 70%;
  padding-left: 8px;
}
.item-content .item-options .address {
  font-size: 14px;
}
.item-content .item-options .agent {
  font-size: 12px;
  width: 60px;
  position: absolute;
  right: -2px;
  top: 0;
  bottom: 0;
  text-align: center;
  border-left: 1px solid rgba(255, 255, 255, 0.4);
  line-height: 13px;
}
.item-content .item-options .agent > div:hover {
  color: #f05b41;
}
.item-content .item-options .agent img {
  display: block;
  width: 13px;
  height: 16px;
  margin: 8px auto 2px;
}
.agent-details > img, .agent-details > div {
  display: inline-block;
  vertical-align: top;
  line-height: 26px;
}
.agent-details > img {
  width: 40px;
  margin-right: 10px;
}
.agent-details .phone {
  font-size: 18px;
}
.popup-property-details {
  overflow: hidden;
  position: relative;
}
.popup-property-details .images {
  width: 700px;
}
.popup-property-details img {
  margin: 10px 10px 10px 0;
}
.dx-button.favorites {
  background-color: #f05b41;
  color: #fff;
  position: absolute;
  top: 5px;
  right: 0;
}
.dx-button.favorites .dx-icon {
  color: #fff;
}

</style>
