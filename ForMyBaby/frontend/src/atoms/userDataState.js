// atoms/userDataState.js
import { atom } from 'recoil';
import { recoilPersist } from 'recoil-persist';

// const { persistUser } = recoilPersist({
//   key: 'userData',
//   storage: localStorage,
// })
export const userDataState = atom({
  key: 'userDataState',
  default: {}, // 초기값은 빈 객체
  //effects_UNSTABLE: [persistUser],
});
