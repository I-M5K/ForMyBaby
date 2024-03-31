// api/kakaoApi.js
const kakaoApi = async (code) => {
    try {
        const headers = {
            "Content-Type": "application/x-www-form-urlencoded",
        };

        const response = await fetch(`http://localhost:8080/v1/oauth/kakao/login?code=${code}`, {
        //const response = await fetch(`http://j10c202.p.ssafy.io:8082/v1/oauth/kakao/login?code=${code}`, {
            method: "POST",
            headers: headers,
        });

        if (!response.ok) {
            throw new Error('네트워크 에러!');
        }

        // 헤더에서 토큰 값을 추출하여 저장
        //const token = response.headers.get('Authorization');
        //const [userData, setUserData] = useRecoilState(userDataState);
        //token.replace('Bearer ', '');
        //setUserData({ ...userData, jwt: token });
        //localStorage.setItem('accessToken', token.replace('Bearer ', ''));
        //localStorage.setItem('id', )
        return response;
    } catch (error) {
        console.error("오류 발생", error);
        throw error; // 오류를 다시 throw하여 호출한 곳에서 처리할 수 있도록 함
    }
};

export default kakaoApi;
