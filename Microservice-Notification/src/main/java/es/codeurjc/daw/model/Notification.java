package es.codeurjc.daw.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long customerId;
    private double amount;

    public Notification(){}
     

    public Notification(Long customerId, double amount){
        this.customerId = customerId;
        this.amount = amount;
    }


    public Notification(Long id, Long customerId, double amount){
        this.id = id;
        this.customerId = customerId;
        this.amount = amount;
    }

    public Notification(NotificationDto notificationDto){
        this.customerId = notificationDto.getCustomerId();
        this.amount = notificationDto.getAmount();
    }

    public double getAmount(){
        return this.amount;
    }

    public Long getCustomerId(){
        return this.customerId;
    }

}