import { useUserStore } from '../../stores/UserStore';
import { Link } from 'react-router-dom';
import './SettingTab.css'

const SettingTab = () => {

    const {family} = useUserStore();
    
    return (
        <div>
            <br />
            <div className="family-code-title">가족 코드</div>
            <br />
            <br />
            <Link to="/mypage-settings">
                <div className="mypage-settings">환경 설정</div>
            </Link>
        </div>
    );
}

export default SettingTab;