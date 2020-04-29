package es.codeurjc.daw.model;

public class UpdateProductDto {

    private double price;
    private int units;

    public UpdateProductDto(){}

    public UpdateProductDto(int units){
        this.units = units;
    }

    public UpdateProductDto(double price, int units){
        this.price = price;
        this.units = units;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public void setUnits(int units) {
        this.units = units;
    }


    public double getPrice() {
        return price;
    }


    public int getUnits() {
        return units;
    }

}