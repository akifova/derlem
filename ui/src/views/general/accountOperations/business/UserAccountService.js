/**
 *
 * @author akifova
 * 14.10.2021
 *
 */

import {CustomStoreCreator} from "@/app/common/utils/CustomStoreCreator";

export const UserAccountService = function () {
    const tagBoxParameters = {
        dataSource: CustomStoreCreator().createStore("/user/role/get/"),
        displayExpr: (e) => {
            if (e) {
                return e.description;
            } else {
                return null
            }
        },
    }

    return {
        getTagBoxParameters: function () {
            return tagBoxParameters;
        }
    }
};
