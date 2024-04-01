import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import "./ChildInfo.css";

import ChildPhoto from "../../assets/smilebaby.png";

const ChildInfo = () => {
  const [children, setChildren] = useState([]);
  const navigate = useNavigate();

  const addChild = () => {
    setChildren([...children, { name: "김싸피", photo: "child_photo_url" }]);
  };

  return (
    <div className="child-info-container">
      <div>
        <p className="child-title">우리 아이 정보</p>
        <div className="child-container">
          {children.map((child, index) => (
            <div
              key={index}
              className="child-item"
              onClick={() => console.log("Clicked on child")}
            >
              <div className="child-photo">
                <img src={ChildPhoto} />
              </div>
              <div>{child.name}</div>
            </div>
          ))}
          <button className="add-child-button" onClick={addChild}>
            아이 <br />
            추가
          </button>
        </div>
      </div>
    </div>
  );
};

export default ChildInfo;
