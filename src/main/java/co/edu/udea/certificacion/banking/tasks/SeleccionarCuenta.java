package co.edu.udea.certificacion.banking.tasks;

import co.edu.udea.certificacion.banking.interactions.Esperar;
import co.edu.udea.certificacion.banking.interactions.SeleccionarOpcion;
import co.edu.udea.certificacion.banking.userinterfaces.AccountPage;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class SeleccionarCuenta implements Task {

    private final String numero;

    public SeleccionarCuenta(String numero) {
        this.numero = numero;
    }

    public static SeleccionarCuenta numero(String numero) {
        return instrumented(SeleccionarCuenta.class, numero);
    }

    @Override
    @Step("{0} selecciona la cuenta #numero")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                SeleccionarOpcion.deTexto(numero).en(AccountPage.SELECT_ACCOUNT),
                Esperar.unMomento());
    }
}
