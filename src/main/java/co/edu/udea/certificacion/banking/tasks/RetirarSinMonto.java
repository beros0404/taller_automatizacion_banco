package co.edu.udea.certificacion.banking.tasks;

import co.edu.udea.certificacion.banking.interactions.DarClic;
import co.edu.udea.certificacion.banking.interactions.Esperar;
import co.edu.udea.certificacion.banking.userinterfaces.AccountPage;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.waits.WaitUntil;

import java.time.Duration;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

/** Camino excepcional: abre el formulario de retiro y lo envia sin diligenciar el monto. */
public class RetirarSinMonto implements Task {

    public static RetirarSinMonto enLaCuenta() {
        return instrumented(RetirarSinMonto.class);
    }

    @Override
    @Step("{0} intenta retirar sin ingresar un monto")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                DarClic.en(AccountPage.BTN_WITHDRAWL),
                WaitUntil.the(AccountPage.LBL_WITHDRAW_FORM, isVisible())
                        .forNoMoreThan(Duration.ofSeconds(10)),
                DarClic.en(AccountPage.BTN_SUBMIT_AMOUNT),
                Esperar.unMomento());
    }
}
