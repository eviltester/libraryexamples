package uk.co.compendiumdev.libraryexamples.gson;


import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

public class GsonParsingSwapiApiData {

    // JSON Data from Swapi.io
    String swapidata = "{\"name\":\"C-3PO\",\"height\":\"167\",\"mass\":\"75\",\"hair_color\":\"n/a\",\"skin_color\":\"gold\",\"eye_color\":\"yellow\",\"birth_year\":\"112BBY\",\"gender\":\"n/a\",\"homeworld\":\"http://swapi.co/api/planets/1/\",\"films\":[\"http://swapi.co/api/films/2/\",\"http://swapi.co/api/films/5/\",\"http://swapi.co/api/films/4/\",\"http://swapi.co/api/films/6/\",\"http://swapi.co/api/films/3/\",\"http://swapi.co/api/films/1/\"],\"species\":[\"http://swapi.co/api/species/2/\"],\"vehicles\":[],\"starships\":[],\"created\":\"2014-12-10T15:10:51.357000Z\",\"edited\":\"2014-12-20T21:17:50.309000Z\",\"url\":\"http://swapi.co/api/people/2/\"}\n";


    // https://github.com/google/gson

    // Simple and generic way to parse JSON quickly
    // - convert it to a Map
    // this can get your code up and running fast but
    // probably isn't best for long term maintenance
    @Test
    public void canParseWithGSon(){

        // generic parsing with Gson
        Gson gson = new Gson();
        Map m = gson.fromJson(swapidata, Map.class);

        String name = (String)m.get("name");
        int mass = Integer.parseInt((String)m.get("mass"));

        // using C-3PO data
        Assert.assertEquals("C-3PO", name);
        Assert.assertNotEquals("R2-D2", name);
        //Assert.assertTrue(mass > 74);
        Assert.assertTrue("expected " + mass + " to be greater than 74",
                mass > 74);
    }


    // longterm use is probably better to create a class to
    // represent the payload
    // and convert the JSON into the payload with fromJson
    private class Person{
        public String name;
        public int mass;
    }
    
    @Test
    public void canParseWithGSonAndObject(){

        // generic parsing with Gson
        Gson gson = new Gson();
        Person c3po = gson.fromJson(swapidata, Person.class);

        // using C-3PO data
        Assert.assertEquals("C-3PO", c3po.name);
        Assert.assertNotEquals("R2-D2", c3po.mass);
        //Assert.assertTrue(mass > 74);
        int mass = c3po.mass;
        Assert.assertTrue("expected " + mass + " to be greater than 74",
                mass > 74);

    }

    /* Exercises:

        You can easily view the JSON used by visiting the Swapi.co site
            http://swapi.co/api/people/2/?format=api

        - Create a Person object that represents all the attributes of the Swapi.co format
        - and parse the string using GSON fromJson in the Person object

        - There are many ways to convert a Json string to objects
           - https://stackoverflow.com/questions/4110664/gson-directly-convert-string-to-jsonobject-no-pojo
        - Try parsing and using Json Objects the `new JsonParser().parse(swapidata).getAsJsonObject()`

     */
}
