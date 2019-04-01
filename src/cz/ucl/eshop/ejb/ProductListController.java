package cz.ucl.eshop.ejb;

import cz.ucl.eshop.model.Product;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@RequestScoped
public class ProductListController implements Serializable {

    @EJB
    private JPAService JPAService;

    private List<Product> productList;

    public JPAService getJPAService() { return JPAService; }

    public void setJPAService(JPAService JPAService) { this.JPAService = JPAService; }

    public List<Product> getProductList() { return productList; }

    public void setProductList(List<Product> productList) { this.productList = productList; }

    @PostConstruct
    public void init() {
        this.setProductList(JPAService.getResultList());
    }

}
