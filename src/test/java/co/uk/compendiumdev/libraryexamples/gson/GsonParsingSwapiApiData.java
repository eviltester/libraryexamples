package co.uk.compendiumdev.libraryexamples.gson;


import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

public class GsonParsingSwapiApiData {

    // JSON Data from Swapi.io
    String swapidata = "{\"name\":\"C-3PO\",\"height\":\"167\",\"mass\":\"75\",\"hair_color\":\"n/a\",\"skin_color\":\"gold\",\"eye_color\":\"yellow\",\"birth_year\":\"112BBY\",\"gender\":\"n/a\",\"homeworld\":\"http://swapi.co/api/planets/1/\",\"films\":[\"http://swapi.co/api/films/2/\",\"http://swapi.co/api/films/5/\",\"http://swapi.co/api/films/4/\",\"http://swapi.co/api/films/6/\",\"http://swapi.co/api/films/3/\",\"http://swapi.co/api/films/1/\"],\"species\":[\"http://swapi.co/api/species/2/\"],\"vehicles\":[],\"starships\":[],\"created\":\"2014-12-10T15:10:51.357000Z\",\"edited\":\"2014-12-20T21:17:50.309000Z\",\"url\":\"http://swapi.co/api/people/2/\"}\n";



    // https://github.com/google/gson

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
}
