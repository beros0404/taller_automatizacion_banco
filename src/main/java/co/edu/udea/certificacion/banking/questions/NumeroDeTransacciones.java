package co.edu.udea.certificacion.banking.questions;

import co.edu.udea.certificacion.banking.userinterfaces.TransactionsPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

/** Cantidad de movimientos listados en el historial de la cuenta. */
public class NumeroDeTransacciones implements Question<Integer> {

    public static NumeroDeTransacciones registradas() {
        return new NumeroDeTransacciones();
    }

    @Override
    public Integer answeredBy(Actor actor) {
        return TransactionsPage.FILAS.resolveAllFor(actor).size();
    }
}
