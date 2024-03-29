import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import './babyselect.css';

const ChildSelect = ({ handleClose }) => {
  const [children, setChildren] = useState([
    { id: 1, name: '김싸피', birthday: '2022-01-01', selected: false },
    { id: 2, name: '김콜라', birthday: '2022-02-02', selected: false },
    { id: 3, name: '김사이다', birthday: '2022-03-03', selected: false }
    // 다른 아이들의 정보를 추가할 수 있습니다.
  ]);

  const handleChildSelect = (id) => {
    const updatedChildren = [...children]; // 배열 복사

    // 선택된 아이를 배열의 맨 앞으로 이동
    const selectedChildIndex = updatedChildren.findIndex(child => child.id === id);
    if (selectedChildIndex !== -1) {
      const selectedChild = updatedChildren.splice(selectedChildIndex, 1)[0];
      updatedChildren.unshift(selectedChild);
    }

    // 선택된 아이의 selected 상태를 토글
    updatedChildren.forEach(child => {
      if (child.id === id) {
        child.selected = true;
      } else {
        child.selected = false;
      }
    });

    setChildren(updatedChildren);
  };

  return (
    <div className="child-select-container">
      {children.map(child => (
        <div key={child.id} className={`child-box ${child.selected ? 'selected' : ''}`} onClick={() => handleChildSelect(child.id)}>
          <div className="child-photo"></div>
          <div className="child-info">
            <div className="child-name">{child.name}</div>
            <div className="child-birthday">{child.birthday}</div>
          </div>
          {child.selected && <div className="selected-icon">✔</div>}
        </div>
      ))}
      <Link to="/baby-add-more">
        <button className="add-child-btn">+</button>
        아이 추가 등록
      </Link>
    </div>
  );
};

export default ChildSelect;
