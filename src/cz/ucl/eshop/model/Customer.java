package cz.ucl.eshop.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Customer {
    @Id
    @Column(name = "customer_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String email;
    private int telephone;
    @OneToMany(mappedBy = "customer")
    private List<CreditCard> creditCardList;
    @ManyToMany
    @JoinTable(name = "customer_address", joinColumns = @JoinColumn(name = "customer_id"), inverseJoinColumns = @JoinColumn(name = "address_id"))
    private List<Address> addressList;
    @OneToMany(mappedBy = "customer")
    private List<Order> orderList;

    public Customer(){
        this.creditCardList = new ArrayList<>();
        this.addressList = new ArrayList<>();
        this.orderList = new ArrayList<>();
    }

    public List<Address> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<Address> addresses) {
        this.addressList = addresses;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public List<CreditCard> getCreditCardList() {
        return creditCardList;
    }

    public void setCreditCardList(List<CreditCard> creditCardList) {
        this.creditCardList = creditCardList;
    }
}
