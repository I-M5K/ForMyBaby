import create from 'zustand';
import { persist, createJSONStorage } from 'zustand/middleware';

export const useRecordStore = create(persist((set) => ({
    danger: 0,
    hours: 0,
    awake: 0,
    setDanger: (danger) => set({ danger: danger }),
    setHours: (hours) => set({ hours: hours }),
    setAwake: (awake) => setAwake({ awake: awake}),
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
    setAwakeList: (awake) => setAwake({ awakeList: awake}),
}), {
    name: "detail",
    storage: createJSONStorage(() => localStorage)
}));

