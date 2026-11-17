package co.edu.udea.certificacion.banking.tasks;

import co.edu.udea.certificacion.banking.interactions.DarClic;
import co.edu.udea.certificacion.banking.interactions.Esperar;
import co.edu.udea.certificacion.banking.userinterfaces.TransactionsActionsPage;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class ReiniciarHistorial implements Task {

    public static ReiniciarHistorial delaCuenta() {
        return instrumented(ReiniciarHistorial.class);
    }

    @Override
    @Step("{0} reinicia (Reset) el historial de transacciones")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(DarClic.en(TransactionsActionsPage.BTN_RESET), Esperar.unMomento());
    }
}
