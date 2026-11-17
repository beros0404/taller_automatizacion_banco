package co.edu.udea.certificacion.banking.questions;

import co.edu.udea.certificacion.banking.exceptions.SaldoNoLegibleException;
import co.edu.udea.certificacion.banking.userinterfaces.AccountPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

/** Saldo mostrado para la cuenta actualmente seleccionada. */
public class SaldoActual implements Question<Integer> {

    public static SaldoActual valor() {
        return new SaldoActual();
    }

    @Override
    public Integer answeredBy(Actor actor) {
        String texto = AccountPage.LBL_BALANCE.resolveFor(actor).getText().trim();
        try {
            return Integer.parseInt(texto);
        } catch (NumberFormatException e) {
            throw new SaldoNoLegibleException(texto, e);
        }
    }
}
