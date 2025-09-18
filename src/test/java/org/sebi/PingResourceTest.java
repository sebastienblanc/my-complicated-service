package org.sebi;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

@QuarkusTest
class PingResourceTest {

    @Test
    void testPingEndpoint() {
        given()
          .when().get("/ping")
          .then()
             .statusCode(200)
             .contentType("application/json")
             .body("status", is("ok"))
             .body("timestamp", notNullValue());
    }

    @Test
    void testPingEndpointMultipleCalls() {
        // Verify endpoint is consistent and lightweight
        for (int i = 0; i < 3; i++) {
            given()
              .when().get("/ping")
              .then()
                 .statusCode(200)
                 .contentType("application/json")
                 .body("status", is("ok"))
                 .body("timestamp", notNullValue());
        }
    }
}