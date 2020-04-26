package es.codeurjc.daw;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import es.codeurjc.daw.model.Customer;
import es.codeurjc.daw.model.Order;
import es.codeurjc.daw.model.Product;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)

public class OrderTest {

    @LocalServerPort
    int port;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
    }

    @Autowired
    private ObjectMapper objectMapper;

    @Test
	@DisplayName("Create an order and verify that is created.")
	public void createOrderTest() throws Exception{

        Customer customer = new Customer("Adrian", 100);
        Customer createdCustomer = createCustomer(customer);
        validateUserCreated(customer, createdCustomer);

        Product product = new Product("Coca-Cola", 10, 2.5);
        Product createdProduct = createProduct(product);
        validateProduct(product, createdProduct);

        Order order = new Order(createdCustomer.getId(),createdProduct.getId(), 5);
        Order createdOrder = createOrder(order);
        validateOrder(order, createdOrder);
    }

    private Order createOrder(Order order) throws JsonProcessingException {
        return given().
                port(port).
            request()
                .body(objectMapper.writeValueAsString(order))
                .contentType(ContentType.JSON).
        when().
            post("/api/order/").
        then().
            assertThat().
                statusCode(201)
            .extract().as(Order.class);
    }


    private void validateOrder(Order order, Order createdOrder) {
        given().
            port(port).
        when().
            get("/api/order/{id}", createdOrder.getId()).
        then().
            assertThat().
                statusCode(200).
                and().
                    body("customerId", equalTo(Integer.valueOf(order.getCustomerId().toString()))).
                and().
                    body("productId", equalTo(Integer.valueOf(order.getProductId().toString()))).
                and().
                    body("units", equalTo(order.getUnits()));
    }

    private Customer createCustomer(Customer customer) throws JsonProcessingException {
        return given().
                port(port).
            request()
                .body(objectMapper.writeValueAsString(customer))
                .contentType(ContentType.JSON).
        when().
            post("/api/customer/").
        then().
            assertThat().
                statusCode(201).
                body("name", equalTo(customer.getName()))
            .extract().as(Customer.class);
    }


    private void validateUserCreated(Customer customer, Customer createdCustomer) {
        given().
            port(port).
        when().
            get("/api/customer/{id}", createdCustomer.getId()).
        then().
             assertThat().
                statusCode(200).
                and().
                    body("name", equalTo(customer.getName())).
                and().
                    body("credit", equalTo((float) customer.getCredit()));
    }

    private Product createProduct(Product product) throws JsonProcessingException {
        return given().
                port(port).
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
        given().
            port(port).
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