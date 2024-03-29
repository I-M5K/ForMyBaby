import React, { useRef } from 'react';
import { Swiper, SwiperSlide } from 'swiper/react';
import 'swiper/css';
import NavBar from '../../components/NavBar';
import StampPage1 from '../../components/feature/parentingstamp/StampPage1';
import StampPage2 from '../../components/feature/parentingstamp/StampPage2';
import StampPage3 from '../../components/feature/parentingstamp/StampPage3';
import StampPage4 from '../../components/feature/parentingstamp/StampPage4';
import './ParentingStamp.css';

function ParentingStamp() {
  const swiperRef = useRef(null);

  // 스와이퍼 설정
  const swiperSettings = {
    slidesPerView: 1,
  };

  const goToSlide = (index) => {
    if (swiperRef.current) {
      swiperRef.current.swiper.slideTo(index);
    }
  };


  return (
    <div className="ParentingStamp">
      <div className='parenstamp-title'>성장 스탬프</div>
      <hr />
      <div className='parenstamp-header'>
        <button onClick={() => goToSlide(0)}>0~3개월</button>
        <button onClick={() => goToSlide(1)}>4~6개월</button>
        <button onClick={() => goToSlide(2)}>7~9개월</button>
        <button onClick={() => goToSlide(3)}>10~12개월</button>
      </div>
      <hr />
      <div className='parenstamp-contents'>
        <Swiper {...swiperSettings}>
          <SwiperSlide><StampPage1 /></SwiperSlide>
          <SwiperSlide><StampPage2 /></SwiperSlide>
          <SwiperSlide><StampPage3 /></SwiperSlide>
          <SwiperSlide><StampPage4 /></SwiperSlide>
        </Swiper>
      </div>
      <NavBar />
    </div>
  );
}

export default ParentingStamp;
