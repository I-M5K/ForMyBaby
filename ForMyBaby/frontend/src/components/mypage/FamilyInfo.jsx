import React, { useState } from "react";
import "./FamilyInfo.css";

import Hagrid from "../../assets/hagrid.png";

const FamilyInfo = () => {
  const [familyMembers, setFamilyMembers] = useState([]);

  const addFamilyMember = () => {
    setFamilyMembers([
      ...familyMembers,
      { name: "아들", photo: "family_member_photo_url" },
    ]);
  };

  return (
    <div className="family-info-container">
      <div>
        <p className="family-title">우리 가족 정보</p>
        <div className="family-container">
          {familyMembers.map((member, index) => (
            <div
              key={index}
              className="family-member"
              onClick={() => console.log("Clicked on family member")}
            >
              <div className="family-photo">
                <img src={Hagrid} alt={member.name} />
              </div>
              <div>{member.name}</div>
            </div>
          ))}
          <button className="add-family-button" onClick={addFamilyMember}>
            가족 <br />
            추가
          </button>
        </div>
      </div>
    </div>
  );
};

export default FamilyInfo;
