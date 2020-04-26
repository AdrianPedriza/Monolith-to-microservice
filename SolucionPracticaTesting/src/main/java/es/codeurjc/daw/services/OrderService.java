package es.codeurjc.daw.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import es.codeurjc.daw.model.Order;
import es.codeurjc.daw.repositories.OrderRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    public List<Order> getAll(){
        return this.orderRepository.findAll();
    }

	public void addOrder(Order order) {
        this.orderRepository.save(order);
	}
}