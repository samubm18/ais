package es.urjc.code.daw.library.rest;



import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static io.restassured.path.json.JsonPath.from;
import static org.hamcrest.Matchers.equalTo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import io.restassured.RestAssured;
import io.restassured.response.Response;





@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)


public class RestTest {
	
	@LocalServerPort
    int port;
	
	@BeforeEach
    public void setUp() {
        RestAssured.port = port;
    }
    
    @Test
    public void bookAddTest(){
    	
        given().
        contentType("application/json")
                .body("{ \"title\" : \"ss\", \"description\":\"rr\" }").
                
        when().
        post("/api/books/").
		then().
		     statusCode(201).
		  body("title",equalTo("ss")).
          body("description",equalTo("rr"));
		     
        
        
    }
    
    
    @Test
   	public void bookGetOneTest() {
   		
   		//Given
   		Response response = given().
   			contentType("application/json")
   			.body("{ \"title\" : \"ss\", \"description\":\"rr\" }").
   		when().
   			post("/api/books/").andReturn();
   		
   		int id = from(response.getBody().asString()).get("id");
   			
   		//When
   		when()
   			.get("/api/books/{id}",id).
   		
   		//Then
   		then()
   			.statusCode(200).
   			body(
   				"id", equalTo(id),
   				"title", equalTo("ss"),
   				"description",equalTo("rr"));
   		
   	}  
    @Test
	public void bookDeleteTest() {
		
		//Given
		Response response = given().
			contentType("application/json").
			body("{\"description\":\"milk\",\"checked\":false }").
		when().
			post("/api/books/").andReturn();
		
		int id = from(response.getBody().asString()).get("id");
			
		//When
		when().
			delete("/api/books/{id}",id).

		//Then	
		then().
			statusCode(200);
		
		when()
			.get("/api/books/{id}",id).
		then()
			.statusCode(404);
	}
           
    
   
		 
			
    
		
		

   
   	
   
	
	

	
    
}


