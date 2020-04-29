package es.codeurjc.daw.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "Product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int stock;
    private String name;
    private double price;

    public Product(){}

    public Product(String name, int stock, double price){
        this.name = name;
        this.stock = stock;
        this.price = price;
    }

    public Product(ProductDto productDto){
        this.id = productDto.getId();
        this.name = productDto.getName();
        this.price = productDto.getPrice();
        this.stock = productDto.getStock();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getStock() {
        return stock;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
}