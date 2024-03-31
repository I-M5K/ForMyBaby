import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './App';
import './index.css';
import * as serviceWorkerRegistration from './serviceWorkerRegistration';
import reportWebVitals from './reportWebVitals';
import { RecoilRoot } from 'recoil';

// if ("serviceWorker" in navigator) {
//   window.addEventListener("load", () => {
//     navigator.serviceWorker
//       .register("/firebase-messaging-sw.js")
//       .then((registration) => {
//         console.log("서비스 워커가 등록되었습니다.", registration);
//       })
//       .catch((error) => {
//         console.error("서비스 워커 등록에 실패했습니다.", error);
//       });
//   });
// } else {
//   console.log("서비스 워커를 지원하지 않는 브라우저입니다.");
// }

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <RecoilRoot>
    <App />
  </RecoilRoot>
);

serviceWorkerRegistration.register();
reportWebVitals();
