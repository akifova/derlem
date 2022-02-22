/**
 *
 * @author akifova
 * 14.10.2021
 *
 */
import CustomStore from "devextreme/data/custom_store";
import restapi from "@/app/common/utils/restapi";

export const YarnGroupLineDao = function () {
    const yarnGroupLineDataSource = function (userId) {
        return new CustomStore({
            key: "yarnProperty.id",
            load: () => {
                return restapi({
                    method: 'GET',
                    url: '/yarn/group-to-property/get-by-group-id/' + userId
                })
            },
        })
    }

    return {
        yarnGroupLineDataSource: function (userId) {
            return yarnGroupLineDataSource(userId);
        }
    }
};
