package ch.zhaw.securitylab.marketplace.web.backing;

import java.io.Serializable;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.security.enterprise.AuthenticationStatus;
import jakarta.security.enterprise.SecurityContext;
import jakarta.security.enterprise.credential.Credential;
import jakarta.security.enterprise.credential.Password;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletResponse;
import static jakarta.security.enterprise.AuthenticationStatus.SEND_CONTINUE;
import static jakarta.security.enterprise.AuthenticationStatus.SEND_FAILURE;
import static jakarta.security.enterprise.authentication.mechanism.http.AuthenticationParameters.withParams;
import ch.zhaw.securitylab.marketplace.common.utility.Message;

@Named
@SessionScoped
public class AuthenticationBacking implements Serializable {

    private static final long serialVersionUID = 1L;
    @Inject
    private SecurityContext securityContext;
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public void login() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Credential credential = new UsernamePasswordCredential(username, new Password(password));
        AuthenticationStatus status = securityContext.authenticate(
            getRequest(facesContext), getResponse(facesContext), withParams().credential(credential));
        if (status.equals(SEND_CONTINUE)) {
            facesContext.responseComplete();
        } else if (status.equals(SEND_FAILURE)) {
            Message.setMessage("Username or password wrong");
        }
    }

    public String logout() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpServletRequest request = getRequest(facesContext);
        try {
            request.logout();
        } catch (ServletException e) {
            // Do nothing
        }
        Message.setMessage("You have been logged off");
        return "/view/public/search";
    }
    
    private static HttpServletResponse getResponse(FacesContext context) {
        return (HttpServletResponse) context.getExternalContext().getResponse();
    }

    private static HttpServletRequest getRequest(FacesContext context) {
        return (HttpServletRequest) context.getExternalContext().getRequest();
    }
}
