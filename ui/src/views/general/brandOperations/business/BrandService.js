import {CustomStoreCreator} from "@/app/common/utils/CustomStoreCreator";

/**
 *
 * @author cihanogut
 * @since 15.10.2021
 *
 */
export const BrandService = function () {
    const selectBoxParameters = {
        dataSource: CustomStoreCreator().createStore("/common/brand/"),
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

    return {
        getSelectBoxParameters: function () {
            return selectBoxParameters;
        }
    }
};
