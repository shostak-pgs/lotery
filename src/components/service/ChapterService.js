import axios from 'axios'
import React, { Component } from 'react';
import { MessageWrapper } from '../utils/CustomTags';
import BaseService from './BaseService';

const CHAPTERS_API_URL = 'https://fanfics-app.herokuapp.com/api/chapters'

class ChaptersService extends Component {
    constructor(props) {
        super(props)
        this.getChapters = this.getChapters.bind(this);
    }

    getChapters(props) {
        return BaseService.get({url : `${CHAPTERS_API_URL}/${props.articleId}`,
                                messageId : 'get-chapters-error',
                                alert: props.alert,
                                config : {jwt: props.user.jwt} })
       
   }

    getChapter(props) {
        return BaseService.get({url : `${CHAPTERS_API_URL}/${props.chapterId}`,
                                messageId : 'get-chapter-error',
                                alert: props.alert,
                                config : {jwt: props.user.jwt} })
      
    }

    deleteChapter(props) {
        return BaseService.delete({url : `${CHAPTERS_API_URL}?id=${props.id}`,
                                   messageId : 'delete-chapter-error',
                                   alert: props.alert,
                                   config : {jwt : props.user.jwt, idSet : props.id} })
     
    }

    postChapter(props) {
        return axios.post(`${CHAPTERS_API_URL}`, props.data, {
            withCredentials: true,
            headers: { "Authorization" : `Bearer ${props.user.jwt}`,
                       "Accept": "application/json",
                       "Content-Type": "multipart/form-data",
            }
        })
        .then(response => {return response})
        .catch(error => {
             props.alert.show(<MessageWrapper id = 'create-chapter-error'/>);
             return Promise.reject(error)
        });
    }

    putChapter(props) {
        return axios.put(`${CHAPTERS_API_URL}/${props.id}`, props.data, {
            withCredentials: true,
            headers: { "Authorization" : `Bearer ${props.user.jwt}`,
                       "Accept": "application/json",
                       "Content-Type": "multipart/form-data",
            }
        })
        .then(response => {return response})
        .catch(error => {
            if(error.response.status === 403) {
                props.alert.show(<MessageWrapper id = 'only-owner-message'/>);
            }
             props.alert.show(<MessageWrapper id = 'update-chapter-error'/>);
             return Promise.reject(error)
        });
    }
}

export default new ChaptersService();