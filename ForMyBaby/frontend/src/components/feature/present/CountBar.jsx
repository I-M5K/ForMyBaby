import React, { useEffect, useState } from 'react';

import './CountBar.css'

const GaugeBar = ({ value, maxValue }) => {
    const [barWidth, setBarWidth] = useState(0);
  
    // Calculate the width of the gauge bar
    const calculateWidth = () => {
      const width = (value / maxValue) * 100;
      return width > 100 ? 100 : width; // Cap width at 100%
    };
  
    // Update the width when the component mounts or when the value changes
    React.useEffect(() => {
      setBarWidth(calculateWidth());
    }, [value]);
  
    return (
      <div className="gauge-bar-container">
        <div
          className="gauge-bar"
          style={{ width: `${barWidth}%`, backgroundColor:'#F7C515' }}
        >
          {`${value}%`}
        </div>
      </div>
    );
  };

export default GaugeBar;
