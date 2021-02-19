package cucumber.Options;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)

@CucumberOptions(features="src/test/java",plugin="json:target/jsonReports/cucumber-report.json",glue= {"stepDefinations"})

//tags= "@DeletePlace"

// maven phases -  mvn compile mvn test mvn verify
public class TestRunner {

}
