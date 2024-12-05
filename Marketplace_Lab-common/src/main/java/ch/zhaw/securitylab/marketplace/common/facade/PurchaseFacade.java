package ch.zhaw.securitylab.marketplace.common.facade;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import ch.zhaw.securitylab.marketplace.common.model.Purchase; 

@Stateless
public class PurchaseFacade extends AbstractFacade<Purchase> {

    @PersistenceContext(unitName = "marketplace")
    private EntityManager entityManager;

    public PurchaseFacade() {
        super(Purchase.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }
    
    public Purchase findById(int id) {
        Query query = entityManager.createNamedQuery("Purchase.findById");
        query.setParameter("purchaseID", id);
        return getSingleResultOrNull(query);
    }
}
