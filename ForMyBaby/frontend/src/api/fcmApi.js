import axiosWrapper from './axiosWrapper';

export const sendDeviceToken = (token) => {
    axiosWrapper({
      method:'patch',
      url:`/v1/users/fcm`,
      data:{
        'fcmToken' : `${token}`
      }
    })
    .then((res)=>{
      console.log('토큰 전송 완료')
    })
    .catch((err) => console.log(err))
  }
