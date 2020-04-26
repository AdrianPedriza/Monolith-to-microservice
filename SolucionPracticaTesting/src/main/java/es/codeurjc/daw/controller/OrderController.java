package es.codeurjc.daw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.codeurjc.daw.model.Customer;
import es.codeurjc.daw.model.Order;
import es.codeurjc.daw.model.Product;
import es.codeurjc.daw.services.CustomerService;
import es.codeurjc.daw.services.OrderService;
import es.codeurjc.daw.services.ProductService;

@RestController
@RequestMapping("/api")
public class OrderController {


	@Autowired
    private OrderService orderService;
    
    @Autowired
    private CustomerService customerService;
    
    @Autowired
	private ProductService productService;
    
    @PostMapping("/order")
	public ResponseEntity<Order> newOrder(@RequestBody Order order) {

        Long orderAmount = this.productService.getAmount(order.getProductId(), order.getUnits());
        Customer customer = this.customerService.get(order.getCustomerId());
        Product product = this.productService.get(order.getProductId());

        if (costumerHasEnoguthMoney(orderAmount, customer) && 
            isOrderInStock(product, order.getUnits())){
            this.customerService.removeCredit(customer, orderAmount);
            this.orderService.addOrder(order);
            return new ResponseEntity<>(order, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(order, HttpStatus.METHOD_NOT_ALLOWED);
        }
    }
    
    private boolean isOrderInStock(Product product, int units) {
        return this.productService.hasEnoughtStock(product, units);
    }

    private boolean costumerHasEnoguthMoney(Long orderAmount, Customer customer) {
        return orderAmount <= customerService.getCredit(customer);
    }

}
