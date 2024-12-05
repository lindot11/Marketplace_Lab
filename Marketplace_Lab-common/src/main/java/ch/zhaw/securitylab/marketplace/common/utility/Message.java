package ch.zhaw.securitylab.marketplace.common.utility;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

public class Message {

    public static void setMessage(String message) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        FacesMessage facesMessage = new FacesMessage(message);
        facesContext.addMessage(null, facesMessage);
    }
}
