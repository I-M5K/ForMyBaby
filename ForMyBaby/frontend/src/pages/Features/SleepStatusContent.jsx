import React from 'react';
import { Bar } from 'react-chartjs-2';
import { Chart, registerables, CategoryScale } from 'chart.js';

Chart.register(CategoryScale);
Chart.register(...registerables);

const SleepStatusContent = () => {
    // 임의의 값을 사용하여 그래프 생성
    const temperature = 25;
    const humidity = 70;
    const danger = 3;
    const hours = 7;
    const awake = 2;

    const data = {
      labels: ['Temperature', 'Humidity', 'Danger', 'Hours', 'Awake'],
      datasets: [
        {
          label: 'Value',
          backgroundColor: ['#5cb85c', '#5bc0de', '#f0ad4e', '#5bc0de', '#d9534f'],
          data: [temperature, humidity, danger, hours, awake],
          barThickness: 30 // 막대 두께 조절
        }
      ]
    };
  
    const options = {
        indexAxis: 'y',
        responsive: true,
        plugins: {
          legend: {
            display: false
          },
          tooltip: {
            enabled: false
          },
          annotation: {
            barPercentage: 0.5 // 막대 간격을 조절하는 비율
          }
        },
        scales: {
          x: {
            display: false
          },
          y: {
            display: true
          }
        }
      };
  
    return (
        <div className="sleep-status-content">
        <p className='sleep-title'>오늘의 수면 현황</p>
        <div className="sleep-status">
          <div className="sleep-status-item">
            <Bar data={data} options={options} />
          </div>
        </div>
      </div>
    );
};

export default SleepStatusContent;
