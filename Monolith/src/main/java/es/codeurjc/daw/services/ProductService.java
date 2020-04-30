package es.codeurjc.daw.services;

import java.util.List;
import java.util.Optional;

import java.lang.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.codeurjc.daw.model.Product;
import es.codeurjc.daw.repositories.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAll() {
        return this.productRepository.findAll();
    }

    public Optional<Product> get(Long id) {
        return this.productRepository.findById(id);
    }

    public void addProduct(Product product){
        this.productRepository.save(product);
    }

	public double getAmount(Product product, int units) {
        return product.getPrice() * units;
    }
    
    public void update(Product product, int units){
        if(units < 0){
            product.setStock(product.getStock()- Math.abs(units));
        }else{
            product.setStock(product.getStock() + units);
        }
        this.productRepository.save(product);
    }

	public boolean hasEnoughtStock(Product product, int units) {
        if(units < 0){
            return this.productRepository.findById(product.getId()).get().getStock() >= Math.abs(units);
        }else{
            return true;
        }
		
	}
}