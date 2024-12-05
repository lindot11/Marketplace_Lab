package ch.zhaw.securitylab.marketplace.common.facade;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import ch.zhaw.securitylab.marketplace.common.model.UserInfo;

@Stateless
public class UserInfoFacade extends AbstractFacade<UserInfo> {

    private static final long serialVersionUID = 1L;

    @PersistenceContext(unitName = "marketplace")
    private EntityManager entityManager;

    public UserInfoFacade() {
        super(UserInfo.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

    public UserInfo findByUsername(String username) {
        Query query = entityManager.createNamedQuery("UserInfo.findByUsername");
        query.setParameter("username", username);
        return getSingleResultOrNull(query);
    }
}
