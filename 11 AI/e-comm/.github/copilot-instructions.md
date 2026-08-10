# GitHub Copilot System Instructions: E-Commerce Application

## 1. Technical Stack & Architecture
- **Backend Framework:** Java 21, Spring Boot 3.x, Spring Security (Stateless JWT), Spring Data JPA.
- **Frontend Framework:** React 18+ (Vite/TypeScript or JS), Redux Toolkit (with `createAsyncThunk` and `entityAdapter`), Axios, React Router v6.
- **Database:** H2 In-Memory (Dev) / PostgreSQL ready. Entity Manager / JPA managing entities.

## 2. Security & Authorization Matrix
- **Stateless JWT**: Header `Authorization: Bearer <TOKEN>`.
- **Roles**: `ROLE_ADMIN` (Shopkeeper), `ROLE_CUSTOMER`.
- **Public Endpoints**: `/api/auth/register`, `/api/auth/login`, `GET /api/products/**`.
- **Admin Protected**: `/api/admin/**`, `/api/reports/**`, `POST/PUT/DELETE /api/products/**`.
- **Customer Protected**: `/api/cart/**`, `/api/orders/**`, `/api/checkout/**`.

## 3. Frontend Architecture Rules
- Use Redux Toolkit `createEntityAdapter` for state normalization (Products, Cart, Orders).
- Use `createAsyncThunk` for API workflows with Axios interceptors managing JWT injection.
- Implement `ProtectedRoute` wrapper checking Redux auth state and user roles.

## 4. Coding Standards
- Spring Controllers must return `ResponseEntity<ApiResponse<T>>`.
- Frontend code must explicitly separate UI components from Redux thunk dispatches.