import axios from "axios";
import { logout } from "../reduxState/userSlice";
import type { AppDispatch, RootState } from "../reduxState/appStore";

type AppStore = {
  getState: () => RootState;
  dispatch: AppDispatch;
};

let appStore: AppStore | undefined;

export const setAppStore = (store: AppStore) => {
  appStore = store;
};

const apiClient = axios.create({
    baseURL: "/api"
})

// Request Interceptor: Attach JWT to every request
apiClient.interceptors.request.use((config) => {
  const token = appStore?.getState().auth.token;
  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }
  return config;
});

// Response Interceptor: Handle expired/invalid JWTs
apiClient.interceptors.response.use(
  (response) => response,
  (error) => {
    if (error.response && error.response.status === 401) {
      // JWT expired or invalid according to Spring Boot Security
      appStore?.dispatch(logout());
    }
    return Promise.reject(error);
  }
);

export default apiClient;