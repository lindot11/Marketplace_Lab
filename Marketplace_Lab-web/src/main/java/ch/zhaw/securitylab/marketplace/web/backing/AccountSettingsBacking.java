package ch.zhaw.securitylab.marketplace.web.backing;

import ch.zhaw.securitylab.marketplace.common.service.AccountSettingsService;

import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.faces.context.FacesContext;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.validation.constraints.Size;
import ch.zhaw.securitylab.marketplace.common.utility.Message;

@Named
@RequestScoped
public class AccountSettingsBacking {

    @Inject
    private AccountSettingsService accountSettingsService;
    @Size(min=4, message = "Please insert a new password with at least 4 characters")
    private String newPassword;

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String changePassword() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        if (accountSettingsService.changePassword(request.getRemoteUser(), newPassword)) {
            Message.setMessage("Your password was successfully changed");
            return "/view/admin/admin";
        } else {
            Message.setMessage("The password could not be changed");
            return "/view/admin/account/accountsettings";
        }
    }
}
