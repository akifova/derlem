<template>
  <div id="root">
    <div :class="cssClasses">
      <component
          :is="$route.meta.layout"
          :title="title"
          :is-x-small="screen.getScreenSizeInfo.isXSmall"
          :is-large="screen.getScreenSizeInfo.isLarge"
      >
        <div class="content">
          <load-panel/>
          <router-view></router-view>
          <vue-notification-list
              position="bottom-right"
              show-icon="true"
              duration="10000"
              show-duration-progress="true"
              apperance="dark"
          />
        </div>
        <template #footer>
          <app-footer />
        </template>
      </component>
    </div>
  </div>
</template>

<script>
import AppFooter from "./app/common/layout/components/app-footer";
import {sizes, subscribe, unsubscribe} from "./app/common/utils/media-query";
import {computed, getCurrentInstance, onBeforeUnmount, onMounted, reactive} from "vue";
import loadPanel from "@/app/common/layout/components/load-panel";

function getScreenSizeInfo() {
  const screenSizes = sizes();

  return {
    isXSmall: screenSizes["screen-x-small"],
    isLarge: screenSizes["screen-large"],
    cssClasses: Object.keys(screenSizes).filter(cl => screenSizes[cl])
  };
}

export default {
  components: {
    AppFooter,
    loadPanel
  },
  setup() {
    const vm = getCurrentInstance();

    const title = vm.proxy.$appInfo.title;
    const screen = reactive({ getScreenSizeInfo: {} });
    screen.getScreenSizeInfo = getScreenSizeInfo();

    function screenSizeChanged () {
      screen.getScreenSizeInfo = getScreenSizeInfo();
    }

    onMounted(() => {
      subscribe(screenSizeChanged);
    });

    onBeforeUnmount(() => {
      unsubscribe(screenSizeChanged);
    });

    const cssClasses = computed(() => {
      return ["app"].concat(screen.getScreenSizeInfo.cssClasses);
    });

    return {
      title,
      screen,
      cssClasses
    };
  }
};
</script>

<style lang="scss">
html,
body {
  margin: 0px;
  min-height: 100%;
  height: 100%;
}

#root {
  height: 100%;
}

* {
  box-sizing: border-box;
}

.app {
  @import "src/app/common/themes/generated/variables.base.scss";
  background-color: darken($base-bg, 5);
  display: flex;
  height: 100%;
  width: 100%;
}
</style>
