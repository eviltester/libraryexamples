package uk.co.compendiumdev.libraryexamples.cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin={"pretty", "html:target/cucumber"})
public class RunCukesTest {
}
