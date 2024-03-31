import React from 'react';
import { Doughnut } from 'react-chartjs-2';
import './babyDoughnutChart.css';

const DoughnutChartComponent = () => {

  const data1 = {
    datasets: [
      {
        label: '# of Votes',
        data: [12, 8], // 첫 번째 차트 데이터
        backgroundColor: [
          'rgba(255, 206, 86, 0.2)',
          'rgba(200, 200, 200, 0.2)',
        ],
        borderColor: [
          'rgba(255, 206, 86, 1)',

        ],
        borderWidth: [2, 0]
      },
    ],
  };

  const data2 = {
    datasets: [
      {
        label: '# of Votes',
        data: [19, 15], // 두 번째 차트 데이터
        backgroundColor: [
          'rgba(255, 206, 86, 0.2)',
          'rgba(200, 200, 200, 0.2)',
        ],
        borderColor: [
          'rgba(255, 206, 86, 1)',

        ],
        borderWidth: [2, 0]
      },
    ],
  };

  const data3 = {
    datasets: [
      {
        label: '# of Votes',
        data: [8, 12], // 세 번째 차트 데이터
        backgroundColor: [
          'rgba(255, 206, 86, 0.2)',
          'rgba(200, 200, 200, 0.2)',
        ],
        borderColor: [
          'rgba(255, 206, 86, 1)',

        ],
        borderWidth: [2, 0]
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
        text: '65%',
        color: '#FF6384',
        fontStyle: 'Arial',
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

  return (
    <div>
        <div className='chart-detail-header'>
            135일된 우리 땡구의<br />
            수면현황입니다
        </div>
        <div className="chart-container">
        <div className="chart-item">
            <div className="chart-title">수면 시간</div>
            <Doughnut data={data1} options={options} />
            <div className="chart-footer">부족</div>
        </div>

        <div className="chart-item">
            <div className="chart-title">깨어남</div>
            <Doughnut data={data2} options={options} />
            <div className="chart-footer">적합</div>
        </div>

        <div className="chart-item">
            <div className="chart-title">위험 행동</div>
            <Doughnut data={data3} options={options} />
            <div className="chart-footer">위험</div>
        </div>
        </div>
    </div>
  );
};

export default DoughnutChartComponent;
