package co.edu.udea.certificacion.banking.tasks;

import co.edu.udea.certificacion.banking.interactions.DarClic;
import co.edu.udea.certificacion.banking.interactions.Esperar;
import co.edu.udea.certificacion.banking.userinterfaces.LoginPage;
import co.edu.udea.certificacion.banking.userinterfaces.ManagerPage;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.waits.WaitUntil;

import java.time.Duration;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class IniciarSesionComoGerente implements Task {

    public static IniciarSesionComoGerente delBanco() {
        return instrumented(IniciarSesionComoGerente.class);
    }

    @Override
    @Step("{0} inicia sesion como gerente del banco")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                DarClic.en(LoginPage.BTN_MANAGER_LOGIN),
                WaitUntil.the(ManagerPage.BTN_ADD_CUSTOMER, isVisible())
                        .forNoMoreThan(Duration.ofSeconds(15)),
                Esperar.unMomento());
    }
}
