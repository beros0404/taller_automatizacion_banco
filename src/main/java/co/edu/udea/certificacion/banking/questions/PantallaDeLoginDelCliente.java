package co.edu.udea.certificacion.banking.questions;

import co.edu.udea.certificacion.banking.userinterfaces.CustomerLoginPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

/** Verdadero si se muestra la pantalla donde se elige al cliente para iniciar sesion. */
public class PantallaDeLoginDelCliente implements Question<Boolean> {

    public static PantallaDeLoginDelCliente visible() {
        return new PantallaDeLoginDelCliente();
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        return !CustomerLoginPage.SELECT_USER.resolveAllFor(actor).isEmpty();
    }
}
