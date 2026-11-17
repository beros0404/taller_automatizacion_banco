package co.edu.udea.certificacion.banking.runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

/** Punto de entrada: solo los escenarios criticos (@smoke), ideal para la socializacion. */
@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "co.edu.udea.certificacion.banking.stepdefinitions",
        tags = "@smoke",
        snippets = CucumberOptions.SnippetType.CAMELCASE)
public class SmokeRunner {
}
