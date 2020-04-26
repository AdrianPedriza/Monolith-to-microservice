package es.codeurjc.daw.controller;

import java.util.Optional;

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

        Optional<Product> product = this.productService.get(order.getProductId());
        Optional<Customer> customer = this.customerService.get(order.getCustomerId());

        if(product.isPresent() && customer.isPresent()){
            double orderAmount = this.productService.getAmount(order.getProductId(), order.getUnits());
            if (costumerHasEnoguthMoney(orderAmount, customer.get()) && isOrderInStock(product.get(), order.getUnits())){
                this.customerService.removeCredit(customer.get(), orderAmount);
                this.orderService.addOrder(order);
                return new ResponseEntity<Order>(order, HttpStatus.CREATED);
            }
        }
        return new ResponseEntity<Order>(HttpStatus.METHOD_NOT_ALLOWED);
    }
    
    private boolean isOrderInStock(Product product, int units) {
        return this.productService.hasEnoughtStock(product, units);
    }

    private boolean costumerHasEnoguthMoney(double orderAmount, Customer customer) {
        return orderAmount <= customerService.getCredit(customer);
    }

}
