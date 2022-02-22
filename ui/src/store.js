import {createStore} from "vuex";
import createPersistedState from "vuex-persistedstate";

import SecureLS from "secure-ls";

const ls = new SecureLS({isCompression: false});

const key = process.env.BASE_URL;
const store = createStore({
    state: {
        user: null,
        isLoadPanelVisible: false
    },
    mutations: {
        setIsLoadPanelVisible(state, loadPanel) {
            state.isLoadPanelVisible = loadPanel
        },
        setUser(state, user) {
            state.user = user;
        },
        setToken(state, token) {
            const data = {...state.user}
            data.token = token
            state.user = data
        },
        setRoles(state, roles) {
            const data = {...state.user}
            data.roles = roles
            state.user = data
        },
    },
    getters: {
        _isAuthenticated(state) {
            if (state.user !== null) {
                return true
            } else {
                return false;
            }
        },
        _getCurrentUser(state) {
            return state.user;
        },
        _getIsLoadPanelVisible(state) {
            return state.isLoadPanelVisible
        }
    },
    plugins: [createPersistedState({
        key,
        storage: {
            getItem: key => ls.get(key),
            setItem: (key, value) => ls.set(key, value),
            removeItem: key => ls.remove(key)
        }
    })],
})

export default store;
