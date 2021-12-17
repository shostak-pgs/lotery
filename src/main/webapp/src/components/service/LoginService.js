import { Component } from 'react';
import BaseService from './BaseService';

const LOGIN_API_URL = 'https://godel-lotery.herokuapp.com/api/login';
const LOGOUT_API_URL = 'https://godel-lotery.herokuapp.com/api/logout';
const APPLICATION_API_URL = 'https://godel-lotery.herokuapp.com/api';

class LoginService extends Component {
    constructor(props) {
        super(props)
        this.logout = this.logout.bind(this);
        this.login = this.login.bind(this);
        this.createUser.bind(this);
        this.runLotery.bind(this);
    }

    login(props) {
        return BaseService.post({url : LOGIN_API_URL,
                                 data : { email: props.userData.email, password: props.userData.password},
                                 messageId : 'incorrect-login',
                                 alert: props.alert })
        
    }

    confirm(props) {
        return BaseService.post({url : `${APPLICATION_API_URL}/activate`,
                                 data : props.data,
                                 messageId : 'incorrect-activation-code',
                                 alert: props.alert })
         
    }

    logout(props) {
        return BaseService.post({url : LOGOUT_API_URL,
                                 data : '',
                                 messageId : 'unable-to-logout',
                                 alert: props.alert,
                                 config : {jwt : props.user.jwt} })
      
    }

    createUser(props) {
        return BaseService.post({url : `${APPLICATION_API_URL}/users`,
                                 data : props.data,
                                 messageId : 'unable-to-create-user',
                                 alert: props.alert })
    }

    runLotery(props) {
        return BaseService.get({url : `${APPLICATION_API_URL}/run`,
            data : props.data,
            messageId : 'unable-to-create-user',
            alert: props.alert })
    }
}

export default new LoginService();