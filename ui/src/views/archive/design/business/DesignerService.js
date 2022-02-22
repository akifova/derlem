/**
 *
 * @author cihanogut
 * @since 10.11.2021
 *
 */
import {CustomStoreCreator} from "@/app/common/utils/CustomStoreCreator";

export const DesignerService = function (){
    const selectBoxParameters = {
        dataSource: CustomStoreCreator().createLookupStore(`/user/account/get/search`, ['name', 'surname'], 'email'),
        searchEnabled: true,
        minSearchLength: 2,
        displayExpr: (e) => {
            if (e) {
                return e.nameSurname;
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
