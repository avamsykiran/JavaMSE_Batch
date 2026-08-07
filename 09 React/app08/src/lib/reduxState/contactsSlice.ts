import { createEntityAdapter, createSlice, isPending, isRejected} from "@reduxjs/toolkit";
import type { Contact } from "../models/Contact";
import { addContact, deleteContact, getAllContacts, updateContact } from "./contactsThunks";

export const contactsEntityAdapter = createEntityAdapter<Contact>({
    selectId: cx => cx.contactId
});

interface ContactsSliceExtraFields {
    status: 'idle' | 'error' | 'completed' | 'pending';
    errMsg: string | null;
}

const contactsSlice = createSlice({
    name: "contactsSlice",
    initialState: contactsEntityAdapter.getInitialState<ContactsSliceExtraFields>({
        status: "idle",
        errMsg: null
    }, []),
    reducers: {},
    extraReducers: (builder) => {
        builder
            .addCase(getAllContacts.fulfilled, (state, action) => {
                state.status = "completed";
                state.errMsg = null;
                contactsEntityAdapter.setAll(state, action.payload);
            })
            .addCase(addContact.fulfilled, (state, action) => {
                state.status = "completed";
                state.errMsg = null;
                contactsEntityAdapter.addOne(state, action.payload);
            })
            .addCase(updateContact.fulfilled, (state, action) => {
                state.status = "completed";
                state.errMsg = null;
                contactsEntityAdapter.setOne(state, action.payload);
            })
            .addCase(deleteContact.fulfilled, (state, action) => {
                state.status = "completed";
                state.errMsg = null;
                contactsEntityAdapter.removeOne(state, action.payload);
            })
            .addMatcher(
                isPending(getAllContacts, addContact, deleteContact, updateContact),
                (state, _action) => {
                    state.status = "pending";
                    state.errMsg = null;
                }
            )
            .addMatcher(
                isRejected(getAllContacts, addContact, deleteContact, updateContact),
                (state, action) => {
                    state.status = "error";
                    state.errMsg = "Operation Failed! We regret the inconvinience, please retry later!";
                    console.error(action.payload);
                }
            )
    },
});

export const contactsReducer = contactsSlice.reducer;

//export const { addContact, updateContact, deleteContact, incrementNextId } = contactsSlice.actions;
