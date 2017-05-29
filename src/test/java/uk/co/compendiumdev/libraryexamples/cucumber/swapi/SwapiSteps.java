package uk.co.compendiumdev.libraryexamples.cucumber.swapi;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;

import static org.hamcrest.core.IsEqual.equalTo;


public class SwapiSteps {

    private String userId;
    private String endpoint;
    private Response response;

    @Given("^a user ID \"([^\"]*)\"$")
    public void aUserID(String userId) throws Throwable {
        this.userId = userId;
    }

    @When("^a call to the \"([^\"]*)\" api is made$")
    public void aCallToTheApiIsMade(String apiendpoint) throws Throwable {
        this.endpoint = apiendpoint;
        response = RestAssured.get(
                "http://swapi.co/api/" + apiendpoint + "/" + this.userId + "/?format=json").
                andReturn();
    }

    @Then("^the name of the person is \"([^\"]*)\"$")
    public void theNameOfThePersonIs(String givenName) throws Throwable {
        String json = response.getBody().asString();
        JsonPath jsonPath = new JsonPath(json);
        Assert.assertEquals(
                givenName,
                jsonPath.getString("name"));
    }


    @Given("^Users exist with the following \\\"([^\\\"]*)\\\" and \\\"([^\\\"]*)\\\"$")
    public void users_exist_with_the_following(String anid, String name) throws Throwable {
        RestAssured.get(
                "http://swapi.co/api/people/" + anid + "/?format=json").
                then().
                assertThat().
                body("name", equalTo(name));
    }

}
