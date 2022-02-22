/**
 *
 * @author cihanogut
 * @since 10.11.2021
 *
 */
import {CustomStoreCreator} from "@/app/common/utils/CustomStoreCreator";

export const CustomerService = function (){
    const selectBoxParameters = {
        dataSource: CustomStoreCreator().createLookupStore(`/common/firm/search`, ['name', 'tradeName']),
        searchEnabled: true,
        minSearchLength: 2,
        displayExpr: (e) => {
            if (e) {
                return e.name;
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
