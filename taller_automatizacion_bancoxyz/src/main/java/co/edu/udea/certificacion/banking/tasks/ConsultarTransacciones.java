package co.edu.udea.certificacion.banking.tasks;

import co.edu.udea.certificacion.banking.interactions.DarClic;
import co.edu.udea.certificacion.banking.interactions.Esperar;
import co.edu.udea.certificacion.banking.userinterfaces.AccountPage;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class ConsultarTransacciones implements Task {

    public static ConsultarTransacciones delaCuenta() {
        return instrumented(ConsultarTransacciones.class);
    }

    @Override
    @Step("{0} consulta el historial de transacciones de la cuenta")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Esperar.unMomento(),
                DarClic.en(AccountPage.BTN_TRANSACTIONS),
                Esperar.unMomento());
    }
}
