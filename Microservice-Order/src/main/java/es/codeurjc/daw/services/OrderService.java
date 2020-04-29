package es.codeurjc.daw.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import es.codeurjc.daw.model.Order;
import es.codeurjc.daw.repositories.OrderRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getAll(){
        return this.orderRepository.findAll();
    }

    public Optional<Order> get(Long id){
        return this.orderRepository.findById(id);
    }

	public void addOrder(Order order) {
        this.orderRepository.save(order);
	}
}