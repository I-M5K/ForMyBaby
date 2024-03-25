import React, { useState } from 'react';
import './BabyRelation.css'
import { Link } from 'react-router-dom'
import { useUserStore } from '../../stores/UserStore';
import { updateBabyRole } from '../../api/userApi'; // Assume there is an API function for updating baby roles

const BabyRelationPage = () => {
    const { babyList, setBabyList, id, family } = useUserStore();
    const [relationship, setRelationship] = useState('');
    const [isFormValid, setIsFormValid] = useState(false);

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
            try {
                // Array to store promises for updating roles
                const roleUpdatePromises = [];
                // Iterate over babyList to find babies with role "none" and update their roles
                babyList.forEach(baby => {
                    if (baby.role === "none") {
                        const data = {
                            'familyCode': family,
                            'babyId': baby.id,
                            'userId': id,
                            'role': relationship
                        }
                        // Push the promise to the array
                        console.log(data);
                        roleUpdatePromises.push(updateBabyRole(data));
                    }
                });
        
                // Wait for all role update requests to complete
                await Promise.all(roleUpdatePromises);
        
                console.log('Relationship updated successfully:', relationship);
                // Update baby roles in local state (zustand-persist) for babies with role "none"
                const updatedBabyList = babyList.map(baby => {
                    if (baby.role === "none") {
                        return {
                            ...baby,
                            role: relationship
                        };
                    }
                    return baby;
                });
                console.log('Updated babyList:', updatedBabyList);
            } catch (error) {
                console.error('Error occurred while updating relationship:', error);
            }
        }
    };
    
    return (
        <div className="baby-add-container">
            <p className="baby-rel-comment">아이와의 관계를 선택해주세요</p>
            <form className='baby-rel-form'>
                <div className="relationship-buttons">
                    <button
                        type="button"
                        className={`relation-btn ${relationship === '엄마' ? 'selected' : ''}`}
                        onClick={() => handleSelectRelationship('엄마')}
                    >
                        엄마
                    </button>
                    <button
                        type="button"
                        className={`relation-btn ${relationship === '아빠' ? 'selected' : ''}`}
                        onClick={() => handleSelectRelationship('아빠')}
                    >
                        아빠
                    </button>
                    <button
                        type="button"
                        className={`relation-btn ${relationship === '할머니' ? 'selected' : ''}`}
                        onClick={() => handleSelectRelationship('할머니')}
                    >
                        할머니
                    </button>
                    <button
                        type="button"
                        className={`relation-btn ${relationship === '할아버지' ? 'selected' : ''}`}
                        onClick={() => handleSelectRelationship('할아버지')}
                    >
                        할아버지
                    </button>
                    <button
                        type="button"
                        className={`relation-btn ${relationship === '기타' ? 'selected' : ''}`}
                        onClick={() => handleSelectRelationship('기타')}
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