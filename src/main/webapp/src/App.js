import React from 'react';
import { connect } from 'react-redux';
import { Route, Switch } from 'react-router-dom';
import { Provider as AlertProvider } from 'react-alert'
import AlertTemplate from 'react-alert-template-basic';
import LoginContainer from './components/login/LoginContainer';
import UsersContainer from './components/users/UsersContainer';
import ThemeLangContainer from './components/themeAndLang/ThemeAndLangContainer';
import CredentialsContainer from './components/credentials/CredentialsContainer';
import ConfirmRegistrationContainer from './components/confirm/ConfirmRegistrationContainer';
import './App.css';
import logo from './logo.svg';

const App = (props) => {
    return (
            <AlertProvider template={AlertTemplate}>
                <div className = {props.theme === 'DARK' ? "dark" : "light"}>
                   <CredentialsContainer />
                       <header className="App-header">
                           <img src={logo} className="App-logo" alt="logo" />
                           <Switch>
                               <Route exact path='/login' render = { () => <LoginContainer />}/>
                               <Route exact path='/create' render = { () => <LoginContainer />}/>
                               <Route path='/confirm' render = { () => <ConfirmRegistrationContainer />}/>
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