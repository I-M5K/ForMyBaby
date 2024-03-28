import React, { useState, useRef } from 'react';
import './FamilyCode.css';
import axios from 'axios';
import { submitFamilyCode, getFamilyCode } from '../../api/userApi';
import { useUserStore, useLocationStore } from '../../stores/UserStore'; // Zustand 스토어 import
import { useNavigate } from 'react-router-dom';

function FamilyCodeForm() {
  const [code, setCode] = useState('');
  const [invalidCode, setInvalidCode] = useState(false); // 잘못된 가족 코드 여부 상태 추가
  const inputRefs = useRef([]);
  const navigate = useNavigate(); // useNavigate 훅 사용
  const { setFamily, setBabyList } = useUserStore();

  const handleSubmit = async () => {
    try {
      const res = await submitFamilyCode(code);
      if (res == null || res.length === 0) { // 잘못된 가족 코드 처리
        setInvalidCode(true); // 잘못된 가족 코드 여부 상태 업데이트
        setCode(''); // 코드 상태 초기화
        inputRefs.current[0].focus(); // 첫 번째 입력란에 포커스
        return; // 함수 종료
      }
      setFamily(code);
      setBabyList(res); 
      navigate('/baby-relation');
    } catch (error) {
      console.error("가족 코드 제출 오류:", error);
      // 오류 처리
    }
  };

  const handleChange = (index, value) => {
    const newCode = code.split('');
    newCode[index] = value;
    setCode(newCode.join(''));
    setInvalidCode(false); // 입력이 변경되면 잘못된 코드 상태 초기화

    // 다음 칸으로 포커스 이동
    if (value !== '' && index < inputRefs.current.length - 1) {
      inputRefs.current[index + 1].focus();
    }
  };

  const handleSkip = () => { // 신규회원
    navigate('/baby-add'); // 아이 정보 등록 페이지로
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
      {invalidCode && <p style={{ color: 'red' }}>잘못된 가족 코드입니다.</p>} {/* 잘못된 코드 메시지 */}
      <button type="button" className="submit-button" onClick={handleSubmit}>확인하기</button>
      <button type="button" className="skip-button" onClick={handleSkip}>공유 없이 가입하기</button>
    </form>
  );
}

export default FamilyCodeForm;
