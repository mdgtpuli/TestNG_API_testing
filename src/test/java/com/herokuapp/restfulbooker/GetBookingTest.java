package com.herokuapp.restfulbooker;


import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


public class GetBookingTest extends BaseTest{

    //To avoid the test failing due to changes in the data, we are creating our own record
    // before getting the details using the endpoint

    @Test
    public void getBookingById() {

        //expected values
        String expectedFirstName= "Puli";
        String expectedLastName= "GT";
        Integer expectedTotalPrice = 120;
        boolean expectedDeposit = true;
        String expectedCheckin = "2020-11-28";
        String expectedCheckout = "2020-11-30";
        String expectedAdditionalNeeds = "spa";

        Response response = createBooking(expectedFirstName, expectedLastName, expectedTotalPrice, expectedDeposit,
                expectedCheckin, expectedCheckout, expectedAdditionalNeeds );
        response.print();

        String bookingId = response.jsonPath().get("bookingid").toString();
        String URL = "https://restful-booker.herokuapp.com/booking/" + bookingId;
        System.out.println("URL: " + URL);

        //get booking
        //use from 1-10 for the test
        Response response1 = RestAssured.get(URL);
        response1.print();

        //Verifications: 200
        SoftAssert soft = new SoftAssert();
        soft.assertEquals(response1.getStatusCode(), 200,
                "Status code of the response is expected to be 200");

        // Verification - correct information : first name and last name
        soft.assertEquals(response1.jsonPath().get("firstname"), expectedFirstName,
                "Name does not match the expected value: " + expectedLastName);
        soft.assertEquals(response1.jsonPath().get("lastname"), expectedLastName,
                "Surname does not match the expected value: " + expectedLastName);
        soft.assertEquals(response1.jsonPath().get("totalprice"), expectedTotalPrice,
                "Surname does not match the expected value: " + expectedTotalPrice);

        soft.assertAll();
    }
}

