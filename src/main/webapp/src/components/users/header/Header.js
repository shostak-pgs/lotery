import React from 'react';

const Header = (props) => {

    let blockUsers = () => {
       props.updateUsers({action : 'BLOCK'});
    }

    let unblockUsers = () => {
        props.updateUsers({action : 'UNBLOCK'});
    }

    return (    
        <div>
             <nav className='switchBar'>
                 <div>
    <button onClick = {blockUsers} id = 'Button1' className ='button'>block-button</button>
                      <button onClick = {unblockUsers} id = 'Button2' className ='button'>unblock-button'</button>
                 </div>
             </nav>
          </div>
    )
}

export default Header;