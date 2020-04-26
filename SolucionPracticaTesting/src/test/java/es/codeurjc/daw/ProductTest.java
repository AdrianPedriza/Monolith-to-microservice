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

import es.codeurjc.daw.model.Product;

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

		// Product product = new Product("Coca-Cola", 10, 2.50);
    	
        // Product createdProduct = 
        //     given().
        //         request()
        //             .body(objectMapper.writeValueAsString(product))
        //             .contentType(ContentType.JSON).
        //     when().
        //         post("/api/product/").
        //     then().
        //         assertThat().
        //         statusCode(201).
        //         body("name", equalTo(product.getName()))
        //         .extract().as(Product.class);

        // when().
        //     get("/api/product/{id}", createdProduct.getId()).
        // then().
        //      assertThat().
        //      statusCode(200).
        //      body("name", equalTo(product.getName())).
        //      and().
        //      body("price", equalTo(product.getPrice())).
        //      and().
        //      body("stock", equalTo(product.getStock()));
    }

    // @Test
	// @DisplayName("Añadir un comentario a un post y verificar que se añade el comentario")
	// public void createCommentTest() throws Exception {

	// 	// CREAMOS UN NUEVO POST

	// 	Post post = new Post("Mi titulo", "Mi contenido");
    	
    //     Post createdPost = 
    //         given().
    //             request()
    //                 .body(objectMapper.writeValueAsString(post))
    //                 .contentType(ContentType.JSON).
    //         when().
    //             post("/api/post/").
    //         then()
    //             .extract().as(Post.class);
        
    //     // CREAMOS UN NUEVO COMENTARIO

    //     Comment comment = new Comment("Juan", "Buen post");

    //     given().
    //         request()
    //                 .body(objectMapper.writeValueAsString(comment))
    //                 .contentType(ContentType.JSON).
    //     when().
    //         post("/api/post/{postId}/comment", createdPost.getId()).
    //     then().
    //         assertThat().
    //         statusCode(201).
    //         body("author", equalTo(comment.getAuthor())).
    //         body("message", equalTo(comment.getMessage()));

        
    //     // COMPROBAMOS QUE EL COMENTARIO EXISTE

    //     when().
    //         get("/api/post/{id}", createdPost.getId()).
    //     then().
    //          assertThat().
    //          statusCode(200).
    //          body("comments[0].author", equalTo(comment.getAuthor())).
    //          body("comments[0].message", equalTo(comment.getMessage()));
    
    // }

    // @Test
	// @DisplayName("Borrar un comentario de un post y verificar que no aparece el comentario")
	// public void deleteCommentTest() throws Exception {

    //     // CREAMOS UN NUEVO POST

	// 	Post post = new Post("Mi titulo", "Mi contenido");
    	
    //     Post createdPost = 
    //         given().
    //             request()
    //                 .body(objectMapper.writeValueAsString(post))
    //                 .contentType(ContentType.JSON).
    //         when().
    //             post("/api/post/").
    //         then()
    //             .extract().as(Post.class);
        
    //     // CREAMOS UN NUEVO COMENTARIO

    //     Comment comment = new Comment("Juan", "Buen post");

    //     Comment createdComment = given().
    //         request()
    //                 .body(objectMapper.writeValueAsString(comment))
    //                 .contentType(ContentType.JSON).
    //     when().
    //         post("/api/post/{postId}/comment", createdPost.getId()).
    //     then().
    //         assertThat().
    //         statusCode(201).
    //         body("author", equalTo(comment.getAuthor())).
    //         body("message", equalTo(comment.getMessage())).
    //         extract().as(Comment.class);

    //     // BORRAMOS EL COMENTARIO
        
    //     when().
    //          delete(
    //              "/api/post/{postId}/comment/{commentId}", 
    //              createdPost.getId(), createdComment.getId()
    //          ).
    //     then().
    //          assertThat().
    //          statusCode(204);


        
    //     // COMPROBAMOS QUE EL COMENTARIO NO EXISTE

    //     when().
    //          get("/api/post/{id}", createdPost.getId()).
    //     then().
    //          assertThat().
    //          statusCode(200).
    //          body("comments", IsEmptyCollection.empty());
    
    // }
}