package ch.zhaw.securitylab.marketplace.rest.core;

import java.security.InvalidParameterException;
import java.math.BigDecimal;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.enterprise.context.RequestScoped;
import jakarta.validation.Valid;
import ch.zhaw.securitylab.marketplace.common.model.CheckoutDto;
import ch.zhaw.securitylab.marketplace.common.facade.ProductFacade;
import ch.zhaw.securitylab.marketplace.common.facade.PurchaseFacade;
import ch.zhaw.securitylab.marketplace.common.model.Product;
import ch.zhaw.securitylab.marketplace.common.model.Purchase;

@RequestScoped
@Path("purchases")
public class PurchaseRest {

    @Inject
    private PurchaseFacade purchaseFacade;
    @Inject
    private ProductFacade productFacade;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void post(@Valid CheckoutDto checkoutDto) {
        BigDecimal totalPrice = new BigDecimal(0.0);
        Product product;
        int validProductCodes = 0;

        for (String productCode : checkoutDto.getProductCodes()) {
            product = productFacade.findByCode(productCode);
            if (product != null) {
                totalPrice = totalPrice.add(product.getPrice());
                validProductCodes++;
            }
        }
        if (validProductCodes > 0) {
            Purchase purchase = new Purchase();
            purchase.setFirstname(checkoutDto.getFirstname());
            purchase.setLastname(checkoutDto.getLastname());
            purchase.setCreditCardNumber(checkoutDto.getCreditCardNumber());
            purchase.setTotalPrice(totalPrice);
            purchaseFacade.create(purchase);
        } else {
            throw new InvalidParameterException("The purchase could not be completed because no valid product codes were submitted");
        }
    }
}
