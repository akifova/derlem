import axios from 'axios';
import store from "@/store";
import {NotificationService} from "@/app/common/utils/NotificationService";
import {XhrErrorParser} from "@/app/common/utils/XhrErrorParser";

let restapi = axios.create({
    baseURL: process.env.BASE_URL,
    timeout: 60000
});

restapi.interceptors.request.use(
    (config) => {
        store.commit('setIsLoadPanelVisible', true)
        //console.log("Başladı:", store.getters._getIsLoadPanelVisible)
        let token = store.getters._getCurrentUser?.token;
        config.headers['Authorization'] = `${token}`;
        return config;
    },
    (error) => {
        console.log('hata var');
        return Promise.reject(error);
    }
);

restapi.interceptors.response.use(
    (response) => {
        if (response.headers.authorization){
            store.commit('setToken', response.headers.authorization)
            //localStorage.setItem('token',response.headers.authorization)
        }
        if (response.headers.roles){
            store.commit('setRoles',response.headers.roles)
            //localStorage.setItem('roles',response.headers.roles)
        }

        switch (response.config.method.toUpperCase()) {
            case "POST":
                NotificationService().showToaster({"message": "Kayıt İşlemi Başarılı"})
                break;
            case "PUT":
                NotificationService().showToaster({"message": "Güncelleme İşlemi Başarılı"})
                break;
            case "DELETE":
                NotificationService().showToaster({"message": "Silme İşlemi Başarılı", "type": "info"})
                break
            default:
        }

        store.commit('setIsLoadPanelVisible', false)

        return response;
    },
    (error) => {
        if (error.response.status == 401) {
            console.log('Giriş başarısız')
            store.commit('setUser',null)
            NotificationService().showToaster({message:"Giriş Başarısız oldu. \nLütfen Email ve Şifrenizi Kontrol Ediniz.", type:"warning"})
        } else {
            NotificationService().showXhrError(error);
        }
        store.commit('setIsLoadPanelVisible', false)
        return XhrErrorParser().parserError(error);
    }
);

export default restapi;

