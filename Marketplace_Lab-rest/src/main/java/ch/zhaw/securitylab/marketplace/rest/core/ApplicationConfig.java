package ch.zhaw.securitylab.marketplace.rest.core;

import java.util.HashSet;
import java.util.Set;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("rest")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }
    
    /**
     * Do not modify addRestResourceClasses() method. It is automatically
     * populated with all resources defined in the project. If required, comment
     * out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(ch.zhaw.securitylab.marketplace.rest.core.AdminProductRest.class);
        resources.add(ch.zhaw.securitylab.marketplace.rest.core.AdminPurchaseRest.class);
        resources.add(ch.zhaw.securitylab.marketplace.rest.core.AuthenticationRest.class);
        resources.add(ch.zhaw.securitylab.marketplace.rest.core.CORSFilter.class);
        resources.add(ch.zhaw.securitylab.marketplace.rest.core.ConstraintViolationExceptionMapper.class);
        resources.add(ch.zhaw.securitylab.marketplace.rest.core.InvalidParameterExceptionMapper.class);
        resources.add(ch.zhaw.securitylab.marketplace.rest.core.ProductRest.class);
        resources.add(ch.zhaw.securitylab.marketplace.rest.core.PurchaseRest.class);
    }
}
