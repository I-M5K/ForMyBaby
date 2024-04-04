import React from 'react';
import Logo from '../../assets/logopage.png';

const LoadingPage = () => {
    return (
        <div style={{ display: 'flex', justifyContent: 'center', alignItems: 'center', height: '100vh' }}>
            <img src={Logo} alt="Logo" style={{ maxWidth: '100%', maxHeight: '100%' }} />
        </div>
    );
}

export default LoadingPage;
