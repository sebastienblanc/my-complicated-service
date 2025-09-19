package org.sebi;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class HelloResourceTest {
    @Test
    void testHelloEndpoint() {
        given()
          .when().get("/hello")
          .then()
             .statusCode(200)
             .body(is("Hello from Quarkus REST"));
    }

    @Test
    void testReverseEndpoint() {
        given()
          .body("hello")
          .when().post("/hello/reverse")
          .then()
             .statusCode(200)
             .body(is("olleh"));
    }

    @Test
    void testReverseEndpointEmptyString() {
        given()
          .body("")
          .when().post("/hello/reverse")
          .then()
             .statusCode(200)
             .body(is(""));
    }

    @Test
    void testReverseEndpointWithNumbers() {
        given()
          .body("123456")
          .when().post("/hello/reverse")
          .then()
             .statusCode(200)
             .body(is("654321"));
    }

}