package ch.zhaw.securitylab.marketplace.web.backing;

import java.io.Serializable;
import java.util.List;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import ch.zhaw.securitylab.marketplace.common.model.Product;
import ch.zhaw.securitylab.marketplace.common.service.CartService;

@Named
@SessionScoped
public class CartBacking implements Serializable {

    private static final long serialVersionUID = 1L;
    @Inject
    private CartService cartService;

    public List<Product> getProducts() {
        return cartService.getProducts();
    }

    public int getCount() {
        return cartService.getCount();
    }

    public String addProduct(Product product) {
        cartService.addProduct(product);
        return "/view/public/cart";
    }
}
