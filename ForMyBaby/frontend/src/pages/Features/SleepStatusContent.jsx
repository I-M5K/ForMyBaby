import React from 'react';
import { Bar } from 'react-chartjs-2';
import Chart from 'chart.js/auto';
import ChartDataLabels from 'chartjs-plugin-datalabels';

import './SleepStatusContent.css';

Chart.register(ChartDataLabels);

const SleepStatusContent = () => {
    // 임의의 값을 사용하여 그래프 생성
    const temperature = 25;
    const humidity = 70;
    const danger = 31;
    const hours = 8;
    const awake = 42;

    const data = {
        labels: ['온도', '습도', '위험 감지', '수면 총 시간', '기상 횟수'],
        datasets: [
            {
                label: 'Value',
                backgroundColor: ['#5cb85c', '#5bc0de', '#f0ad4e', '#5bc0de', '#d9534f'],
                data: [temperature, humidity, danger, hours, awake],
                barThickness: 15, // 막대 두께 조절
                borderRadius: 10, // 막대 모서리 둥글게 만들기
            },
        ],
    };

    const options = {
        indexAxis: 'y',
        responsive: true,
        plugins: {
            legend: {
                display: false,
            },
            tooltip: {
                enabled: false,
            },
            datalabels: {
                color: 'black',
                anchor: 'end', // 막대의 오른쪽 끝에 수치 표시
                align: 'end', // 막대의 오른쪽 끝에 수치 표시
                offset: 4, // 수치의 위치를 조정합니다.
                formatter: (value, context) => {
                    return value; // 수치를 그대로 표시
                },
            },
        },
        scales: {
            x: {
                display: false,
            },
            y: {
                grid: {
                    display: false, // Y 축 그리드 숨기기
                },
                ticks: {
                    font: {
                        size: 10, // Y 축 눈금의 폰트 크기 조절
                    },
                },
            },
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
