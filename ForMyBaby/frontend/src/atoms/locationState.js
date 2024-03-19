// atoms/locationState.js
import { atom } from 'recoil';

export const latitudeState = atom({
  key: 'latitudeState',
  default: null, // 초기값은 null
});

export const longitudeState = atom({
  key: 'longitudeState',
  default: null, // 초기값은 null
});

