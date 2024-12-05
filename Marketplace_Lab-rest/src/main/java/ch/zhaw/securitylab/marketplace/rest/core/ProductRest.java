package ch.zhaw.securitylab.marketplace.rest.core;

import java.util.List;
import jakarta.inject.Inject;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.enterprise.context.RequestScoped;
import jakarta.validation.constraints.Size;
import ch.zhaw.securitylab.marketplace.common.facade.ProductFacade;
import ch.zhaw.securitylab.marketplace.common.model.Product;

@RequestScoped
@Path("products")
public class ProductRest {

    @Inject
    private ProductFacade productFacade;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Product> getFiltered(@Size(max=50, message = "The search string must not be longer than 50 characters") @DefaultValue("") @QueryParam("filter") String filter) {
        return productFacade.findByDescription(filter);
    }
}
