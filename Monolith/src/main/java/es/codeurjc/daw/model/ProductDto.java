package es.codeurjc.daw.model;


public class ProductDto {

    private Long id;

    private int stock;
    private String name;
    private double price;

    public ProductDto(){}

    public ProductDto(String name, int stock, double price){
        this.name = name;
        this.stock = stock;
        this.price = price;
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