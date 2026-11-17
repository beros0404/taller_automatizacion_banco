package co.edu.udea.certificacion.banking.questions;

import co.edu.udea.certificacion.banking.userinterfaces.AccountPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

/** Nombre del cliente que muestra el mensaje de bienvenida. */
public class NombreDeBienvenida implements Question<String> {

    public static NombreDeBienvenida mostrado() {
        return new NombreDeBienvenida();
    }

    @Override
    public String answeredBy(Actor actor) {
        return AccountPage.LBL_WELCOME.resolveFor(actor).getText().trim();
    }
}
