import React, { useState } from 'react';
import { NavLink, useLocation } from 'react-router-dom';
import homeIconOrange from '../assets/navbar/orange_home.png';
import homeIconGray from '../assets/navbar/home.png';
import monitoringIconOrange from '../assets/navbar/orange_monitering.png';
import monitoringIconGray from '../assets/navbar/monitering.png';
import stampIconOrange from '../assets/navbar/orange_stamp.png';
import stampIconGray from '../assets/navbar/stamp.png';
import profileIconOrange from '../assets/navbar/orange_profile.png';
import profileIconGray from '../assets/navbar/profile.png';

function Footer() {
  const location = useLocation(); // 현재 경로 가져오기
  const [active, setActive] = useState(getActive(location.pathname)); // 현재 경로에 따라 초기 active 값 설정

  // 클릭 핸들러: 선택된 항목의 상태를 업데이트합니다.
  const handleClick = (name) => {
    setActive(name);
  };

  // 현재 경로에 따라 초기 active 값을 설정하는 함수
  function getActive(pathname) {
    switch (pathname) {
      case "/main":
        return "home";
      case "/baby-guard":
        return "baby-guard";
      case "/parenting-stamp":
        return "parenting-stamp";
      case "/my-page":
        return "my-page";
      default:
        return "home"; // 기본적으로 홈으로 설정
    }
  }

  // 각 아이콘에 대한 이미지 경로를 선택된 항목에 따라 동적으로 결정합니다.
  const getIconPath = (itemName) => {
    if (active === itemName) {
      switch (itemName) {
        case "home":
          return homeIconOrange;
        case "baby-guard":
          return monitoringIconOrange;
        case "parenting-stamp":
          return stampIconOrange;
        case "my-page":
          return profileIconOrange;
        default:
          return "";
      }
    } else {
      switch (itemName) {
        case "home":
          return homeIconGray;
        case "baby-guard":
          return monitoringIconGray;
        case "parenting-stamp":
          return stampIconGray;
        case "my-page":
          return profileIconGray;
        default:
          return "";
      }
    }
  };

  return (
    <footer style={styles.footer}>
      <nav>
        <ul style={styles.navList}>
          <li className={active === "home" ? "animated-button" : ""} style={{...styles.navItem, color: active === "home" ? '#F7C515' : '#000'}} onClick={() => handleClick("home")}>
            <NavLink to="/main" activeClassName="active-link">
              <img src={getIconPath("home")} alt="Home" style={styles.icon} /><br />
              홈
            </NavLink>
          </li>
          <li className={active === "baby-guard" ? "animated-button" : ""} style={{...styles.navItem, color: active === "baby-guard" ? '#F7C515' : '#000'}} onClick={() => handleClick("baby-guard")}>
            <NavLink to="/baby-guard" activeClassName="active-link">
              <img src={getIconPath("baby-guard")} alt="baby-guard" style={styles.icon} /><br />
              모니터링
            </NavLink>
          </li>
          <li className={active === "parenting-stamp" ? "animated-button" : ""} style={{...styles.navItem, color: active === "parenting-stamp" ? '#F7C515' : '#000'}} onClick={() => handleClick("parenting-stamp")}>
            <NavLink to="/parenting-stamp" activeClassName="active-link">
              <img src={getIconPath("parenting-stamp")} alt="parenting-stamp" style={styles.icon} /><br />
              성장스탬프
            </NavLink>
          </li>
          <li className={active === "my-page" ? "animated-button" : ""} style={{...styles.navItem, color: active === "my-page" ? '#F7C515' : '#000'}} onClick={() => handleClick("my-page")}>
            <NavLink to="/my-page" activeClassName="active-link">
              <img src={getIconPath("my-page")} alt="my-page" style={styles.icon} /><br />
              프로필
            </NavLink>
          </li>
        </ul>
      </nav>
    </footer>
  );
}

const styles = {
  footer: {
    backgroundColor: '#fff',
    textAlign: 'center',
    position: 'fixed',
    left: 0,
    bottom: 0,
    width: '100%',
  },
  navList: {
    listStyleType: 'none',
    padding: 0,
    display: 'flex',
    justifyContent: 'space-around',
  },
  navItem: {
    display: 'inline-block',
    margin: '0 10px',
    textAlign: 'center',
    transition: 'color 1s ease', // 색상 변경 애니메이션
  },
  icon: {
    width: '30px',
    height: '30px',
  },
};

export default Footer;

