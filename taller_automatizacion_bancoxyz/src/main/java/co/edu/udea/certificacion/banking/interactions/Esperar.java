package co.edu.udea.certificacion.banking.interactions;

import co.edu.udea.certificacion.banking.utils.Constantes;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;

import static net.serenitybdd.screenplay.Tasks.instrumented;

/** Pausa entre pasos para observar la prueba con detenimiento (se configura con -Dpausa.ms). */
public class Esperar implements Interaction {

    public static Esperar unMomento() {
        return instrumented(Esperar.class);
    }

    @Override
    @Step("{0} espera un momento para observar la accion")
    public <T extends Actor> void performAs(T actor) {
        try {
            Thread.sleep(Constantes.PAUSA_MS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
