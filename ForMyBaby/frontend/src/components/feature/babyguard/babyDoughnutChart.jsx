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
          "rgba(255, 206, 86, 0.2)",
          "rgba(200, 200, 200, 0.2)",
        ],
        borderColor: ["rgba(255, 206, 86, 1)"],
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
          "rgba(255, 206, 86, 0.2)",
          "rgba(200, 200, 200, 0.2)",
        ],
        borderColor: ["rgba(255, 206, 86, 1)"],
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
          "rgba(255, 206, 86, 0.2)",
          "rgba(200, 200, 200, 0.2)",
        ],
        borderColor: ["rgba(255, 206, 86, 1)"],
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

  // const totalData1 = data1.datasets[0].data.reduce((acc, curr) => acc + curr, 0);
  // const totalData2 = data2.datasets[0].data.reduce((acc, curr) => acc + curr, 0);
  // const totalData3 = data3.datasets[0].data.reduce((acc, curr) => acc + curr, 0);

  // const percentData1 = data1.datasets[0].data.map(value => ((value / totalData1) * 100).toFixed(2));
  // const percentData2 = data2.datasets[0].data.map(value => ((value / totalData2) * 100).toFixed(2));
  // const percentData3 = data3.datasets[0].data.map(value => ((value / totalData3) * 100).toFixed(2));

  // const data1WithPercent = {
  //   datasets: [
  //     {
  //       ...data1.datasets[0],
  //       data: percentData1,
  //     },
  //   ],
  // };

  // const data2WithPercent = {
  //   datasets: [
  //     {
  //       ...data2.datasets[0],
  //       data: percentData2,
  //     },
  //   ],
  // };

  // const data3WithPercent = {
  //   datasets: [
  //     {
  //       ...data3.datasets[0],
  //       data: percentData3,
  //     },
  //   ],
  // };
useEffect(() => {
    // 선택된 아이의 이름과 생일 업데이트
    const selectedBaby = babyList.find((baby) => baby.babyId === babySelected);
    if (selectedBaby) {
      const givenDateStr = selectedBaby.birthDate; // 주어진 날짜 문자열
      // 주어진 날짜 문자열을 연도, 월, 일로 분리
      const [year, month, day] = givenDateStr.split("-");
      // 분리된 연도, 월, 일을 이용하여 Date 객체 생성
      const givenDate = new Date(year, month - 1, day); // month는 0부터 시작하므로 1을 빼줌
      const today = new Date(); // 오늘 날짜 객체
      // 두 날짜 간의 차이를 밀리초로 계산
      const timeDiff = today.getTime() - givenDate.getTime();
      // 밀리초를 일로 변환하여 일수 차이 계산
      const daysDiff = Math.ceil(timeDiff / (1000 * 60 * 60 * 24));
      console.log("오늘과 주어진 날짜의 일수 차이:", daysDiff);

      // const particle = (() => {
      //   if (babyName && babyName.length > 0) {
      //     const lastChar = babyName[babyName.length - 1];
      //     if (lastChar) {
      //       return lastChar.match(/[가-힣]/) ? (lastChar.charCodeAt(0) - 0xac00) % 28 > 0 ? '이는' : '는' : '';
      //     }
      //   }
      //   return '';
      // })();
      if (selectedBaby.babyName && selectedBaby.babyName.length > 0) {
        const lastChar =
          selectedBaby.babyName[selectedBaby.babyName.length - 1];
        if (lastChar) {
          if (
            lastChar.match(/[가-힣]/) &&
            (lastChar.charCodeAt(0) - 0xac00) % 28 > 0
          ) {
            setSelectedBabyName(selectedBaby.babyName.slice(1) + "이의");
          } else {
            setSelectedBabyName(selectedBaby.babyName.slice(1) + "의");
          }
        }
      }
      //setSelectedBabyName(getPostWord(selectedBaby.babyName, '이', ''));
      setSelectedBabyDay(daysDiff);
      setSelectedBabyName(selectedBaby.babyName);
    }
  }, [babyList, babySelected]);
  return (
    <div>
      <div className="chart-detail-header">
      <span className="bold-text">{selectedBabyDay}일 된 우리 {selectedBabyName}</span>
        <br />
        <span className="bold-text">수면현황 </span>입니다
      </div>

      <div className="chart-container">
        <div className="chart-item">
          <div className="chart-title">수면 시간</div>
          <Doughnut data={data1} options={options} />
          <div className="chart-footer">부족</div>
        </div>

        <div className="chart-item">
          <div className="chart-title">기상 횟수</div>
          <Doughnut data={data2} options={options} />
          <div className="chart-footer">적합</div>
        </div>

        <div className="chart-item">
          <div className="chart-title">위험 감지</div>
          <Doughnut data={data3} options={options} />
          <div className="chart-footer">위험</div>
        </div>
      </div>
    </div>
  );
};

export default DoughnutChartComponent;
