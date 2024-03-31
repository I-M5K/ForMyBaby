import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import './babyselect.css';
import { useUserStore } from '../../stores/UserStore';
import {selectBaby} from '../../api/userApi';

const ChildSelect = ({ handleClose }) => {
  const { babyList, babySelected, setBabySelected } = useUserStore();

  const handleChildSelect = (id) => {
    setBabySelected(id);
    selectBaby(id);
  };

  return (
    <div className="child-select-container">
      {babyList.map(baby => (
        <div key={baby.babyId} className={`child-box ${baby.babyId === babySelected ? 'selected' : ''}`} onClick={() => handleChildSelect(baby.babyId)}>
          <div className="child-photo"></div>
          <div className="child-info">
            <div className="child-name">{baby.babyName}</div>
            <div className="child-birthday">{baby.babyBirthdate}</div>
          </div>
          {baby.babyId === babySelected && <div className="selected-icon">✔</div>}
        </div>
      ))}
      <Link to="/baby-add">
        <button className="add-child-btn">+</button>
        아이 추가 등록
      </Link>
    </div>
  );
};

export default ChildSelect;
