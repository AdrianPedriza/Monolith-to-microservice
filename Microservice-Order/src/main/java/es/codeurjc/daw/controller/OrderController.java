package es.codeurjc.daw.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.codeurjc.daw.model.Order;
import es.codeurjc.daw.model.OrderDto;
import es.codeurjc.daw.sagas.OrderSagaService;
import es.codeurjc.daw.services.OrderService;

@RestController
@RequestMapping("/api")
public class OrderController {

    @Autowired
    private OrderSagaService orderSagaService;

    @Autowired
    private OrderService orderService;

    @PostMapping("/order")
    public ResponseEntity<Order> newOrder(@RequestBody OrderDto orderDto) {
        try{
            this.orderSagaService.createOrderSagas(new Order(orderDto));
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @GetMapping("/order/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable final Long id) {
        Optional<Order> order = this.orderService.get(id);
        if(order.isPresent()){
            return new ResponseEntity<>(order.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
