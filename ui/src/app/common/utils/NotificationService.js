import {useNotificationStore} from '@dafcoe/vue-notification'
import _ from 'lodash';
import {XhrErrorParser} from "@/app/common/utils/XhrErrorParser";

/**
 *
 * @author akifova
 * 15.10.2021
 *
 */
export const NotificationService = function () {
    const showToaster = function (property) {
        let defaultProps = {
            "type": "info",
            "showIcon": true,
            "dismiss": {
                "manually": true,
                "automatically": true
            },
            "duration": 15000,
            "showDurationProgress": true,
            "appearance": "dark"
        };

        let setterKeys = Object.keys(property).filter(f => f == "message");

        setterKeys.forEach(k => {
            if(property[k].isEmpty()) {
                delete property[k];
            }
        })
        const messageProperty = _.extend({}, defaultProps, property);

        const { setNotification } = useNotificationStore();
        setNotification(messageProperty);
    };

    function showXhrError(error) {
        if(typeof error == "string") {
            let errs = [];
            errs.push(error);
            return errs;
        }
        const xhrMessages = XhrErrorParser().parserError(error);
        xhrMessages.catch(m => {
            if(Array.isArray(m)) {
                m.forEach(xhrMessage=> {
                    xhrMessage.catch(e => {
                        showToaster( {"message": e, "type": "alert"});
                    })
                })
            } else {
                xhrMessages.catch(e => {
                    showToaster( {"message": e, "type": "alert"});
                })
            }
        });
    }

    return {
        showXhrError: function (error) {
            showXhrError(error);
        },
        showToaster: function (property) {
            showToaster(property);
        }
    }
};
