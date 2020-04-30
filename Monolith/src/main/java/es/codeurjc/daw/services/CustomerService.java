package es.codeurjc.daw.services;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.codeurjc.daw.model.Customer;
import es.codeurjc.daw.repositories.CustomerRepository;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public void addOrder(Customer customer) {
        this.customerRepository.save(customer);
    }

    public boolean isEnoguthMoney(Customer customer, double minimun) {
        if(minimun < 0){
            if(customer.getCredit() >= Math.abs(minimun)){
                return true;
            }else{
                return false;
            }
        }else{
            return true;
        }
    }

    public Optional<Customer> get(Long id) {
        return this.customerRepository.findById(id);
    }

	public double getCredit(Customer customer) {
		return customer.getCredit();
    }
    
    public void updateCredit(Customer customer, double amount){
        if(amount > 0){
            customer.setCredit(customer.getCredit() + amount);
        }else{
            customer.setCredit(customer.getCredit() - Math.abs(amount));
        }
        this.customerRepository.save(customer);

    }

	public void add(Customer customer) {
        this.customerRepository.save(customer);
	}

}