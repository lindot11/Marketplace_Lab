package ch.zhaw.securitylab.marketplace.web.backing;

import java.io.Serializable;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import ch.zhaw.securitylab.marketplace.common.facade.PurchaseFacade;
import ch.zhaw.securitylab.marketplace.common.model.Purchase;
import ch.zhaw.securitylab.marketplace.common.service.CartService;
import ch.zhaw.securitylab.marketplace.common.utility.Message;

@Named
@RequestScoped
public class CheckoutBacking implements Serializable {

    private static final long serialVersionUID = 1L;
    @Inject
    private PurchaseFacade purchaseFacade;
    @Inject
    private CartService cartService;
    private Purchase purchase = new Purchase();

    public Purchase getPurchase() {
        return purchase;
    }

    public String completePurchase() {
        if (cartService.getCount() > 0) {
            purchase.setTotalPrice(cartService.getTotalPrice());
            purchaseFacade.create(purchase);
            Message.setMessage("Your purchase has been completed, thank you for shopping with us");
        } else {
            Message.setMessage("The purchase could not be completed because there are no products in the shopping cart");
        }
        cartService.empty();
        return "/view/public/search";
    }
}
