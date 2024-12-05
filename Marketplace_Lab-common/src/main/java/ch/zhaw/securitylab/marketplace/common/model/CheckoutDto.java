package ch.zhaw.securitylab.marketplace.common.model;

import java.util.List;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import ch.zhaw.securitylab.marketplace.common.validation.CreditCardCheck;

public class CheckoutDto {
    
    @NotNull(message = "Element with key 'firstname' is missing")
    @Pattern(regexp = "^[a-zA-Z']{2,32}$",
            message = "Please insert a valid first name (between 2 and 32 characters)")
    private String firstname;
    @NotNull(message = "Element with key 'lastname' is missing")
    @Pattern(regexp = "^[a-zA-Z']{2,32}$",
            message = "Please insert a valid last name (between 2 and 32 characters)")
    private String lastname;
    @NotNull(message = "Element with key 'creditCardNumber' is missing")
    @CreditCardCheck
    private String creditCardNumber;
    @NotNull(message = "Element with key 'productCodes' is missing")
    private List<String> productCodes;

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

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public List<String> getProductCodes() {
        return productCodes;
    }

    public void setProductCodes(List<String> productCodes) {
        this.productCodes = productCodes;
    }
}