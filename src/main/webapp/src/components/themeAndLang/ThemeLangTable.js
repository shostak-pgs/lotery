import React from 'react';
import './ThemeLangTable.css';

const ThemeLangTable = (props) => {

    let switchTheme = () => {
        props.setTheme();
    }

    return (    
        <div>
             <nav>
                 <div className = 'line'>
    <button onClick = {switchTheme} disabled = {props.theme === 'DARK' ? true : false} className ='theam-lang-table-button'>ark</button>
                      <button onClick = {switchTheme} disabled = {props.theme === 'DARK' ? false : true} className ='theam-lang-table-button'>light</button>
                 </div>
             </nav>
          </div>
    )
}

export default ThemeLangTable;