package co.edu.udea.certificacion.banking.interactions;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.targets.Target;

import static net.serenitybdd.screenplay.Tasks.instrumented;

/** Interaccion de bajo nivel: dar clic sobre un elemento de la interfaz. */
public class DarClic implements Interaction {

    private final Target objetivo;

    public DarClic(Target objetivo) {
        this.objetivo = objetivo;
    }

    public static DarClic en(Target objetivo) {
        return instrumented(DarClic.class, objetivo);
    }

    @Override
    @Step("{0} da clic en #objetivo")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Click.on(objetivo));
    }
}
