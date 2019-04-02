package cz.ucl.eshop.ejb;

import cz.ucl.eshop.model.OrderedItem;
import cz.ucl.eshop.model.Product;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Named
@SessionScoped
public class ShoppingCartController implements Serializable {

    @EJB
    private JPAService JPAService;
    private int selectedQuantity;
    private List<OrderedItem> selectedProducts;
    private boolean quantityUnavaible;

    public boolean isQuantityUnavaible() {
        return quantityUnavaible;
    }

    public void setQuantityUnavaible(boolean quantityUnavaible) {
        this.quantityUnavaible = quantityUnavaible;
    }

    public List<OrderedItem> getSelectedProducts() {
        return selectedProducts;
    }

    public void setSelectedProducts(List<OrderedItem> selectedProducts) {
        this.selectedProducts = selectedProducts;
    }

    public int getSelectedQuantity() {
        return selectedQuantity;
    }

    public void setSelectedQuantity(int selectedQuantity) {
        this.selectedQuantity = selectedQuantity;
    }

    public JPAService getJPAService() {
        return JPAService;
    }

    public void setJPAService(JPAService JPAService) {
        this.JPAService = JPAService;
    }

    @PostConstruct
    public void init() {
        selectedProducts = new ArrayList<>();
        quantityUnavaible = false;
        selectedQuantity = 1;
    }

    /** Add item to the order
     *
     * @param productId
     */
    public String addItem(long productId){
        Product product = JPAService.findProductById(productId);

        OrderedItem orderedItem = this.itemAlreadyInCart(product);
        if (orderedItem == null) {
            orderedItem = new OrderedItem(product);
            orderedItem.setQuantity(0);
            selectedProducts.add(orderedItem);
        }

        if (selectedQuantity <= product.getUnitsInStock()) {
            orderedItem.setQuantity(orderedItem.getQuantity() + selectedQuantity);
            product.setUnitsInStock(product.getUnitsInStock() - selectedQuantity);
            quantityUnavaible = false;
        } else {
            quantityUnavaible = true;
            orderedItem.setQuantity(orderedItem.getQuantity() + 1);
            product.setUnitsInStock(product.getUnitsInStock() - 1);
        }

        JPAService.saveProduct(product);
        orderedItem.setPriceAllUnits(product.getPrice()* orderedItem.getQuantity());
        selectedQuantity = 1;
        return "product-list";
    }

    /**
     * Change quantity of an product in shopping cart
     * @param productId
     * @return
     */
    public String changeQuantity (long productId) {
        Product product = JPAService.findProductById(productId);
        OrderedItem orderedItem = this.itemAlreadyInCart(product);

        if (selectedQuantity == orderedItem.getQuantity()) {
            quantityUnavaible = false;
        } else if ((selectedQuantity < orderedItem.getQuantity()) || (selectedQuantity <= orderedItem.getProduct().getUnitsInStock())) {
            // required amount is avaible
            product.setUnitsInStock(product.getUnitsInStock() + orderedItem.getQuantity());
            orderedItem.setQuantity(selectedQuantity);
            product.setUnitsInStock(product.getUnitsInStock() - selectedQuantity);
            quantityUnavaible = false;
            JPAService.saveProduct(product);
            orderedItem.setPriceAllUnits(product.getPrice()* orderedItem.getQuantity());
        } else {
            quantityUnavaible = true;
        }

        selectedQuantity = 1;
        return "shopping-cart";
    }

    /**
     * Return OrderedItem if its already selected in the shopping cart
     * @param product
     * @return
     */
    private OrderedItem itemAlreadyInCart(Product product){
        for (OrderedItem orderedItem : selectedProducts) {
            if (orderedItem.getProduct().getId() == product.getId()) {
                return orderedItem;
            }
        }
        return null;
    }

    /**
     * Remove product from the shopping cart
     * @param productId
     * @return
     */
    public String removeFromCart(long productId){
        Product product = JPAService.findProductById(productId);
        OrderedItem orderedItem = this.itemAlreadyInCart(product);

        product.setUnitsInStock(product.getUnitsInStock() + orderedItem.getQuantity());
        JPAService.saveProduct(product);
        this.selectedProducts.remove(orderedItem);
        return "shopping-cart";
    }

    /**
     * Count price for whole order
     * @return
     */
    public double getOrderPrice(){
        return selectedProducts.stream().mapToDouble(OrderedItem::getPriceAllUnits).sum();
    }
}
