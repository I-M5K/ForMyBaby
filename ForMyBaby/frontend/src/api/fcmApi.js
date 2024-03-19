import axiosWrapper from './axiosWrapper';

export const sendDeviceToken = (token) => {
    axiosWrapper({
      method:'patch',
      url:`oauth/fcm`,
      data:{
        'fcmToken' : `${token}`
      }
    })
    .then((res)=>{
      console.log('토큰 전송 완료')
    })
    .catch((err) => console.log(err))
  }
