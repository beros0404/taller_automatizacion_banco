package co.edu.udea.certificacion.banking.tasks;

import co.edu.udea.certificacion.banking.interactions.Esperar;
import co.edu.udea.certificacion.banking.utils.Constantes;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class AbrirElBanco implements Task {

    public static AbrirElBanco enLaPaginaPrincipal() {
        return instrumented(AbrirElBanco.class);
    }

    @Override
    @Step("{0} abre el portal del Banco XYZ")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Open.url(Constantes.URL_BANCO), Esperar.unMomento());
    }
}
