package ch.zhaw.securitylab.marketplace.common.model;

import java.io.Serializable;
import jakarta.persistence.*;

@Entity
@Table(name = "UserInfo")
@NamedQuery(name = "UserInfo.findByUsername", query = "SELECT u FROM UserInfo u WHERE u.username = :username")
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private String username;
    private String pbkdf2Hash;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPdbkf2Hash() {
        return pbkdf2Hash;
    }

    public void setPdbkf2Hash(String pbkdf2Hash) {
        this.pbkdf2Hash = pbkdf2Hash;
    }
}
