package cz.ucl.eshop.model;

import cz.ucl.eshop.model.types.OrderStatus;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "order_of_goods")
public class Order {
    @Id
    @Column(name = "order_of_goods_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @Column(name = "status")
    private OrderStatus orderStatus;
    @Column(name = "total_price")
    private double totalPrice;
    @OneToMany(mappedBy = "order")
    List<OrderedItem> orderedItemList;

    public Order(){ orderedItemList = new ArrayList<>(); }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<OrderedItem> getOrderedItemList() {
        return orderedItemList;
    }

    public void setOrderedItemList(List<OrderedItem> orderedItemList) {
        this.orderedItemList = orderedItemList;
    }
}
