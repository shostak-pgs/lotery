import React from 'react';
import './Login.css';
import {TextArea} from '../utils/CustomTags';
import { required } from './../validator/CrUpdValidator';
import {Field, reduxForm } from 'redux-form';

let HandleGuest = (props) => {

    const handleSubmit = (formData) => {
        props.createUser(formData);
    }

    const create = () => {
        props.history.push(`create`);
    }

    const getNameFields = () => {
            return (<div hidden={props.user !== undefined && props.user.email !== undefined}>
                <div><Field placeholder = {'first-name'} name = {'firstName'} component = {TextArea}
                            validate = {required} className = {'loginInput'} /></div>
                <div><Field placeholder = {'last-name'} name = {'lastName'} component = {TextArea}
                            validate = {required} className = {'loginInput'} /></div></div>);
    }

    return(
        <div>
            <HandleGuestReduxForm getNameFields = {getNameFields} user = {props.user} onSubmit = {handleSubmit} create = {create}/>
        </div>
    )
}

const HandleGuestForm = (props) => {
    return(
        <div className = 'formContainer'>
            <form>
                {props.getNameFields()}
                <div><button type = 'button'
                             hidden={props.user !== undefined && props.user.email !== undefined}
                             onClick = {props.handleSubmit}
                             id = 'Create'
                             className = {'loginButton'} >Create user</button></div>
            </form>
        </div>
    )
}

const HandleGuestReduxForm = reduxForm( { form : 'create' } ) (HandleGuestForm)

export default HandleGuest;