/**
 *
 * @author akifova
 * 15.10.2021
 *
 */
export const XhrErrorParser = function () {
    const parseError = function (error) {
        if(error.response) {
            return responseError(error);
        } else if (error.request) {
            return requestError(error);
        } else {
            return error;
        }
    };

    const responseError = function (error) {
        const errs = error.response.data;
        let messages = "";
        if(Array.isArray(errs)){
            errs.forEach(err =>{
                if(err.type === "translated") {
                    messages = messages + err.message + "\n";
                }
            });
        } else {
            messages = error.response.data;
        }
        return Promise.reject(messages);
    }

    const requestError = function (error) {
        return Promise.reject(error.request);
    }

    return {
        parserError: function (error) {
            return parseError(error);
        }
    }
};