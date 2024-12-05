package ch.zhaw.securitylab.marketplace.common.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import jakarta.persistence.*;

@Entity
@Table(name = "UserRole")
@NamedQuery(name = "UserRole.findByUsername", query = "SELECT u FROM UserRole u WHERE u.username = :username")
public class UserRole implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private String username;
    @Id
    private String rolename;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }
    
    public static Set<String> getRolesSet(List<UserRole> userRoles) {
        Set<String> roles = new HashSet();
        for (UserRole userRole : userRoles) {
            roles.add(userRole.getRolename());
        }
        return roles;
    }
}
