package co.edu.udea.certificacion.banking.questions;

import co.edu.udea.certificacion.banking.userinterfaces.AccountPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import java.time.Duration;

/** Mensaje (exito o error) que muestra el portal tras un deposito o retiro. Vacio si no aparece. */
public class MensajeDeTransaccion implements Question<String> {

    public static MensajeDeTransaccion mostrado() {
        return new MensajeDeTransaccion();
    }

    @Override
    public String answeredBy(Actor actor) {
        try {
            return AccountPage.LBL_MESSAGE.resolveFor(actor)
                    .withTimeoutOf(Duration.ofSeconds(3))
                    .waitUntilVisible()
                    .getText()
                    .trim();
        } catch (Exception e) {
            return "";
        }
    }
}
