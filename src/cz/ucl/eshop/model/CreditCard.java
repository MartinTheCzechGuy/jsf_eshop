package cz.ucl.eshop.model;

import javax.persistence.*;

@Entity(name ="credit_card")
public class CreditCard {

    @Id
    @Column(name = "credit_card_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    private String number;
    private String validity;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getValidity() {
        return validity;
    }

    public void setValidity(String validity) {
        this.validity = validity;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
