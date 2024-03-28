import create from 'zustand';
import { persist, createJSONStorage } from 'zustand/middleware';

export const useRecordStore = create(persist((set) => ({
    danger: null,
    hours: null,
    awake: null,
    setDanger: (danger) => set({ danger: danger }),
    setHours: (hours) => set({ hours: hours }),
    setAwake: (awake) => set({ awake: awake}),
}), {
    name: "record",
    storage: createJSONStorage(() => localStorage)
}));

export const useRecordDetailStore = create(persist((set) => ({
    endTime: null,
    dangerList: [],
    hoursList: [],
    awakeList: [],
    setEndTime: (endTime) => set({ endTimeList: endTime }),
    setDangerList: (danger) => set({ dangerList: danger }),
    setHoursList: (hours) => set({ hoursList: hours }),
    setAwakeList: (awake) => set({ awakeList: awake}),
}), {
    name: "detail",
    storage: createJSONStorage(() => localStorage)
}));

// 위험행동 개수 추가
export const addDanger = (count) => {
    useRecordStore.setState((state) => ({
        danger: state.danger + count
    }));
};

// 깨어남 개수 추가
export const addAwake = (count) => {
    useRecordStore.setState((state) => ({
        awake: state.awake + count
    }));
};