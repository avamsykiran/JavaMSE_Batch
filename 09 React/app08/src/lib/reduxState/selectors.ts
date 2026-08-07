import type { RootState } from "./appStore";
import { contactsEntityAdapter } from "./contactsSlice";
import { userAdapter } from "./userSlice";

export const { selectAll: selectAllContacts, selectById: selectContactById, selectTotal: selectContactsCount } =
    contactsEntityAdapter.getSelectors((state: RootState) => state.contacts);

const { selectAll: selectAllUsers } =
    userAdapter.getSelectors((state: RootState) => state.auth);

export const selectCurrentUser = (state:RootState) => {
    const users = selectAllUsers(state);
    return users.length>0? users[0] : null;
}

export const selectIsAuthenticated = (state:RootState) => state.auth.isAuthenticated;
export const selectAuthIsLoading = (state:RootState) => state.auth.isLoading;
export const selectAuthErrMsg = (state:RootState) => state.auth.errMsg;