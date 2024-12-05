package ch.zhaw.securitylab.marketplace.rest.core;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.Set;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.POST;
import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;
import ch.zhaw.securitylab.marketplace.common.model.JWTDto;
import ch.zhaw.securitylab.marketplace.common.model.CredentialDto;
import ch.zhaw.securitylab.marketplace.common.facade.UserInfoFacade;
import ch.zhaw.securitylab.marketplace.common.facade.UserRoleFacade;
import ch.zhaw.securitylab.marketplace.common.model.UserInfo;
import ch.zhaw.securitylab.marketplace.common.model.UserRole;
import ch.zhaw.securitylab.marketplace.rest.security.JWT;

@RequestScoped
@Path("authenticate")
public class AuthenticationRest {

    @Inject
    private UserInfoFacade userInfoFacade;
    @Inject
    private UserRoleFacade userRoleFacade;
    @Inject
    private Pbkdf2PasswordHash pbkdf2PasswordHash;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public JWTDto createJWT(CredentialDto credentialDto) {
        UserInfo userInfo = userInfoFacade.findByUsername(credentialDto.getUsername());
        List<UserRole> userRoles = userRoleFacade.findByUsername(credentialDto.getUsername());
        if (userInfo == null || userRoles == null) {
            throw new InvalidParameterException("Username or password wrong");
        }
        if (pbkdf2PasswordHash.verify(credentialDto.getPassword().toCharArray(), userInfo.getPdbkf2Hash())) {
            String jwt = JWT.createJWT(credentialDto.getUsername());
            Set<String> roles = UserRole.getRolesSet(userRoles);
            JWTDto jwtDto = new JWTDto();
            jwtDto.setJwt(jwt);
            jwtDto.setRoles(roles);
            return jwtDto;
        } else {
            throw new InvalidParameterException("Username or password wrong");
        }
    }
}
