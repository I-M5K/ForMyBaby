import create from 'zustand';
import { persist, createJSONStorage } from 'zustand/middleware';

export const useUserStore = create(persist((set) => ({
    id: 0,
    email: null,
    profileImg: null,
    name: null,
    jwt: null,
    fcm: null,
    family: null,
    babyList: [],
    setId: (id) => set({ id: id}),
    setName: (name) => set({ name: name}),  
    setEmail: (email) => set({ email: email}),
    setProfileImg: (profileImage) => set({ profileImg: profileImage }),
    setJwt: (jwt) => set({ jwt: jwt }),
    setFcm: (fcm) => set({ fcm: fcm }),
    setFamily: (family) => set({ family: family}),
    setBabyList: (list) => set({ babyList: list }),
}), {
    name: "user",
    storage: createJSONStorage(() => localStorage)
}));

export const useLocationStore = create(
    persist(
      (set) => ({
        isExist: false,
        setIsExist : (isExist) => set({isExist: isExist}),
      }),
      {
        name: "location",
        storage: createJSONStorage(() => sessionStorage)
      }
    )
  )
