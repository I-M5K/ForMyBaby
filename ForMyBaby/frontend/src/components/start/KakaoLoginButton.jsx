import React from 'react';
import { RiKakaoTalkFill } from "react-icons/ri";
import KakaoLogin from 'react-kakao-login';

const KakaoLoginButton = () => {
    const responseKaKao = (response) => {
        console.log(response);
    };

    return (
        <KakaoLogin
            tokenUrl="[카카오에서 발급받은 토큰 URL]"
            onSuccess={responseKaKao}
            onFail={responseKaKao}
            onLogout={responseKaKao}
        >
            <RiKakaoTalkFill />
        </KakaoLogin>
    );
};

export default KakaoLoginButton;
