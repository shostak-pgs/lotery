import axios from 'axios'
import React, { Component } from 'react';
import BaseService from './BaseService';
import { MessageWrapper } from '../utils/CustomTags';

const ARTICLES_API_URL = 'https://fanfics-app.herokuapp.com/api/articles'
const RATES_API_URL = 'https://fanfics-app.herokuapp.com/api/rates'
const APP_API_URL = 'https://fanfics-app.herokuapp.com/api/common'

class ArticlesService extends Component {
    constructor(props) {
        super(props)
        this.retrieveAllArticles=this.retrieveAllArticles.bind(this);
        this.deleteArticle = this.deleteArticle.bind(this);
        this.getConfig = this.getConfig.bind(this);
        this.searchArticles = this.searchArticles.bind(this);
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

    retrieveAllArticles(props) {
        return BaseService.get({url : ARTICLES_API_URL,
                                messageId : 'get-articles-error',
                                alert: props.alert })
      
    }

    getEnums(props) {
        return BaseService.get({url : APP_API_URL,
                                messageId : 'get-enums-error',
                                alert: props.alert,
                                config : {jwt: props.user.jwt} })
      
    }

    searchArticles(props) {
        return BaseService.get({url : `${ARTICLES_API_URL}/search?text=${props.data}`,
                                messageId : 'search-articles-error',
                                alert: props.alert,
                                config : {jwt: props.user.jwt} })
        
    }

    getArticle(props) {
        return BaseService.get({url : `${ARTICLES_API_URL}/${props.articleId}`,
                                messageId : 'get-article-error',
                                alert: props.alert,
                                config : {jwt: props.user.jwt} })
        
    }

    deleteArticle(props) {
        return BaseService.delete({url : `${ARTICLES_API_URL}?id=${props.id}`,
                                   messageId : 'delete-articles-error',
                                   alert: props.alert,
                                   config : {jwt : props.user.jwt} })
       
    }

    postArticle(props) {
        return BaseService.post({url : ARTICLES_API_URL,
                                 data : props.data,
                                 messageId : 'create-article-error',
                                 alert: props.alert,
                                 config : {jwt: props.user.jwt} })
       
    }

    postRate(props) {
        return BaseService.post({url : RATES_API_URL,
                                 data : props.data,
                                 messageId : 'create-rate-error',
                                 alert: props.alert,
                                 config : {jwt: props.user.jwt} })
       
    }

    putArticle(props) {
        return axios.put(`${ARTICLES_API_URL}/${props.id}`, props.data, this.getConfig({jwt : props.user.jwt}))
        .then(response => {return response})
        .catch(error => {
            if(error.response.status === 403) {
                props.alert.show(<MessageWrapper id = 'only-owner-message'/>);
            }
             props.alert.show(<MessageWrapper id = 'update-article-error'/>);
             return Promise.reject(error)
        });
    }

    getChapters(props) {
        return BaseService.get({url : `${ARTICLES_API_URL}/${props.articleId}/chapters`,
                                messageId : 'get-chapters-error',
                                alert: props.alert,
                                config : {jwt: props.user.jwt} })
        
   }
}

export default new ArticlesService();