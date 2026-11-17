package co.edu.udea.certificacion.banking.tasks;

import co.edu.udea.certificacion.banking.interactions.DarClic;
import co.edu.udea.certificacion.banking.interactions.Esperar;
import co.edu.udea.certificacion.banking.userinterfaces.AccountPage;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class CerrarSesion implements Task {

    public static CerrarSesion delCliente() {
        return instrumented(CerrarSesion.class);
    }

    @Override
    @Step("{0} cierra la sesion del cliente (Logout)")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(DarClic.en(AccountPage.BTN_LOGOUT), Esperar.unMomento());
    }
}
