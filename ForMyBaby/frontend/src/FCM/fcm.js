// "use client";

// import { getApps, initializeApp } from "firebase/app";
// import {
//     Messaging,
//     getMessaging,
//     getToken,
//     isSupported,
// } from "firebase/messaging";

// const firebaseConfig = {
//     apiKey: "AIzaSyDoj1PlYSKrUWQvHOKEfBo5r05CCHVV_zo",
//     authDomain: "ssafy-ai-415702.firebaseapp.com",
//     projectId: "ssafy-ai-415702",
//     storageBucket: "ssafy-ai-415702.appspot.com",
//     messagingSenderId: "153226356709",
//     appId: "1:153226356709:web:07c5dffac2788e0b87710c",
//     measurementId: "G-Y2C0CE431D"
//   };

// // Initialize Firebase
// export const app = !getApps()?.length
//     ? initializeApp(firebaseConfig)
//     : getApps()[0];

// // Initialize Firebase Cloud Messaging and get a reference to the service
// export const getMessagingObj = async () => {
//     const supported = await isSupported();
//     console.log("is supported fcm? >>", supported);
//     if (!supported || typeof window === "undefined") return null;
//     return getMessaging(app);
// };

// export const fetchToken = async () => {
//     try {
//         const messaging = await getMessagingObj();
//         if (messaging) {
//             const token = await getToken(messaging, {
//                 vapidKey: process.env.REACT_APP_VAPI_KEY || "",
//             });
//             return token;
//         }
//         return null;
//     } catch (err) {
//         console.error(err);
//         return null;
//     }
// };
