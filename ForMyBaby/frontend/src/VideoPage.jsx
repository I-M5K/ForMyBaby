// import React, { useState, useEffect } from 'react';
// import socketIOClient from 'socket.io-client';

// const ENDPOINT = 'http://localhost:3001'; // 서버의 엔드포인트에 맞게 수정

// function ClientComponent() {
//   const [imageData, setImageData] = useState(null);

//   useEffect(() => {
//     const socket = socketIOClient(ENDPOINT, {
//       transports: ['websocket'], // WebSocket 프로토콜 강제 사용
//     });

//     socket.on('image', (data) => {
//       // ArrayBuffer를 Base64로 변환
//       const base64String = btoa(
//         new Uint8Array(data).reduce((data, byte) => data + String.fromCharCode(byte), '')
//       );
//       // 이미지 데이터를 Base64로 인코딩하여 상태 업데이트
//       setImageData(`data:image/jpeg;base64,${base64String}`);
//     });

//     return () => {
//       socket.disconnect(); // 컴포넌트가 언마운트될 때 소켓 연결 종료
//     };
//   }, []); // 페이지가 처음 로드될 때 한 번만 실행

//   return (
//     <div>
//       <h1>Received Image</h1>
//       {imageData && <img src={imageData} alt="Received" />}
//     </div>
//   );
// }

// export default ClientComponent;
