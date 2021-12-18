import React, { Component } from 'react';
import { connect } from 'react-redux';
import Select from 'react-select';
import './CustomTags.css';
import Dropzone from 'react-dropzone';
import { Container, Row, Col} from 'react-bootstrap';
import StarRatings from 'react-star-ratings';

export class SingleFileDropZone extends Component{

    constructor() {
        super();
        this.onChange = this.onChange.bind(this)
        this.onDrop = (files) => {
            this.setState({files})
        };
        this.state = {
            files: []
        };
    }

    onChange(files) {
        if (this.props.input) {
            const {input: {onChange}} = this.props
           this.setState({files})
            onChange(files[0])
        }
        else if(this.props.onChange){
            this.props.onChange(files[0])
        }
    }

    componentDidUpdate(prevProps, prevState) {
        if(this.state.files.length === 0 && this.props.placeholder !== undefined && this.props.placeholder !== null) {
            this.onChange([this.props.placeholder]);
        }
    }

    render() {
        const files = () => {
            if (this.state.files.length === 1) {
                return (
                    this.state.files.map(file => (
                        <Container key={file.name}>
                        </Container>
                    ))
                );
            }
        }
    
    return (
        <Dropzone onDrop={this.onChange} multiple = {false}>
            {({getRootProps, getInputProps}) => (
                <section className="container">
                    <div {...getRootProps({className: 'dropzone'})}>
                        <input {...getInputProps()} />
                        <p>drug-n-drop-file</p>
                    </div>
                    <aside>
                        <ul>{files()}</ul>
                     </aside>
                </section>
            )}
        </Dropzone>
    );
  }
}

export const RenderSelectInput = props => {
    const { input } = props;
    let placeholder = props.placeholder;
      
    let getTags = () => {
        return props.object.map(element => ({ label: element, value: element }));
    }
    
    return (
           <Select {...input} isMulti onChange={value => input.onChange(value)} onBlur={() => input.onBlur(input.value)} 
                   placeholder = {placeholder} options={getTags()}/>
    )
}

export const TextArea = ({input, meta,  ...props}) => {
    const isCorrect = meta.touched && meta.error;
    let placeholder = props.placeholder;

    return (
            <div className = {isCorrect ? 'error' : 'valid'}>
                <input {...input} type={props.type} {...props} placeholder = {placeholder} />
                <div>{isCorrect && <span>{meta.error}</span>}</div>
            </div>
    );
};

export class WrapedTextArea extends Component {
    constructor(props) {
        super(props);
        this.handleOnChange.bind(this);
        this.state = {
          value: ''
        };
      }

    componentDidUpdate = (prevProps, prevState) =>{
        if(this.props.object !== prevProps.object) {
            this.setState({ value : this.props.object}); 
            this.props.input.onDrop();
        } else {
            return '';
        }
    }

    handleOnChange = (event) => {
        let value = event.target.value
        this.setState({value})
    };

    isItCorrect(){
        if((this.props.meta.touched === false) && (this.state.value === '')) {
            return true;
        } else if((this.state.value !== '')) {
            return true;
        } else return false;
    }

    
    render() {
        return (   
            <div className = {this.isItCorrect() ? 'valid' : 'error'}>
                        <div><textarea {...this.props.input} {...this.props} value = {this.state.value} onChange = {this.handleOnChange}
                                       placeholder = 'placeholder'></textarea></div>
                    )}
                {(this.isItCorrect() === false) && <span>{this.props.meta.error}</span>}
            </div>
        );
    }
}

    export const MySelect = ({input, meta, ...props}) => {
        const isCorrect = meta.touched && meta.error;
        let placeholder = 'placeholder';

        let renderSelectOptions = (unit) => (
            <option key={unit} value={unit}>{unit}</option>
        )
        return(
            <div className = {isCorrect ? 'error' : 'valid'}>
                <select {...input}>
                    <option disabled hidden value="">{props.placeholder === 'undefined' ? MySelect : placeholder}</option>
                    {props.enum.map(renderSelectOptions)} 
                </select>
                <div>{isCorrect && <span>{meta.error}</span>}</div>
            </div>
        )
    }

export function reactIntlInject ({ children, ...props }) {
    if (typeof children === 'function') {
      return (
        children(props)
      )
    } else {
      return null
    }
  }

export const MessageWrapperr = ({...props}) => {
    let message = 'id';
    return (
            <div>{message}</div>
    );
};
 
export class Rating extends Component {
    constructor(props) {
        super(props);
        this.changeRating = this.changeRating.bind(this);
        this.state = {
          rating: 0
        };
      }

    changeRating(newRating) {
        this.props.postRate(newRating);
        this.setState({ rating: newRating });
    }
 
    render() {
      return (
        <StarRatings
          rating={this.state.rating}
          starRatedColor="blue"
          changeRating={this.changeRating}
          starDimension="30px"
          numberOfStars={5}
          name='rating'
        />
      );
    }
}
 
let mapStateToProps = (state) => {
    return {      
        theme: state.themeBar.theme,
        language: state.themeBar.language
    }
  }
  
export default connect(mapStateToProps, {})(TextArea);
  
export const MessageWrapper = connect(mapStateToProps, {})(MessageWrapperr);

export const Checkbox = ({ type = 'checkbox', name, checked = false, onChange }) => (
    <input type={type} name={name} checked={checked} onChange={onChange} />
);