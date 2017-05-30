package uk.co.compendiumdev.libraryexamples.restassured;


import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.core.IsEqual.equalTo;

public class SwapiAPIUsageTest {


    /*

    - REST/HTTP abstraction layer
        - https://code.google.com/p/rest-assured/
    - Easier and more reliable than apache http client
        - https://hc.apache.org/
    - see also http://www.compendiumdev.co.uk/page/tracksrestapibook



     */

    // use basic RestAssured to get a JSON object form url
    // and parse with the in built assertions using Hamcrest matchers
    @Test
    public void canGetLuke(){

        RestAssured.get("http://swapi.co/api/people/1/?format=json").
                then().
                assertThat().
                body("name",
                        equalTo("Luke Skywalker"));
    }

    // use RestAssured to make call then return the Response
    // use JsonPath to parse the JSON in response
    // JsonPath can be imported and used separately from RestAssured if required
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


    private class Person{
        public String name;
        public int mass;
    }


    // JsonPath can parse Json directly into an object, like Gson can
    @Test
    public void canGetC3POandParseWithJsonPathIntoObject(){

        // use RestAssured to make an HTML Call
        Response response = RestAssured.get(
                "http://swapi.co/api/people/2/?format=json").
                andReturn();

        String json = response.getBody().asString();
        System.out.println(json);

        // Use the JsonPath parsing library of RestAssured to Parse the JSON into an object

       Person c3po = new JsonPath(json).getObject("$", Person.class);
        Assert.assertEquals(
                "C-3PO",
                c3po.name);

    }


    
    /* Exercises:

        You can easily view the JSON used by visiting the Swapi.co site
            http://swapi.co/api/people/2/?format=api

        - automate more of the Swapi API e.g. planets etc.

        - Create a Person object that represents all the attributes of the Swapi.co format
        - and parse the string using JsonPath into the Person object

       - Create an abstraction layer for the Swapi
         - e.g.

         ~~~~~~~~
         Swapi swapi = new Swapi();
         String json = swapi.getPersonJson(1);
         ~~~~~~~~

         - extend the abstraction layer to have a Person object which models the star wars person as an object
            - see Gson example of RestAssured JsonPath example for how to do this

         - e.g.

         ~~~~~~~~
         Person luke = swapi.getPerson(1);
         Assert.assertTrue("Luke Skywalker",luke.getName());
         ~~~~~~~~

         - add dependency injection to the Swapi so you have different implementations
             - create a `SwapiApi` Interface to support different implementations

         ~~~~~~~~
         Swapi swapi = new Swapi(new JsoupBackedSwapi());
         Swapi swapi = new Swapi(new RestAssuredBackedSwapi());
         Swapi swapi = new Swapi(new WebDriverBackedSwapi());
         ~~~~~~~~

     */


}
