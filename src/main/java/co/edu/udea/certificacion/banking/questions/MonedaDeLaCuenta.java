package co.edu.udea.certificacion.banking.questions;

import co.edu.udea.certificacion.banking.userinterfaces.AccountOverviewPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class MonedaDeLaCuenta implements Question<String> {

    public static MonedaDeLaCuenta seleccionada() {
        return new MonedaDeLaCuenta();
    }

    @Override
    public String answeredBy(Actor actor) {
        return AccountOverviewPage.LBL_CURRENCY.resolveFor(actor).getText().trim();
    }
}
