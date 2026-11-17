package co.edu.udea.certificacion.banking.tasks;

import co.edu.udea.certificacion.banking.interactions.DarClic;
import co.edu.udea.certificacion.banking.interactions.Esperar;
import co.edu.udea.certificacion.banking.userinterfaces.LoginPage;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class VolverAlInicio implements Task {

    public static VolverAlInicio delPortal() {
        return instrumented(VolverAlInicio.class);
    }

    @Override
    @Step("{0} regresa a la pantalla de inicio del portal")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(DarClic.en(LoginPage.BTN_HOME), Esperar.unMomento());
    }
}
