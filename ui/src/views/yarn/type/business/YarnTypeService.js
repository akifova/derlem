import {YarnTypeDao} from "@/views/yarn/type/persistence/YarnTypeDao";

/**
 *
 * @author cihanogut
 * @since 15.10.2021
 *
 */
export const YarnTypeService = function () {
    const selectBoxParameters = {
        dataSource: YarnTypeDao().yarnTypeDataSource(),
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
