import React from 'react';
import { useNavigate } from 'react-router-dom';

import './backbutton.css'

import Arrowleft from '../assets/arrow_left.png'

function BackButton() {
  let navigate = useNavigate();

  function handleBack() {
    navigate(-1); // 뒤로 가기
  }

  return (
    <button className="back-button" onClick={handleBack}>
      <img src={Arrowleft} alt="" />
    </button>
  );
}

export default BackButton;
