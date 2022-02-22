import i18n from "@/app/common/utils/i18n";

export default [
    {
        text: i18n.global.t('app.menu.home'),
        path: '/home',
        icon: 'fas fa-home'
    },
    {
        text: i18n.global.t('app.menu.archive.text'),
        icon: 'fas fa-palette',
        items: [
            {
                text: i18n.global.t('app.menu.design.plan.text'),
                path: '/design/plan'
            },
            {
                text: i18n.global.t('app.menu.archive.designList.text'),
                path: '/archive/design'
            },
            {
                text: i18n.global.t('app.archive.designList.convert-to-sample'),
                path: '/archive/design/list'
            },
            {
                text: i18n.global.t('app.menu.archive.addDesign'),
                path: '/archive/add-design'
            },
            {
                text: i18n.global.t('app.menu.archive.search.text'),
                path: '/archive/design/search'
            },
            {
                text: i18n.global.t('app.menu.archive.showroom.text'),
                path: '/archive/design/showroom'
            }
        ]
    },
    {
        text: i18n.global.t('app.menu.stock-card.text'),
        icon: 'fas fa-bezier-curve',
        items: [
            {
                text: i18n.global.t('app.menu.stock-card.quality.text'),
                path: '/stockCard/qualityOperations'
            },
            {
                text: i18n.global.t('app.menu.stock-card.collection.text'),
                path: '/stockCard/collection'
            },
            {
                text: i18n.global.t('app.menu.stock-card.pattern-code.text'),
                path: '/stockCard/patternCodeOperations'
            },
            {
                text: i18n.global.t('app.menu.stock-card.size-definitions.text'),
                path: '/stockCard/sizeDefinitionOperations'
            },
            {
                text: i18n.global.t('app.menu.stock-card.palette.text'),
                path: '/stockCard/palette'
            },
            {
                text: i18n.global.t('app.menu.stock-card.palette.list'),
                path: '/stockCard/paletteList'
            },
            {
                text: i18n.global.t('app.menu.stock-card.shape.text'),
                path: '/stockCard/shapeOperations'
            },
            {
                text: i18n.global.t('app.menu.stock-card.edge.text'),
                path: '/stockCard/edge'
            },
        ]
    },
    {
        text: i18n.global.t('app.menu.specimen.text'),
        icon: 'fas fa-disease',
        items: [
            {
                text: i18n.global.t('app.menu.specimen.creel.text'),
                path: '/creel'
            },
            {
                text: i18n.global.t('app.menu.specimen.creel.list'),
                path: '/creelList'
            },
            {
                text: i18n.global.t('app.menu.specimen.yarn.text'),
                path: '/specimen-yarn'
            }
        ]
    },
    {
        text: i18n.global.t('app.menu.yarn.text'),
        icon: 'fas fa-bacon',
        items: [
            {
                text: i18n.global.t('app.menu.yarn.title'),
                path: '/yarn'
            },
            {
                text: i18n.global.t('app.menu.yarn.list'),
                path: '/yarnList'
            },
            {
                text: i18n.global.t('app.menu.yarn.property.text'),
                path: '/yarn/property'
            },
            {
                text: i18n.global.t('app.menu.yarn.group.text'),
                path: '/yarn/group'
            },
            {
                text: i18n.global.t('app.menu.yarn.type.text'),
                path: '/yarn/type'
            }
        ]
    },
    {
        text: i18n.global.t('app.menu.general.text'),
        icon: 'fas fa-cogs',
        items: [
            {
                text: i18n.global.t('app.menu.general.account.text'),
                path: '/general/accountOperations'
            },
            {
                text: i18n.global.t('app.menu.general.firm.text'),
                path: '/general/firmOperations'
            },
            {
                text: i18n.global.t('app.menu.general.uom.text'),
                path: '/general/uomOperations'
            },
            {
                text: i18n.global.t('app.menu.general.brand.text'),
                path: '/general/brandOperations'
            }
        ]
    },
    {
        text: i18n.global.t('app.menu.license.text'),
        icon: 'fas fa-key',
        items: [
            {
                text: i18n.global.t('app.menu.license.all'),
                path: '/license/all'
            },
            {
                text: i18n.global.t('app.menu.license.add'),
                path: '/license/add'
            }
        ]
    },
]
