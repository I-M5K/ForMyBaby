import React from "react";
import "./Profile.css";
import { MdArrowBackIos } from "react-icons/md";

import BangaBanga from "../../assets/bangabanga.png";

const UserProfile = ({ userPhoto, userName, onProfileEdit }) => {
  return (
    <div>
      <MdArrowBackIos className="arrow-back-icon" />
      <div className="my-title">마이 페이지</div>
      <div className="user-profile">
        <div className="user-profile-container">
          <div className="user-photo-container">
            <img src={BangaBanga} alt="User" className="user-photo" />
          </div>
          <div className="user-info">
            <h3>김싸피</h3>
          </div>
          <button className="edit-profile-button" onClick={onProfileEdit}>
            프로필 수정
          </button>
        </div>
      </div>
    </div>
  );
};

export default UserProfile;
