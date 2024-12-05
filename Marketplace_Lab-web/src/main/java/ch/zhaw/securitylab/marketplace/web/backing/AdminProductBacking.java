package ch.zhaw.securitylab.marketplace.web.backing;

import java.util.List;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import ch.zhaw.securitylab.marketplace.common.facade.ProductFacade;
import ch.zhaw.securitylab.marketplace.common.model.Product;

@Named
@RequestScoped
public class AdminProductBacking {

    @Inject
    private ProductFacade productFacade;
    private List<Product> products;

    @PostConstruct
    public void init() {
        products = productFacade.findAll();
    }

    public List<Product> getProducts() {
        return products;
    }

    public int getCount() {
        return products.size();
    }

    public String removeProduct(Product product) {
        productFacade.remove(product);
        products.remove(product);
        return "/view/admin/admin";
    }
}
