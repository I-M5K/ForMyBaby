import React, { useEffect } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import LoadingPage from './LoadingPage';
import kakaoApi from '../../api/kakaoApi'; // API 모듈 import
import { useUserStore } from '../../stores/UserStore'; // Zustand 스토어 import

const KakaoRedirectPage = () => {
  const { setBabyList, jwt, fcm, family, setId, setEmail, setProfileImg, setName, setJwt, setFamily, setFcm } = useUserStore(); // Zustand 스토어 설정 함수 가져오기
  const navigate = useNavigate(); // useNavigate 훅 사용
  const location = useLocation();
  const code = new URLSearchParams(location.search).get("code");
  console.log(code);
  // if (jwt != null) { // 로그인 성공
  //   //localStorage.removeItem("accessToken");
  //   if (family){ // 로그인인 경우
  //     navigate('/main');
  //   } else { // 회원가입인 경우
  //     navigate('/agree'); // 페이지 이동
  //   }
  // } else { // 로그인 실패
  //   navigate('/');
  // }

  useEffect(() => {
    //console.log('userData', id, email, name); // 상태 확인

    const fetchData = async () => {
      try {
        const response = await kakaoApi(code);
        const token = response.headers.get('Authorization');
        const newToken = token.replace('Bearer ', '');
        console.log(newToken);
        const data = await response.json();
        console.log(data);
        console.log(data.id);
        console.log(data.kakao_account.email);

        // 데이터 저장 - FCM, JWT, 가족 코드 null 여부에 따라 저장
        setId(data.userId);
        setEmail(data.kakao_account.email);
        setProfileImg(data.kakao_account.profile.profile_image_url);
        setName(data.kakao_account.name);
        setJwt(newToken);
        localStorage.setItem("accessToken", newToken);
        setFamily(data.familyCode);
        setFcm(data.fcmToken);
        setBabyList(data.babyList);

        console.log('jwt: ', jwt);
        if (newToken != null) { // 로그인 성공
          //localStorage.removeItem("accessToken");
          console.log('family: ', family);
          if (data.familyCode){ // 로그인인 경우
            navigate('/main');
          } else { // 회원가입인 경우
            console.log('동의페이지로!');
            navigate('/agree'); // 페이지 이동
          }
        } else { // 로그인 실패
          console.log('로그인실패');
          navigate('/');
        }

      } catch (error) {
        console.error("API 호출 오류 발생", error);
        navigate('/');
      }
    };

    console.log('순서 확인하기');
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
