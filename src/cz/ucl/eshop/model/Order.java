package cz.ucl.eshop.model;

import cz.ucl.eshop.model.types.OrderStatus;

import javax.persistence.*;
import java.util.List;

@Entity(name = "product_order")
public class Order {
    @Id
    @Column(name = "product_order_id")
    private long id;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @Column(name = "status")
    private OrderStatus orderStatus;
    @Column(name = "total_price")
    private double totalPrice;
    @ManyToMany
    @JoinTable(name = "orderofgoods_ordereditem", joinColumns = @JoinColumn(name = "order_of_goods_id"), inverseJoinColumns = @JoinColumn(name = "ordered_item_id"))
    private List<OrderedItem> orderedItemList;

}
