const SET_THEME = 'SET_THEME';
const SET_LANGUAGE = 'SET_LANGUAGE';

let initialData = {
    state: {
        theme : 'DARK',
        language : 'EN',
    }
} 

const ThemeReducer = (state = initialData, action) => {
    switch(action.type) {
        case 'SET_THEME':  {
            return {
                ...state,
                theme : state.theme === 'DARK'? 'LIGHT' : 'DARK',
            }
        }
        case 'SET_LANGUAGE':  {
            return {
                ...state,
                language : state.language === 'EN'? 'RU' : 'EN',
            }
        }
        default : return state;
    }
}

export const setTheme = (props) => ({
    type: SET_THEME })

export const setLanguage = (props) => ({
    type: SET_LANGUAGE })
    
export default ThemeReducer;