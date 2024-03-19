// src/FamilyCodeForm.js

import React, { useState, useRef } from 'react';
import './FamilyCode.css';

function FamilyCodeForm({ onSubmit, goToNextPage }) {
  const [code, setCode] = useState('');
  const inputRefs = useRef([]);

  const handleSubmit = (e) => {
    e.preventDefault();
    onSubmit(code);
  };

  const handleChange = (index, value) => {
    const newCode = code.split('');
    newCode[index] = value;
    setCode(newCode.join(''));

    // 다음 칸으로 포커스 이동
    if (value !== '' && index < inputRefs.current.length - 1) {
      inputRefs.current[index + 1].focus();
    }
  };

  const handleSkip = () => {
    goToNextPage(); // 부모 컴포넌트에서 전달된 함수 호출하여 페이지 이동
  };

  return (
    <form className="family-code-form" onSubmit={handleSubmit}>
      <img src={require('../../assets/babybear.png')} className='familyImage'/>
      <h2>가족과 공유를 할 수 있어요</h2>
      <p>
        가족 중 등록된 아이가 있다면,<br />
        프로필 → 가족 코드에서 코드를 확인해주세요.
      </p>
      <div className="code-input">
        {[...Array(6)].map((_, index) => (
          <input
            key={index}
            ref={(el) => (inputRefs.current[index] = el)}
            type="text"
            maxLength={1}
            value={code[index] || ''}
            onChange={(e) => handleChange(index, e.target.value)}
          />
        ))}
      </div>
      <button type="submit" className="submit-button">확인하기</button>
      <button type="button" className="skip-button" onClick={handleSkip}>공유 없이 가입하기</button>
    </form>
  );
}

export default FamilyCodeForm;
