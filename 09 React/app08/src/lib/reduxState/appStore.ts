import { configureStore } from "@reduxjs/toolkit";
import { contactsReducer } from "./contactsSlice";
import userReducer from "./userSlice";

export const appStore = configureStore({
    reducer:{
        contacts: contactsReducer,
        auth: userReducer
    }
});

export type RootState = ReturnType<typeof appStore.getState>;
export type AppDispatch = typeof appStore.dispatch;