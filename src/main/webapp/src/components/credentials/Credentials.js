import React from 'react';
import { Navbar, Nav, Form } from 'react-bootstrap';
import { withRouter, NavLink } from 'react-router-dom';
import homeicon from './../../img/homeicon.png';
import mypageicon from './../../img/mypageicon.png';
import './../users/header/ActionBar.css';

const Credentials = (props) => {
    let user = props.user;

    let isUserRegistered = () => {
        if (user.id !== '') {
            return true;
        } return false;
    }

    let isAdmin = () => {
        if (isUserRegistered && user.role === 'ADMIN') {
             return true;
        } return false;
    }

    let credentData = () => {
        if(isUserRegistered() && user.firstName !== "") {
            return (<div >{`${user.lastName} ${user.firstName}`}</div>)
        } else {
            return (<div>unauthorized</div>)
        }
    }

    let runLotery = () => {
        props.runLotery();
        }

    let logout = () => {
        return (
            <NavLink to={'/'}>
                <button onClick = {props.logout} hidden={user.role !== 'ADMIN'} className = 'button'>Logout</button>
            </NavLink>
        )
    }

    let loginOrRegister = () => {
        return (
           <NavLink to={'/login'}>
               <button className = 'button'>Login</button>
           </NavLink>
        )
    }

    return(
        <Navbar >
            <Nav><NavLink to={'/'}><button hidden={user.role !== 'ADMIN'} onClick = {runLotery} lassName = 'button'><img src = {homeicon} alt = ''/></button></NavLink></Nav>
            <Nav><NavLink to={'/'}><button className = 'button'><img src = {homeicon} alt = ''/></button></NavLink></Nav>
            <Nav className='mr-auto'>
                <button hidden={true} className = 'button'>users-managment-button</button>
            </Nav>
            <Form>
                <Navbar.Text>{credentData()}</Navbar.Text>
                <Nav>{isUserRegistered() === true ? logout() : loginOrRegister()}</Nav>
            </Form>

        </Navbar>
    )
}

let WithRouteCredentials = withRouter(Credentials);

export default WithRouteCredentials;