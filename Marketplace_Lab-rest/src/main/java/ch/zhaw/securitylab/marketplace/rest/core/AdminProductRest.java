package ch.zhaw.securitylab.marketplace.rest.core;

import java.util.List;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.SecurityContext;
import ch.zhaw.securitylab.marketplace.common.model.AdminProductDto;
import ch.zhaw.securitylab.marketplace.common.model.Product;

@RequestScoped
@Path("admin/products")
public class AdminProductRest {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<AdminProductDto> get() {
        
        // Implement
        
        return null;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void post(Product product, @Context SecurityContext context) {
        
        /* @Context injects the SecurityContext object of the current request 
           as a method parameter. SecurityContext is part of JAX-RS and provides
           security-relevant information such as the name of the user who sent 
           the request. Accessing this name can be done with 
           context.getUserPrincipal().getName() */
        
        // Implement
        
    }

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") String id) {
        
        // Implement

    }
}
