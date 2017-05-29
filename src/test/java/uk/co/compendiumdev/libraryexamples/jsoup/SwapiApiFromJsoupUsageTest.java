package uk.co.compendiumdev.libraryexamples.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;


public class SwapiApiFromJsoupUsageTest {

    @Test
    public void canGetLuke() throws IOException {

        // have to ignore content type or it throws exception if not text/*, application/xml, or application/xhtml+xml
        Document doc = Jsoup.connect("http://swapi.co/api/people/1/?format=json").ignoreContentType(true).get();

        String json = doc.text();
        System.out.println(json);

        // JSoup does not supply JSON parsing routines
        Assert.assertTrue(json.contains("Luke Skywalker"));

    }

    @Test
    public void canGetC3PO() throws IOException {

        Document doc = Jsoup.connect("http://swapi.co/api/people/2/?format=json").ignoreContentType(true).get();

        String json = doc.text();
        System.out.println(json);

        // JSoup does not supply JSON parsing routines
        Assert.assertTrue(json.contains("C-3PO"));
    }


    /* JSOUP API Exercises

         - make sure your URL call is always upto date by getting the url from the root api
             - make a call to http://swapi.co/api/?format=json and use the url for people in the returned array

         - automate more of the Swapi API e.g. planets etc.

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

         ~~~~~~~~
         Swapi swapi = new Swapi(new JsoupBackedSwapi());
         Swapi swapi = new Swapi(new RestAssuredBackedSwapi());
         Swapi swapi = new Swapi(new WebDriverBackedSwapi());
         ~~~~~~~~


     */
}
