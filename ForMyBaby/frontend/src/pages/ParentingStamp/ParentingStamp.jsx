import React, { useState, useEffect } from "react";
import NavBar from "../../components/NavBar";
import StampPage1 from "../../components/feature/parentingstamp/StampPage1";
import StampPage2 from "../../components/feature/parentingstamp/StampPage2";
import StampPage3 from "../../components/feature/parentingstamp/StampPage3";
import StampPage4 from "../../components/feature/parentingstamp/StampPage4";
import { getStampList } from "../../api/stampApi";
import "./ParentingStamp.css";

function ParentingStamp() {
  const [selectedPage, setSelectedPage] = useState(null);
  const [note, setNote] = useState("");
  const [animationClass, setAnimationClass] = useState("");
  const [stampList, setStampList] = useState([]);

  const handlePageSelection = (newPage) => {
    setAnimationClass("slide-out");
    setTimeout(() => {
      setSelectedPage(newPage);
      setAnimationClass("slide-in");
    }, 200); // 애니메이션 지속 시간과 일치
  };

  useEffect(() => {
    // 페이지가 처음 렌더링될 때 첫 번째 버튼을 클릭한 것처럼 설정
    fetchStampList();
    handlePageSelection(<StampPage1 />);
  }, []);

  const fetchStampList = async () => {
    try {
      // API를 통해 스탬프 목록 가져오기
      const stamps = await getStampList();
      // 가져온 스탬프 목록을 상태에 설정
      setStampList(stamps);
    } catch (error) {
      console.error("스탬프 목록을 가져오는 중 에러 발생:", error);
    }
  };

  const saveNote = () => {
    console.log("저장된 글:", note);
    // 노트 저장 로직 추가
  };

  return (
    <div className="ParentingStamp">
      <div className="parenstamp-title">성장 스탬프</div>
      <div className="parenstamp-header">
        <button
          onClick={() =>
            handlePageSelection(
              <StampPage1 stampList={stampList.slice(0, 5)} />
            )
          }
        >
          0~3개월
        </button>
        <button
          onClick={() =>
            handlePageSelection(
              <StampPage2 stampList={stampList.slice(5, 10)} />
            )
          }
        >
          4~6개월
        </button>
        <button
          onClick={() =>
            handlePageSelection(
              <StampPage3 stampList={stampList.slice(10, 15)} />
            )
          }
        >
          7~9개월
        </button>
        <button
          onClick={() =>
            handlePageSelection(
              <StampPage4 stampList={stampList.slice(15, 20)} />
            )
          }
        >
          10~12개월
        </button>
      </div>

      <div className={`parenstamp-contents ${animationClass}`}>
        {selectedPage}
      </div>
      <NavBar />
    </div>
  );
}

export default ParentingStamp;
