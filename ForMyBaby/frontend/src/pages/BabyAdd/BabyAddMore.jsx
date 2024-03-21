import React, { useState } from 'react';
import './BabyAdd.css';

const BabyAddPage = () => {
    const [babyName, setBabyName] = useState('');
    const [babyGender, setBabyGender] = useState('');
    const [babyBirthDate, setBabyBirthDate] = useState('');
    const [babyPhoto, setBabyPhoto] = useState(null);
    const [babyPhotoPreview, setBabyPhotoPreview] = useState(null); // 미리보기 URL 상태 추가

    const isFormValid = babyName && babyGender && babyBirthDate && babyPhoto;

    const handlePhotoChange = (event) => {
        if (event.target.files && event.target.files[0]) {
            const file = event.target.files[0];
            setBabyPhoto(file);

            const reader = new FileReader();
            reader.onload = (e) => {
                setBabyPhotoPreview(e.target.result); // 파일 읽기가 완료되면 미리보기 URL을 설정
            };
            reader.readAsDataURL(file);
        }
    };

    const handleGenderSelect = (gender) => {
        setBabyGender(gender);
    };

    const handleSubmit = (event) => {
        event.preventDefault();
        if (isFormValid) {
            // 실제 등록 로직 구현
            console.log('Form Submitted');
            console.log({ babyName, babyGender, babyBirthDate, babyPhoto });
        }
    };

    return (
        <div className="baby-add-container">
            <p className="baby-add-comment">추가할 아이 정보를 입력해주세요</p>
            <form className='baby-add-form' onSubmit={handleSubmit}>
                <div className='photo-gender'>
                    <div>
                        {babyPhotoPreview && <img src={babyPhotoPreview} alt="Preview" className="baby-photo-preview" />}
                        <input type="file" id="babyPhoto" onChange={handlePhotoChange} className="file-input" />
                        <label htmlFor="babyPhoto" className="file-input-label">+</label>
                    </div>
                    <div>
                        <div className="gender-selection">
                            <button type="button" className={`gender-btn ${babyGender === 'male' ? 'active' : ''}`} onClick={() => handleGenderSelect('male')}>남자</button>
                            <button type="button" className={`gender-btn ${babyGender === 'female' ? 'active' : ''}`} onClick={() => handleGenderSelect('female')}>여자</button>
                        </div>
                    </div>
                </div>
                <div className='text-date'>
                    <input
                        type="text"
                        value={babyName}
                        onChange={(e) => setBabyName(e.target.value)}
                        className="input-field"
                        placeholder="이름"
                    />
                    <input
                        type="date"
                        value={babyBirthDate}
                        onChange={(e) => setBabyBirthDate(e.target.value)}
                        className="input-field"
                    />
                </div>
                <button type="submit" className='baby-add-submit' disabled={!isFormValid}>등록하기</button>
            </form>
        </div>
    );
};

export default BabyAddPage;