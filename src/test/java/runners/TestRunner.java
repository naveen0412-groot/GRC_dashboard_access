package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Listeners;

@CucumberOptions(
        features = {"src/test/resources/nave.feature"},
        glue = {"stepDefinitions", "hooks"},
        plugin = {"pretty",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
                "html:target/cucumber-reports/cucumber-html-report.html",
                "json:target/cucumber-reports/Cucumber.json"
        },
        monochrome = true
)
@Listeners({io.qameta.allure.testng.AllureTestNg.class})
public class TestRunner extends AbstractTestNGCucumberTests {
}
