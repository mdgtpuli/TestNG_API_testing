package com.herokuapp.restfulbooker;

import io.restassured.response.Response;
import org.json.JSONObject;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class BaseTest {
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
        Response response = RestAssured.given().contentType(ContentType.JSON).body(body.toString())
                .post("https://restful-booker.herokuapp.com/booking");

        return response;
    }
}
