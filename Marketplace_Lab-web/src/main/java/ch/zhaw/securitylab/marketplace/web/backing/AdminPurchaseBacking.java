package ch.zhaw.securitylab.marketplace.web.backing;

import java.util.List;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import ch.zhaw.securitylab.marketplace.common.facade.PurchaseFacade;
import ch.zhaw.securitylab.marketplace.common.model.Purchase;

@Named
@RequestScoped
public class AdminPurchaseBacking {

    @Inject
    private PurchaseFacade purchaseFacade;
    private List<Purchase> purchases;

    @PostConstruct
    private void init() {
        purchases = purchaseFacade.findAll();
    }

    public List<Purchase> getPurchases() {
        return purchases;
    }

    public int getCount() {
        return purchases.size();
    }

    public String removePurchase(Purchase purchase) {
        purchaseFacade.remove(purchase);
        purchases.remove(purchase);
        return "/view/admin/admin";
    }
}
