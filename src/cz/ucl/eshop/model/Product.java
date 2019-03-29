package cz.ucl.eshop.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Product {

    @Id
    @Column(name = "product_id")
    private long id;
    private String name;
    private double price;
    private String img;
    private String description;
    @Column(name = "units_in_stock")
    private int unitsInStock;
    @OneToMany(mappedBy = "product")
    private List<OrderedItem> orderedItems;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
