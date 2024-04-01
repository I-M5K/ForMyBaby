import React from 'react';
import { Line } from 'react-chartjs-2';
import {
  Chart as ChartJS,
  CategoryScale,
  LinearScale,
  PointElement,
  LineElement,
  Title,
  Tooltip,
  Legend,
} from 'chart.js';

ChartJS.register(
  CategoryScale,
  LinearScale,
  PointElement,
  LineElement,
  Title,
  Tooltip,
  Legend
);

// 날짜 라벨 생성 함수
const generateDateLabels = ({ time }) => {
  //console.log(time);
  const labels = [];
  const yesterday = new Date(time);
  for (let i = 6; i >= 0; i--) {
    const day = new Date(yesterday.getFullYear(), yesterday.getMonth(), yesterday.getDate() - i);
    labels.push(`${day.getMonth() + 1}/${day.getDate()}`);
  }
  return labels;
};

const SleepChart = ({ type, time, weekData }) => {
  // 날짜 라벨 동적 생성
  const labels = generateDateLabels({ time } );

  const startDate = labels[0];
  const endDate = labels[labels.length - 1];

  const data = {
    labels,
    datasets: [
      {
        label: '수면 시간 (시간)',
        data: weekData, // 일주일간 수면 시간 예시 데이터
        backgroundColor: '#FFC107',
        borderColor: '#FFC107',
        tension: 0.1
      },
    ],
  };

  const options = {
    indexAxis: 'x',
    responsive: true,
    maintainAspectRatio: false,
    scales: {
      y: {
        beginAtZero: true,
        max: 18
      }
    },
    plugins: {
        legend: {
            display: false, // 범례 숨기기
            },
            tooltip: {
            enabled: false, // 툴팁 숨기기
            },
      title: {
        display: true,
        text: `일주일간 수면 시간 (${startDate} - ${endDate})`,
        position: 'top',
      },
    },
  };

  return (
    <div style={{ width: '400px', height: '300px', marginTop: '20px' }}> {/* 차트의 크기를 조절합니다. */}
      <Line data={data} options={options} />
    </div>
  );
};

export default SleepChart;
