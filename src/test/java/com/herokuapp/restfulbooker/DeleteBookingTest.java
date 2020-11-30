package com.herokuapp.restfulbooker;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


public class DeleteBookingTest extends BaseTest{

    @Test
    public void deleteBookingTest(){
        //expected values
        String expectedFirstName= "William";
        String expectedLastName= "Blue";
        int expectedTotalPrice = 350;
        boolean expectedDeposit = false;
        String expectedCheckin = "2020-12-03";
        String expectedCheckout = "2021-12-10";
        String expectedAdditionalNeeds = "King size bed";

        //Create booking
        Response responseCreate = createBooking(expectedFirstName, expectedLastName, expectedTotalPrice, expectedDeposit,
                expectedCheckin, expectedCheckout, expectedAdditionalNeeds );
        responseCreate.print();

        //Get booking id
        int bookingid = responseCreate.jsonPath().getInt("bookingid");

        //Delete booking
        Response deleteResponse = RestAssured
                .given()
                .auth().preemptive().basic("admin", "password123")
                .delete("https://restful-booker.herokuapp.com/booking/" + bookingid);
        deleteResponse.print();


        //Verifications
        Assert.assertEquals(deleteResponse.getStatusCode(), 201, "Status code was not 201, as expected");

        //Get inesistend bookingId
        Response getBookingId = RestAssured.given(request).get("/booking/" + bookingid);

        //Verify that the system does not find the booking
        Assert.assertEquals(getBookingId.statusCode(), 404, "The getBookingId response is not 404, as expected");


    }
}