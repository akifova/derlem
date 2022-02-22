import 'devextreme/dist/css/dx.common.css';
import './app/common/themes/generated/theme.base.css';
import './app/common/themes/generated/theme.additional.css';
import '@dafcoe/vue-notification/dist/vue-notification.css'

import {createApp} from "vue";
import router from "./router";
import store from "./store";
import App from "./App";
import appInfo from "./app/common/app-info";
import i18n from './app/common/utils/i18n'
import trMessages from "devextreme/localization/messages/tr.json";

import {loadMessages, locale} from "devextreme/localization";

import {dom, library} from "@fortawesome/fontawesome-svg-core";
import {FontAwesomeIcon} from "@fortawesome/vue-fontawesome";
import {fas} from '@fortawesome/free-solid-svg-icons'
import {far} from '@fortawesome/free-regular-svg-icons';
import VueNotificationList from '@dafcoe/vue-notification'

library.add(fas);
library.add(far);
dom.watch();

const app = createApp(App).use(i18n);
app.use(loadMessages(trMessages));
app.use(locale("tr"));
app.component("font-awesome-icon", FontAwesomeIcon)
app.use(store)
app.use(router);
app.config.globalProperties.$appInfo = appInfo;
app.config.globalProperties.$activeProfile = process.env.ACTIVE_PROFILE;
app.use(VueNotificationList)

String.prototype.isEmpty = function() {
    return (this.length === 0 || !this.trim());
};
store.commit('setIsLoadPanelVisible', false);

app.mount('#app');
