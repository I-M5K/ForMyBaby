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
              <img src={homeIcon} alt="Home" style={styles.icon} />
            </a>
          </li>
          <li style={styles.navItem}>
            <a href="/baby-guard">
              <img src={moniteringIcon} alt="baby-guard" style={styles.icon} />
            </a>
          </li>
          <li style={styles.navItem}>
            <a href="/parenting-stamp">
              <img src={stampIcon} alt="parenting-stamp" style={styles.icon} />
            </a>
          </li>
          <li style={styles.navItem}>
            <a href="/my-page">
              <img src={profileIcon} alt="my-page" style={styles.icon} />
            </a>
          </li>
        </ul>
      </nav>
    </footer>
  );
}

const styles = {
  footer: {
    color: '#fff',
    backgroundcolor: '#fff',
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
  },
  icon: {
    width: '50px', // Adjust size as needed
    height: '50px', // Adjust size as needed
  },
};

export default Footer;
