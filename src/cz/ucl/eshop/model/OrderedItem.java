package cz.ucl.eshop.model;

import javax.persistence.*;

@Entity(name = "ordered_item")
public class OrderedItem {
    @Id
    @Column(name = "ordered_item_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    private int quantity;
    @Column(name = "price_all_units")
    private double priceAllUnits;
    @ManyToOne
    @JoinColumn(name = "order_of_goods_id")
    private Order order;

    public OrderedItem(Product product, int quantity, double priceAllUnits, Order order){
        this.product = product;
        this.quantity = quantity;
        this.priceAllUnits = priceAllUnits;
        this.order = order;
    }

    public OrderedItem(){}

    public OrderedItem(Product product){
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPriceAllUnits() {
        return priceAllUnits;
    }

    public void setPriceAllUnits(double priceAllUnits) {
        this.priceAllUnits = priceAllUnits;
    }
}
