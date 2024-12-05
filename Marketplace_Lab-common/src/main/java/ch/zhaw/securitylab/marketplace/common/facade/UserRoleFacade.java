package ch.zhaw.securitylab.marketplace.common.facade;

import java.util.List;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import ch.zhaw.securitylab.marketplace.common.model.UserInfo;
import ch.zhaw.securitylab.marketplace.common.model.UserRole;

@Stateless
public class UserRoleFacade extends AbstractFacade<UserInfo> {

    private static final long serialVersionUID = 1L;

    @PersistenceContext(unitName = "marketplace")
    private EntityManager entityManager;

    public UserRoleFacade() {
        super(UserInfo.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

    public List<UserRole> findByUsername(String username) {
        Query query = entityManager.createNamedQuery("UserRole.findByUsername");
        query.setParameter("username", username);
        return query.getResultList();
    }
}
