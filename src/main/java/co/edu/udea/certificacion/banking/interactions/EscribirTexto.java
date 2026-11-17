package co.edu.udea.certificacion.banking.interactions;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.targets.Target;

import static net.serenitybdd.screenplay.Tasks.instrumented;

/** Interaccion de bajo nivel: tipear un texto en un campo. */
public class EscribirTexto implements Interaction {

    private final String texto;
    private final Target campo;

    public EscribirTexto(String texto, Target campo) {
        this.texto = texto;
        this.campo = campo;
    }

    public static Destino elTexto(String texto) {
        return new Destino(texto);
    }

    public static class Destino {
        private final String texto;
        Destino(String texto) { this.texto = texto; }
        public EscribirTexto en(Target campo) {
            return instrumented(EscribirTexto.class, texto, campo);
        }
    }

    @Override
    @Step("{0} escribe '#texto' en #campo")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Enter.theValue(texto).into(campo));
    }
}
