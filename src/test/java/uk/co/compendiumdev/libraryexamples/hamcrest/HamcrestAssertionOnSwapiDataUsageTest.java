package uk.co.compendiumdev.libraryexamples.hamcrest;

import io.restassured.path.json.JsonPath;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class HamcrestAssertionOnSwapiDataUsageTest {

    // JSON Data from Swapi.io
    String swapidata = "{\"name\":\"C-3PO\",\"height\":\"167\",\"mass\":\"75\",\"hair_color\":\"n/a\",\"skin_color\":\"gold\",\"eye_color\":\"yellow\",\"birth_year\":\"112BBY\",\"gender\":\"n/a\",\"homeworld\":\"http://swapi.co/api/planets/1/\",\"films\":[\"http://swapi.co/api/films/2/\",\"http://swapi.co/api/films/5/\",\"http://swapi.co/api/films/4/\",\"http://swapi.co/api/films/6/\",\"http://swapi.co/api/films/3/\",\"http://swapi.co/api/films/1/\"],\"species\":[\"http://swapi.co/api/species/2/\"],\"vehicles\":[],\"starships\":[],\"created\":\"2014-12-10T15:10:51.357000Z\",\"edited\":\"2014-12-20T21:17:50.309000Z\",\"url\":\"http://swapi.co/api/people/2/\"}\n";


    // http://hamcrest.org/JavaHamcrest/

    @Test
    public void canAssertWithHamcrest(){

        // using RestAssured JsonPath to parse the Json Data
        JsonPath json = new JsonPath(swapidata);

        String name = json.getString("name");
        int mass = json.getInt("mass");

        // using C-3PO data
        assertThat(name, is(equalTo("C-3PO")));
        assertThat(name, is(not(equalTo("R2-D2"))));
        assertThat(mass, is(greaterThan(74)));
        
    }

    
    /*

        You can easily view the JSON used by visiting the Swapi.co site
            http://swapi.co/api/people/2/?format=api

        Exercise:
           - Use Json path to extract more values from JSON
           - Experiment with Hamcrest matchers to assert on the values from Json
           - Expand Json Path to extract to an object and assert on object fields or 'get' methods
               - see `SwapoAPIUsageTest`
     */

}
