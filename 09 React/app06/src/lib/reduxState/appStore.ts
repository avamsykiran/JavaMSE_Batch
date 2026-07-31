import { configureStore } from "@reduxjs/toolkit";
import { contactsReducer } from "./contactsSlice";

export const appStore = configureStore({
    reducer:{
        contacts: contactsReducer,
        //emps: empsReducer,
        //depts: deptsReducer,
    }
});

export type RootState = ReturnType<typeof appStore.getState>;
export type AppDispatch = typeof appStore.dispatch;