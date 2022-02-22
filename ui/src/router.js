import auth from "./app/common/auth";
import store from "@/store";
import {createRouter, createWebHashHistory} from "vue-router";

import i18n from "@/app/common/utils/i18n";

import Home from "./views/home";
import defaultLayout from "./app/common/layout/side-nav-outer-toolbar";
import simpleLayout from "./app/common/layout/single-card";
import roleService from "@/app/common/utils/role";

/*
######## GENERAL #######
*/
import brandOperations from "./views/general/brandOperations/list";
import uomOperations from "./views/general/uomOperations/list";
import accountOperations from "./views/general/accountOperations/list";
import profile from "./views/general/accountOperations/profile";
import firmOperations from "./views/general/firmOperations/list";
/*
######## YARN #######
*/
import yarnType from "./views/yarn/type/list";
import propertyType from "./views/yarn/property/list";
import yarnGroup from "./views/yarn/group/yarnGroup";
import yarn from "./views/yarn/list";
import yarnList from "./views/yarn/yarnList";
/*
######## NUMUNE #######
*/
import specimenYarn from "./views/specimen/yarn/list"
import creel from "./views/specimen/creel/list";
import designPlanGrid from "./views/specimen/creel/list/designPlanGrid";
/*
/*
######## DESEN #######
*/
import designPlan from "./views/design/plan/designPlan"
/*
######## ARCHIVE #######
*/
import archiveDesign from "./views/archive/design/design"
import archiveAddDesign from "./views/archive/design/AddDesign"
import archiveDesignList from "./views/archive/designlist/list"
import searchPanel from "@/views/archive/search/SearchPanel";
import showRoom from "@/views/archive/showroom/ShowRoom";
/*
######## STOCK CARD #######
 */
import qualityOperations from "./views/stockCard/qualityOperations/list";
import patternCodeOperations from "./views/stockCard/patternCodeOperations/list";
import sizeDefinitionOperations from "./views/stockCard/sizeDefinitionOperations/list";
import shapeOperations from "./views/stockCard/shapeOperations/list";
import edge from "./views/stockCard/edge/list";
import collection from "./views/stockCard/collection/collection";
import paletteCreate from "./views/stockCard/palette/paletteCreate";
import paletteList from "./views/stockCard/palette/list/list";
/*
######## LICENSE #######
 */
import licenseAdd from "./views/license/add"
import licenseAll from "@/views/license/licenseAll"

function loadView(view) {
    return () => import (/* webpackChunkName: "login" */ `./views/authorization/${view}.vue`)
}

