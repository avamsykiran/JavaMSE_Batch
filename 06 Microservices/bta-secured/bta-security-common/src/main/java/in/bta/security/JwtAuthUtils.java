package in.bta.security;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.List;

public final class JwtAuthUtils {

    private JwtAuthUtils() {}

    public static boolean hasRole(Jwt jwt, String role) {
        List<String> roles = jwt.getClaimAsStringList("roles");
        return roles != null && roles.contains(role);
    }

    public static boolean isOwnId(Jwt jwt, Long ahId) {
        String principalAhId = jwt.getClaimAsString("ahId");
        return principalAhId != null && principalAhId.equals(String.valueOf(ahId));
    }

    public static void requireAdmin(Jwt jwt) {
        if (!hasRole(jwt, "ADMINS")) {
            throw new AccessDeniedException("Admin access required");
        }
    }

    public static void requireAdminOrOwn(Jwt jwt, Long ahId) {
        if (!hasRole(jwt, "ADMINS") && !isOwnId(jwt, ahId)) {
            throw new AccessDeniedException("Access denied");
        }
    }
}
