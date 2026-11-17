package co.edu.udea.certificacion.banking.tasks;

import co.edu.udea.certificacion.banking.interactions.DarClic;
import co.edu.udea.certificacion.banking.interactions.Esperar;
import co.edu.udea.certificacion.banking.interactions.EscribirTexto;
import co.edu.udea.certificacion.banking.models.Movimiento;
import co.edu.udea.certificacion.banking.userinterfaces.AccountPage;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.waits.WaitUntil;

import java.time.Duration;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class Depositar implements Task {

    private final Movimiento movimiento;

    public Depositar(Movimiento movimiento) {
        this.movimiento = movimiento;
    }

    public static Depositar elMovimiento(Movimiento movimiento) {
        return instrumented(Depositar.class, movimiento);
    }

    /** Camino excepcional: abre el formulario y lo envia sin diligenciar el monto. */
    public static Performable sinIngresarMonto() {
        return Task.where("{0} intenta depositar sin ingresar un monto",
                DarClic.en(AccountPage.BTN_DEPOSIT),
                WaitUntil.the(AccountPage.LBL_DEPOSIT_FORM, isVisible())
                        .forNoMoreThan(Duration.ofSeconds(10)),
                DarClic.en(AccountPage.BTN_SUBMIT_AMOUNT),
                Esperar.unMomento());
    }

    @Override
    @Step("{0} deposita #movimiento en la cuenta seleccionada")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                DarClic.en(AccountPage.BTN_DEPOSIT),
                WaitUntil.the(AccountPage.LBL_DEPOSIT_FORM, isVisible())
                        .forNoMoreThan(Duration.ofSeconds(10)),
                EscribirTexto.elTexto(movimiento.getMonto()).en(AccountPage.INPUT_AMOUNT),
                DarClic.en(AccountPage.BTN_SUBMIT_AMOUNT),
                Esperar.unMomento());
    }
}
