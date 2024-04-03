import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import "./ChildInfo.css";

import { PiBaby } from "react-icons/pi";
import ChildPhoto from "../../assets/smilebaby.png";
import { useUserStore } from "../../stores/UserStore";

const ChildInfo = () => {

const {babyList} = useUserStore();
  

  return (
    <div className="child-info-container">
      <div>
        <p className="child-title">우리 아이 정보</p>
        <div className="child-container">
          {babyList.map((child, index) => (
            <div
              key={index}
              className="child-item"
              onClick={() => console.log("Clicked on child")}
            >
              <div className="child-photo">
                <img src={child.profileImg} />
              </div>
              <div>{child.name}</div>
            </div>
          ))}
          <button className="add-child-button" onClick={''}>
            아이 <br />
            추가
          </button>
        </div>
      </div>
    </div>
  );
};

export default ChildInfo;
