package es.codeurjc.daw.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.codeurjc.daw.services.ProductService;
import es.codeurjc.daw.model.Product;
import es.codeurjc.daw.model.UpdateProductDto;

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

	@PutMapping("/product/{id}")
	public ResponseEntity<UpdateProductDto> getUpdateProduct(@PathVariable Long id, @RequestBody UpdateProductDto updateProductDto) {

		Optional<Product> product = this.productService.get(id);
		if(product.isPresent()){
			if (this.productService.hasEnoughtStock(product.get(), updateProductDto.getUnits())){
				this.productService.update(product.get(), updateProductDto.getUnits());
				double price = this.productService.getAmount(id, updateProductDto.getUnits());
				updateProductDto.setPrice(price);
				return new ResponseEntity<>(updateProductDto, HttpStatus.CREATED);
			}else{
				return new ResponseEntity<>(updateProductDto, HttpStatus.METHOD_NOT_ALLOWED);
			}
		}
		return new ResponseEntity<>(updateProductDto, HttpStatus.NOT_FOUND);
	}

	@PostMapping("/product")
	public ResponseEntity<Product> newProduct(@RequestBody Product product) {
		this.productService.addProduct(product);
		return new ResponseEntity<>(product, HttpStatus.CREATED);
	}

}
