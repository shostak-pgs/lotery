import LoginReducer from './LoginReducer';
import UsersReducer from './UsersReducer';
import ArticlesReducer from './ArticlesReducer';
import ThemeReducer from './ThemeReducer';
import ArticleOverviewReducer from './ArticleOverviewReducer';
import {createStore, combineReducers} from 'redux';
import { reducer as formReducer } from 'redux-form';
import {loadState, saveState} from './LocalStorage';
import editChapterReducer from './EditChapterReducer';

let reducers = combineReducers({
   usersPage: UsersReducer,
   themeBar: ThemeReducer,
   articlesPage : ArticlesReducer,
   articleOverviewPage : ArticleOverviewReducer,
   editChapterPage : editChapterReducer,
   loginPage : LoginReducer,
   form : formReducer
});

const persistedState = loadState();

let store = createStore(reducers, persistedState);

store.subscribe(() => {
  saveState({
    loginPage: store.getState().loginPage,
    themeBar: store.getState().themeBar
  });
});

export default store;