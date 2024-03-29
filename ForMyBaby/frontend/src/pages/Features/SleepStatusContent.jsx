import React from 'react';
import { Bar } from 'react-chartjs-2';
import Chart from 'chart.js/auto';
import ChartDataLabels from 'chartjs-plugin-datalabels';

import './SleepStatusContent.css';

Chart.register(ChartDataLabels);

const SleepStatusContent = () => {
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
                backgroundColor: ['#FF6384', '#36A2EB', '#FFCE56', '#000000', '#9966FF'],
                data: [temperature, humidity, danger, hours, awake],
                barThickness: 20,
                borderRadius: 10,
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
                anchor: 'end',
                align: 'end',
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
                        style: 'bold',
                    },
                },
            },
        },
        layout: {
            padding: {
                left: 10,
                right: 10,
                top: 20,
                bottom: 20
            }
        },
        animation: {
            duration: 2000,
            easing: 'easeInOutQuad',
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
