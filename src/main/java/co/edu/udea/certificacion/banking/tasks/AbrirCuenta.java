package co.edu.udea.certificacion.banking.tasks;

import co.edu.udea.certificacion.banking.interactions.AceptarAlerta;
import co.edu.udea.certificacion.banking.interactions.DarClic;
import co.edu.udea.certificacion.banking.interactions.Esperar;
import co.edu.udea.certificacion.banking.interactions.SeleccionarOpcion;
import co.edu.udea.certificacion.banking.models.Cuenta;
import co.edu.udea.certificacion.banking.userinterfaces.ManagerPage;
import co.edu.udea.certificacion.banking.userinterfaces.OpenAccountPage;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.waits.WaitUntil;

import java.time.Duration;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class AbrirCuenta implements Task {

    private final Cuenta cuenta;

    public AbrirCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public static AbrirCuenta conLaCuenta(Cuenta cuenta) {
        return instrumented(AbrirCuenta.class, cuenta);
    }

    @Override
    @Step("{0} abre una cuenta en #cuenta")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                DarClic.en(ManagerPage.BTN_OPEN_ACCOUNT),
                WaitUntil.the(OpenAccountPage.SELECT_CUSTOMER, isVisible())
                        .forNoMoreThan(Duration.ofSeconds(10)),
                SeleccionarOpcion.deTexto(cuenta.getTitular()).en(OpenAccountPage.SELECT_CUSTOMER),
                SeleccionarOpcion.deTexto(cuenta.getMoneda()).en(OpenAccountPage.SELECT_CURRENCY),
                Esperar.unMomento(),
                DarClic.en(OpenAccountPage.BTN_PROCESS),
                AceptarAlerta.delNavegador(),
                Esperar.unMomento());
    }
}
