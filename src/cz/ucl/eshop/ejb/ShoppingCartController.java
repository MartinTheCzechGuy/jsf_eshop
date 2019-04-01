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

    public String changeQuantity (long productId) {
        Product product = JPAService.findProductById(productId);
        OrderedItem orderedItem = this.itemAlreadyInCart(product);

        if (selectedQuantity <= orderedItem.getProduct().getUnitsInStock()) {
            orderedItem.setQuantity(orderedItem.getQuantity() + selectedQuantity);
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

    public void changeAmount(long productId, Integer newAmount){
        //this.selectedProducts.computeIfPresent(JPAService.findProductById(productId), (k,v) -> v = newAmount);
    }

    private OrderedItem itemAlreadyInCart(Product product){
        for (OrderedItem orderedItem : selectedProducts) {
            if (orderedItem.getProduct().getId() == product.getId()) {
                return orderedItem;
            }
        }
        return null;
    }
}
