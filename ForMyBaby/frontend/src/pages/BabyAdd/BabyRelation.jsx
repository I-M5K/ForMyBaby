import React, { useState } from 'react';
import './BabyRelation.css'
import { Link } from 'react-router-dom'
import { useUserStore } from '../../stores/UserStore';
import { updateBabyRole } from '../../api/userApi'; // Assume there is an API function for updating baby roles
import { useLocation, useNavigate } from 'react-router-dom';
import { MdArrowBackIos } from "react-icons/md";
const BabyRelationPage = () => {
    const { babyList, setBabyList, id, family } = useUserStore();
    const [relationship, setRelationship] = useState('');
    const [isFormValid, setIsFormValid] = useState(false);
    const navigate = useNavigate(); // useNavigate 훅 사용

    // // Sample babyList data
    // const sampleBabyList = [
    //     { id: 1, name: '아기1', role: 'n' },
    //     { id: 2, name: '아기2', role: '엄마' },
    //     { id: 3, name: '아기3', role: 'none' }
    // ];

    const handleSelectRelationship = (selectedRelationship) => {
        setRelationship(selectedRelationship);
        setIsFormValid(true); // Form is always valid when a relationship is selected
    };

    const handleSubmit = async (event) => {
        event.preventDefault();
        // Check if the form is valid before submitting
        if (isFormValid) {
            const data = {
                'familyCode': family,
                'userId': id,
                'role': relationship
            }
            try {
                // API 호출하여 아기의 역할 업데이트
                const response = await updateBabyRole(data);
                console.log('update 후 모든 아이리스트', response.data);
                
                // 서버 응답에 따라 필요한 작업을 수행할 수 있습니다.
                setBabyList(response.data);
                navigate("/main");
            } catch (error) {
                console.error('Failed to update role:', error);
                // 오류 처리를 할 수 있습니다.
                navigate("/baby-relation");
            }
        

        }
    };
    
    return (
        
        <div className="baby-add-container">
            <MdArrowBackIos className="arrow-back-icon" />

            <p className="baby-rel-comment">아이와의 관계를 선택해주세요</p>
            
            <form className='baby-rel-form'>

                <div className="relationship-buttons">
                    <button
                        type="button"
                        className={`relation-btn ${relationship === 'Mother' ? 'selected' : ''}`}
                        onClick={() => handleSelectRelationship('Mother')}
                    >
                        엄마
                    </button>
                    <button
                        type="button"
                        className={`relation-btn ${relationship === 'Father' ? 'selected' : ''}`}
                        onClick={() => handleSelectRelationship('Father')}
                    >
                        아빠
                    </button>
                    <button
                        type="button"
                        className={`relation-btn ${relationship === 'Grandmother' ? 'selected' : ''}`}
                        onClick={() => handleSelectRelationship('Grandmother')}
                    >
                        할머니
                    </button>
                    <button
                        type="button"
                        className={`relation-btn ${relationship === 'Grandfather' ? 'selected' : ''}`}
                        onClick={() => handleSelectRelationship('Grandfather')}
                    >
                        할아버지
                    </button>
                    <button
                        type="button"
                        className={`relation-btn ${relationship === 'Others' ? 'selected' : ''}`}
                        onClick={() => handleSelectRelationship('Others')}
                    >
                        기타
                    </button>
                </div>
                <button type="submit" onClick={handleSubmit} className='baby-add-submit-fin' disabled={!isFormValid}>
                    등록하기
                </button>
            </form>
        </div>
    );
};

export default BabyRelationPage;
