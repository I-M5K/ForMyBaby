import React from 'react';
import ParentingStamp from '../../../pages/ParentingStamp/ParentingStamp';

const ParentingStampGallery = () => {
  const stamps = [
    { imageUrl: "", description: "설명 1" },
    { imageUrl: "", description: "설명 2" },
    { imageUrl: "", description: "설명 3" },
    { imageUrl: "", description: "설명 4" },
  ];

  return (
    <div>
      {/* {stamps.map((stamp, index) => (
        <ParentingStamp key={index} imageUrl={stamp.imageUrl} description={stamp.description} />
      ))} */}
    </div>
  );
};

export default ParentingStampGallery;
