import React, { useState } from 'react';
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

import './babyWeekChart.css'

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
  const labels = [];
  const yesterday = new Date(time);
  for (let i = 6; i >= 0; i--) {
    const day = new Date(yesterday.getFullYear(), yesterday.getMonth(), yesterday.getDate() - i);
    labels.push(`${day.getMonth() + 1}/${day.getDate()}`);
  }
  return labels;
};

const SleepChart = ({ type, time }) => {
  // 예시 데이터
  const exampleData = {
    sleep: [8, 7, 6, 8, 9, 7, 8],
    wake: [1, 2, 1, 3, 2, 1, 2],
    risk: [0, 0, 1, 0, 0, 0, 1],
  };

  // 날짜 라벨 동적 생성
  const labels = generateDateLabels({ time });

  const startDate = labels[0];
  const endDate = labels[labels.length - 1];

  const [dataType, setDataType] = useState('sleep'); // 기본적으로 수면시간 데이터를 표시

  const getData = () => {
    switch (dataType) {
      case 'wake':
        return exampleData.wake;
      case 'risk':
        return exampleData.risk;
      default:
        return exampleData.sleep;
    }
  };

  const data = {
    labels,
    datasets: [
      {
        label: dataType === 'wake' ? '기상 횟수' : dataType === 'risk' ? '위험 감지 횟수' : '수면 시간',
        data: getData(),
        backgroundColor: dataType === 'risk' ? '#FF5722' : '#FFC107',
        borderColor: dataType === 'risk' ? '#FF5722' : '#FFC107',
        tension: 0.1,
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
        max: dataType === 'risk' ? 1 : 12, // 위험 감지 횟수의 경우 최대값 1로 설정
      },
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
        text: `일주일간 ${dataType === 'wake' ? '기상 횟수' : dataType === 'risk' ? '위험 감지 횟수' : '수면 시간'} (${startDate} - ${endDate})`,
        position: 'top',
      },
    },
  };

  return (
    <div style={{ width: '380px', height: '300px', marginTop: '20px' }}> {/* 차트의 크기를 조절합니다. */}
      <div className='week-chart-btns'>
        <div onClick={() => setDataType('sleep')}>수면 시간</div>
        <div onClick={() => setDataType('wake')}>기상 횟수</div>
        <div onClick={() => setDataType('risk')}>위험 감지 횟수</div>
      </div>
      <Line data={data} options={options} />
    </div>
  );
};

export default SleepChart;
