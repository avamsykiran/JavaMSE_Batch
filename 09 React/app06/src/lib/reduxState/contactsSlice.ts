import { createEntityAdapter, createSlice, type PayloadAction } from "@reduxjs/toolkit";
import type { Contact } from "../models/Contact";

export const contactsEntityAdapter = createEntityAdapter<Contact>({
    selectId: cx => cx.contactId
});

const contactsSlice = createSlice({
    name: "contactsSlice",
    initialState: contactsEntityAdapter.getInitialState({
        nextId:5
    },[
        { contactId: 1, fullName: "Vamsy", mobileNumber: "9999999991", mailId: "v@g.com", dateOfBirth: "1985-06-11" },
        { contactId: 2, fullName: "Murthy", mobileNumber: "9999999992", mailId: "m@g.com", dateOfBirth: "1985-06-12" },
        { contactId: 3, fullName: "Suresh", mobileNumber: "9999999993", mailId: "s@g.com", dateOfBirth: "1985-06-13" },
        { contactId: 4, fullName: "Ramesh", mobileNumber: "9999999994", mailId: "r@g.com", dateOfBirth: "1985-06-14" },
    ]),
    reducers:{
        addContact: contactsEntityAdapter.addOne,
        updateContact: contactsEntityAdapter.updateOne,
        deleteContact: contactsEntityAdapter.removeOne,
        incrementNextId: (state,_action:PayloadAction<void>) => {
            state.nextId+=1;
        }
    }
});

export const contactsReducer = contactsSlice.reducer;

export const { addContact,updateContact,deleteContact,incrementNextId } = contactsSlice.actions;
