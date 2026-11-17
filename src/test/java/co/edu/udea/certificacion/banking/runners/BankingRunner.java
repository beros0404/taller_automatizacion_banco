package co.edu.udea.certificacion.banking.runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

/** Punto de entrada: dispara TODA la bateria de pruebas del banco. */
@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "co.edu.udea.certificacion.banking.stepdefinitions",
        tags = "not @ignore",
        snippets = CucumberOptions.SnippetType.CAMELCASE)
public class BankingRunner {
}
