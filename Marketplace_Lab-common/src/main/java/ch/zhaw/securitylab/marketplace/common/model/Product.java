package ch.zhaw.securitylab.marketplace.common.model;

import java.io.Serializable;
import java.math.BigDecimal;
import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "Product")
@NamedQueries({
        @NamedQuery(name = "Product.findByDescription", query = "SELECT p FROM Product p WHERE p.description LIKE :description"),
        @NamedQuery(name = "Product.findByCode", query = "SELECT p FROM Product p WHERE p.code = :code"),
        @NamedQuery(name = "Product.findById", query = "SELECT p FROM Product p WHERE p.productID = :productID") })
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private int productID;
    @Pattern(regexp = "^[a-zA-Z0-9]{4}$", message = "Please insert a valid code (4 letters / digits)")
    private String code;
    @Pattern(regexp = "^[a-zA-Z0-9 ,'-]{10,100}$", message = "Please insert a valid description (10-100 characters: letters / digits / space / - / , / ')")
    private String description;
    @NotNull
    @PositiveOrZero
    @Digits(integer = 6, fraction = 2, message = "Please insert a valid price (between 0 and 999'999.99, with at most two decimal places)")
    private BigDecimal price;
    @JsonbTransient
    private String username;

    @JsonbTransient
    public int getProductId() {
        return productID;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Product)) {
            return false;
        }
        Product other = (Product) obj;
        return productID == other.productID;
    }
}
