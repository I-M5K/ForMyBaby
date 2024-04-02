import './BabyProfileEdit.css';
import React, { useEffect, useState } from "react";
import babyImage from '../../assets/hagrid.png'; // 아이 사진 이미지 경로를 적절히 수정해주세요.
import { useLocation } from "react-router-dom";
import { Link } from 'react-router-dom'; // Link 컴포넌트 import
import Xbutton from '../../assets/x_button.png'
import { useUserStore } from "../../stores/UserStore";

const KidInfo = () => {
    const {
        babyList,
        babySelected
      } = useUserStore();
    const [selectedBabyName, setSelectedBabyName] = useState("");
    const [selectedBabyImg, setSelectedBabyImg] = useState("");
    const [selectedBabyGender, setSelectedBabyGender] = useState("");
    const [selectedBabyBirthDate, setSelectedBabyBirthDate] = useState("");

    useEffect(() => {
        // 선택된 아이의 이름과 생일 업데이트
        const selectedBaby = babyList.find((baby) => baby.babyId === babySelected);
        if (selectedBaby) {
          setSelectedBabyName(selectedBaby.babyName);
          setSelectedBabyImg(selectedBaby.profileImg);
          setSelectedBabyGender(selectedBaby.babyGender);
          setSelectedBabyBirthDate(selectedBaby.birthDate);
        }
      }, [babyList, babySelected]);

    return (
        <div className="baby-profile-container">
            <Link to="/main">
                <button className="present-quit-button">
                    <img src={Xbutton} className='present-quit-button-img' />
                </button>
            </Link>
        <div className="baby-profile">
            <div className="baby-profile-image">
            <img src={selectedBabyImg} alt="baby" />
            </div>
            <div className="baby-info">
            {/* <div className="baby-info-item">
                <span className="baby-label">{setSelectedBabyGender}</span>
                <div className="baby-gender">s
                {gender === 'male' ? '남' : (gender === 'female' ? '여' : null)}
                </div>
            </div> */}
            <div className="baby-info-item">
                <span className="baby-label">이름</span>
                <span className="baby-value">{selectedBabyName}</span>
            </div>
            <div className="baby-info-item">
                <span className="baby-label">생일</span>
                <span className="baby-value">{selectedBabyBirthDate}</span>
            </div>
            </div>
        </div>
        </div>
    );
};

export default KidInfo;
