package es.codeurjc.daw.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.codeurjc.daw.model.Product;
import es.codeurjc.daw.repositories.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public List<Product> getAll(){
        return this.productRepository.findAll();
    }

    public Product get(Long id){
        return this.productRepository.findById(id).get();
    }

    public void addProduct(Product product){
        this.productRepository.save(product);
    }

	public Long getAmount(Long productId, int units) {
		return null;
	}

	public boolean hasEnoughtStock(Product product, int units) {
		return false;
	}
}