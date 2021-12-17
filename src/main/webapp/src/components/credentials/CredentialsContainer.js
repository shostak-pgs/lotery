import React, { Component } from 'react';
import { connect } from 'react-redux';
import { withRouter} from 'react-router-dom';
import { withAlert } from 'react-alert'
import { setUser } from './../store/LoginReducer';
import { switchIsFetching } from './../store/UsersReducer';

import LoginService from './../service/LoginService';
import Credentials from './Credentials';
import Preloader from './../preloader/Preloader';

class CredentialsContainer extends Component {  
    constructor(props){
        super(props);
        this.logout = this.logout.bind(this);
        this.runLotery= this.runLotery.bind(this);
    }

    logout() {
        this.props.setUser({user : {id : '', firstName : '', password : '' }});
        this.props.history.push('/');
    };

    createUser(userData){
        this.props.switchIsFetching({isFetching : true});
        LoginService.createUser({data : userData, alert : this.props.alert, user : this.props.user}).then(response => {
            if ((response.status === 201)) {
                this.props.setUser({user : response.data});
                this.props.alert.show('successfully-created');
            } else {
                this.props.setUser({user : {id : '', firstName : '', password : '' }})
            }
        });
        this.props.switchIsFetching({isFetching : false});
    }

    runLotery(userData){
        this.props.switchIsFetching({isFetching : true});
        LoginService.runLotery({userData : userData, alert : this.props.alert}).then(response => {
            if ((response.firstName !== undefined)) {
                let data = `Winner: ${response.firstName} ${response.lastName}`;
                this.props.alert.show(data);
            } else {
                this.props.alert.show('Some Error occurred');
            }
        });
        this.props.switchIsFetching({isFetching : false});
    }

    render() {
        return(
            <div>{this.props.isFetching ? <Preloader/> : null}
                <Credentials logout = {this.logout}
                             runLotery = {this.runLotery}
                             theme = {this.props.theme}
                             user = {this.props.user}/> 
            </div>
        )
    }
}
let WithAlertCredentialsContainer = withAlert()(CredentialsContainer);

let WithRouteCredentialsContainer = withRouter(WithAlertCredentialsContainer);

let mapStateToProps = (state) => {
    return {      
        user: state.loginPage.user,
    }
}

export default connect(mapStateToProps, {
    setUser, switchIsFetching
    })(WithRouteCredentialsContainer);