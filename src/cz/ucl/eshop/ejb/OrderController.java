package cz.ucl.eshop.ejb;

import cz.ucl.eshop.model.*;
import cz.ucl.eshop.model.types.OrderStatus;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ConversationScoped
public class OrderController implements Serializable {

    @Inject
    private Conversation conversation;
    @EJB
    private JPAService jpaService;
    @Inject
    private ShoppingCartController shoppingCartController;
    private Order order;
    private Customer customer;
    private Address address;
    private CreditCard creditCard;

    public ShoppingCartController getShoppingCartController() {
        return shoppingCartController;
    }

    public void setShoppingCartController(ShoppingCartController shoppingCartController) {
        this.shoppingCartController = shoppingCartController;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @PostConstruct
    public void init() {
        System.out.println("order init()");
        conversation.begin();
        customer = new Customer();
        address = new Address();
        order = new Order(OrderStatus.NEW);
        creditCard = new CreditCard();
    }

    /**
     * Submit order - save all the data to DB and end conversation.
     *
     * @return
     */
    public String submitOrder(){
        jpaService.createCustomer(customer);
        jpaService.createOrder(order);
        jpaService.createAddress(address);
        jpaService.createCreditCard(creditCard);

        order.setCustomer(customer);
        order.setOrderStatus(OrderStatus.CONFIRMED);
        //order.setTotalPrice();
        customer.getCreditCardList().add(creditCard);
        customer.getAddressList().add(address);
        customer.getOrderList().add(order);
        address.getCustomers().add(customer);
        creditCard.setCustomer(customer);

        jpaService.saveCustomer(customer);
        jpaService.saveOrder(order);
        jpaService.saveAddress(address);
        jpaService.saveCreditCard(creditCard);

        conversation.end();
        return "product-list";
    }

    /**
     * End conversation - order is either submited or canceled
     *
     * @return
     */
    public String cancelOrder() {
        conversation.end();
        return "product-list?faces-redirect=true";
    }

    /**
     * Load all selected items from shopping card into the order
     */
    public String loadSelectedItems(){
        this.order.getOrderedItemList().addAll(shoppingCartController.getSelectedProducts());
        shoppingCartController.getSelectedProducts().clear();
        return "checkout";
    }

    /**
     * volana po odeslani formulare s Customer a Address -
     *
     */
    public String submitCustomerDetails(){
        this.order.setOrderStatus(OrderStatus.PENDING_PAYMENT);
        return "payment-info";
    }

    /**
     * volana po odeslani formulare s CreditCard
     *
     */
    public String submitPaymentDetails(){
        return "order-submit";
    }
}
