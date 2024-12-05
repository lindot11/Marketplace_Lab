package ch.zhaw.securitylab.marketplace.rest.security;

import java.util.List;
import java.util.Set;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.security.enterprise.AuthenticationException;
import jakarta.security.enterprise.AuthenticationStatus;
import jakarta.security.enterprise.CallerPrincipal;
import jakarta.security.enterprise.authentication.mechanism.http.HttpAuthenticationMechanism;
import jakarta.security.enterprise.authentication.mechanism.http.HttpMessageContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import static jakarta.ws.rs.core.HttpHeaders.AUTHORIZATION;
import ch.zhaw.securitylab.marketplace.common.facade.UserRoleFacade;
import ch.zhaw.securitylab.marketplace.common.model.UserRole;

@ApplicationScoped
public class JWTAuthenticationMechanism implements HttpAuthenticationMechanism {

    private static final String PROTECTED_PREFIX = "/admin";
    private static final String BEARER = "Bearer ";

    @Inject
    UserRoleFacade userRoleFacade;

    @Override
    public AuthenticationStatus validateRequest(HttpServletRequest request,
            HttpServletResponse response, HttpMessageContext context)
            throws AuthenticationException {
        if (request.getPathInfo().startsWith(PROTECTED_PREFIX) &&
            !request.getMethod().equals("OPTIONS")) {
            String authorizationHeader = request.getHeader(AUTHORIZATION);
            if (authorizationHeader != null && authorizationHeader.startsWith(BEARER)) {
                String jwt = authorizationHeader.substring(BEARER.length());
                String username = JWT.validateJWTandGetUsername(jwt);
                if (username != null) {
                    List<UserRole> userRoles = userRoleFacade.findByUsername(username);
                    Set<String> roles = UserRole.getRolesSet(userRoles);
                    return context.notifyContainerAboutLogin(
                            new CallerPrincipal(username), roles);
                }
            }
            return context.responseUnauthorized();
        }
        return context.doNothing();
    }
}
