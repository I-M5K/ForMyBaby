import React from 'react';
import Video from '../../components/feature/present/Video';
import CountBar from '../../components/feature/present/CountBar';

import { Link } from 'react-router-dom'; // Link 컴포넌트 import
import Xbutton from '../../assets/x_button.png'

import './Present.css'

const PresentPage = () => {
    return (
        <div className='present-container'>
            <Link to="/main">
                <button className="present-quit-button">
                    <img src={Xbutton} className='present-quit-button-img' />
                </button>
            </Link>
            <CountBar />
            <Video />
        </div>
    );
}

export default PresentPage;
