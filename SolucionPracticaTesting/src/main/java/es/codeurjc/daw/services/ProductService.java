package es.codeurjc.daw.services;

import java.util.List;
import java.util.Optional;

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

	public double getAmount(Long productId, int units) {
        Optional<Product> product = this.productRepository.findById(productId);
        if (product.isPresent()){
            return product.get().getPrice() * units;
        }
		return 0;
	}

	public boolean hasEnoughtStock(Product product, int units) {
		return this.productRepository.findById(product.getId()).get().getStock() >= units;
	}
}