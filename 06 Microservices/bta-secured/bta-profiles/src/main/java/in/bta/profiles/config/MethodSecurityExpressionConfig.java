package in.bta.profiles.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.core.Authentication;

@Configuration
public class MethodSecurityExpressionConfig extends GlobalMethodSecurityConfiguration {

	@Override
	protected MethodSecurityExpressionHandler createExpressionHandler() {
		DefaultMethodSecurityExpressionHandler handler = new DefaultMethodSecurityExpressionHandler();
		handler.setPermissionEvaluator((authentication, targetDomainObject, permission) -> {
			if (authentication instanceof JwtAuthenticationToken && targetDomainObject instanceof Long) {
				Long ahId = (Long) targetDomainObject;
				String principalAhId = ((JwtAuthenticationToken) authentication).getToken().getClaimAsString("ahId");
				return principalAhId != null && principalAhId.equals(String.valueOf(ahId));
			}
			return false;
		});
		return handler;
	}
}
