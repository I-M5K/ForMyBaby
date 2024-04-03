import React, { useState } from "react";
import "./BabyAdd.css";
import { Link } from "react-router-dom";
import { useUserStore } from "../../stores/UserStore";
import { addBabyInfo, addFirstBabyInfo } from "../../api/userApi";
import BabyGender from "../../api/BabyGender";
import { useNavigate } from "react-router-dom";
import { FaCamera } from "react-icons/fa";
import { MdArrowBackIos } from "react-icons/md";
const BabyAddPage = () => {
  const {
    family,
    setFamily,
    id,
    babyList,
    setBabyList,
    babySelected,
    setBabySelected,
  } = useUserStore();

  const [babyName, setBabyName] = useState("");
  const [babyGender, setBabyGender] = useState("");
  const [babyBirthDate, setBabyBirthDate] = useState("");
  const [babyPhoto, setBabyPhoto] = useState(null);
  const [babyPhotoPreview, setBabyPhotoPreview] = useState(null);
  const navigate = useNavigate();
  const isFormValid = babyName && babyGender && babyBirthDate && babyPhoto;
  const [nameEmpty, setNameEmpty] = useState(false);
  const [birthDateEmpty, setBirthDateEmpty] = useState(false);

  const handlePhotoChange = (event) => {
    if (event.target.files && event.target.files[0]) {
      const file = event.target.files[0];
      setBabyPhoto(file);

      const reader = new FileReader();
      reader.onload = (e) => {
        setBabyPhotoPreview(e.target.result);
      };
      reader.readAsDataURL(file);
    }
  };

  const handleGenderSelect = (gender) => {
    setBabyGender(gender);
  };

  const handleSubmit = async (event) => {
    event.preventDefault();
    if (isFormValid) {
      const formData = new FormData();
      formData.append("userId", id);
      formData.append("babyName", babyName);
      if (babyGender === "male") {
        formData.append("babyGender", BabyGender.Male);
      } else {
        formData.append("babyGender", BabyGender.Female);
      }
      formData.append("babyBirthDate", babyBirthDate);
      formData.append("profileImg", babyPhoto);

      if (family === null) {
        try {
          const data = await addFirstBabyInfo(formData);
          console.log("First Baby information submitted successfully!");
          const babyList = data.babyReadResponseList;
          if (babySelected === null) {
            setBabySelected(babyList[0].babyId);
          }
          setBabyList(babyList);
          setFamily(babyList[0].familyCode);
        } catch (error) {
          console.error(error);
        }
      } else {
        try {
          const data = await addBabyInfo(formData);
          console.log("Baby information submitted successfully!");
          console.log(data);
          const list = data;
          if (babySelected === null) {
            setBabySelected(list[0].babyId);
          }
          setBabyList(list);
        } catch (error) {
          console.error(error);
        }
      }
      navigate("/baby-relation");
    } else {
      // 이름이나 생년월일이 비어있는 경우 상태 업데이트
      setNameEmpty(!babyName);
      setBirthDateEmpty(!babyBirthDate);
    }
  };

  return (
    <div className="baby-add-container">
      <MdArrowBackIos className="arrow-back-icon" />
      <p className="baby-add-comment">아이 정보를 입력해주세요</p>
      <form className="baby-add-form" onSubmit={handleSubmit}>
        <div className="photo-gender">
          <div>
            {babyPhotoPreview ? (
              <img
                src={babyPhotoPreview}
                alt="Preview"
                className="baby-photo-preview"
              />
            ) : (
              <label htmlFor="babyPhoto" className="file-input-label">
                <FaCamera className="facamera" size={40} color="white" />
                <input
                  type="file"
                  id="babyPhoto"
                  onChange={handlePhotoChange}
                  className="file-input"
                />
              </label>
            )}
          </div>
          <div>
            <div className="gender-selection">
              <button
                type="button"
                className={`gender-btn ${
                  babyGender === "male" ? "active" : ""
                }`}
                onClick={() => handleGenderSelect("male")}
              >
                남자
              </button>
              <button
                type="button"
                className={`gender-btn ${
                  babyGender === "female" ? "active" : ""
                }`}
                onClick={() => handleGenderSelect("female")}
              >
                여자
              </button>
            </div>
          </div>
        </div>
        <div className="text-date">
          <input
            type="text"
            value={babyName}
            onChange={(e) => {
              setBabyName(e.target.value);
              setNameEmpty(false);
            }}
            className={`input-field ${nameEmpty ? "empty" : ""}`}
            placeholder="이름"
          />
          {nameEmpty && <p className="error-message">이름을 입력해주세요</p>}
          <input
            type="date"
            value={babyBirthDate}
            onChange={(e) => {
              setBabyBirthDate(e.target.value);
              setBirthDateEmpty(false);
            }}
            className={`input-field ${birthDateEmpty ? "empty" : ""}`}
          />
          {birthDateEmpty && (
            <p className="error-message">생년월일을 입력해주세요</p>
          )}
        </div>
        <Link to="/baby-relation">
          <button
            type="submit"
            onClick={handleSubmit}
            className="baby-add-submit"
            disabled={!isFormValid}
          >
            등록하기
          </button>
        </Link>
      </form>
    </div>
  );
};

export default BabyAddPage;
