import React, { useEffect, useState } from 'react';
import BabyDoughnutChart from '../../components/feature/babyguard/babyDoughnutChart'; 
import BabyWeekChart from '../../components/feature/babyguard/babyWeekChart'; 
import { getWeekData } from '../../api/sleepApi';
import { useRecordStore } from '../../stores/RecordStore';

const BabyGuardChange = ({ danger, hours, awake }) => {
  const { endTime, setEndTime, dangerList, setDangerList, hoursList, setHoursList, awakeList, setAwakeList } = useRecordStore();
  const [time, setTime] = useState();
  const [type, setType] = useState('hours');
  const [weekData, setWeekData] = useState(null);

  useEffect(() => {
    // ************** 유저로부터 날짜와 그래프 종류 입력 받는 로직 필요 *********
    
    // 일주일치 데이터 가져오기
    const fetchWeekData = async () => {
      try {
        const yesterday = new Date();
        if (time == null){ // 디폴트값
          yesterday.setDate(yesterday.getDate() - 1);
          setTime(yesterday.getTime()); // 타임스탬프 반환
          console.log(yesterday.getTime());
          //setEndTime(yesterday.getTime()); // setEndTime 함수를 사용하여 endTime 상태를 설정
        }
        const data = await getWeekData(yesterday.getTime()); // 서버로부터 일주일치 데이터 가져오기
        console.log(data);
        setDangerList(data.dangerList);
        setHoursList(data.hoursList);
        setAwakeList(data.awakeList);
      } catch (error) {
        console.error('Error fetching week data:', error);
      }
    };

    fetchWeekData(); // 데이터 가져오기 함수 호출
  }, []); 

  // type 상태에 따라서 데이터 설정
  let chartData;
  if (type === 'hours') {
    chartData = hoursList;
  } else if (type === 'danger') {
    chartData = dangerList;
  } else if (type === 'awake') {
    chartData = awakeList;
  }

  return (
    <div>
      <BabyDoughnutChart danger={danger} hours={hours} awake={awake}/>
      <BabyWeekChart type={type} time={time} weekData={chartData}/>
    </div>
  );
};

export default BabyGuardChange;
