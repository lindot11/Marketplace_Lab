package ch.zhaw.securitylab.marketplace.web.backing;

import java.io.Serializable;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.Conversation;
import jakarta.enterprise.context.ConversationScoped;
import ch.zhaw.securitylab.marketplace.common.service.AdminProductService;
import ch.zhaw.securitylab.marketplace.common.utility.Message;
import ch.zhaw.securitylab.marketplace.common.model.Product;

@Named
@ConversationScoped
public class EditProductBacking implements Serializable {

    private static final long serialVersionUID = 1L;
    @Inject
    private Conversation conversation;
    @Inject
    private AdminProductService adminProductService;
    private Product product = new Product();

    public Product getProduct() {
        return product;
    }

    @PostConstruct
    public void init() {
        initConversation();
    }

    public String editProduct(Product product) {
        this.product = product;
        return "/view/admin/product/editproduct";
    }

    public String updateProduct() {
        if (adminProductService.updateProduct(product)) {
            Message.setMessage("The product could successfully be updated");
            endConversation();
            return "/view/admin/admin";
        } else {
            Message.setMessage("The product could not be updated as a product with the same code already exists");
            return "/view/admin/product/editproduct";
        }
    }

    public void initConversation() {
        conversation.begin();
    }

    public void endConversation() {
        conversation.end();
    }
}
