package es.codeurjc.daw.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.daw.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}