import React, { useState, useEffect } from "react";
import { useSpring, animated } from "react-spring";
import "./BabyWelcome.css";
import { Link } from "react-router-dom";
import Confetti from "react-confetti";
import sarang from "../../assets/bears/saranghaeyo.png";

const WelcomePage = () => {
  return (
    <div className="baby-welcome-container">
      <img className="sarang" src={sarang} alt="" />
      <h1 className="weltex">
        포마베와 함께 새로운 <br />
        나날들을 시작해보아요
      </h1>

      <Confetti numberOfPieces={150} recycle={false} timeout={10000} />
      <Link to="/main">
        <button type="button" className="baby-welcome">
          시작하기
        </button>
      </Link>
    </div>
  );
};

export default WelcomePage;
