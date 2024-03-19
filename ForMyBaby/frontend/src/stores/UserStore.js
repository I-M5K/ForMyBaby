import create from 'zustand';
import { persist, createJSONStorage } from 'zustand/middleware';

export const useUserStore = create(persist((set) => ({
    id: 0,
    email: null,
    profileImg: null,
    name: null,
    jwt: null,
    fcm: null,
    setId: (id) => set({ id: id}),
    setName: (name) => set({ name: name}),  
    setEmail: (email) => set({ email: email}),
    setProfileImg: (profileImage) => set({ profileImg: profileImage }),
    setJwt: (jwt) => set({ jwt: jwt }),
    setFcm: (fcm) => set({ fcm: fcm }),
}), {
    name: "user",
    storage: createJSONStorage(() => localStorage)
}));
