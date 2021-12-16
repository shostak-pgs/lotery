import React, { Component } from 'react';
import { connect } from 'react-redux';
import { withRouter } from 'react-router-dom';
import { withAlert } from 'react-alert';
import Preloader from '../preloader/Preloader';
import { Container, Row} from 'react-bootstrap';
import LoginService from '../service/LoginService';
import { setUser } from '../store/LoginReducer';
import { switchIsFetching } from '../store/UsersReducer';
import ConfirmRegistration from './ConfirmRegistration';

class ConfirmRegistrationContainer extends Component {

    constructor(props){
        super(props);
        this.getUser = this.getUser.bind(this);
     }
   
    getUser(data){
        this.props.switchIsFetching({isFetching : true});
            LoginService.confirm({data : data, alert : this.props.alert}).then(response => {
                if ((response.status === 200)) {
                    this.props.setUser({user : response.data});
                    this.props.alert.show('completed-registration');
                    this.props.history.push(`/`); 
                } else {
                    this.props.setUser({user : {id : '', firstName : '', password : '' }})
                    this.props.history.push(`/`)
                }
        });
        this.props.switchIsFetching({isFetching : false});
    }

    render() {
        return(
            <Container>
                <Row>{this.props.isFetching ? <Preloader/> : null}</Row>
                <Row><ConfirmRegistration getUser = {this.getUser}/></Row>
            </Container>
        )
    }
}

let mapStateToProps = (state) => {
    return {
        isFetching : state.usersPage.isFetching,
        user: state.loginPage.user
    }
}
let WithAlertConfirmRegistrationContainer = withAlert()(ConfirmRegistrationContainer);

let WithRouteConfirmRegistrationContainer = withRouter(WithAlertConfirmRegistrationContainer);
  
export default connect(mapStateToProps, {
    setUser, switchIsFetching
    })(WithRouteConfirmRegistrationContainer);