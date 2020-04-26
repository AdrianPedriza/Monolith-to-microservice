package es.codeurjc.daw.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.codeurjc.daw.model.Customer;
import es.codeurjc.daw.repositories.CustomerRepository;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void addOrder(Customer customer) {
        this.customerRepository.save(customer);
    }

    public boolean isEnoguthMoney(Long customerId) {
        return false;
    }

    public Customer get(Long id) {
        return this.customerRepository.findById(id).get();
    }

	public double getCredit(Customer customer) {
		return customer.getCredit();
	}

	public void removeCredit(Customer customer, Long orderAmount) {
        customer.setCredit(customer.getCredit() + orderAmount);
        this.customerRepository.save(customer);
	}

	public void addCredit(Customer customer, double money) {
        customer.setCredit(customer.getCredit() - money);
        this.customerRepository.save(customer);
	}

	public void add(Customer customer) {
        this.customerRepository.save(customer);
	}

}