import { jwtDecode } from "jwt-decode";
import type { User } from "../models/User";
import { createEntityAdapter, createSlice, isPending, type PayloadAction } from "@reduxjs/toolkit";
import { login, register } from "./userThunks";

interface JwtPayLoad {
    sub: string;
    roles: string[];
}

const getJwtPayload = (token: string): User | null => {
    var user: User | null = null;
    try {
        const payLoad = jwtDecode<JwtPayLoad>(token);

        user = {
            id: payLoad.sub,
            username: payLoad.sub,
            roles: payLoad.roles
        };
    } catch (err) {
        console.error(err);
    }

    return user;
}

export const userAdapter = createEntityAdapter<User>();

// Check for existing token in localStorage on app start
const initialToken = localStorage.getItem('jwt_token');
const initialUser = initialToken ? getJwtPayload(initialToken) : null;

interface UserSliceExtraData {
    token: string | null;
    isAuthenticated: boolean;
    isLoading: boolean;
    errMsg: string | null;
}

// Initialize adapter state
const initialState = userAdapter.getInitialState<UserSliceExtraData>(
    {
        token: initialUser ? initialToken : null,
        isAuthenticated: !!initialUser,
        isLoading: false,
        errMsg: null
    },
    initialUser ? [initialUser] : [] // Seed entity adapter if valid token exists!
);

export const userSlice = createSlice({
    name: 'user',
    initialState,
    reducers: {
        logout: (state) => {
            localStorage.removeItem('jwt_token');
            state.token = null;
            state.isAuthenticated = false;
            userAdapter.removeAll(state);
        }
    },
    extraReducers: (builder) => {
        builder
            .addCase(login.fulfilled, (state, action: PayloadAction<{ token: string }>) => {
                const { token } = action.payload;
                state.isLoading = false;
                let user = getJwtPayload(token);
                if (user) {
                    state.token = action.payload.token;
                    userAdapter.setOne(state, user);
                    state.isAuthenticated = true;
                    state.errMsg = null;
                    localStorage.setItem('jwt_token', token);
                }
            })
            .addCase(register.fulfilled, (state, _action: PayloadAction<void>) => {
                state.isLoading = false;
            })
            .addCase(login.rejected, (state, _action) => {
                state.isLoading = false;
                state.token = null;
                state.isAuthenticated = false;
                state.errMsg = "Authentication Failed";
                localStorage.removeItem('jwt_token');
            })
            .addCase(register.rejected, (state, _action) => {
                state.isLoading = false;
                state.errMsg = "Registration Failed";
            })
            .addMatcher(
                isPending(login, register),
                (state, _action) => {
                    state.isLoading = true;
                }
            )
    },
});

export const {logout} = userSlice.actions;

export const userReducer = userSlice.reducer;

  