package co.edu.udea.certificacion.banking.interactions;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.actions.SelectFromOptions;
import net.serenitybdd.screenplay.targets.Target;

import static net.serenitybdd.screenplay.Tasks.instrumented;

/** Interaccion de bajo nivel: seleccionar una opcion (por texto visible) de una lista. */
public class SeleccionarOpcion implements Interaction {

    private final String opcion;
    private final Target lista;

    public SeleccionarOpcion(String opcion, Target lista) {
        this.opcion = opcion;
        this.lista = lista;
    }

    public static Destino deTexto(String opcion) {
        return new Destino(opcion);
    }

    public static class Destino {
        private final String opcion;
        Destino(String opcion) { this.opcion = opcion; }
        public SeleccionarOpcion en(Target lista) {
            return instrumented(SeleccionarOpcion.class, opcion, lista);
        }
    }

    @Override
    @Step("{0} selecciona '#opcion' en #lista")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(SelectFromOptions.byVisibleText(opcion).from(lista));
    }
}
