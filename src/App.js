import React from 'react';
import { connect } from 'react-redux';
import { Route, Switch } from 'react-router-dom';
import { Provider as AlertProvider } from 'react-alert'
import AlertTemplate from 'react-alert-template-basic';
import LoginContainer from './components/login/LoginContainer';
import UsersContainer from './components/users/UsersContainer';
import ThemeLangContainer from './components/themeAndLang/ThemeAndLangContainer';
import CredentialsContainer from './components/credentials/CredentialsContainer';
import './App.css';

const App = (props) => {
    return (
            <AlertProvider template={AlertTemplate}>
                <div className = 'DARK'>
                   <CredentialsContainer />
                       <header>
                           <Switch>
                               <Route exact path='/login' render = { () => <LoginContainer />}/>
                               <Route exact path='/create' render = { () => <LoginContainer />}/>
                               <Route exact path='/' render = { () => <UsersContainer />}/>
                           </Switch>
                       </header>
                   <ThemeLangContainer/>
                </div>
            </AlertProvider>
    )
}

let mapStateToProps = (state) => {
  return {      
      theme: state.themeBar.theme,
      language: state.themeBar.language
  }
}

export default connect(mapStateToProps, {
  })(App);