import { useUserStore } from '../../stores/UserStore';
import './SettingTab.css'

const PresentPage = () => {

    const {family} = useUserStore();
    
    return (
        <div>
            <p className="family-code-title">가족 코드 : {family && family[0].familyCode}</p>
        </div>
    );
}

export default PresentPage;