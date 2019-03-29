package cz.ucl.eshop.model.utils;

import cz.ucl.eshop.model.Product;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@SessionScoped
@Named
public class JPAUtilsManager implements Serializable {

    @Produces
    @PersistenceContext(unitName = "e-shop")
    private EntityManager entityManager;

    @Transactional(Transactional.TxType.REQUIRED)
    public List<Product> getAllProducts(){
        Query query = entityManager.createQuery("from product");
        return query.getResultList();
    }

    @PostConstruct
    public void init() {
        System.out.println("JPA init()");
    }
}
