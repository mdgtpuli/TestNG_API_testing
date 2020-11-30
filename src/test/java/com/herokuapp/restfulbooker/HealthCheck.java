package com.herokuapp.restfulbooker;


import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class HealthCheck extends BaseTest{

    @Test
    public void healthTestCheck(){

                given().spec(request)
                .when()
                    .get("/ping")
                .then()
                    .assertThat()
                        .statusCode(201);
    }
}