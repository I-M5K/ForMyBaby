import React from "react";
import { Bar } from "react-chartjs-2";
import Chart from "chart.js/auto";
import ChartDataLabels from "chartjs-plugin-datalabels";

import "./SleepStatusContent.css"; // 귀하의 CSS 스타일링

Chart.register(ChartDataLabels);

const SleepStatusContent = () => {
  // { danger, hours, awake, temp, humid }
  const danger = 2; // '위험 감지'에 대한 값
  const hours = 13; // '수면 총 시간'에 대한 값
  const awake = 8; // '기상 횟수'에 대한 값
  const temp = 26; // '온도'에 대한 값
  const humid = 35; // '습도'에 대한 값

  const sleepData = {
    labels: ["수면 총 시간", "기상 횟수", "위험 감지"],
    datasets: [
      {
        label: "Value",
        backgroundColor: ["#FFCE56", "#000000", "#9966FF"],
        data: [danger, hours, awake],
        barThickness: 10,
        borderRadius: 10,
      },
    ],
  };

  const weatherData = {
    labels: ["온도", "습도"],
    datasets: [
      {
        label: "Value",
        backgroundColor: ["#FF6384", "#36A2EB"],
        data: [temp, humid],
        barThickness: 10,
        borderRadius: 10,
      },
    ],
  };

  // 기본 옵션
  const baseOptions = {
    indexAxis: "y",
    responsive: false,
    layout: {
      padding: {
        right: 30,
        bottom: 70,
      },
    },
    plugins: {
      legend: {
        display: false,
      },
      tooltip: {
        enabled: false,
      },
      datalabels: {
        color: "black",
        anchor: "end",
        align: "end",
        offset: 4,
        formatter: (value) => value,
      },
    },
    scales: {
      x: {
        display: false,
      },
      y: {
        grid: {
          display: false,
        },
        ticks: {
          font: {
            size: 14,
          },
        },
      },
    },
    animation: {
      duration: 2000,
      easing: "easeInOutQuad",
    },
  };

  // sleepData 차트에 특화된 옵션
  const sleepOptions = {
    ...baseOptions,
    layout: {
      padding: {
        right: 30,
        bottom: 40,
      },
    },
    scales: {
      ...baseOptions.scales,
      y: {
        ...baseOptions.scales.y,
        ticks: {
          ...baseOptions.scales.y.ticks,
          font: {
            size: 10, // sleepData의 라벨 폰트 크기 줄임
          },
          stepSize: 5, // y축 간격 조정
          min: 0, // y축 최소값
          max: 100, // y축 최대값
        },
      },
    },
  };

  return (
    <div className="sleep-status-content">
      <p className="sleep-status-title">오늘의 수면 현황</p>
      <div className="sleep-status-chart">
        <div className="sleep-status-item" style={{ height: 150 }}>
          <Bar data={sleepData} options={sleepOptions} />
        </div>
        <div className="sleep-status-item" style={{ height: 100 }}>
          <Bar data={weatherData} options={baseOptions} />
        </div>
      </div>
    </div>
  );
};

export default SleepStatusContent;
