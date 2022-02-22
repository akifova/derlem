import CustomStore from "devextreme/data/custom_store";
import restapi from "@/app/common/utils/restapi";

/**
 *
 * @author cihanogut
 * @since 15.10.2021
 *
 */
export const PatternCodeDao = function () {
    const url = '/scm/production/definition/stock-card/design/';
    const patternCodeDataSource = new CustomStore({
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
            return restapi({
                method: 'GET',
                url: url + key
            })
        },
    })

    return {
        patternCodeDataSource: function () {
            return patternCodeDataSource;
        }
    }
};
