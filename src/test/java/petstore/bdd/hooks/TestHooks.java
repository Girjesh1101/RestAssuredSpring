package petstore.bdd.hooks;




import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import petstore.bdd.stepdefinitions.TestContext;
import petstore.model.user.Users;

import java.util.List;

public class TestHooks {

    @Autowired
    private TestContext testContext;

    @Before
    public void beforeScenario(Scenario scenario) {
        System.out.println("Starting scenario: " + scenario.getName());
        testContext.reset();
    }

    @After
    public void afterScenario(Scenario scenario) {
        // Clean up any test users created during the scenario
        if (testContext.getCurrentUsername() != null) {
            testContext.cleanUpTestUser(testContext.getCurrentUsername());
        }

        // Clean up bulk created users

        System.out.println("Finished scenario: " + scenario.getName() + " - " + scenario.getStatus());
    }
}