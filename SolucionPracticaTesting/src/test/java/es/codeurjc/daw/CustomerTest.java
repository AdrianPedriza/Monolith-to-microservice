package es.codeurjc.daw;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.LocalServerPort;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import es.codeurjc.daw.model.Customer;

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

        long credit = 100;

		Customer customer = new Customer("Adrian", credit);
    	
        Customer createdCustomer = 
            given().
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

        when().
            get("/api/customer/{id}", createdCustomer.getId()).
        then().
             assertThat().
             statusCode(200).
             body("name", equalTo(customer.getName())).
             and().body("credit", equalTo(customer.getCredit()));
        
    }
}