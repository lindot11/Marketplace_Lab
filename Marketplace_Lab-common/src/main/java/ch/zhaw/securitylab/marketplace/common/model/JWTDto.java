package ch.zhaw.securitylab.marketplace.common.model;

import java.util.Set;

public class JWTDto {

    private String jwt;
    private Set<String> roles;

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
    
    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}
