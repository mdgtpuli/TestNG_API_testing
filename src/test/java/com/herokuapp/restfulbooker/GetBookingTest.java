package com.herokuapp.restfulbooker;


import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class GetBookingTest {

    @Test
    public void getBookingById() {
        String bookingId = "2";
        String expectedName = "Eric";
        String expectedSurname = "Ericsson";
        String URL = "https://restful-booker.herokuapp.com/booking/" + bookingId;

        //get booking
        //use from 1-10 for the test
        Response response1 = RestAssured.get(URL);
        response1.print();

        //Verifications: 200
        Assert.assertEquals(response1.getStatusCode(), 200,
                "Status code of the response is expected to be 200");

        // Verification - correct information : first name and last name
        Assert.assertEquals(response1.jsonPath().get("firstname"), expectedName,
                "Name does not match the expected value: " + expectedName);
        Assert.assertEquals(response1.jsonPath().get("lastname"), expectedSurname,
                "Surname does not match the expected value: " + expectedSurname);
    }
}

