package ch.zhaw.securitylab.marketplace.common.service;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import ch.zhaw.securitylab.marketplace.common.model.Product;
import ch.zhaw.securitylab.marketplace.common.facade.ProductFacade;

@RequestScoped
public class AdminProductService {

    @Inject
    private ProductFacade productFacade;

    /**
     * Inserts product into the database if no product with the same code
     * already exists.
     * 
     * @param product The product to insert
     * @return true if the product was inserted, false otherwise
     */
    public boolean insertProduct(Product product) {
        // Complete this method
        if (productFacade.findByCode(product.getCode()) == null) {
            productFacade.create(product);
            return true;
        }
        return false;
    }

    /**
     * Update product in the database if no product with the same code
     * already exists.
     * 
     * @param updatedProduct The product to update
     * @return true if the product was updated, false otherwise
     */
    /*
     * public boolean updateProduct(Product updatedProduct) {
     * Product productWithSameCode =
     * productFacade.findByCode(updatedProduct.getCode());
     * if (productWithSameCode == null || updatedProduct.getProductId() ==
     * productWithSameCode.getProductId()) {
     * Product product = productFacade.findById(updatedProduct.getProductId());
     * product.setCode(updatedProduct.getCode());
     * product.setDescription(updatedProduct.getDescription());
     * product.setPrice(updatedProduct.getPrice());
     * productFacade.edit(product);
     * return true;
     * } else {
     * return false;
     * }
     * }
     */
}