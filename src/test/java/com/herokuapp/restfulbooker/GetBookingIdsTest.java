package com.herokuapp.restfulbooker;


import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class GetBookingIdsTest extends BaseTest {

    @Test
    public void getBookingIdsWithoutFilter() {

        //Get response with booking ids
        Response response = RestAssured
                .given(request)
                .get("/booking");

        response.print();

        //Verify that the response is 200
        Assert.assertEquals(response.getStatusCode(), 200, "Status SHOULD be 200.");

        //Verify that there is at least 1 booking id in the response
        List<Integer> bookingIds = response.jsonPath().getList("bookingId");
        Assert.assertFalse(bookingIds.isEmpty(), "List of booking ids should NOT be empty");
    }

    @Test
    public void getBookingIdsWithFilter(){
        //Create booking
        Response responseCreate = createBooking("Puli", "La maravillosa", 155, false,
                "2020-11-30", "2020-12-03", "champagne" );
        responseCreate.print();


        //add query parameters
        request.queryParam("firstname","Puli" );
        request.queryParam("lastname", "La maravillosa");

        //Get response with booking ids filtering by name
        Response response = RestAssured
                .given(request)
                .get("/booking");

        response.print();

        //Verify that the response is 200
        Assert.assertEquals(response.getStatusCode(), 200, "Status SHOULD be 200.");

        //Verify that there is at least 1 booking id in the response
        List<Integer> bookingIds = response.jsonPath().getList("bookingId");
        Assert.assertFalse(bookingIds.isEmpty(), "List of booking ids should NOT be empty");
    }

}

