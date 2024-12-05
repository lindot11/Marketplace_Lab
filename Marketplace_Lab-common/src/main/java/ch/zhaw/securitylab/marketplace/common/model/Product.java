package ch.zhaw.securitylab.marketplace.common.model;

import java.io.Serializable;
import java.math.BigDecimal;
import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.*;

@Entity
@Table(name = "Product")
@NamedQueries({
    @NamedQuery(name = "Product.findByDescription", query = "SELECT p FROM Product p WHERE p.description LIKE :description"),
    @NamedQuery(name = "Product.findByCode", query = "SELECT p FROM Product p WHERE p.code = :code"),
    @NamedQuery(name = "Product.findById", query = "SELECT p FROM Product p WHERE p.productID = :productID")})
public class Product implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    private int productID;
    private String code;
    private String description;
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
