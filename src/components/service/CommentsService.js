import { Component } from 'react';
import BaseService from './BaseService';

const COMMENTS_API_URL = 'https://fanfics-app.herokuapp.com/api/comments'

class CommentsService extends Component {
    constructor(props) {
        super(props)
        this.getComments = this.getComments.bind(this);
        this.postComment = this.postComment.bind(this);
    }

    getComments(props) {
        return BaseService.get({url : `${COMMENTS_API_URL}/${props.articleId}`,
                                messageId : 'get-comments-error',
                                alert: props.alert,
                                config : {jwt: props.user.jwt} })

   }

    postComment(props) {
        return BaseService.post({url : COMMENTS_API_URL,
                                 data : props.data,
                                 messageId : 'create-comment-error',
                                 alert: props.alert,
                                 config : {jwt : props.user.jwt} })
       
    }
}

export default new CommentsService()