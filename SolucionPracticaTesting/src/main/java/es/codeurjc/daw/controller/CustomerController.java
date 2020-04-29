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
import es.codeurjc.daw.model.CustomerDto;
import es.codeurjc.daw.model.Notification;
import es.codeurjc.daw.model.ReserveCreditDto;
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
    public ResponseEntity<Customer> newCustomer(@RequestBody CustomerDto customerDto) {
        Customer customer = new Customer(customerDto);
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
	public ResponseEntity<ReserveCreditDto> reserveCredit(@RequestBody ReserveCreditDto reserveCreditDto, @PathVariable final Long id) {
        Optional<Customer> customer = this.customService.get(id);
        if (customer.isPresent()){
            if(this.customService.isEnoguthMoney(customer.get(), reserveCreditDto.getAmount())){
                this.customService.updateCredit(customer.get(), reserveCreditDto.getAmount());
                this.notificationService.notify(new Notification(customer.get().getId(),reserveCreditDto.getAmount()));
                return new ResponseEntity<ReserveCreditDto>(reserveCreditDto, HttpStatus.OK);
            }
            reserveCreditDto.setAmount(-1.0);
            return new ResponseEntity<ReserveCreditDto>(reserveCreditDto, HttpStatus.METHOD_NOT_ALLOWED);
        }else{
            reserveCreditDto.setAmount(-1.0);
            return new ResponseEntity<ReserveCreditDto>(reserveCreditDto, HttpStatus.NOT_FOUND);
        }
    }

}
