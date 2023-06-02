package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;
public class ProductsExtractionTest {
    static ValidatableResponse response;


    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/products")
                .then().statusCode(200);
    }


    //21. Extract the limit
    @Test
    public void test021() {
        int limit = response.extract().path("limit");


        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The limit is : " + limit);
        System.out.println("------------------End of Test---------------------------");




    }


    //22. Extract the total
    @Test
    public void test022() {
        int total = response.extract().path("total");


        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The total is : " + total);
        System.out.println("------------------End of Test---------------------------");




    }


    //23. Extract the name of 5th product
    @Test
    public void test023() {
        String productName = response.extract().path("data[4].name");


        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The 5th product is : " + productName);
        System.out.println("------------------End of Test---------------------------");




    }


    //24. Extract the names of all the products
    @Test
    public void test024() {
        List<String> listOfProducts = response.extract().path("data.name");


        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of Products are : " + listOfProducts);
        System.out.println("------------------End of Test---------------------------");




    }


    //25. Extract the productId of all the products
    @Test
    public void test025() {
        List<String> listOfIds = response.extract().path("data.id");


        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of Ids are : " + listOfIds);
        System.out.println("------------------End of Test---------------------------");


    }


    //26. Print the size of the data list
    @Test
    public void test026() {
        List<Integer> sizeOfData = response.extract().path("data");


        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The size for data list is : " + sizeOfData.size());
        System.out.println("------------------End of Test---------------------------");


    }


    //27. Get all the value of the product where product name = Energizer - MAX Batteries AA (4-Pack)
    @Test
    public void test027() {
        List<HashMap<String, ?>> values = response.extract().path("data.findAll{it.name =='Energizer - MAX Batteries AA (4-Pack)'}");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The values for product name 'Energizer - MAX Batteries AA (4-Pack)' are: " + values);


        System.out.println("------------------End of Test---------------------------");
    }


    //28. Get the model of the product where product name = Energizer - N Cell E90 Batteries (2-Pack)
    @Test
    public void test028() {
        List<String> model = response.extract().path("data.findAll{it.name =='Energizer - N Cell E90 Batteries (2-Pack)'}.model");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The model for product name 'Energizer - N Cell E90 Batteries (2-Pack)' is: " + model);


        System.out.println("------------------End of Test---------------------------");


    }


    //29. Get all the categories of 8th products
    @Test
    public void test029() {
        List<HashMap<String, ?>> categories = response.extract().path("data[7].categories");




        System.out.println("------------------StartingTest---------------------------");
        System.out.println("All the categories of 8th products are: ");
        for (HashMap e : categories) {
            System.out.println(e);
        }
        System.out.println("------------------End of Test---------------------------");




    }


    //30. Get categories of the store where product id = 150115
    @Test
    public void test030() {
        List<HashMap<String, ?>> categories = response.extract().path("data[3].categories");


        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Categories of the store where product id = 150115: ");
        for (HashMap e : categories) {
            System.out.println(e);
        }
        System.out.println("------------------End of Test---------------------------");




    }


    //31. Get all the descriptions of all the products
    @Test
    public void test031() {
        List<String> description = response.extract().path("data.description");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("All the descriptions of all the products are : ");
        for (String e : description) {
            System.out.println(e);
        }
        System.out.println("------------------End of Test---------------------------");
    }


    //32. Get id of all the all categories of all the products
    @Test
    public void test032() {
        List<HashMap<String, ?>> categoriesListMap = response.extract().path("data.categories.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("All the all categories of all the products are : " + categoriesListMap);
//        for (HashMap e:categoriesListMap){
//            System.out.println(e);
//        }
        System.out.println("------------------End of Test---------------------------");
    }


    //33. Find the product names Where type = HardGood
    @Test
    public void test033() {
        List<String> name = response.extract().path("data.findAll{it.type =='HardGood'}.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The product names Where type is: ");
        for (String e : name) {
            System.out.println(e);
        }
        System.out.println("------------------End of Test---------------------------");
    }


    //34. Find the Total number of categories for the product where product name = Duracell - AA1.5V CopperTop Batteries (4-Pack)
    @Test
    public void test034() {
        List<Integer> numberOfCategories = response.extract().path("data.findAll{it.name =='Duracell - AA 1.5V CopperTop Batteries (4-Pack)'}");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The size for data list is : " + numberOfCategories.size());
        System.out.println("------------------End of Test---------------------------");
    }


    //35. Find the createdAt for all products whose price < 5.49
    @Test
    public void test035() {
        List<String> createdAt = response.extract().path("data.findAll{it.price < 5.49}.createdAt");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println(" List of CreatedAt for all products whose price < 5.49 : ");
        for (String e : createdAt) {
            System.out.println(e);
        }
        System.out.println("------------------End of Test---------------------------");


    }


    //36. Find the name of all categories Where product name = “Energizer - MAX Batteries AA (4-Pack)”
    @Test
    public void test036() {
        List<HashMap<String, ?>> nameOfCategories = response.extract().path("data.findAll{it.name == 'Energizer - MAX Batteries AA (4-Pack)'}.categories.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("All the all categories of all the products are : " + nameOfCategories);
//       for (HashMap e:nameOfCategories){
//           System.out.println(e);
//        }
        System.out.println("------------------End of Test---------------------------");
    }


    //37. Find the manufacturer of all the products
    @Test
    public void test037() {
        List<String> manufacturer = response.extract().path("data.manufacturer");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("All the manufacturer of all the products are : ");
        for (String e : manufacturer) {
            System.out.println(e);
        }
        System.out.println("------------------End of Test---------------------------");
    }


    //38. Find the image of products whose manufacturer is = Energizer
    @Test
    public void test038() {
        List<String> image = response.extract().path("data.findAll{it.manufacturer =='Energizer'}.image");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("All the image of all the products whose manufacturer is  : ");
        for (String e : image) {
            System.out.println(e);
        }
        System.out.println("------------------End of Test---------------------------");
    }


    //39. Find the createdAt for all categories products whose price > 5.99
    @Test
    public void test039() {
        List<HashMap<String, ?>> createdAt = response.extract().path("data.findAll{it.price > 5.99}.categories.createdAt");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println(" List of CreatedAt for all categories products whose price > 5.99 : " + createdAt);
//        for (HashMap e:createdAt){
//            System.out.println(e);
//        }
        System.out.println("------------------End of Test---------------------------");
    }


    //40. Find the url of all the products
    @Test
    public void test040() {
        List<String> url = response.extract().path("data.url");


        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The url of all the products: ");
        for (String e : url) {
            System.out.println(e);
        }
        System.out.println("------------------End of Test---------------------------");
    }
}


