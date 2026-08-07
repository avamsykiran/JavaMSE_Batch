import { createAsyncThunk } from "@reduxjs/toolkit";
import type { Contact } from "../models/Contact";
import apiClient from "../services/apiClient";
import type { AxiosResponse } from "axios";

const contactsEndPoint = "/contacts";

export const getAllContacts = createAsyncThunk<Contact[],void,{}>(
    "contacts/getAllContacts",
    async () => {
        const resp:AxiosResponse<Contact[]> = await apiClient.get(contactsEndPoint);
        return resp.data;
    }
);

export const addContact = createAsyncThunk<Contact,Contact,{}>(
    "contacts/addContact",
    async (c:Contact) => {
        const resp:AxiosResponse<Contact> = await apiClient.post(contactsEndPoint,c);
        return resp.data;
    }
);

export const updateContact= createAsyncThunk<Contact,Contact,{}>(
    "contacts/updateContact",
    async (c:Contact) => {
        const resp:AxiosResponse<Contact> = await apiClient.put(contactsEndPoint,c);
        return resp.data;
    }
);

export const deleteContact = createAsyncThunk<number,number,{}>(
    "contacts/deleteContact",
    async (cid:number) => {
        await apiClient.delete(contactsEndPoint +"/"+cid);
        return cid;
    }
);