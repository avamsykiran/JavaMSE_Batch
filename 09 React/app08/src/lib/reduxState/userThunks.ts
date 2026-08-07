import { createAsyncThunk } from "@reduxjs/toolkit";
import apiClient from "../services/apiClient";
import type { AxiosResponse } from "axios";

const logInEndPoint = "/auth/signin";
const registerEndPoint = "/auth/signup";

export const login = createAsyncThunk<{token:string},{username:string,password:string},{}>(
    "user/login",
    async (user:{username:string,password:string}) => {
        const resp:AxiosResponse<{token:string}> = await apiClient.post(logInEndPoint,user);
        return resp.data;
    }
);

export const register = createAsyncThunk<void,{username:string,password:string},{}>(
    "user/register",
    async (user:{username:string,password:string}) => {
        await apiClient.post(registerEndPoint,user);        
    }
);
