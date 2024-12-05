package ch.zhaw.securitylab.marketplace.rest.core;

import java.security.InvalidParameterException;
import java.util.List;
import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.RequestScoped;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import ch.zhaw.securitylab.marketplace.common.facade.PurchaseFacade;
import ch.zhaw.securitylab.marketplace.common.model.Purchase;

@RequestScoped
@Path("admin/purchases")
public class AdminPurchaseRest {

    @Inject
    private PurchaseFacade purchaseFacade;

    @GET
    @RolesAllowed({"marketing", "sales"})
    @Produces(MediaType.APPLICATION_JSON)
    public List<Purchase> get() {
        return purchaseFacade.findAll();
    }

    @DELETE
    @RolesAllowed("sales")
    @Path("{id}")
    public void delete(@Min(value = 1, message = "The PurchaseID must be between 1 and 999'999")
            @Max(value = 999999, message = "The PurchaseID must be between 1 and 999'999") 
            @PathParam("id") String id) {
        Purchase purchase;
        purchase = purchaseFacade.findById(Integer.parseInt(id));
        if (purchase != null) {
            purchaseFacade.remove(purchase);
        } else {
            throw new InvalidParameterException("The purchase with PurchaseID = '" + id + "' does not exist");
        }
    }
}
