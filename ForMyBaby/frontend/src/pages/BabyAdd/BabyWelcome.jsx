import React, { useState, useEffect } from 'react';
import { useSpring, animated } from 'react-spring';
import './BabyWelcome.css';
import { Link } from 'react-router-dom';
import Confetti from 'react-confetti'


const WelcomePage = () => {

    return (
        <div className='baby-welcome-container'>
            <h1>축하축하축하</h1>
            <Confetti />
            <Link to='/main'>
                <button type="button" className='baby-welcome'>시작하기</button>
            </Link>
        </div>
    );
}

export default WelcomePage;
