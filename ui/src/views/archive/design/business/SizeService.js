/**
 *
 * @author cihanogut
 * @since 10.11.2021
 *
 */
import {CustomStoreCreator} from "@/app/common/utils/CustomStoreCreator";

export const SizeService = function (){
    const selectBoxParameters = {
        dataSource: CustomStoreCreator().createLookupStore(`/scm/production/definition/stock-card/measure/search`, ['code', 'definition']),
        searchEnabled: true,
        minSearchLength: 2,
        displayExpr: (e) => {
            if (e) {
                return e.definition;
            } else {
                return null
            }
        }
    }

    return {
        getSelectBoxParameters: function (){
            return selectBoxParameters;
        }
    }
}
