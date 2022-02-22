import CustomStore from "devextreme/data/custom_store";
import restapi from "@/app/common/utils/restapi";

/**
 *
 * @author akifova
 * 16.10.2021
 *
 */
export const CustomStoreCreator = function () {
    const createStore = function (properties) {
        return new CustomStore({
            key: properties.key,
            load: () => {
                return restapi({
                    method: properties.load.method,
                    url: properties.load.url
                })
            },
            insert: (values) => {
                return restapi({
                    method: properties.insert.method,
                    data: values,
                    url: properties.insert.url
                })
            },
            update: (key, values) => {
                return restapi({
                    method: properties.update.method,
                    data: values,
                    url: properties.update.url
                })
            },
            remove: (key) => {
                return restapi({
                    method: properties.remove.method,
                    url: properties.remove.url + key
                })
            },
            byKey: function (key) {
                return restapi({
                    method: properties.byKey.method,
                    url: properties.byKey.url + (typeof key == "object" ? key.id : key)
                })
            }
        });
    };

    const createByDefaultUrl = function (_defaultUrl, _key) {
        let opts = {
            "key": _key ? _key : "id",
            "load": {"method": "GET", "url": _defaultUrl},
            "insert": {"method": "POST", "url": _defaultUrl},
            "update": {"method": "PUT", "url": _defaultUrl},
            "remove": {"method": "DELETE", "url": _defaultUrl},
            "byKey": {"method": "GET", "url": _defaultUrl == '/user/account/' ? _defaultUrl + 'get/' : _defaultUrl},
        };
        return createStore(opts);
    }

    const createStoreFromUrl = function (loadUrl, insertUrl, updateUrl, removeUrl, byKeyUrl, key) {
        let opts = {
            "key": key ? key : "id",
            "load": {"method": "GET", "url": loadUrl},
            "insert": {"method": "POST", "url": insertUrl},
            "update": {"method": "PUT", "url": updateUrl},
            "remove": {"method": "DELETE", "url": removeUrl},
            "byKey": {"method": "GET", "url": byKeyUrl},
        };
        return createStore(opts);
    }

    const loadStore = (loadUrl, key) => {
        if(!key) {key = "id"}
        return new CustomStore({
            key: key,
            load: () => {
                return restapi({
                    method: "GET",
                    url: loadUrl
                })
            },
            byKey: function (key) {
                let keyUrl = loadUrl === '/user/account/' ? loadUrl + 'get/' : loadUrl;
                return restapi({
                    method: 'GET',
                    url: keyUrl + (typeof key == "object" ? key.id : key)
                }).then(result => result.data)
            }
        });
    }

    const lookupStore = (loadUrl, searchFields, key) => {
        if(!key) {key = "id"}
        return new CustomStore({
            key: key,
            //remoteOperations: true,
            load: (loadOptions) => {
                let sp = '';
                for (let i = 0; i < searchFields.length; i++) {
                    if(i===0) {
                        sp = searchFields[i] + '==*' + loadOptions.searchValue + '*';
                    } else {
                        sp = sp + ',' + searchFields[i] + '==*' + loadOptions.searchValue + '*';
                    }
                }
                return restapi({
                    method: "GET",
                    url: loadUrl + '?sp=' + sp,
                })
            },
            byKey: function (key) {
                let getUrl = loadUrl.replace('/search', '/');
                getUrl = getUrl === '/user/account/' ? getUrl + 'get/' : getUrl;
                return restapi({
                    method: 'GET',
                    url: getUrl + (typeof key == "object" ? key.id : key)
                }).then(result => result.data)
            }
        });
    }

    return {
        createLookupStore: function (loadUrl, searchFields, key) {
          return lookupStore(loadUrl, searchFields, key);
        },
        createLoadStore: function (loadUrl, key) {
            return loadStore(loadUrl, key);
        },
        createStore: function (defaultUrl, key) {
            return createByDefaultUrl(defaultUrl, key);
        },
        createStoreFromUrl: function (loadUrl, insertUrl, updateUrl, removeUrl, byKeyUrl, key) {
            return createStoreFromUrl(loadUrl, insertUrl, updateUrl, removeUrl, byKeyUrl, key);
        },
        createStoreWithAllProperties: function (props) {
            if(typeof props == "string") {
                return createByDefaultUrl(props);
            } else {
                return createStore(props);
            }
            /*const exampleProps ={
                "key": "id",
                "load": {"method": "GET", "url": "loadUrl"},
                "insert": {"method": "POST", "url": "insertUrl"},
                "update": {"method": "PUT", "url": "updateUrl"},
                "remove": {"method": "DELETE", "url": "removeUrl"},
                "byKey": {"method": "GET", "url": "byKeyUrl"},
            }*/
        }
    }
};
