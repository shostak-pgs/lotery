import React from 'react';
import { Checkbox } from '../../utils/CustomTags';

let RenderTableData = (props) => {

    let handleChange =(e) => {
       props.checkUser(e);
    }

        let checkAll =() => {
        props.checkAll();
        }

    let header = () => {
        return (
            <thead>
                <tr>
                    <th class='col-3'>First Name</th>
                    <th class='col-3'>Second Name</th>
                </tr>
            </thead>
        )
    };

    let body = (props) => {
        return (
            props.users.map(
                user =>
                    <tbody>
                        <tr key={user.id}>
                            <td>{user.firstName}</td>
                            <td>{user.secondName}</td>
                        </tr>
                    </tbody>
            )
        )
    }

    return (
         <table class="table">
            {header()}
            {body(props)}
        </table>
    )
}

export default RenderTableData;