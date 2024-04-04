import React from 'react';
import Video from '../../components/feature/present/Video';
import GaugeBar from "../../components/feature/present/CountBar.jsx"


import { Link } from 'react-router-dom'; // Link 컴포넌트 import
import Arrowleft from '../../assets/arrow_left.png'

import './Present.css'



const PresentPage = () => {
    return (
        <div className='present-container'>
            <Link to="/main">
                <button className="present-quit-button">
                    <img src={Arrowleft} className='present-quit-button-img' />
                </button>
            </Link>
            <h1>깜~~~짝 선물</h1>
            <Video />
            <GaugeBar value={70} maxValue={100} />
        </div>
    );
}

export default PresentPage;
