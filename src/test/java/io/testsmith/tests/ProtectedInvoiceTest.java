package io.testsmith.tests;

import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ProtectedInvoiceTest extends TestBase {
    private String token;

    @BeforeMethod
    public void login() {
        String payload = """
                {
                    "email": "customer@practicesoftwaretesting.com",
                    "password": "welcome01"
                }
                """;

        token = given()
                    .log().ifValidationFails()
                    .contentType(ContentType.JSON)
                    .body(payload)
                .when()
                    .post("/users/login")
                .then()
                    .log().ifValidationFails()
                    .statusCode(HttpStatus.SC_OK)
                    .and()
                    .extract().path("access_token");
    }

    @Test
    public void shouldRetrieveInvoicesWithValidToken() {
        given()
               .log().ifValidationFails()
               .auth().oauth2(token)
        .when()
               .get("/invoices")
        .then()
               .log().ifValidationFails()
               .statusCode(HttpStatus.SC_OK)
               .and()
               .body("data.size()", greaterThanOrEqualTo(15));
    }
}

