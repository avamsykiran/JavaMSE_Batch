import axios from "axios";
import { appStore } from "../reduxState/appStore";
import { logout } from "../reduxState/userSlice";

const apiClient = axios.create({
    baseURL: "/api"
})

// Request Interceptor: Attach JWT to every request
apiClient.interceptors.request.use((config) => {
  const token = appStore.getState().auth.token;
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
      appStore.dispatch(logout());
    }
    return Promise.reject(error);
  }
);

export default apiClient;