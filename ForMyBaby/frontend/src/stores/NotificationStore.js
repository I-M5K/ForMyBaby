import create from 'zustand';
import { persist, createJSONStorage } from 'zustand/middleware';
import { getNotificationList, deleteNotification, deleteNotificationAll } from '../api/notificationApi';

export const useNotificationStore = create(persist(
  (set) => ({
    notifications: [],
    fetchNotificationList: async () => {
      try {
        const notificationList = await getNotificationList();
        set({ notifications: notificationList });
      } catch (error) {
        console.error('알림 목록을 가져오는 중 에러 발생:', error);
      }
    },
    deleteNotificationById: async (notificationId) => {
      await deleteNotification(notificationId);
      const updatedNotificationList = await getNotificationList();
      set({ notifications: updatedNotificationList });
    },
    deleteAllNotifications: async () => {
      await deleteNotificationAll();
      set({ notifications: [] });
    }
  }),
  {
    name: "notification", // 스토어 이름 설정
    storage: createJSONStorage(() => localStorage)
  }
));