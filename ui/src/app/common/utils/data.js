import CustomStore from "devextreme/data/custom_store";
import restapi from "@/app/common/utils/restapi";

export const quality = new CustomStore({
    key: 'id',
    load: () => {
        return restapi({
            method: 'GET',
            url: `/scm/production/definition/stock-card/quality/`
        })
    },
    byKey: function (key) {
        return key
    },
})

export const yarn = new CustomStore({
    key: 'id',
    load: () => {
        return restapi({
            method: 'GET',
            url: `/yarn/`
        })
    },
    byKey: function (key) {
        return key
    },
})

export const brand = new CustomStore({
    key: 'id',
    load: () => {
        return restapi({
            method: 'GET',
            url: `/common/brand/`
        })
    },
    byKey: function (key) {
        return key
    },
})

export const uom = new CustomStore({
    key: 'id',
    load: () => {
        return restapi({
            method: 'GET',
            url: `/common/uom/`
        })
    },
    byKey: function (key) {
        return key
    },
})

export const ArchiveCountByStatus = new CustomStore({
    key: 'id',
    load: () => {
        return restapi({
            method: 'GET',
            url: `/dashboard/archive/count/by-status/`
        })
    },
    byKey: function (key) {
        return key
    },
})
export const ArchiveCountByStarRating = new CustomStore({
    key: 'id',
    load: () => {
        return restapi({
            method: 'GET',
            url: `/dashboard/archive/count/by-star-rating/`
        })
    },
    byKey: function (key) {
        return key
    },
})
export const ArchiveCountByDifficulty = new CustomStore({
    key: 'id',
    load: () => {
        return restapi({
            method: 'GET',
            url: `/dashboard/archive/count/by-difficulty/`
        })
    },
    byKey: function (key) {
        return key
    },
})
export const ArchiveCountByDesigner = new CustomStore({
    key: 'id',
    load: () => {
        return restapi({
            method: 'GET',
            url: `/dashboard/archive/count/by-designer/`
        })
    },
    byKey: function (key) {
        return key
    },
})

export const licenseAll = new CustomStore({
    key: 'id',
    load: () => {
        return restapi({
            method: 'GET',
            url: `/license/all`
        })
    },
    byKey: function (key) {
        return key
    },
})

export const licenseActives = new CustomStore({
    key: 'id',
    load: () => {
        return restapi({
            method: 'GET',
            url: `/license/actives`
        })
    },
    byKey: function (key) {
        return key
    },
})
