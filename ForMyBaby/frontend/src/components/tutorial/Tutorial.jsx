import React, { useRef, useState } from 'react';
import { Swiper, SwiperSlide } from 'swiper/react';

import 'swiper/css';
import 'swiper/css/pagination';

import './Tutorial.css';

import { Pagination } from 'swiper/modules';

import tutorialImg1 from '../../assets/sleepbaby.png'; 
import tutorialImg2 from '../../assets/moonbaby.png'; 
import tutorialImg3 from '../../assets/smilebaby.png'; 
import tutorialImg4 from '../../assets/presentbaby.png';

export default function App() {
  const slides = [
    {
      image: tutorialImg1,
      title: '우리 아이의 안전한 일상',
      content:  (
        <>
          우리 아이가 있는 공간의 온도, 습도,<br />
          예상치 못한 위험까지 감지하여 안전을 유지합니다
        </>
      )
    },
    {
      image: tutorialImg2,
      title: '우리 아이 수면 패턴 파악',
      content: (
        <>
          우리 아이의 수면 패턴까지 분석하여<br />
          모니터링 할 수 있어요
        </>
      )
    },
    {
      image: tutorialImg3,
      title: '우리 아이의 소중한 순간들',
      content: (
        <>
          우리 아이의 소중한 순간들을<br />
          직접 기록과 함께 간직할 수 있어요
        </>
      )
    },
    {
      image: tutorialImg4,
      title: '우리 아이의 순간 포착',
      content: (
        <>
          우리 아이의 몰랐던 순간까지 포착!<br />
          예상치 못한 선물이 기다릴지도...?
        </>
      )
    },
  ]

  return (
    <>
      <Swiper pagination={true} modules={[Pagination]} className="mySwiper">
        {slides.map((slide, index) => (
          <SwiperSlide key={index} className='tutorial-swiper'>
            <div className="slide-content">
              <img src={slide.image} alt="이미지" className="slide-image" />
              <h2 className="slide-title">{slide.title}</h2>
              <p className="slide-content">{slide.content}</p>
            </div>
          </SwiperSlide>
        ))}
      </Swiper>
    </>
  );
}
