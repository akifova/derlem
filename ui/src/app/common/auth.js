import restapi from "@/app/common/utils/restapi";
import store from "@/store";

export default {
  async logIn(username, password) {
    try {
      let url = '/authorization/user/login?email='+ encodeURIComponent(username) + '&password=' + encodeURIComponent(password)
      await restapi({
        method: 'GET',
        url,
      }).then(async () => {
        await restapi({
          method: 'GET',
          url: '/user/account/get/' + encodeURIComponent(username)
        }).then((e) => {

          let data = {
            ...store.state.user,
            'fullName' : `${e.data.name} ${e.data.surname}`,
            'username' : `${username}`,
            'userId' : `${e.data.id}`
          }

          store.commit('setUser',data)

             // localStorage.setItem('fullName', e.data.name + ' ' + e.data.surname)
             // localStorage.setItem('username', username)
             // localStorage.setItem('userId', e.data.id)
            }
        )
      })
      return {
        isOk: true,
      }
    }
    catch {
      return {
        isOk: false,
        message: "Authentication failed"
      };
    }
  },

  async logOut() {
    try{
      console.log('cikis')
      store.commit('setUser',null)
        //localStorage.removeItem('token')
        //localStorage.removeItem('username')
        //localStorage.removeItem('fullName')
        //localStorage.removeItem('userId')
        //localStorage.removeItem('roles')
      return true
    } catch {
      return false
    }
  },

  checkToken(){
    let token = store.getters._getCurrentUser?.token;
    if (token) {
      let url = '/user/authentication/check';
      return restapi({
        method: 'GET',
        url
      });
    } else {
      return new Promise((resolve, reject) => {
        reject(false);
      })
    }
  }
};
