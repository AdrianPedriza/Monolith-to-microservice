package es.codeurjc.daw.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Request")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long customerId;
    private Long productId;
    private int units;

    public Order(){}

    public Order(Long customerId, Long productId, int units){
        this.customerId = customerId;
        this.productId = productId;
        this.units = units;
    }

    public Order(OrderDto orderDto){
        this.customerId = orderDto.getCustomerId();
        this.productId = orderDto.getProductId();
        this.units = orderDto.getUnits();
    }


    public void setUnits(int units) {
        this.units = units;
    }


    public int getUnits() {
        return units;
    }


    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }


    public Long getCustomerId() {
        return customerId;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }


    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getProductId() {
        return productId;
    }

}