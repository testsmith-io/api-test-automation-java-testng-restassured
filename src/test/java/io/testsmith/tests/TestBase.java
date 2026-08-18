package io.testsmith.tests;

import io.restassured.RestAssured;

public abstract class TestBase {

  public TestBase() {
    RestAssured.baseURI = "https://api.practicesoftwaretesting.com";
  }
}
