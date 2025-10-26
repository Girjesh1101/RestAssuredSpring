package petstore.bdd.runner;


import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.*;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features/user")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "petstore.bdd")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty, html:target/cucumber-reports.html, json:target/cucumber-reports.json, io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm")
@ConfigurationParameter(key = FILTER_TAGS_PROPERTY_NAME, value = "@UserManagement or @UserLogin or @BulkOperations")
@ConfigurationParameter(key = PLUGIN_PUBLISH_QUIET_PROPERTY_NAME, value = "true")
public class UserTestRunner {
    // This class will run all Cucumber tests
}