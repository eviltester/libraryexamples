package co.uk.compendiumdev.libraryexamples.restassured;


import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.core.IsEqual.equalTo;

public class SwapiAPIUsageTest {

    @Test
    public void canGetLuke(){

        RestAssured.get("http://swapi.co/api/people/1/?format=json").
                then().
                assertThat().
                body("name",
                        equalTo("Luke Skywalker"));
    }

    @Test
    public void canGetC3POandParseWithJsonPath(){

        // use RestAssured to make an HTML Call
        Response response = RestAssured.get(
                          "http://swapi.co/api/people/2/?format=json").
                           andReturn();

        String json = response.getBody().asString();
        System.out.println(json);

        // Use the JsonPath parsing library of RestAssured to Parse the JSON
        
        JsonPath jsonPath = new JsonPath(json);
        Assert.assertEquals(
                "C-3PO",
                jsonPath.getString("name"));

    }


    /*

        Assert that Luke Skywalker is male
        Assert that C-3PO gender is n/a

                  
        Exercise
          - read the RestAssured documentation https://github.com/rest-assured/rest-assured/wiki/Usage
          - read the Swapi Documentation  - https://swapi.co/documentation
          - Experiment with the API
          
     */
}
