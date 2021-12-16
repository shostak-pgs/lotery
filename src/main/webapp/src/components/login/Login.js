import React from 'react';
import './Login.css';
import {TextArea} from '../utils/CustomTags';
import { required } from './../validator/CrUpdValidator';
import {Field, reduxForm } from 'redux-form';

let Login = (props) => {

    const onCreatePage = () => {
        if (props.history.location.pathname === '/create') {
            return true;
        } return false;
    }

    const handleSubmit = (formData) => {
        onCreatePage() === true ? props.createUser(formData) : props.getUser(formData);
    }

    const create = () => {
        props.history.push(`create`);
    }

    const getNameFields = () => {
        if(onCreatePage() === true) {
            return (<div>
                <div><Field placeholder = {'first-name'} name = {'firstName'} component = {TextArea}
                            validate = {required} className = {'loginInput'} /></div>
                <div><Field placeholder = {'last-name'} name = {'lastName'} component = {TextArea}
                            validate = {required} className = {'loginInput'} /></div></div>);
        }
    }

    return(
        <div>
            <h3>{(onCreatePage() === true) ? 'create-user-button' : 'login-button' }</h3>
            <LoginReduxForm getNameFields = {getNameFields} onCreatePage = {onCreatePage} onSubmit = {handleSubmit} create = {create}/>
        </div>
    )
}

const LoginForm = (props) => {
    return(
        <div className = 'formContainer'>
            <form>
                {props.getNameFields()}
                <div><Field placeholder = {'email-column'} name = {'email'} component = {TextArea}
                            validate = {required} className = {'loginInput'} /></div>
                <div><Field placeholder = {'password'} type = 'password' name = {'password'} component = {TextArea}
                            validate = {required} className = {'loginInput'} /></div>
                <div><button type = 'submit' disabled = {props.onCreatePage() === true ? true : false} onClick = {props.handleSubmit} id = 'Login' className = {'loginButton'}>login-button</button>
                     <button type = 'button' onClick = {props.onCreatePage() === true ? props.handleSubmit : props.create}  id = 'Create' className = {'loginButton'} >create-user-button</button></div>
            </form>
        </div>
    )
}

const LoginReduxForm = reduxForm( { form : 'login' } ) (LoginForm)

export default Login;