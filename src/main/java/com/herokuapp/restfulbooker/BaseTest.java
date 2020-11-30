package com.herokuapp.restfulbooker;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeMethod;


public class BaseTest {
    protected RequestSpecification request;

    @BeforeMethod
    public void setUp(){
        request = new RequestSpecBuilder()
                .setBaseUri("https://restful-booker.herokuapp.com")
                .build();


    }

    protected Response createBooking(String expectedFirstName, String expectedLastName, int expectedTotalPrice,
                                     boolean expectedDeposit, String expectedCheckin, String expectedCheckout,
                                     String expectedAdditionalNeeds){

        //Create json body
        JSONObject body = new JSONObject();
        body.put("firstname", expectedFirstName);
        body.put("lastname", expectedLastName);
        body.put("totalprice", expectedTotalPrice);
        body.put("depositpaid", expectedDeposit);

        JSONObject bookingdates = new JSONObject();
        bookingdates.put("checkin", expectedCheckin);
        bookingdates.put("checkout", expectedCheckout);

        body.put("bookingdates", bookingdates);
        body.put("additionalneeds", expectedAdditionalNeeds);

        //Post response
        Response response = RestAssured.given(request).contentType(ContentType.JSON).body(body.toString())
                .post("/booking");

        return response;
    }
}
