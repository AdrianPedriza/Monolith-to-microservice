package es.codeurjc.daw.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.daw.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}