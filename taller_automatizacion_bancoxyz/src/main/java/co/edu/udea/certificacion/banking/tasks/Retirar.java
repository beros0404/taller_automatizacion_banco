package co.edu.udea.certificacion.banking.tasks;

import co.edu.udea.certificacion.banking.interactions.DarClic;
import co.edu.udea.certificacion.banking.interactions.Esperar;
import co.edu.udea.certificacion.banking.interactions.EscribirTexto;
import co.edu.udea.certificacion.banking.models.Movimiento;
import co.edu.udea.certificacion.banking.userinterfaces.AccountPage;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class Retirar implements Task {

    private final Movimiento movimiento;

    public Retirar(Movimiento movimiento) {
        this.movimiento = movimiento;
    }

    public static Retirar elMovimiento(Movimiento movimiento) {
        return instrumented(Retirar.class, movimiento);
    }

    @Override
    @Step("{0} retira #movimiento de la cuenta seleccionada")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                DarClic.en(AccountPage.BTN_WITHDRAWL),
                EscribirTexto.elTexto(movimiento.getMonto()).en(AccountPage.INPUT_AMOUNT),
                DarClic.en(AccountPage.BTN_SUBMIT_AMOUNT),
                Esperar.unMomento());
    }
}
