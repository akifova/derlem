import notify from 'devextreme/ui/notify';
import CustomStore from "devextreme/data/custom_store";
import restapi from "@/app/common/utils/restapi";
import i18n from '@/app/common/utils/i18n';

export default {
    showMessage(message, types) {
        const messageData = message.response?.data;
        switch (types){
            case 'error':
                if(Array.isArray(messageData)) {
                    let msg = "";
                    for (let i = 0; i < messageData.length; i++) {
                        if(messageData[i]["type"] == "translated") {
                            msg = "- " + messageData[i]["message"] + "\n";
                        } else {
                            msg = "- " + messageData[i] + "\n";
                        }
                    }
                    return notify(msg, 'error', 4000);
                } else {
                    return notify(messageData, 'error', 4000);
                }
            default:
                return notify(i18n.global.t('common.created'), 'success', 4000);
        }
    },
    CustomStoreService(url, key = 'id') {
        return new CustomStore({
            key,
            load: () => {
                return restapi({
                    method: 'GET',
                    url
                });
            },
            byKey: function (key) {
                return key;
            }
        })
    },
    RestService(url, data, method = 'GET', headers) {
        return restapi({
            method,
            url,
            headers,
            data
        });
    },
    timestampToDate(time) {
        return new Date(time).toLocaleDateString("tr-TR")
    },
    trDateTimeFormat(date){
        console.log(date)
        let dateTime = new Date(date.year,date.monthValue,date.dayOfMonth,date.hour,date.minute,date.second)
        return dateTime.toLocaleDateString("tr-TR")+' '+dateTime.toLocaleTimeString("tr-TR")
    },
    downloadImage (url,fileName) {
        restapi({
            url,
            method:'GET',
            responseType:'blob'
        }).then(res => {
            let fileUrl = window.URL.createObjectURL(new Blob([res.data]))
            let fileLink = document.createElement('a')
            fileLink.href = fileUrl

            fileLink.setAttribute('download',fileName)
            document.body.appendChild(fileLink)
            fileLink.click()
        })
    },
    downloadZip (url,fileName) {
        restapi({
            method:'GET',
            url,
            responseType: 'arraybuffer'
        }).then(res => {
            let fileUrl = window.URL.createObjectURL(new Blob([res.data]))
            let fileLink = document.createElement('a')
            fileLink.href = fileUrl

            fileLink.setAttribute('download',fileName)
            document.body.appendChild(fileLink)
            fileLink.click()
        })
    },
    convertToTreeDigit(val){
        return val.toLocaleString('en-US',{
            minimumIntegerDigits: 3,
            useGrouping: false
        })
    },
    thumbnailPicture(url,fileName) {
        let file = fileName.split(".")[0]
        return process.env.BASE_URL+url+file+'.png';
    },
    displayJpgImage (url) {
       return restapi({
            url,
            method:'GET',
            responseType:'blob'
        }).then(res => {
            return window.URL.createObjectURL(new Blob([res.data]))
        })
    },
    isObjectEmpty(obj) {
        return Object.keys(obj).length === 0;
    }
}
