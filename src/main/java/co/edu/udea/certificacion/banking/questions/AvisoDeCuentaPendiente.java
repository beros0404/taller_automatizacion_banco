package co.edu.udea.certificacion.banking.questions;

import co.edu.udea.certificacion.banking.userinterfaces.AccountOverviewPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;

import java.time.Duration;

/** Aviso que ve un cliente que aun no tiene cuentas. Vacio si no aparece. */
public class AvisoDeCuentaPendiente implements Question<String> {

    public static AvisoDeCuentaPendiente mostrado() {
        return new AvisoDeCuentaPendiente();
    }

    @Override
    public String answeredBy(Actor actor) {
        try {
            return AccountOverviewPage.LBL_NO_ACCOUNT.resolveFor(actor)
                    .withTimeoutOf(Duration.ofSeconds(3))
                    .waitUntilVisible()
                    .getText()
                    .trim();
        } catch (TimeoutException | NoSuchElementException e) {
            return "";
        }
    }
}
