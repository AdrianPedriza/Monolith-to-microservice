package es.codeurjc.daw.controller;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.codeurjc.daw.model.Customer;
import es.codeurjc.daw.services.CustomerService;
import es.codeurjc.daw.services.NotificationService;

@RestController
@RequestMapping("/api")
public class CustomerController {

    @Autowired
    private CustomerService customService;

    @Autowired
    private NotificationService notificationService;

    @PostMapping("/customer/")
    public ResponseEntity<Customer> newCustomer(@RequestBody Customer customer) {
        this.customService.add(customer);
        return new ResponseEntity<Customer>(customer, HttpStatus.CREATED);
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable final Long id) {
        Optional<Customer> customer = this.customService.get(id);
        if(customer.isPresent()){
            return new ResponseEntity<Customer>(customer.get(), HttpStatus.OK);
        }
        return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/customer/{id}/credit")
	public ResponseEntity<Customer> addCredit(@RequestBody Customer newCustomer, @PathVariable final Long id) {
        Optional<Customer> customer = this.customService.get(id);
        if (customer.isPresent()){
            this.customService.addCredit(customer.get(), newCustomer.getCredit());
            this.notificationService.notify("Credito añadido.");
            return new ResponseEntity<Customer>(customer.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
        }
    }

}
