import React from 'react';
import homeIcon from '../assets/navbar/home.png';
import moniteringIcon from '../assets/navbar/monitering.png';
import stampIcon from '../assets/navbar/stamp.png';
import profileIcon from '../assets/navbar/profile.png';

function Footer() {
  return (
    <footer style={styles.footer}>
      <nav>
        <ul style={styles.navList}>
          <li style={styles.navItem}>
            <a href="/main">
              <img src={homeIcon} alt="Home" style={styles.icon} /><br />
              홈
            </a>
          </li>
          <li style={styles.navItem}>
            <a href="/baby-guard">
              <img src={moniteringIcon} alt="baby-guard" style={styles.icon} /><br />
              모니터링
            </a>
          </li>
          <li style={styles.navItem}>
            <a href="/parenting-stamp">
              <img src={stampIcon} alt="parenting-stamp" style={styles.icon} /><br />
              성장스탬프
            </a>
          </li>
          <li style={styles.navItem}>
            <a href="/my-page">
              <img src={profileIcon} alt="my-page" style={styles.icon} /><br />
              프로필
            </a>
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
  },
  icon: {
    width: '30px', // 이미지 크기 조정
    height: '30px', // 이미지 크기 조정
  },
};

export default Footer;
