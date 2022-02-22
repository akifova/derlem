/**
 *
 * @author akifova
 * 14.10.2021
 *
 */
import CustomStore from "devextreme/data/custom_store";
import restapi from "@/app/common/utils/restapi";

export const UserAuthorizationDao = function () {
    const userAuthorizationDataSource = function (userId) {
        return new CustomStore({
            key: "id",
            load: () => {
                return restapi({
                    method: 'GET',
                    url: '/user/authorization/' + userId
                })
            },
            insert: (data) => {
                return restapi({
                    method: 'PUT',
                    url: '/user/authorization/' + userId + "/" + data.role
                });
            },
            remove: (key) => {
                return restapi({
                    method: 'DELETE',
                    url: '/user/authorization/' + key
                })
            }
        })
    }

    return {
        userAuthorizationDataSource: function (userId) {
            return userAuthorizationDataSource(userId);
        }
    }
};