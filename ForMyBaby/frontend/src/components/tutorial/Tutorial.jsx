import React, { useRef, useState } from 'react';
import { Swiper, SwiperSlide } from 'swiper/react';

import 'swiper/css';
import 'swiper/css/pagination';

import './styles.css';

import { Pagination } from 'swiper/modules';

export default function App() {
  return (
    <>
      <Swiper pagination={true} modules={[Pagination]} className="mySwiper">
        <SwiperSlide>수면 패턴 & 온습도 알림</SwiperSlide>
        <SwiperSlide>우리 아이 지킴이 모니터링</SwiperSlide>
        <SwiperSlide>우리 아이의 소중한 순간들</SwiperSlide>
        <SwiperSlide>우리 아이의 순간 포착</SwiperSlide>
      </Swiper>
    </>
  );
}
