import React from "react";
import "./Profile.css";
import { LuPencil } from "react-icons/lu";


import BangaBanga from "../../assets/bangabanga.png";

const UserProfile = ({ userPhoto, userName, onProfileEdit }) => {
  const {profileImg, name, } = useUserStore();
  return (
    <div>
      <div className="my-title">마이 페이지</div>
      <div className="user-profile">
        <div className="user-profile-container">
          <div className="user-photo-container">
            <img src={profileImg} alt="User" className="user-photo" />
          </div>
          <div className="user-info">
            <h3>{name}</h3>
          </div>
        </div>
      </div>
    </div>
  );
};

export default UserProfile;
