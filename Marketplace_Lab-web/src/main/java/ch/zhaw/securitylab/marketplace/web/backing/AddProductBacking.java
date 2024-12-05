package ch.zhaw.securitylab.marketplace.web.backing;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.servlet.http.HttpServletRequest;
import ch.zhaw.securitylab.marketplace.common.service.AdminProductService;
import ch.zhaw.securitylab.marketplace.common.utility.Message;
import ch.zhaw.securitylab.marketplace.common.model.Product;

@Named
@RequestScoped
public class AddProductBacking {

    @Inject
    private AdminProductService adminProductService;
    private Product product = new Product();

    public Product getProduct() {
        return product;
    }

    public String addProduct() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        product.setUsername(request.getRemoteUser());
        if (adminProductService.insertProduct(product)) {
            Message.setMessage("The product could successfully be added");
            return "/view/admin/admin";
        } else {
            Message.setMessage("The product could not be added as a product with the same code already exists");
            return "/view/admin/product/addproduct";
        }
    }
}
