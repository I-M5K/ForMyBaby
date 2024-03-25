import React, { useState } from 'react';
import NavBar from '../../components/NavBar';
import StampPage1 from '../../components/feature/parentingstamp/StampPage1';
import StampPage2 from '../../components/feature/parentingstamp/StampPage2';
import StampPage3 from '../../components/feature/parentingstamp/StampPage3';
import StampPage4 from '../../components/feature/parentingstamp/StampPage4';

import './ParentingStamp.css'

function ParentingStamp() {
  const [selectedPage, setSelectedPage] = useState(null);
  const [note, setNote] = useState('');

  const handlePageSelection = (page) => {
    setSelectedPage(page);
  };

  const saveNote = () => {
    console.log('저장된 글:', note);
    // 노트 저장 로직 추가
  };

  return (
    <div className="ParentingStamp">
      <div className='parenstamp-title'>성장 스탬프</div>
      <hr />
      <div className='parenstamp-header'>
        <button onClick={() => handlePageSelection(<StampPage1 />)}>0~3개월</button>
        <button onClick={() => handlePageSelection(<StampPage2 />)}>4~6개월</button>
        <button onClick={() => handlePageSelection(<StampPage3 />)}>7~9개월</button>
        <button onClick={() => handlePageSelection(<StampPage4 />)}>10~12개월</button>
      </div>
      <hr />
      <div className='parenstamp-contents'>
        {selectedPage}
      </div>
      <NavBar />
    </div>
  );
}

export default ParentingStamp;
