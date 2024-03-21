import React from 'react';
import './BabyWelcome.css';
import { Link } from 'react-router-dom'


const WelcomePage = () => {

    return (
        <div className='baby-welcome-container'>
            <h1>축하축하축하</h1>

            <Link to='/main'>
                    <button type="submit" className='baby-welcome'>메인으로</button>
            </Link>
        </div>
    );
}

export default WelcomePage;