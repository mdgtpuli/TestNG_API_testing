package com.herokuapp.restfulbooker;


import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class GetBookingIdsTest {

    @Test
    public void getBookingIdsWithoutFilter() {
        //Get response with booking ids
        Response response = RestAssured.get("https://restful-booker.herokuapp.com/booking");
        response.print();

        //Verify that the response is 200
        Assert.assertEquals(response.getStatusCode(), 200, "Status SHOULD be 200.");

        //Verify that there is at least 1 booking id in the response
        List<Integer> bookingIds = response.jsonPath().getList("bookingId");
        Assert.assertFalse(bookingIds.isEmpty(), "List of booking ids should NOT be empty");
    }
}

//get booking
//use from 1-10 for the test
//Verifications: 200, correct information : first name and last name