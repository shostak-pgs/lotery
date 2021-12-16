const SET_ARTICLES = 'SET_ARTICLES';
const CHECK_ARTICLE = 'CHECK_ARTICLE';
const SET_ENUMS = 'SET_ENUMS';

let articles = {
    articles: [],
    checked : [],
    enumsMap: {
        genres : [],
        tags : [],
    },
}

const ArticlesReducer = (state = articles, action) => {
switch(action.type) {
    case 'SET_ARTICLES':  {
        return {
            ...state,
            articles : action.articles,
        }
    }
    case 'CHECK_ARTICLE':  {
        return {
            ...state,
            checked : state.checked === action.checked? [] : action.checked,
        }
    }
    case 'SET_ENUMS':  {
        return {
            ...state,
            enumsMap : action.enumsMap,
        }
    }
    default :
        return state;
    }
}
    export const setArticles = (props) => ({
        type: SET_ARTICLES,
        articles: props.articles })

    export const checkArticle = (props) => ({
        type: CHECK_ARTICLE,
        checked : props.checked })

    export const setEnums = (props) => ({
        type: SET_ENUMS,
        enumsMap : props.enumsMap })       

export default ArticlesReducer;