const SET_ARTICLE = 'SET_ARTICLE';
const SET_CHAPTERS = 'SET_CHAPTERS';
const SET_COMMENTS = 'SET_COMMENTS';
const SET_LIKES = 'SET_LIKES';
const CHECK_CHAPTER = 'CHECK_CHAPTER';

let articles = {
    article: {user : { id : '' },    },
    chapters : [],
    comments : [],
    likes : [],
    checked : {}
}

const ArticleOverviewReducer = (state = articles, action) => {
switch(action.type) {
    case 'SET_ARTICLE':  {
        return {
            ...state,
            article : action.article
        }
    }
    case 'SET_CHAPTERS':  {
        return {
            ...state,
            chapters : action.chapters,
        }
    }
    case 'SET_COMMENTS':  {
        return {
            ...state,
            comments : action.comments,
        }
    }
    case 'SET_LIKES':  {
        return {
            ...state,
            likes : action.likess,
        }
    }
    case 'CHECK_CHAPTER':  {
        let id = Number.parseInt(action.checked);
        return {
            ...state,
            checked : state.checked === id? [] : id,
        }
    }
    default :
        return state;
    }
}
    export const setArticle = (props) => ({
        type: SET_ARTICLE,
        article: props.article })

    export const setChapters = (props) => ({
        type: SET_CHAPTERS,
        chapters: props.chapters })

    export const setComments = (props) => ({
        type: SET_COMMENTS,
        comments: props.comments })
    
    export const setLikes = (props) => ({
        type: SET_LIKES,
        likes: props.likes })  
        
    export const checkChapter = (props) => ({
        type: CHECK_CHAPTER,
        checked : props.checked })        

export default ArticleOverviewReducer;