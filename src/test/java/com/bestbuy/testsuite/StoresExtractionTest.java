package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class StoresExtractionTest {
    static ValidatableResponse response;
    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/stores")
                .then().statusCode(200);}
        //1. Extract the limit
        @Test
        public void test01 () {
            int limit = response.extract().path("limit");

            System.out.println("------------------StartingTest---------------------------");
            System.out.println("The value of limit is : " + limit);
            System.out.println("------------------End of Test---------------------------");
        }

        //2. Extract the total
        @Test
        public void test02 () {
            int total = response.extract().path("total");

            System.out.println("------------------StartingTest---------------------------");
            System.out.println("The value of total is : " + total);
            System.out.println("------------------End of Test---------------------------");
        }

        //3. Extract the name of 5th store
        @Test
        public void test03 () {
            String name = response.extract().path("data[4].name");
            System.out.println("------------------StartingTest---------------------------");
            System.out.println("The name is : " + name);
            System.out.println("------------------End of Test---------------------------");
        }

        // 4. Extract the names of all the store
        @Test
        public void test04 () {
            List<String> names = response.extract().path("data.name");
            System.out.println("------------------StartingTest---------------------------");
            System.out.println("The list of names are : " + names);
            System.out.println("------------------End of Test---------------------------");
        }

        // 5. Extract the storeId of all the stores
        @Test
        public void test05 () {
            // List<Integer> storeIds = response.extract().path("data.id"); for all id
            List<Integer> storeIds = response.extract().path("data.services.storeservices.storeId"); //for all store id
            // List<Integer> storeIds = response.extract().path("data.services.id"); //for all services id of all stores
            System.out.println("------------------StartingTest---------------------------");
            System.out.println("The list of storeIds are : " + storeIds);
            System.out.println("------------------End of Test---------------------------");
        }
        //6. Print the size of the data list()
        @Test
        public void test06 () {
            List<Integer> sizeOfData = response.extract().path("data");

            System.out.println("------------------StartingTest---------------------------");
            System.out.println("The size for data list is : " + sizeOfData.size());
            System.out.println("------------------End of Test---------------------------");
        }

        //7. Get all the value of the store where store name = St Cloud
        @Test
        public void test07 () {
            List<HashMap<String, ?>> values = response.extract().path("data.findAll{it.name == 'St Cloud'}");

            System.out.println("------------------StartingTest---------------------------");
            System.out.println("The values for store name 'St Cloud' are: " + values);
            System.out.println("------------------End of Test---------------------------");
        }

        //8. Get the address of the store where store name = Rochester
        @Test
        public void test08 () {
            List<HashMap<String, ?>> getAddress = response.extract().path("data.findAll{it.name == 'Rochester'}.address");

            System.out.println("------------------StartingTest---------------------------");
            System.out.println("The address for store name 'Rochester' is : " + getAddress);
            System.out.println("------------------End of Test---------------------------");
        }

        //9. Get all the services of 8th store($.data[7].services)
        @Test
        public void test09 () {
            List<HashMap<String, ?>> getServices = response.extract().path("data[7].services");

            System.out.println("------------------StartingTest---------------------------");
            System.out.println("The values for services for store 8 : " + getServices);
            System.out.println("------------------End of Test---------------------------");
        }

        //10. Get store services of the store where service name = Windows Store
        @Test
        public void test010 () {
            List<HashMap<String, ?>> getServices = response.extract().path("data.services.findAll{it.name == 'Windows Store'}.storeservices");
            System.out.println("------------------StartingTest---------------------------");
            System.out.println("The values for services where name 'Windows Store' : " + getServices);
            System.out.println("------------------End of Test---------------------------");
        }
        //11. Get all the storeId of all the store
        @Test
        public void test011 () {
            List<Integer> storeIds = response.extract().path("data.services.storeservices.storeId");
            System.out.println("------------------StartingTest---------------------------");
            System.out.println("The list of storeIds are : " + storeIds);
            System.out.println("------------------End of Test---------------------------");
        }

        //12. Get id of all the store
        @Test
        public void test012 () {
            List<Integer> storeIds = response.extract().path("data.id");
            System.out.println("------------------StartingTest---------------------------");
            System.out.println("The list of storeIds are : " + storeIds);
            System.out.println("------------------End of Test---------------------------");
        }

        //13. Find the store names Where state = ND
        @Test
        public void test013 () {
            List<String> name = response.extract().path("data.findAll{it.state == 'ND'}.name");
            System.out.println("------------------StartingTest---------------------------");
            System.out.println("The name of store is : " + name);
            System.out.println("------------------End of Test---------------------------");
        }

        @Test
        public void test014 () {
            List<Integer> noOfServices = response.extract().path("data[8].services");
            //List<?> totalService = response.extract().path("data.find{it.name == 'Rochester'}.services");
            //List<Integer> noOfServices = response.extract().path("data.findAll{it.name == 'Rochester'}.services");
            System.out.println("------------------StartingTest---------------------------");
            System.out.println("The total services :" + noOfServices.size());
            //  System.out.println("The total services : " + noOfServices.size());
            System.out.println("------------------End of Test---------------------------");
        }

        //15. Find the createdAt for all services whose name = “Windows Store”
        @Test
        public void test015 () {
            // List<HashMap<String, ?>> getCreatedAt = response.extract().path("data.findAll{it.services.name == 'Windows Store'}.createdAt");
            //  List<HashMap<String, ?>> getCreatedAt = response.extract().path("data.services.findAll{it.name == 'Windows Store'}.createdAt");
            List<?> getCreatedAt = response.extract().path("data.find{it.services}.services.findAll{it.name=='Windows Store'}.storeservices.createdAt");
            System.out.println("------------------StartingTest---------------------------");
            System.out.println("The total services : " + getCreatedAt);
            System.out.println("------------------End of Test---------------------------");
        }
        //16. Find the name of all services Where store name = “Fargo”
        @Test
        public void test016 () {
            List<String> names = response.extract().path("data.findAll{it.name == 'Fargo'}.services.name");
            System.out.println("------------------StartingTest---------------------------");
            System.out.println("The names of services : " + names);
            System.out.println("------------------End of Test---------------------------");
        }

        // 17. Find the zip of all the store
        @Test
        public void test017 () {
            List<Integer> zipOfAll = response.extract().path("data.zip");
            System.out.println("------------------StartingTest---------------------------");
            System.out.println("The zip code of all stores : " + zipOfAll);
            System.out.println("------------------End of Test---------------------------");
        }

        //18. Find the zip of store name = Roseville
        @Test
        public void test018 () {
            List<Integer> zipOfRoseville = response.extract().path("data.findAll{it.name == 'Roseville'}.zip");
            System.out.println("------------------StartingTest---------------------------");
            System.out.println("The zip code of Roseville store : " + zipOfRoseville);
            System.out.println("------------------End of Test---------------------------");
        }

        //19. Find the store services details of the service name = Magnolia Home Theater
        @Test
        public void test019 () {
            // List<HashMap<String, ?>> createdAt = response.extract().path("data.findAll.services.findAll{it.name == 'Magnolia Home Theater'}.storeservices");
            //   List<HashMap<String, ?>> createdAt = response.extract().path("data.services.findAll{it.name == 'Magnolia Home Theater'}.storeservices");
            List<HashMap<String, ?>> createdAt = response.extract().path("data[2].services.findAll{it.name == 'Magnolia Home Theater'}.storeservices");
            System.out.println("------------------StartingTest---------------------------");
            System.out.println("The total services : " + createdAt);
            System.out.println("------------------End of Test---------------------------");
        }

        //20. Find the lat of all the stores
        @Test
        public void test020 () {
            List<Integer> latOfAllStores = response.extract().path("data.lat");
            System.out.println("------------------StartingTest---------------------------");
            System.out.println("The lat of all stores : " + latOfAllStores);
            System.out.println("------------------End of Test---------------------------");
        }
    }



