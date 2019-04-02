package cz.ucl.eshop.ejb;

import cz.ucl.eshop.model.*;

import javax.annotation.PostConstruct;
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

    private Order order;
    private Customer customer;
    private Address address;
    private CreditCard creditCard;

    public Conversation getConversation() {
        return conversation;
    }

    public void setConversation(Conversation conversation) {
        this.conversation = conversation;
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
        order = new Order();
        creditCard = new CreditCard();
    }

    /**
     * Submit order - save all the data to DB and end conversation.
     *
     * @return
     */
    public String submitOrder(){
        //TODO Tady ta metoda musi vse ulozit do DB - objednavka vznikla
        System.out.println("checkuju co mam");
        //ukladani do DB
        conversation.end();
        return "product-list";

    }

    /**
     * End conversation - order is either submited or canceled
     *
     * @return
     */
    public String cancelOrder() {
        // TODO vsechny itemy musis vratit na sklad!!!
        conversation.end();
        return "product-list";
    }

    /**
     * Load all selected items from shopping card into the order
     * @param orderedItemList
     */
    public void loadSelectedItems(List<OrderedItem> orderedItemList){
        order.getOrderedItemList().addAll(orderedItemList);
    }


    // TODO nemusi byt metody
    /**
     * volana po odeslani formulare s Customer a Address -
     *
     */
    public String submitCustomerDetails(){
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
