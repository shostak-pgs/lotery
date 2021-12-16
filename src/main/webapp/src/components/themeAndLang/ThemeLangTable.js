import React from 'react';
import './ThemeLangTable.css';

const ThemeLangTable = (props) => {

    let switchLang = () => {
       props.setLanguage();
    }

    let switchTheme = () => {
        props.setTheme();
    }

    return (    
        <div>
             <nav>
             </nav>
          </div>
    )
}

export default ThemeLangTable;