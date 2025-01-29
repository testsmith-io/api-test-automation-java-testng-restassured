package io.testsmith.tests;

import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasKey;

public class LoginTest extends TestBase {

    @Test
    public void shouldReturnAccessTokenOnSuccessfulLogin() {
        String payload = """
                {
                    "email": "customer@practicesoftwaretesting.com",
                    "password": "welcome01"
                }
                """;

        given()
                .log().ifValidationFails()
                .contentType(ContentType.JSON)
                .body(payload)
        .when()
                .post("/users/login")
        .then()
                .log().ifValidationFails()
                .statusCode(HttpStatus.SC_OK)
                .and()
                .body("$", hasKey("access_token"));
    }
}
