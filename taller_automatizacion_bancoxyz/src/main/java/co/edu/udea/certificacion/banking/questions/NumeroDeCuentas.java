package co.edu.udea.certificacion.banking.questions;

import co.edu.udea.certificacion.banking.userinterfaces.AccountOverviewPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

/** Cantidad de cuentas disponibles en la lista desplegable del cliente. */
public class NumeroDeCuentas implements Question<Integer> {

    public static NumeroDeCuentas disponibles() {
        return new NumeroDeCuentas();
    }

    @Override
    public Integer answeredBy(Actor actor) {
        return AccountOverviewPage.OPCIONES_CUENTA.resolveAllFor(actor).size();
    }
}
