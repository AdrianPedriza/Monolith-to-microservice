package es.codeurjc.daw.model;

public class NotificationDto {
    
    private Long customerId;
    private double amount;

    public NotificationDto(){}
     

    public NotificationDto(Long customerId, double amount){
        this.customerId = customerId;
        this.amount = amount;
    }


    public void setAmount(double amount) {
        this.amount = amount;
    }


    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public double getAmount(){
        return this.amount;
    }

    public Long getCustomerId(){
        return this.customerId;
    }

}