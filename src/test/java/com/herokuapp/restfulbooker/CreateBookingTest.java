package com.herokuapp.restfulbooker;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;



public class CreateBookingTest  extends BaseTest{

    @Test(enabled=false)
    public void createBookingTest(){
        //expected values
        String expectedFirstName= "Puli";
        String expectedLastName= "GT";
        int expectedTotalPrice = 120;
        boolean expectedDeposit = true;
        String expectedCheckin = "2020-11-28";
        String expectedCheckout = "2020-11-30";
        String expectedAdditionalNeeds = "spa";

        Response response = createBooking(expectedFirstName, expectedLastName, expectedTotalPrice, expectedDeposit,
                expectedCheckin, expectedCheckout, expectedAdditionalNeeds );
        response.print();

    //Verifications
        Assert.assertEquals(response.getStatusCode(), 200, "Status code is expected to be 200");

        SoftAssert soft = new SoftAssert();
        soft.assertEquals(response.jsonPath().get("booking.firstname"), expectedFirstName,
                "First name was not as expected");

        soft.assertEquals(response.jsonPath().get("booking.lastname"), expectedLastName,
                "Last name was not as expected");

        soft.assertEquals(response.jsonPath().getInt("booking.totalprice"), expectedTotalPrice,
                "Total price  was not as expected");

        soft.assertEquals(response.jsonPath().getBoolean("booking.depositpaid"), expectedDeposit,
                "Deposit value does not match the expected value" );

        soft.assertEquals(response.jsonPath().get("booking.bookingdates.checkin"), expectedCheckin,
                "Checkin was not as expected");

        soft.assertEquals(response.jsonPath().get("booking.bookingdates.checkout"), expectedCheckout,
                "Checkout was not as expected");

        soft.assertEquals(response.jsonPath().get("booking.additionalneeds"), expectedAdditionalNeeds,
                "additionalneeds was not as expected");

        soft.assertAll();

    }

    @Test
    public void createBookingTest2(){

        //expected values
        String expectedFirstName= "Puli";
        String expectedLastName= "GT";
        int expectedTotalPrice = 120;
        boolean expectedDeposit = true;
        String expectedCheckin = "2020-11-28";
        String expectedCheckout = "2020-11-30";
        String expectedAdditionalNeeds = "spa";

      //Create body -POJO
        Bookingdates bookingdates = new Bookingdates(expectedCheckin, expectedCheckout);
        Booking body = new Booking(expectedFirstName, expectedLastName, expectedTotalPrice,
                expectedDeposit, bookingdates, expectedAdditionalNeeds);

        //Post response
        Response response = RestAssured.given(request).contentType(ContentType.JSON).body(body)
                .post("/booking");
        response.print();

        //Map response to BookingId object
        BookingId bookingIdResponse = response.as(BookingId.class);

        //Verifications - Assert that both objects are equal
         System.out.println("Request: " + body.toString());
         System.out.println("Response: " + bookingIdResponse.getBooking().toString());

         Assert.assertEquals(bookingIdResponse.getBooking().toString(), body.toString());

    }
}