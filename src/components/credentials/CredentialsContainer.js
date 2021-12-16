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
    }

    logout() {
        this.props.switchIsFetching({isFetching : true});  
        LoginService.logout({user : this.props.user, alert : this.props.alert}).then(response => {debugger;
            if ((response.status === 200)) {
                this.props.setUser({user : {id : '', firstName : '', password : '' }});
                this.props.history.push('/login'); 
            }
        this.props.switchIsFetching({isFetching : false});
        });
    }

    render() {
        return(
            <div>{this.props.isFetching ? <Preloader/> : null}
                <Credentials logout = {this.logout}
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
        theme: state.themeBar.theme,
    }
}

export default connect(mapStateToProps, {
    setUser, switchIsFetching
    })(WithRouteCredentialsContainer);