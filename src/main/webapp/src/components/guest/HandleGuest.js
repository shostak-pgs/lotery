import React from 'react';
import {TextArea} from '../utils/CustomTags';
import { required } from './../validator/CrUpdValidator';
import {Field, reduxForm } from 'redux-form';
import './HandleGuest.css';
import createUserIcon from "../../img/createUserIcon.png";

let HandleGuest = (props) => {

    const handleSubmit = (formData) => {
        props.createUser(formData);
    }

    const create = () => {
        props.history.push(`create`);
    }

    const getNameFields = () => {
            return (<div>
                <div className={'fieldContainer'}><Field placeholder={'First Name'} name={'firstName'}
                                                         component={TextArea}
                                                         validate={required} className={'loginInput'}/></div>
                <div><Field placeholder={'Last Name'} name={'lastName'} component={TextArea}
                            validate={required} className={'loginInput'}/></div>
            </div>);
    }



    if(props.user === undefined || props.user.email === undefined) {
        return (
            <div>
                <HandleGuestReduxForm getNameFields={getNameFields} user={props.user} onSubmit={handleSubmit}
                                      create={create}/>
            </div>
        )
    } else {
        return <div/>
    }
}

const HandleGuestForm = (props) => {
    return(
        <div className = 'formContainer'>
            <div className = {'credentials'} hidden={props.user !== undefined && props.user.email !== undefined}>
                <h1>{'Dear Santa,'}</h1>
            </div>
            <form>
                {props.getNameFields()}
                <div className={'createIconBox'}><button type = 'button'
                             hidden={props.user !== undefined && props.user.email !== undefined}
                             onClick = {props.handleSubmit}
                             id = 'Create'
                             className = {'createButton'}>
                    <img className={'createIcon'} src = {createUserIcon} alt = ''/>
                </button></div>
            </form>
        </div>
    )
}

const HandleGuestReduxForm = reduxForm( { form : 'login' } ) (HandleGuestForm)

export default HandleGuest;