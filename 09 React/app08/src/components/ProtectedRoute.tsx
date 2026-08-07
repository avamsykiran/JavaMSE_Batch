
import { useSelector } from 'react-redux';
import { selectCurrentUser, selectIsAuthenticated } from '../lib/reduxState/selectors';
import { Navigate, Outlet, useLocation } from 'react-router';

export function ProtectedRoute({ requiredRole }: { requiredRole?: string }) {
    const isAuthenticated = useSelector(selectIsAuthenticated);
    const currentUser = useSelector(selectCurrentUser);
    const location = useLocation();

    // 1. AuthN Check: Redirect to login if unauthenticated
    if (!isAuthenticated) {
        return <Navigate to="/login" state={{ from: location }} replace />;
    }

    // 2. AuthZ Check: Check if user has required Spring Boot Role
    if (requiredRole) {
        let hasRole = false;
        if (currentUser && currentUser.roles) {
            hasRole = currentUser.roles.includes(requiredRole);
        }
        if (!hasRole) {
            return <Navigate to="/unauthorized" replace />;
        }
    }

    // 3. Render matched child route
    return <Outlet />;
}