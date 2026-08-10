---
description: "Generate React Redux Toolkit Frontend with Axios, Thunks, EntityAdapters, and Protected Routes"
---
Refer to #file:docs/api-contracts.md and #file:.github/copilot-instructions.md.

Task: Generate the complete ReactJS frontend architecture.

Requirements:
1. Configure an Axios instance with request interceptors to automatically attach `Authorization: Bearer <token>` from Redux state.
2. Create Redux Slices using `createSlice`, `createAsyncThunk`, and `createEntityAdapter`:
   - `authSlice`: Handles login, registration, token persistence in localStorage.
   - `productSlice`: Managed via `createEntityAdapter` for normalized inventory caching.
   - `cartSlice`: Handles adding/removing cart items and tracking total price.
   - `orderSlice` & `reportSlice`: Handles customer order history and Admin sales reports.
3. Build React UI Components:
   - `Navbar`: Displays cart counter, auth status, and Admin dashboard links.
   - `Login` & `Register` pages.
   - `ProductCatalog` (Customer view) & `InventoryDashboard` (Admin view with CRUD modals).
   - `ShoppingCart` & `CheckoutModal` (Captures mock credit card info and displays Invoice on success).
   - `AdminReportsPage`: Renders sales summaries and order tables.
4. Implement `ProtectedRoute` component to restrict access based on user roles (`ROLE_ADMIN` vs `ROLE_CUSTOMER`).