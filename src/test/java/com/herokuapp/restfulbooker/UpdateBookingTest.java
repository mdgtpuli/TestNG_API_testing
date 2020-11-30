package com.herokuapp.restfulbooker;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


public class UpdateBookingTest extends BaseTest{

    @Test
    public void updateBookingTest(){
        //expected values
        String expectedFirstName= "Pepa";
        String expectedLastName= "GT";
        int expectedTotalPrice = 150;
        boolean expectedDeposit = true;
        String expectedCheckin = "2020-11-30";
        String expectedCheckout = "2021-01-04";
        String expectedAdditionalNeeds = "Breakfast";
        String newName = "Juanma";
        int newPrice = 133;
        boolean newDeposit = false;

        //Create booking
        Response responseCreate = createBooking(expectedFirstName, expectedLastName, expectedTotalPrice, expectedDeposit,
                expectedCheckin, expectedCheckout, expectedAdditionalNeeds );
        responseCreate.print();

        //Get booking id
        int bookingid = responseCreate.jsonPath().getInt("bookingid");

        //Create json body
        //Create json body
        JSONObject body = new JSONObject();
        body.put("firstname", newName);
        body.put("lastname", expectedLastName);
        body.put("totalprice", newPrice);
        body.put("depositpaid", newDeposit);

        JSONObject bookingdates = new JSONObject();
        bookingdates.put("checkin", expectedCheckin);
        bookingdates.put("checkout", expectedCheckout);

        body.put("bookingdates", bookingdates);
        body.put("additionalneeds", expectedAdditionalNeeds);

        //Update booking
        Response response = RestAssured.given(request)
                .auth().preemptive().basic("admin", "password123")
                .contentType(ContentType.JSON).body(body.toString())
                .put("/booking/" + bookingid);

        response.print();


        //Verifications
        Assert.assertEquals(response.getStatusCode(), 200, "Status code is expected to be 200");

        SoftAssert soft = new SoftAssert();
        soft.assertEquals(response.jsonPath().get("firstname"), newName,
                "First name was not as expected");

        soft.assertEquals(response.jsonPath().get("lastname"), expectedLastName,
                "Last name was not as expected");

        soft.assertEquals(response.jsonPath().getInt("totalprice"), newPrice,
                "Total price  was not as expected");

        soft.assertEquals(response.jsonPath().getBoolean("depositpaid"), newDeposit,
                "Deposit value does not match the expected value" );

        soft.assertEquals(response.jsonPath().get("bookingdates.checkin"), expectedCheckin,
                "Checkin was not as expected");

        soft.assertEquals(response.jsonPath().get("bookingdates.checkout"), expectedCheckout,
                "Checkout was not as expected");

        soft.assertEquals(response.jsonPath().get("additionalneeds"), expectedAdditionalNeeds,
                "additionalneeds was not as expected");

        soft.assertAll();

    }
}