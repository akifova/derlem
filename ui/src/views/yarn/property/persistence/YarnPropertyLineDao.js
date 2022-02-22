/**
 *
 * @author cihanogut
 * @since 15.10.2021
 *
 */
import CustomStore from "devextreme/data/custom_store";
import restapi from "@/app/common/utils/restapi";

export const YarnPropertyLineDao = function () {
    const yarnPropertyLineDataSource = function(id) {
       return new CustomStore({
            key: 'id',
            load: () => {
                return restapi({
                    method: 'GET',
                    url : `/yarn/property-line/get-by-property-id/${id}`
                })
            },
            insert: (values) => {
                return restapi({
                    method: 'POST',
                    data: values,
                    url : `/yarn/property-line/`
                })
            },
            update: (key, values) => {
                return restapi({
                    method: 'PUT',
                    data: values,
                    url : `/yarn/property-line/`
                })
            },
            remove: (key) => {
                return restapi({
                    method: 'DELETE',
                    url: '/yarn/property-line/' + key
                })
            },
            byKey: function (key) {
                return restapi({
                    method: 'GET',
                    url: `/yarn/property-line/get-by-property-id/${id}` + key
                })
            },
        })
    }

    return {
        yarnPropertyLineDataSource: function (id) {
            return yarnPropertyLineDataSource(id);
        }
    }
};
