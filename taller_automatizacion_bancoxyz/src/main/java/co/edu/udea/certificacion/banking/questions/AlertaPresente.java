package co.edu.udea.certificacion.banking.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.NoAlertPresentException;

/** Indica si hay una alerta del navegador abierta en este momento. */
public class AlertaPresente implements Question<Boolean> {

    public static AlertaPresente enPantalla() {
        return new AlertaPresente();
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        try {
            BrowseTheWeb.as(actor).getDriver().switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }
}
