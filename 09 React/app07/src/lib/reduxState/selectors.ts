import type { RootState } from "./appStore";
import { contactsEntityAdapter } from "./contactsSlice";

export const { selectAll: selectAllContacts, selectById:selectContactById,selectTotal:selectContactsCount } =
    contactsEntityAdapter.getSelectors((state: RootState) => state.contacts);

