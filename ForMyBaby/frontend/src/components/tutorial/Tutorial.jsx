import React, { useRef, useState } from 'react';
import { Swiper, SwiperSlide } from 'swiper/react';

import 'swiper/css';
import 'swiper/css/pagination';

import './Tutorial.css';

import { Pagination } from 'swiper/modules';

import tutorialImg1 from '../../assets/tutorial/baby-cctv.png'; 
import tutorialImg2 from '../../assets/tutorial/sleep-temp.png'; 
import tutorialImg3 from '../../assets/tutorial/health-tip.png'; 
import tutorialImg4 from '../../assets/tutorial/paren-stamp.png';

export default function App() {
  const slides = [
    {
      image: tutorialImg1,
      title: '우리 아이 지킴이 모니터링',
      content:  (
        <>
          포마베는 우리 아이 지킴이!<br />
          가족 누구나 실시간으로 아이를 모니터링하며<br />
          예기치 못한 위험 행동을 감지해드려요
        </>
      )
    },
    {
      image: tutorialImg2,
      title: '수면 패턴 & 온습도 알림',
      content: (
        <>
          아이의 수면 상태와 패턴을 쉽게 확인 가능해요!<br />
          또한, 현재 온습도를 체크하여<br />
          최적의 환경을 조성할 수 있도록 도와드려요
        </>
      )
    },
    {
      image: tutorialImg3,
      title: '건강 검진 타임라인 & 육아 팁',
      content: (
        <>
          아이의 검진 일정, 접종 날짜를 손쉽게 확인!<br />
          중요한 정보를 놓치지 않게 도와드려요.<br />
          육아 사전을 통해 육아 꿀팁도 얻을 수 있답니다~!
        </>
      )
    },
    {
      image: tutorialImg4,
      title: '성장 스탬프',
      content: (
        <>
          우리 아이의 몰랐던 순간까지 포착!<br />
          아이의 성장 과정을 기록하며<br /> 
          육아의 기쁨을 놓치지 않고 간직해보세요
        </>
      )
    },
  ]

  return (
    <>
      <Swiper pagination={true} modules={[Pagination]} className="mySwiper">
        {slides.map((slide, index) => (
          <SwiperSlide key={index} className='tutorial-swiper'>
            <div className="tutorial-slide-content">
              <img src={slide.image} alt="이미지" className="tutorial-slide-image" />
              <h2 className="tutorial-slide-title">{slide.title}</h2>
              <p className="tutorial-slide-content">{slide.content}</p>
            </div>
          </SwiperSlide>
        ))}
      </Swiper>
    </>
  );
}
