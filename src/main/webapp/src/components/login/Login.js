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
            return (<div></div>);
        }
    }

    return(
        <div>
            <LoginReduxForm getNameFields = {getNameFields} onCreatePage = {onCreatePage} onSubmit = {handleSubmit} create = {create}/>
        </div>
    )
}

const LoginForm = (props) => {
    return(
        <div className = 'formContainer'>
            <div className = {'credentials'}>
                <h3>{'Enter your credentials'}</h3>
                </div>
                <form>
                {props.getNameFields()}
                <div className={'fieldContainer'}><Field placeholder = {'email'} name = {'email'} component = {TextArea}
                            validate = {required} className = {'loginInput'} /></div>
                <div><Field placeholder = {'password'} type = 'password' name = {'password'} component = {TextArea}
                            validate = {required} className = {'loginInput'} /></div>
                <div><button type = 'submit' disabled = {props.onCreatePage() === true ? true : false} onClick = {props.handleSubmit} id = 'Login' className = {'loginButton'}>Login</button></div>
            </form>
        </div>
    )
}

const LoginReduxForm = reduxForm( { form : 'login' } ) (LoginForm)

export default Login;