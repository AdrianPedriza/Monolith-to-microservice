package es.codeurjc.daw.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.codeurjc.daw.services.ProductService;
import es.codeurjc.daw.model.Product;

@RestController
@RequestMapping("/api")
public class ProductController {

	@Autowired
	private ProductService productService;


	@GetMapping("/product")
	public ResponseEntity<List<Product>> getAllProducts() {
		return new ResponseEntity<>(this.productService.getAll(), HttpStatus.OK);
	}

	@GetMapping("/product/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable Long id) {
		Optional<Product> product = this.productService.get(id);
		if (product.isPresent()) {
            return new ResponseEntity<>(product.get(), HttpStatus.OK);
		}
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PostMapping("/product")
	public ResponseEntity<Product> newProduct(@RequestBody Product product) {
		this.productService.addProduct(product);
		return new ResponseEntity<>(product, HttpStatus.CREATED);
	}

}
