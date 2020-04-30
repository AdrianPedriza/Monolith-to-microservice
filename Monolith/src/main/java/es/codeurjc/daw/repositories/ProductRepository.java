package es.codeurjc.daw.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.daw.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}