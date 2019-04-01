package cz.ucl.eshop.ejb;

import cz.ucl.eshop.model.OrderedItem;
import cz.ucl.eshop.model.Product;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.List;

@Stateless
public class JPAService {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Find all the existing products
     *
     * @return
     */
    public List<Product> getResultList() {
        Query query = entityManager.createQuery("FROM Product");
        try {
            return query.getResultList();
        } catch (Exception e) {
            System.err.println("Error while loading products from DB.");
        }
        return null;
    }

    /**
     * Find product by its ID
     *
     * @param productId
     * @return
     */
    public Product findProductById(long productId) {
        try {
            return entityManager.find(Product.class, productId);
        } catch (Exception e) {
            System.err.println("Exception while finding the product");
        }
        return null;
    }

    /**
     * Persist new OrderedItem
     * @param orderedItem
     */
    public void createOrderedItem(OrderedItem orderedItem){
        try {
            entityManager.persist(orderedItem);
        } catch (Exception e) {
            System.err.println("Exception while saving the Ordered Item: " + e);
        }
    }

    /** Update ordered item int DB
     *
     * @param orderedItem
     */
    public void saveOrderedItem(OrderedItem orderedItem) {
        try {
            entityManager.merge(orderedItem);
        } catch (Exception e) {
            System.err.println("Exception while saving the Ordered Item: " + e);
        }
    }

    /** find OrderedItem instance by its ID
     *
     * @param id
     * @return
     */
    public OrderedItem findItemById (long id) {
        try {
            return entityManager.find(OrderedItem.class, id);
        } catch (Exception e) {
            System.err.println("Exception while finding the ordered item: " + e);
        }
        return null;
    }
}