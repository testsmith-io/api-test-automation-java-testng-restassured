package io.testsmith.tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;

import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

public class BrandsTest extends TestBase {

  @Test
  public void shouldRetrieveAtLeastTwoBrands() {
    given()
        .when()
        .get("/brands")
        .then()
        .log()
        .ifValidationFails()
        .statusCode(HttpStatus.SC_OK)
        .and()
        .body("data.size()", greaterThanOrEqualTo(2));
  }
}
