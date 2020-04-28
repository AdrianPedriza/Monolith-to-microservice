package es.codeurjc.daw.model;

public class ReserveCreditDto {

    private double amount;

    public ReserveCreditDto(){}

    public ReserveCreditDto(double amount){
        this.amount = amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

}