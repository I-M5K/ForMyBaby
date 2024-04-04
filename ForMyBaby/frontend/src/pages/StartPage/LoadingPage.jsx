import React from "react";
import Loading from "../../assets/loading.gif"; // GIF 이미지 경로를 지정해주세요

const LoadingPage = () => {
  return (
    <div
      style={{
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
        height: "100vh",
      }}
    >
      <img
        src={Loading}
        alt="loading"
        style={{ maxWidth: "100%", maxHeight: "100%" }}
      />
    </div>
  );
};

export default LoadingPage;
