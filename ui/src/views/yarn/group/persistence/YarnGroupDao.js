/**
 *
 * @author cihanogut
 * @since 15.10.2021
 *
 */
import CustomStore from "devextreme/data/custom_store";
import restapi from "@/app/common/utils/restapi";

export const YarnGroupDao = function () {
    const url = '/yarn/group/';
    const yarnGroupDataSource = new CustomStore({
        key: 'id',
        load: () => {
            return restapi({
                method: 'GET',
                url
            })
        },
        insert: (values) => {
            return restapi({
                method: 'POST',
                data: values,
                url
            })
        },
        update: (key, values) => {
            return restapi({
                method: 'PUT',
                data: values,
                url
            })
        },
        remove: (key) => {
            return restapi({
                method: 'DELETE',
                url: url + key
            })
        },
        byKey: function (key) {
            console.log(key);
            return restapi({
                method: 'GET',
                url: url + (typeof key == "object" ? key.id : key)
            })
        },
    })

    return {
        yarnGroupDataSource: function () {
            return yarnGroupDataSource;
        }
    }
};
