import React, { useState, useEffect } from 'react';
import { getTodayData, getWeekData } from '../../api/sleepApi';
import { useUserStore } from '../../stores/UserStore';
import { useRecordStore, useRecordDetailStore } from '../../stores/RecordStore';
const GuardDetail = () => {
    const { babySelected } = useUserStore();
    const { danger, hours, awake } = useRecordStore(); 
    const { endTime, dangerList, hoursList, awakeList } = useRecordDetailStore(); 
    useEffect(() => {
        const fetchData = async () => {
            if (danger === null || awake === null || hours === null) {
                try {
                    // API 호출하여 데이터 가져오기
                    const response = await getTodayData();
                    // 가져온 데이터를 상태로 업데이트
                    useRecordStore.setState({
                        danger: response.dangerCnt,
                        awake: response.sleepCnt,
                        hours: response.sleepTime
                    });
                } catch (error) {
                    console.error('Error fetching data:', error);
                }
            }
            // 어제 날짜 yyyyMMdd 문자열 형식으로
            const today = new Date(); // 현재 날짜
            // console.log(today);
            const yesterday = new Date(today);
            yesterday.setDate(today.getDate() - 1); // 어제 날짜 설정
            const formattedYesterday = yesterday.toISOString(); // 이는 ISO 8601 형식으로 변환됩니다. 예: "2024-03-28T00:00:00.000Z"

            console.log(formattedYesterday);
            const year = yesterday.getFullYear(); // 년도
            let month = yesterday.getMonth() + 1; // 월 (0부터 시작하므로 1을 더함)
            if (month < 10) {
                month = '0' + month; // 월이 한 자리 숫자면 앞에 0을 붙임
            }
            let day = yesterday.getDate(); // 일
            if (day < 10) {
                day = '0' + day; // 일이 한 자리 숫자면 앞에 0을 붙임
            }

            const yesterdayStr = `${year}${month}${day}`;
            console.log(yesterdayStr); // 어제 날짜 문자열 출력
          
            if (endTime == null || endTime != yesterdayStr){
                try {
                    // API 호출하여 데이터 가져오기
                    const response = await getWeekData(formattedYesterday);
                    // 가져온 데이터를 상태로 업데이트
                    useRecordDetailStore.setState({
                        dangerList: response.dangerList,
                        awakeList: response.awakeList,
                        hoursList: response.hoursList
                    });
                } catch (error) {
                    console.error('Error fetching data:', error);
                }
            }
        };
    
        fetchData();
    }, []);
    return (
        <div>
            <h1>분석 상세</h1>
        </div>
    );
}

export default GuardDetail;