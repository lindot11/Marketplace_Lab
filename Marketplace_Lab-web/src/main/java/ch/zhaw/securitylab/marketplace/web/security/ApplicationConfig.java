package ch.zhaw.securitylab.marketplace.web.security;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.security.enterprise.authentication.mechanism.http.CustomFormAuthenticationMechanismDefinition;
import jakarta.security.enterprise.authentication.mechanism.http.LoginToContinue;
import jakarta.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;
import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;

@DatabaseIdentityStoreDefinition(
    dataSourceLookup = "java:global/marketplace",
    callerQuery = "SELECT PBKDF2Hash FROM UserInfo WHERE Username = ?",
    groupsQuery = "SELECT Rolename FROM UserRole WHERE Username = ?",
    hashAlgorithm = Pbkdf2PasswordHash.class
)

@CustomFormAuthenticationMechanismDefinition(
    loginToContinue = @LoginToContinue(
        loginPage="/faces/view/public/login.xhtml",
        errorPage=""
    )
)

@ApplicationScoped
public class ApplicationConfig {
}
