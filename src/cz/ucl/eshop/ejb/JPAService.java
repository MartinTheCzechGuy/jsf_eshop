package cz.ucl.eshop.ejb;

import cz.ucl.eshop.model.*;

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
        Query query = entityManager.createQuery("FROM Product WHERE units_in_stock > 0");
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

    /**
     * Persist new Customer
     * @param customer
     */
    public void createCustomer(Customer customer){
        try {
            entityManager.persist(customer);
        } catch (Exception e) {
            System.err.println("Exception while saving the Customer: " + e);
        }
    }

    /**
     * Persist new CreditCard
     * @param creditCard
     */
    public void createCreditCard(CreditCard creditCard){
        try {
            entityManager.persist(creditCard);
        } catch (Exception e) {
            System.err.println("Exception while saving the credit card: " + e);
        }
    }

    /**
     * Persist new Address
     * @param address
     */
    public void createAddress(Address address){
        try {
            entityManager.persist(address);
        } catch (Exception e) {
            System.err.println("Exception while saving the address: " + e);
        }
    }

    /**
     * Persist new Order
     * @param order
     */
    public void createOrder(Order order){
        try {
            entityManager.persist(order);
        } catch (Exception e) {
            System.err.println("Exception while saving the order: " + e);
        }
    }

    /**
     * Save Product to DB
     * @param product
     */
    public void saveProduct(Product product) {
        try {
            entityManager.merge(product);
        } catch (Exception e) {
            System.err.println("Exception while saving the product: " + e);
        }
    }

    /**
     * Save CreditCard to DB
     * @param creditCard
     */
    public void saveCreditCard(CreditCard creditCard) {
        try {
            entityManager.merge(creditCard);
        } catch (Exception e) {
            System.err.println("Exception while saving the credit card: " + e);
        }
    }

    /**
     * Save Customer to DB
     * @param customer
     */
    public void saveCustomer(Customer customer) {
        try {
            entityManager.merge(customer);
        } catch (Exception e) {
            System.err.println("Exception while saving the customer: " + e);
        }
    }

    /**
     * Save Order to DB
     * @param order
     */
    public void saveOrder(Order order) {
        try {
            entityManager.merge(order);
        } catch (Exception e) {
            System.err.println("Exception while saving the order: " + e);
        }
    }

    /**
     * Save Address to DB
     * @param address
     */
    public void saveAddress(Address address) {
        try {
            entityManager.merge(address);
        } catch (Exception e) {
            System.err.println("Exception while saving the address: " + e);
        }
    }
}