package co.edu.udea.certificacion.banking.interactions;

import co.edu.udea.certificacion.banking.exceptions.AlertaNoPresenteException;
import co.edu.udea.certificacion.banking.utils.Constantes;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.Alert;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static net.serenitybdd.screenplay.Tasks.instrumented;

/** Lee y acepta la alerta nativa del navegador; guarda su texto en la memoria del actor. */
public class AceptarAlerta implements Interaction {

    public static AceptarAlerta delNavegador() {
        return instrumented(AceptarAlerta.class);
    }

    @Override
    @Step("{0} lee y acepta la alerta del navegador")
    public <T extends Actor> void performAs(T actor) {
        WebDriver driver = BrowseTheWeb.as(actor).getDriver();
        try {
            Alert alerta = new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.alertIsPresent());
            actor.remember(Constantes.ALERTA_ULTIMA, alerta.getText());
            alerta.accept();
        } catch (TimeoutException e) {
            throw new AlertaNoPresenteException(e);
        }
    }
}
