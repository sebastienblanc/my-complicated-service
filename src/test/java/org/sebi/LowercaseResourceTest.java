package org.sebi;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

@QuarkusTest
class LowercaseResourceTest {

    @Test
    void testConvertToLowercaseByPath() {
        given()
            .when().get("/api/lowercase/HELLO")
            .then()
                .statusCode(200)
                .body("result", is("hello"))
                .body("message", is("Success"));
    }

    @Test
    void testConvertToLowercaseByPathWithMixedCase() {
        given()
            .when().get("/api/lowercase/HeLLo WoRLd")
            .then()
                .statusCode(200)
                .body("result", is("hello world"))
                .body("message", is("Success"));
    }

    @Test
    void testConvertToLowercaseByPathWithSpecialCharacters() {
        given()
            .when().get("/api/lowercase/Hello@123!")
            .then()
                .statusCode(200)
                .body("result", is("hello@123!"))
                .body("message", is("Success"));
    }

    @Test
    void testConvertToLowercaseByQuery() {
        given()
            .queryParam("text", "HELLO")
            .when().get("/api/lowercase")
            .then()
                .statusCode(200)
                .body("result", is("hello"))
                .body("message", is("Success"));
    }

    @Test
    void testConvertToLowercaseByQueryWithMixedCase() {
        given()
            .queryParam("text", "HeLLo WoRLd")
            .when().get("/api/lowercase")
            .then()
                .statusCode(200)
                .body("result", is("hello world"))
                .body("message", is("Success"));
    }

    @Test
    void testConvertToLowercaseByBody() {
        given()
            .contentType("application/json")
            .body("{\"text\": \"HELLO\"}")
            .when().post("/api/lowercase")
            .then()
                .statusCode(200)
                .body("result", is("hello"))
                .body("message", is("Success"));
    }

    @Test
    void testConvertToLowercaseByBodyWithMixedCase() {
        given()
            .contentType("application/json")
            .body("{\"text\": \"HeLLo WoRLd\"}")
            .when().post("/api/lowercase")
            .then()
                .statusCode(200)
                .body("result", is("hello world"))
                .body("message", is("Success"));
    }

    @Test
    void testConvertToLowercaseByQueryEmptyText() {
        given()
            .queryParam("text", "")
            .when().get("/api/lowercase")
            .then()
                .statusCode(400)
                .body("result", is((String) null))
                .body("message", is("Input text cannot be null or empty"));
    }

    @Test
    void testConvertToLowercaseByQueryMissingText() {
        given()
            .when().get("/api/lowercase")
            .then()
                .statusCode(400)
                .body("result", is((String) null))
                .body("message", is("Input text cannot be null or empty"));
    }

    @Test
    void testConvertToLowercaseByBodyEmptyText() {
        given()
            .contentType("application/json")
            .body("{\"text\": \"\"}")
            .when().post("/api/lowercase")
            .then()
                .statusCode(400)
                .body("result", is((String) null))
                .body("message", is("Input text cannot be null or empty"));
    }

    @Test
    void testConvertToLowercaseByBodyNullText() {
        given()
            .contentType("application/json")
            .body("{}")
            .when().post("/api/lowercase")
            .then()
                .statusCode(400)
                .body("result", is((String) null))
                .body("message", is("Input text cannot be null or empty"));
    }

    @Test
    void testConvertToLowercaseByBodyInvalidJson() {
        given()
            .contentType("application/json")
            .body("invalid json")
            .when().post("/api/lowercase")
            .then()
                .statusCode(400);  // Should return 400 for invalid JSON
    }
}