import React from 'react';
import { Navbar, Nav, Form } from 'react-bootstrap';
import { withRouter, NavLink } from 'react-router-dom';
import homeicon from '../../img/homeicon.png';
import mypageicon from '../../img/mypageicon.png';
import '../users/header/ActionBar.css';

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
            return (<div>unautorized</div>)
        }
    }

    let handleUsersPage = () => {
        if(isUserRegistered && user.role === 'ADMIN') {
            props.history.push('/users');
        }
    }

    let logout = () => {
        return (
            <NavLink to={'/'}>
                <button onClick = {props.logout} className = 'button'>logout-button</button>
            </NavLink>
        )
    }

    let loginOrRegister = () => {
        return (
           <NavLink to={'/login'}>
               <button className = 'button'>login-or-register-button</button>
           </NavLink>
        )
    }

    return(
        <Navbar >
            <Nav><NavLink to={'/myArticles/myArticles'}><button hidden = {isAdmin() === true ? false : true} className = 'button'><img src = {mypageicon} width = '50' alt = ''/></button></NavLink></Nav>
            <Nav><NavLink to={'/'}><button className = 'button'><img src = {homeicon} alt = ''/></button></NavLink></Nav>
            <Nav className='mr-auto'>
                <button onClick = {handleUsersPage} hidden={true} className = 'button'>users-managment-button</button>
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