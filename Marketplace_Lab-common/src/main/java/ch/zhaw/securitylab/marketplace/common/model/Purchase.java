package ch.zhaw.securitylab.marketplace.common.model;

import java.io.Serializable;
import java.math.BigDecimal;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import ch.zhaw.securitylab.marketplace.common.validation.CreditCardCheck;

@Entity
@Table(name = "Purchase")
@NamedQuery(name = "Purchase.findById", query = "SELECT p FROM Purchase p WHERE p.purchaseID = :purchaseID")
public class Purchase implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private int purchaseID;
    @Pattern(regexp = "^[a-zA-Z']{2,32}$",
            message = "Please insert a valid first name (between 2 and 32 characters)")
    private String firstname;
    @Pattern(regexp = "^[a-zA-Z']{2,32}$",
            message = "Please insert a valid last name (between 2 and 32 characters)")
    private String lastname;
    @CreditCardCheck
    private String creditCardNumber;
    private BigDecimal totalPrice;

    public int getPurchaseID() {
        return purchaseID;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
