import React, { useEffect } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import LoadingPage from './LoadingPage';
import kakaoApi from '../../api/kakaoApi'; // API 모듈 import
import { useUserStore } from '../../stores/UserStore'; // Zustand 스토어 import

const KakaoRedirectPage = () => {
  const { setId, setEmail, setProfileImg, setName, setJwt } = useUserStore(); // Zustand 스토어 설정 함수 가져오기
  const navigate = useNavigate(); // useNavigate 훅 사용
  const location = useLocation();
  const code = new URLSearchParams(location.search).get("code");
  console.log(code);

  useEffect(() => {
    //console.log('userData', id, email, name); // 상태 확인

    const fetchData = async () => {
      try {
        const data = await kakaoApi(code);
        console.log(data);
        console.log(data.id);
        console.log(data.kakao_account.email);

        // 데이터 저장
        setId(data.id);
        setEmail(data.kakao_account.email);
        setProfileImg(data.kakao_account.profile.profile_image);
        setName(data.kakao_account.name);
        setJwt(localStorage.getItem("accessToken"));

        if (localStorage.getItem("accessToken") != null) {
          navigate('/welcome'); // 페이지 이동
        } else {
          navigate('/');
        }
      } catch (error) {
        console.error("API 호출 오류 발생", error);
        navigate('/');
      }
    };

    if (code) {
      fetchData();
    }
  }, [code]); // code 값이 변경될 때마다 fetchData 함수 호출

  return (
    <div>
      <LoadingPage />
    </div>
  );
};

export default KakaoRedirectPage;
