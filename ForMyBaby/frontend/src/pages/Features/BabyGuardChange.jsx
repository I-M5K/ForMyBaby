import { Link } from 'react-router-dom';

const LoadingPage = () => {
    const handleLogout = () => {
        localStorage.clear();
      };
    return (
        <div>
            <h1>분석 변화</h1>
            <Link to="/">
                <button onClick={() => handleLogout()}>로그아웃</button>
            </Link>
        </div>
    );
}

export default LoadingPage;