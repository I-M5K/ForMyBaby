import React from "react";
import { Bar } from "react-chartjs-2";
import Chart from "chart.js/auto";
import ChartDataLabels from "chartjs-plugin-datalabels";

import "./SleepStatusContent.css";

Chart.register(ChartDataLabels);

const SleepStatusContent = ({ danger, hours, awake, temp, humid }) => {
  // const temperature = 25;
  // const humidity = 100;
  // const danger = 31;
  // const hours = 8;
  // const awake = 42;

  const data = {
    labels: ["온도", "습도", "위험 감지", "수면 시간", "기상 횟수"],
    datasets: [
      {
        label: "Value",
        backgroundColor: [
          "#FF6384",
          "#36A2EB",
          "#FFCE56",
          "#000000",
          "#9966FF",
        ],
        data: [temp, humid, danger, hours, awake],
        barThickness: 20,
        borderRadius: 10,
      },
    ],
  };

  const options = {
    indexAxis: "y",
    responsive: true,
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
        formatter: (value, context) => {
          return value;
        },
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
            size: 15,
            family: "'Roboto', sans-serif",
            style: "bold",
          },
        },
      },
    },
    layout: {
      padding: {
        left: 0,
        right: 35,
        top: 0,
        bottom: 0,
      },
    },
    animation: {
      duration: 2000,
      easing: "easeInOutQuad",
    },
  };

  return (
    <div className="sleep-status-content">
      <p className="sleep-status-title">오늘의 수면 현황</p>
      <div className="sleep-status-chart">
        <div className="sleep-status-item">
          <Bar data={data} options={options} />
        </div>
      </div>
    </div>
  );
};

export default SleepStatusContent;
