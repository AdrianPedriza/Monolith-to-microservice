package es.codeurjc.daw;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import es.codeurjc.daw.model.Product;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ProductTest {

    @LocalServerPort
    int port;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
    }

    @Autowired
    private ObjectMapper objectMapper;

    @Test
	@DisplayName("Create product and verify that is created.")
	public void createProductTest() throws Exception{

		Product product = new Product("Coca-Cola", 10, 2.5);
    	
        Product createdProduct = 
            createProduct(product);

        validateProduct(product, createdProduct);
    }

    private Product createProduct(Product product) throws JsonProcessingException {
        return given().
            request()
                .body(objectMapper.writeValueAsString(product))
                .contentType(ContentType.JSON).
        when().
            post("/api/product/").
        then().
            assertThat().
            statusCode(201).
            body("name", equalTo(product.getName()))
            .extract().as(Product.class);
    }


    private void validateProduct(Product product, Product createdProduct) {
        when().
            get("/api/product/{id}", createdProduct.getId()).
        then().
             assertThat().
                statusCode(200).
                and().
                    body("name", equalTo(product.getName())).
                and().
                    body("price", equalTo((float) product.getPrice())).
                and(). 
                    body("stock", equalTo(product.getStock()));
    }
}