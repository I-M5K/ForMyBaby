// InfoModal.js
import React from 'react';
import './InfoModal.css'; // 스타일을 위한 CSS 파일

const InfoModal = ({ isOpen, onClose, children }) => {
  if (!isOpen) return null;

  return (
    <div className="modal-overlay" onClick={onClose}>
      <div className="modal-content" onClick={(e) => e.stopPropagation()}>
        {children}
        <button onClick={onClose}>Close</button>
      </div>
    </div>
  );
};

export default InfoModal;
