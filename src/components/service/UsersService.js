import axios from 'axios'
import React, { Component } from 'react';
import BaseService from './BaseService';
import { MessageWrapper } from './../utils/CustomTags';

const USERS_API_URL = 'http://localhost:8080/api/users'

class UsersService extends Component {
    constructor(props) {
        super(props)
        this.retrieveAllUsers = this.retrieveAllUsers.bind(this);
        this.getConfig = this.getConfig.bind(this);
        this.deleteUsers = this.deleteUsers.bind(this);
        this.updateUsers = this.updateUsers.bind(this);
        this.retrieveMyArticles = this.retrieveMyArticles.bind(this);
    }

    getConfig(props) {
        let data = isNaN(props.idSet)? [] : props.idSet;
        return {
            withCredentials: true,
            headers: { "Authorization" : `Bearer ${props.jwt}`,
                       "Accept": "application/json",
                       "Content-Type": "application/json",
                       "Data":`${data}`
            }
        }
    }

    
    retrieveMyArticles(props) {
        return BaseService.get({url : `${USERS_API_URL}/${props.id}/articles`,
                                messageId : 'get-articles-error',
                                alert: props.alert,
                                config : {jwt: props.user.jwt} })
        
    }

    retrieveAllUsers(props) {
        return BaseService.get({url : USERS_API_URL,
                                messageId : 'get-users-error',
                                alert: props.alert })

    }

    updateUsers(props) {
        return axios.put(`${USERS_API_URL}`, props.actionDto, this.getConfig({jwt : props.user.jwt}))
        .then(response => {debugger;return response})
        .catch(error => {
            if (error.response.status === 401) {
                return error.response;
            }})
        .catch(error => {
             props.alert.show(<MessageWrapper id = 'update-users-error'/>);
             return Promise.reject(error)
        });
    }

    deleteUsers(props) {
        return axios.delete(`${USERS_API_URL}`, this.getConfig({jwt : props.user.jwt, idSet : props.idSet.idSet}))
        .then(response => {return response})
        .catch(error => {
            if (error.response.status === 401) {
                return error.response;
            }})
        .catch(error => {
            props.alert.show(<MessageWrapper id = 'delete-users-error'/>);
            return Promise.reject(error);
       });
    }
}

export default new UsersService()