const router = new createRouter({
    routes: [
        /*
        ######## GENERAL #######
        */
        {
            path: "/general/brandOperations",
            name: "brandOperations",
            meta: {
                requiresAuth: true,
                layout: defaultLayout
            },
            component: brandOperations
        },
        {
            path: "/general/uomOperations",
            name: "uomOperations",
            meta: {
                requiresAuth: true,
                layout: defaultLayout
            },
            component: uomOperations
        },
        {
            path: "/general/accountOperations",
            name: "accountOperations",
            meta: {
                requiresAuth: true,
                layout: defaultLayout
            },
            component: accountOperations
        },
        {
            path: "/profile",
            name: "profile",
            meta: {
                requiresAuth: true,
                layout: defaultLayout
            },
            component: profile
        },
        {
            path: "/general/firmOperations",
            name: "firmOperations",
            meta: {
                requiresAuth: true,
                layout: defaultLayout
            },
            component: firmOperations
        },
        /*
        ######## YARN #######
        */
        {
            path: "/yarn/type",
            name: "yarnType",
            meta: {
                requiresAuth: true,
                layout: defaultLayout
            },
            component: yarnType
        },
        {
            path: "/yarnList",
            name: "yarnList",
            meta: {
                requiresAuth: true,
                layout: defaultLayout
            },
            component: yarnList
        },
        {
            path: "/yarn/property",
            name: "propertyType",
            meta: {
                requiresAuth: true,
                layout: defaultLayout
            },
            component: propertyType
        },
        {
            path: "/yarn/group",
            name: "yarnGroup",
            meta: {
                requiresAuth: true,
                layout: defaultLayout
            },
            component: yarnGroup
        },
        {
            path: "/yarn",
            name: "yarn",
            meta: {
                requiresAuth: true,
                layout: defaultLayout
            },
            component: yarn
        },
        /*
        ######## NUMUNE #######
        */
        {
            path: "/specimen-yarn",
            name: "specimenYarn",
            meta: {
                requiresAuth: true,
                layout: defaultLayout
            },
            component: specimenYarn
        },
        {
            path: "/creel",
            name: "creel",
            meta: {
                requiresAuth: true,
                layout: defaultLayout
            },
            component: creel
        },
        {
            path: "/creelList",
            name: "creelList",
            meta: {
                requiresAuth: true,
                layout: defaultLayout
            },
            component: designPlanGrid
        },
        /*
        ######## DESIGN #######
        */
        {
            path: "/design/plan",
            name: "designPlan",
            meta: {
                requiresAuth: true,
                layout: defaultLayout
            },
            component: designPlan
        },
        /*
        ######## ARCHIVE #######
        */
        {
            path: "/archive/add-design",
            name: "archiveAddDesign",
            meta: {
                requiresAuth: true,
                layout: defaultLayout
            },
            component: archiveAddDesign
        },
        {
            path: "/archive/design",
            name: "archiveDesign",
            meta: {
                requiresAuth: true,
                layout: defaultLayout
            },
            component: archiveDesign
        },
        {
            path: "/archive/design/list",
            name: "archiveDesignList",
            meta: {
                requiresAuth: true,
                layout: defaultLayout
            },
            component: archiveDesignList
        },
        {
            path: "/archive/design/search",
            name: "archiveSearch",
            meta: {
                requiresAuth: true,
                layout: defaultLayout
            },
            component: searchPanel
        },
        {
            path: "/archive/design/showroom",
            name: "showRoom",
            meta: {
                requiresAuth: true,
                layout: defaultLayout
            },
            component: showRoom
        },
        /*
          ######## STOCK CARD #######
          */
        {
            path: "/stockCard/qualityOperations",
            name: "fieldDefinitions",
            meta: {
                requiresAuth: true,
                layout: defaultLayout
            },
            component: qualityOperations
        },
        {
            path: "/stockCard/patternCodeOperations",
            name: "patternCodeOperations",
            meta: {
                requiresAuth: true,
                layout: defaultLayout
            },
            component: patternCodeOperations
        },
        {
            path: "/stockCard/palette",
            name: "palette",
            meta: {
                requiresAuth: true,
                layout: defaultLayout
            },
            component: paletteCreate
        },
        {
            path: "/stockCard/paletteList",
            name: "paletteList",
            meta: {
                requiresAuth: true,
                layout: defaultLayout
            },
            component: paletteList
        },
        {
            path: "/stockCard/sizeDefinitionOperations",
            name: "sizeDefinitionOperations",
            meta: {
                requiresAuth: true,
                layout: defaultLayout
            },
            component: sizeDefinitionOperations
        },
        {
            path: "/stockCard/shapeOperations",
            name: "shapeOperations",
            meta: {
                requiresAuth: true,
                layout: defaultLayout
            },
            component: shapeOperations
        },
        {
            path: "/stockCard/edge",
            name: "edge",
            meta: {
                requiresAuth: true,
                layout: defaultLayout
            },
            component: edge
        },
        {
            path: "/stockCard/collection",
            name: "collection",
            meta: {
                requiresAuth: true,
                layout: defaultLayout
            },
            component: collection
        },

        /////////////////////////////////////////
        {
            path: "/home",
            name: "home",
            meta: {
                requiresAuth: true,
                layout: defaultLayout
            },
            component: Home
        },
        {
            path: "/license/add",
            name: "licenseAdd",
            meta: {
                requiresAuth: true,
                layout: defaultLayout
            },
            component: licenseAdd
        },
        {
            path: "/license/all",
            name: "licenseAll",
            meta: {
                requiresAuth: true,
                layout: defaultLayout
            },
            component: licenseAll
        },
        {
            path: "/login-form",
            name: "login-form",
            meta: {
                requiresAuth: false,
                layout: simpleLayout,
                title: i18n.global.t('app.login.sign-in')
            },
            component: loadView("login-form")
        },
        {
            path: "/reset-password",
            name: "reset-password",
            meta: {
                requiresAuth: false,
                layout: simpleLayout,
                title: "Reset Password",
                description: "Please enter the email address that you used to register, and we will send you a link to reset your password via Email."
            },
            component: loadView("reset-password-form")
        },
        {
            path: "/create-account",
            name: "create-account",
            meta: {
                requiresAuth: false,
                layout: simpleLayout,
                title: "Sign Up"
            },
            component: loadView("create-account-form"),
        },
        {
            path: "/change-password/:recoveryCode",
            name: "change-password",
            meta: {
                requiresAuth: false,
                layout: simpleLayout,
                title: "Change Password"
            },
            component: loadView("change-password-form")
        },
        {
            path: "/",
            redirect: "/home"
        },
        {
            path: "/recovery",
            redirect: "/home"
        },
        {
            path: "/api",
            beforeEnter() {
                location.href = 'http://localhost:8080/api'
            }
        },
        {
            path: "/:pathMatch(.*)*",
            redirect: "/home"
        }
    ],
    history: createWebHashHistory(process.env.BASE_URL)
});


router.beforeEach((to, from, next) => {
    //console.log(to, from.next)
    let token = store.getters._getCurrentUser?.token;
    if (to.name === "login-form" && token) {
        next({path: "/home"});
    }

    if (to.matched.some(record => record.meta.requiresAuth)) {
        if (!token) {
            next({
                name: "login-form",
                query: {redirect: to.fullPath}
            });
        } else {
            auth.checkToken()
                .then(() => {
                    next();
                })
                .catch(() => {
                    store.commit('setUser', null)
                    next({path: "/login-form"});
                })
        }
    } else {
        const publicPaths = ['/login-form', '/login-form?redirect=/home'];
        if (roleService.checkPath(to.fullPath) || publicPaths.indexOf(to.fullPath) > -1) {
            next()
        }
    }
});

export default router;
