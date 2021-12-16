import React from 'react';
import {TextArea} from '../utils/CustomTags';
import { required } from './../validator/CrUpdValidator';
import {Field, reduxForm } from 'redux-form';
import { Container, Col} from 'react-bootstrap';

let ConfirmRegistration = (props) => {

    const handleSubmit = (formData) => {
        props.getUser(formData);
    }

    return(
        <Container>
            <h4><Col>enter-code</Col></h4>
            <Col><ConfirmRegistrationReduxForm onSubmit = {handleSubmit}/></Col>
        </Container>
    )
}

const ConfirmRegistrationForm = (props) => {
    return(
        <div className = 'formContainer'>
            <form>
                <Field placeholder = {'activation-code'} name = {'activationCode'} component = {TextArea}
                            validate = {required} className = {'loginInput'} />
                <button type = 'submit' onClick = {props.handleSubmit} className = {'loginButton'}>activate-button</button>
            </form>
        </div>    
    )
}

const ConfirmRegistrationReduxForm = reduxForm( { form : 'confirm' } ) (ConfirmRegistrationForm)

export default ConfirmRegistration;