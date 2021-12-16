import React, { Component } from 'react';
import axios from 'axios'
import {MessageWrapper} from '../utils/CustomTags';

class BaseService extends Component {
    constructor(props) {
        super(props)
        this.get=this.get.bind(this);
        this.post = this.post.bind(this);
        this.delete = this.delete.bind(this);
        this.getConfig = this.getConfig.bind(this);
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

    getConfigIfPresent(config){
        if(config !== undefined) {
            return this.getConfig(config);
        }
    }

    get(props) {
        return axios.get(props.url, this.getConfigIfPresent(props.config))
        .then(response => {return response.data})
        .catch(error => {
            props.alert.show(<MessageWrapper id = {props.messageId}/>);
            return Promise.reject(error)
        });
    }

    post(props) {
        return axios.post(props.url, props.data, this.getConfigIfPresent(props.config))
        .then(response => {return response})
        .catch(error => {
            if(error.response.status === 403) {
                props.alert.show(<MessageWrapper id = 'only-owner-message'/>);
            }
             props.alert.show(<MessageWrapper id = {props.messageId}/>);
             return Promise.reject(error)
        });
    }

    delete(props) {
        return axios.delete(props.url, this.getConfigIfPresent(props.config))
        .then(response => {return response})
        .catch(error => {
            if(error.response.status === 403) {
                props.alert.show(<MessageWrapper id = 'only-owner-message'/>);
            }
            props.alert.show(<MessageWrapper id = {props.messageId}/>);
            return Promise.reject(error)
       });
    }
}

export default new BaseService();