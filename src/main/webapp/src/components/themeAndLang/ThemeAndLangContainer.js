import React from 'react';
import { connect } from 'react-redux';
import { Container, Row, Col } from 'react-bootstrap';
import { setLanguage, setTheme } from '../store/ThemeReducer';
import ThemeLangTable from './ThemeLangTable';

let ThemeAndLangContainer = (props) => {
    return (
        <Container>
            <Row className="justify-content-md-center">
                <Col />
                <Col md='auto'><ThemeLangTable setTheme = {props.setTheme}
                                               theme = {props.theme}
                                               language = {props.language}
                                               setLanguage = {props.setLanguage}/></Col>
            </Row>
        </Container>
    )
}

let mapStateToProps = (state) => {
    return {      
        theme: state.themeBar.theme,
        language: state.themeBar.language
    }
}

export default connect(mapStateToProps, {
    setTheme, setLanguage
    })(ThemeAndLangContainer);