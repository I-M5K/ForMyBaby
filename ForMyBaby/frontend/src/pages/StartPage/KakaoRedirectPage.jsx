import React, { useEffect } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import LoadingPage from './LoadingPage';
import kakaoApi from '../../api/kakaoApi'; // API 모듈 import
import { useRecoilState } from 'recoil'; // Recoil의 useRecoilState 임포트
import { userDataState } from '../../atoms/userDataState'; // Recoil 상태 임포트

const KakaoRedirectPage = () => {
  const [userData, setUserData] = useRecoilState(userDataState); // Recoil 상태와 설정 함수 가져오기
  const navigate = useNavigate(); // useNavigate 훅 사용
  const code = new URL(window.location.href).searchParams.get("code");
  console.log(code);

  useEffect(() => {
    console.log('userData', userData); // userData 상태 확인
  
    const fetchData = async () => {
      try {
        const data = await kakaoApi(code);
        console.log(data);
        console.log(data.id);
        console.log(data.kakao_account.email);
        const param = {
          id: data.id,
          email: data.kakao_account.email,
          profileImg: data.kakao_account.profile.profile_image,
          name: data.kakao_account.name,
          jwt: localStorage.getItem("accessToken"),
          fcm: null
        }
        setUserData(param); // Recoil 상태 업데이트
  
        if (param.jwt != null) {
          navigate('/welcome'); // 페이지 이동
        } else {
          navigate('/');
        }
      } catch (error) {
        console.error("API 호출 오류 발생", error);
        navigate('/');
      }
    };
    fetchData();
  }, []); // userData를 의존성 배열에 추가하여 업데이트된 상태 확인
  

  return (
    <div>
      <LoadingPage />
    </div>
  );
};

export default KakaoRedirectPage;
