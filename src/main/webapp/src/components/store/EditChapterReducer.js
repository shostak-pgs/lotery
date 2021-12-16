const SET_NEW_CHAPTER = 'SET_NEW_CHAPTER';

let editChapterState = {
    chapter : {},
};

const editChapterReducer = (state = editChapterState, action) => {
switch(action.type) {
    case 'SET_NEW_CHAPTER':  {
        return { 
            ...state,
            chapter : action.chapter,    
        }
    }
    default :
        return state; 
    }
}

export const setNewChapter = (props) => ({
    type: SET_NEW_CHAPTER,
    chapter : props.chapter })  

export default editChapterReducer;