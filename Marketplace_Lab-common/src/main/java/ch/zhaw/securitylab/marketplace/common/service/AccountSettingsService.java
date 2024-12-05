package ch.zhaw.securitylab.marketplace.common.service;

import java.util.HashMap;
import java.util.Map;
import jakarta.inject.Inject;
import jakarta.enterprise.context.RequestScoped;
import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;
import ch.zhaw.securitylab.marketplace.common.facade.UserInfoFacade;
import ch.zhaw.securitylab.marketplace.common.model.UserInfo;

@RequestScoped
public class AccountSettingsService {

    @Inject
    private UserInfoFacade userFacade;
    @Inject
    private Pbkdf2PasswordHash pbkdf2PasswordHash;

    /**
     * Changes the password of a user.
     * 
     * @param username The username for which to change the password
     * @param newPassword The new password
     * @return whether password change was successful (true) or not (false)
     */
    public boolean changePassword(String username, String newPassword) {
        
        // Get the UserInfo entity of user username from the database
        UserInfo userInfo = userFacade.findByUsername(username);
        if (userInfo == null) {
            return false;
        }
        
        // Initialize pbkdf2PasswordHash with the desired settings
        Map<String, String> parameters = new HashMap<>();
        parameters.put("Pbkdf2PasswordHash.Iterations", "100000");
        parameters.put("Pbkdf2PasswordHash.Algorithm", "PBKDF2WithHmacSHA512");
        parameters.put("Pbkdf2PasswordHash.SaltSizeBytes", "64");
        parameters.put("Pbkdf2PasswordHash.KeySizeBytes", "32");
        pbkdf2PasswordHash.initialize(parameters);
        
        // Implement
        
        // Update the modified UserInfo entity in the database
        userFacade.edit(userInfo);
        return true;
    }
}
