package ch.zhaw.securitylab.marketplace.common.model;

import jakarta.validation.constraints.NotNull;

public class CredentialDto {

    @NotNull(message = "Element with key 'username' is missing")
    private String username;
    @NotNull(message = "Element with key 'password' is missing")
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
}