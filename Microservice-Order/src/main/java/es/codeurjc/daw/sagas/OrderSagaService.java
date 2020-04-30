package es.codeurjc.daw.sagas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

import es.codeurjc.daw.model.Order;
import es.codeurjc.daw.model.ReserveCreditDto;
import es.codeurjc.daw.model.UpdateProductDto;
import es.codeurjc.daw.repositories.OrderRepository;

@Service
public class OrderSagaService {

    static final String URI_RESERVE_CREDIT = "http://monolith:8080/api/customer/{id}/credit";
    static final String URI_UPDATE_STOCK = "http://monolith:8080/api/product/{id}";

    @Autowired
    private OrderRepository orderRepository;

    public OrderSagaService(){}

    public OrderSagaService(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    public Order createOrderSagas(Order order) throws Exception {

        UpdateProductDto updateProductDto = updateStock(order.getProductId(), order.getUnits());
        if(updateProductDto.getPrice() > 0){
            ReserveCreditDto reserveCreditDto = reserveCredit(order.getCustomerId(), updateProductDto.getPrice());
            if(reserveCreditDto.getAmount() > 0){
                this.orderRepository.save(order);
                return order;
            }else{
                updateStock(order.getProductId(), order.getUnits()*(-1));
                throw new Exception("No credit available");
            }
        }else{
            throw new Exception("No product available");
        }
        
    }

    private UpdateProductDto updateStock(Long productId, int units){

        LinkedMultiValueMap<String, Long> params = new LinkedMultiValueMap<String, Long>();
        params.add("id", productId);

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<UpdateProductDto> response = restTemplate.exchange(URI_UPDATE_STOCK, HttpMethod.PUT, new HttpEntity<>(new UpdateProductDto(units)), UpdateProductDto.class, productId);
        return response.getBody();
    }

    private ReserveCreditDto reserveCredit(Long customerId, double credit){

        LinkedMultiValueMap<String, Long> params= new LinkedMultiValueMap<String, Long>();
        params.add("id", customerId);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ReserveCreditDto> response = restTemplate.exchange(URI_RESERVE_CREDIT, HttpMethod.PUT, new HttpEntity<>(new ReserveCreditDto(credit)) , ReserveCreditDto.class, customerId);
        return response.getBody();
    }



}