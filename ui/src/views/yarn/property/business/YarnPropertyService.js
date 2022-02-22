import i18n from "@/app/common/utils/i18n";
import {YarnPropertyDao} from "@/views/yarn/property/persistence/YarnPropertyDao";

/**
 *
 * @author cihanogut
 * @since 15.10.2021
 *
 */
export const YarnPropertyService = function () {
    const selectBoxParameters = {
        dataSource: YarnPropertyDao().yarnPropertyDataSource(),
        displayExpr: (e) => {
            if (e) {
                return e.name;
            } else {
                return null
            }
        },
        valueExpr: (e) => {
            if (e) {
                return {'id': e.id,'name':e.name};
            } else {
                return null
            }
        },
    }
    const selectBoxPropertyParameters = {
        dataSource: [
            {id:'other',  name:i18n.global.t('app.yarn.property.yarn-property-type.other')},
            {id:'color',  name:i18n.global.t('app.yarn.property.yarn-property-type.color')},
            {id:'size',   name:i18n.global.t('app.yarn.property.yarn-property-type.size')},
            {id:'fixed',  name:i18n.global.t('app.yarn.property.yarn-property-type.fixed')},
        ],
        displayExpr: 'name',
        valueExpr: 'id',
    }
    const propertyTypeDataSource = {
        dataSource: [
            {id:'other',  name:i18n.global.t('app.yarn.property.yarn-property-type.other')},
            {id:'color',  name:i18n.global.t('app.yarn.property.yarn-property-type.color')},
            {id:'size',   name:i18n.global.t('app.yarn.property.yarn-property-type.size')},
            {id:'fixed',  name:i18n.global.t('app.yarn.property.yarn-property-type.fixed')},
        ],
    }

    return {
        getSelectBoxParameters: function () {
            return selectBoxParameters;
        },
        getSelectBoxPropertyParameters: function (){
            return selectBoxPropertyParameters;
        },
        getPropertyTypeDataSource: function (){
            return propertyTypeDataSource.dataSource;
        }
    }
};
