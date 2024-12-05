package ch.zhaw.securitylab.marketplace.common.service;

import java.io.Serializable;
import jakarta.ejb.Singleton;

@Singleton
public class LoginThrottlingService implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final int BLOCKING_TIME = 60;
    private static final int BLOCKING_LIMIT = 3;
    
   /**
     * Is called to inform that the login with username has failed.
     * 
     * @param username The username for which the login failed
     */
    public void loginFailed(String username) {
        
        // Implement
    }

    /**
     * Is called to inform that the login with username has succeeded.
     * 
     * @param username The username for which the login succeeded
     */
    public void loginSuccessful(String username) {
        
        // Implement
    }

    /**
     * Returns whether the user username is blocked.
     * 
     * @param username The username to check
     * @return true if the user is blocked, false otherwise
     */
    public boolean isBlocked(String username) {
        
        // Implement
        
        return false;
    }
}
