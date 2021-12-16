import React from 'react';

const Header = (props) => {

    let blockUsers = () => {
       props.updateUsers({action : 'BLOCK'});
    }

    return (    
        <div>
             <nav className='switchBar'>
                 <div>
                      <button onClick = {blockUsers} hidden={true} id = 'Button1' className ='button'>block-button</button>
                 </div>
             </nav>
          </div>
    )
}

export default Header;