package es.codeurjc.daw;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

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
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import es.codeurjc.daw.model.Customer;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CustomerTest {

    @LocalServerPort
    int port;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
    }

    @Autowired
    private ObjectMapper objectMapper;

    @Test
	@DisplayName("Create customer and verify that is created.")
	public void createCustomerTest() throws Exception{

		Customer customer = new Customer("Adrian", 100.00);
    	
        Customer createdCustomer = createCustomer(customer);

        validateUserCreated(customer, createdCustomer);
    }

    @Test
	@DisplayName("Add credit to a customer and verify that is updated.")
	public void addCreditCustomerTest() throws Exception{
        
        Customer customer = new Customer("Adrian", 100.00);
        double newAmount = 50;    

        Customer createdCustomer = 
            createCustomer(customer);
        
        validateUserCreated(customer, createdCustomer);

        Customer modifiedCustomer = addCreditToCustomer(createdCustomer, newAmount);

        assertThat(modifiedCustomer.getCredit(), equalTo(customer.getCredit() + newAmount));
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

    private Customer addCreditToCustomer(Customer createdCustomer, double credit) throws JsonProcessingException {
        return given().port(port).
            request()
                .body(objectMapper.writeValueAsString(new Customer(credit)))
                .contentType(ContentType.JSON).
        when().
            put("/api/customer/{id}/credit", createdCustomer.getId()).
        then().
            assertThat().
                statusCode(200).
                body("name", equalTo(createdCustomer.getName()))
            .extract().as(Customer.class);
    }

}