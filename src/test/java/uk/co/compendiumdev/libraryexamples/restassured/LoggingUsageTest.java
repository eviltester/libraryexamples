package uk.co.compendiumdev.libraryexamples.restassured;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.junit.Test;

import java.io.*;

import static org.hamcrest.core.IsEqual.equalTo;

public class LoggingUsageTest {

    @Test
    public void canGetLukeLoggingToConsole(){

        // configuring logging to console out
        RestAssured.filters(new RequestLoggingFilter(),
                new ResponseLoggingFilter());

        RestAssured.get("https://swapi.dev/api/people/1/?format=json").
                then().
                assertThat().
                body("name",
                        equalTo("Luke Skywalker"));
    }

    @Test
    public void canGetLukeLoggingToFile() throws IOException {

        final String currentDir = System.getProperty("user.dir");
        File outputFile = new File(currentDir,
                    "restassured" + System.currentTimeMillis()+".log");
        System.out.println("log to file:" + outputFile.getAbsolutePath().toString());
        FileOutputStream fileOutput = new FileOutputStream(outputFile);
        PrintStream printToFile = new PrintStream(fileOutput);

        // configuring logging to console out
        RestAssured.filters(new RequestLoggingFilter(printToFile),
                new ResponseLoggingFilter(printToFile));

        RestAssured.get("https://swapi.dev/api/people/1/?format=json").
                then().
                assertThat().
                body("name",
                        equalTo("Luke Skywalker"));

        fileOutput.close();
    }
}
