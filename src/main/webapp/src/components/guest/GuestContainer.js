import React, { Component } from 'react';
import { connect } from 'react-redux';
import { withRouter } from 'react-router-dom';
import { withAlert } from 'react-alert';
import Preloader from '../preloader/Preloader';
import LoginService from '../service/LoginService';
import { setUser } from '../store/LoginReducer';
import { switchIsFetching } from '../store/UsersReducer';
import HandleGuest from './HandleGuest';

class GuestContainer extends Component {

    constructor(props){
        super(props);
        this.createUser = this.createUser.bind(this);
    }

    createUser(userData){
         this.props.switchIsFetching({isFetching : true});
             LoginService.createUser({data : userData, alert : this.props.alert, user : this.props.user}).then(response => {
                 if ((response.status === 201)) {
                    this.props.setUser({user : response.data});
                    this.props.alert.show('successfully-created');
                    this.props.history.push(`/`);
                 } else {
                    this.props.setUser({user : {id : '', firstName : '', password : '' }})
                    this.props.history.push(`/`)
                }
            });
         this.props.switchIsFetching({isFetching : false});
    }

    render() {
        return(<div> {this.props.isFetching ? <Preloader/> : null}
                    <HandleGuest isCreate = {this.props.match.params.create}
                           history = {this.props.history}
                           createUser = {this.createUser}
                           user = {this.props.user}/>
                </div>
        )
    }
}

let mapStateToProps = (state) => {
    return {
        isFetching : state.usersPage.isFetching,
        user: state.loginPage.user
    }
}
let WithAlertGuestContainer = withAlert()(GuestContainer);

let WithRouteGuestContainer = withRouter(WithAlertGuestContainer);
  
export default connect(mapStateToProps, {
    setUser, switchIsFetching
    })(WithRouteGuestContainer);