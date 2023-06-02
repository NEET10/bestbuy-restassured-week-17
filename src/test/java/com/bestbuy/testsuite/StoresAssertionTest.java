package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;

public class StoresAssertionTest{
    static ValidatableResponse response;
    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI="http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/stores")
                .then().statusCode(200);
    }
    // 1. Verify the if the total is equal to 1561
    @Test
    public void test001() {
        response.body("total", equalTo(1561));
    }

    //2. Verify the if the stores of limit is equal to 10
    @Test
    public void test002() {
        response.body("limit", equalTo(10));
    }

    //3. Check the single ‘Name’ in the Array list (Inver Grove Heights)
    @Test
    public void test003() {
        response.body("data[1].name", equalTo("Inver Grove Heights"));
    }

    // 4. Check the multiple ‘Names’ in the ArrayList (Roseville, Burnsville, Maplewood)
    @Test
    public void test004() {
        response.body("data.name", hasItems("Roseville", "Burnsville", "Maplewood"));
    }

    //5. Verify the storeId=7 inside store services of the third store of second services($.data[2].services[1].storeservices.storeId)
    @Test
    public void test005() {
        response.body("data[2].services[1].storeservices.storeId", equalTo(7));
    }

    //6. Check hash map values ‘createdAt’ inside storeservices map where store name = Roseville($.data[2].services[*].storeservices.createdAt)
    @Test
    public void test006() {
        List<HashMap<String, ?>> values = response.extract().path("data[2].services.storeservices.createdAt");
        System.out.println(values);
    }

    //7. Verify the state = MN of forth store($.data[3].state)
    @Test
    public void test007() {
        response.body("data[3].state", equalTo("MN"));
    }

    //8. Verify the store name = Rochester of 9th store($.data[8].name)
    @Test
    public void test008() {
        response.body("data[8].name", equalTo("Rochester"));
    }

    //9. Verify the storeId = 11 for the 6th store($.data[5].id)
    @Test
    public void test009() {
        response.body("data[5].id", equalTo(11));
    }

    // 10. Verify the serviceId = 4 for the 7th store of forth service($.data[6].services[3].storeServices.serviceId)
    @Test
    public void test0010() {
        response.body("data[6].services[3].storeServices.serviceId", equalTo(4));
    }

}
