import React, { useState } from 'react';
import './BabyRelation.css'
import { Link } from 'react-router-dom'

const BabyAddPage = () => {
    const [relationship, setRelationship] = useState('');
    const [isFormValid, setIsFormValid] = useState(false);

    const handleSelectRelationship = (selectedRelationship) => {
        setRelationship(selectedRelationship);
        setIsFormValid(true); // Form is always valid when a relationship is selected
    };

    const handleSubmit = (event) => {
        event.preventDefault();
        // Handle submission logic here, for example, sending the selected relationship to the server
        console.log('Selected relationship:', relationship);
    };

    return (
        <div className="baby-add-container">
            <p className="baby-rel-comment">아이와의 관계를 선택해주세요</p>
            <form className='baby-rel-form' onSubmit={handleSubmit}>
                <div className="relationship-buttons">
                    <button
                        className={`relation-btn ${relationship === '엄마' ? 'selected' : ''}`}
                        onClick={() => handleSelectRelationship('엄마')}
                    >
                        엄마
                    </button>
                    <button
                        className={`relation-btn ${relationship === '아빠' ? 'selected' : ''}`}
                        onClick={() => handleSelectRelationship('아빠')}
                    >
                        아빠
                    </button>
                    <button
                        className={`relation-btn ${relationship === '할머니' ? 'selected' : ''}`}
                        onClick={() => handleSelectRelationship('할머니')}
                    >
                        할머니
                    </button>
                    <button
                        className={`relation-btn ${relationship === '할아버지' ? 'selected' : ''}`}
                        onClick={() => handleSelectRelationship('할아버지')}
                    >
                        할아버지
                    </button>
                    <button
                        className={`relation-btn ${relationship === '기타' ? 'selected' : ''}`}
                        onClick={() => handleSelectRelationship('기타')}
                    >
                        기타
                    </button>
                </div>
                <Link to='/baby-welcome'>
                    <button type="submit" className='baby-add-submit-fin' disabled={!isFormValid}>
                        등록하기
                    </button>
                </Link>
            </form>
        </div>
    );
};

export default BabyAddPage;
