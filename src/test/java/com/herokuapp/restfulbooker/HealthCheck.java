package com.herokuapp.restfulbooker;


import io.restassured.RestAssured;
import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
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

    @Test
    public void HeadersAndCookiesTest(){
        //We can add headers directly to the request
        Header extraHeader = new Header("extra header name", "extra header value");
        request.header(extraHeader);

        //We can add cookies directly to the request
        Cookie newCookie = new Cookie.Builder("new cookie name", "new cookie value").build();
        request.cookie(newCookie);


        Response response = RestAssured.given(request)
                //add cookie to request
                .cookie("Test cookie name", "test cookie value")
                //add header to request
                .header("Test header name", "Test header value")
                //use log to check headers and cookies in console
                .log().all()
                .get("/ping");

        //Get headers
        Headers headers = response.getHeaders();
        System.out.println("Headers: " + headers);

        //Get specific header from headers (name and value)
        Header serverHeader1 = headers.get("Server");
        System.out.println("Header name: " + serverHeader1.getName());
        System.out.println("Header value: " + serverHeader1.getValue());

        //Get specific header form response (only value)
        System.out.println("Server value from response: " + response.getHeader("Server"));


        //Get cookies
        Cookies cookies = response.getDetailedCookies();
        System.out.println("Cookies: " + cookies);
    }
}