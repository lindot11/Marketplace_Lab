package ch.zhaw.securitylab.marketplace.common.model;

import java.math.BigDecimal;

public class AdminProductDto {

    private int productID;
    private String code;
    private String description;
    private BigDecimal price;
    private String username;

    public AdminProductDto() {
    }

    public AdminProductDto(Product product) {
        productID = product.getProductId();
        code = product.getCode();
        description = product.getDescription();
        price = product.getPrice();
        username = product.getUsername();
    }

    public int getProductId() {
        return productID;
    }

    public void setProductId(int productID) {
        this.productID = productID;
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
}
