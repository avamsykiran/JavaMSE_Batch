import { configureStore } from "@reduxjs/toolkit";
import { contactsReducer } from "./contactsSlice";
import { userReducer } from "./userSlice";
import { setAppStore } from "../services/apiClient";


export const appStore = configureStore({
    reducer:{
        contacts: contactsReducer,
        auth: userReducer
    }
});

setAppStore(appStore);

export type RootState = ReturnType<typeof appStore.getState>;
export type AppDispatch = typeof appStore.dispatch;