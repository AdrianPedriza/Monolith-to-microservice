package es.codeurjc.daw.model;

public class OrderDto {

    private Long customerId;
    private Long productId;
    private int units;

    public OrderDto(){}

    public OrderDto(Long customerId, Long productId, int units){
        this.customerId = customerId;
        this.productId = productId;
        this.units = units;
    }


    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }


    public void setProductId(Long productId) {
        this.productId = productId;
    }


    public void setUnits(int units) {
        this.units = units;
    }


    public Long getCustomerId() {
        return customerId;
    }


    public Long getProductId() {
        return productId;
    }

    public int getUnits() {
        return units;
    }
}