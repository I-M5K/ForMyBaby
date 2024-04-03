import React, { useEffect, useState } from "react";
import { Doughnut } from "react-chartjs-2";
import "./babyDoughnutChart.css";
import { useUserStore } from "../../../stores/UserStore";

const DoughnutChartComponent = ({ danger, hours, awake }) => {
  const { babyList, babySelected } = useUserStore();
  const [selectedBabyName, setSelectedBabyName] = useState("");
  const [selectedBabyDay, setSelectedBabyDay] = useState("");

  const data1 = {
    datasets: [
      {
        label: "# of Votes",
        data: [hours, 24 - hours], // 첫 번째 차트 데이터
        backgroundColor: [
          "rgba(0, 255, 0, 0.2)", // 초록색
          "rgba(200, 200, 200, 0.2)",
        ],
        borderColor: ["rgba(0, 255, 0, 1)"], // 초록색
        borderWidth: [2, 0],
      },
    ],
  };

  const data2 = {
    datasets: [
      {
        label: "# of Votes",
        data: [awake, 10 - awake], // 두 번째 차트 데이터
        backgroundColor: [
          "rgba(0, 255, 0, 0.2)", // 초록색
          "rgba(200, 200, 200, 0.2)",
        ],
        borderColor: ["rgba(0, 255, 0, 1)"], // 초록색
        borderWidth: [2, 0],
      },
    ],
  };

  const data3 = {
    datasets: [
      {
        label: "# of Votes",
        data: [danger, 20 - danger], // 세 번째 차트 데이터
        backgroundColor: [
          "rgba(255, 0, 0, 0.2)", // 빨간색
          "rgba(200, 200, 200, 0.2)",
        ],
        borderColor: ["rgba(255, 0, 0, 1)"], // 빨간색
        borderWidth: [2, 0],
      },
    ],
  };

  const options = {
    maintainAspectRatio: false,
    aspectRatio: 1,
    plugins: {
      legend: {
        display: false,
      },
      tooltip: {
        enabled: false,
      },
      datalabels: {
        display: true,
        formatter: (value, context) => {
          return value;
        },
      },
    },
    elements: {
      center: {
        text: "65%",
        color: "#FF6384",
        fontStyle: "Arial",
        sidePadding: 10,
      },
    },
  };

  useEffect(() => {
    // 선택된 아이의 이름과 생일 업데이트
    const selectedBaby = babyList.find((baby) => baby.babyId === babySelected);
    if (selectedBaby) {
      const givenDateStr = selectedBaby.birthDate; // 주어진 날짜 문자열
      const [year, month, day] = givenDateStr.split("-");
      const givenDate = new Date(year, month - 1, day);
      const today = new Date();
      const timeDiff = today.getTime() - givenDate.getTime();
      const daysDiff = Math.ceil(timeDiff / (1000 * 60 * 60 * 24));
      setSelectedBabyDay(daysDiff);

      if (selectedBaby.babyName && selectedBaby.babyName.length > 0) {
        const lastChar = selectedBaby.babyName[selectedBaby.babyName.length - 1];
        if (lastChar) {
          setSelectedBabyName(selectedBaby.babyName.slice(1) + "의");
        }
      }
    }
  }, [babyList, babySelected]);

  // 범위에 따른 텍스트 표시 함수
  const getRangeText = (value) => {
    if (value === "hours") {
      return hours >= 8 ? "적합" : hours >= 6 ? "부족" : "위험";
    } else if (value === "awake") {
      return awake <= 2 ? "적합" : awake <= 4 ? "부족" : "위험";
    } else if (value === "danger") {
      return danger <= 5 ? "적합" : danger <= 10 ? "부족" : "위험";
    }
  };

  return (
    <div>
      <div className="chart-detail-header">
        <span className="bold-text">{selectedBabyDay}일 된 우리 {selectedBabyName}</span>
        <br />
        <span className="bold-text">수면현황</span>입니다
      </div>

      <div className="chart-container">
        <div className="chart-item">
          <div className="chart-title">수면 시간</div>
          <Doughnut data={data1} options={options} />
          <div className={`chart-footer ${getRangeText("hours")}`}>{getRangeText("hours")}</div>
        </div>

        <div className="chart-item">
          <div className="chart-title">기상 횟수</div>
          <Doughnut data={data2} options={options} />
          <div className={`chart-footer ${getRangeText("awake")}`}>{getRangeText("awake")}</div>
        </div>

        <div className="chart-item">
          <div className="chart-title">위험 감지</div>
          <Doughnut data={data3} options={options} />
          <div className={`chart-footer ${getRangeText("danger")}`}>{getRangeText("danger")}</div>
        </div>
      </div>
    </div>
  );
};

export default DoughnutChartComponent;
