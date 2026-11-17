package co.edu.udea.certificacion.banking.tasks;

import co.edu.udea.certificacion.banking.interactions.DarClic;
import co.edu.udea.certificacion.banking.interactions.Esperar;
import co.edu.udea.certificacion.banking.interactions.SeleccionarOpcion;
import co.edu.udea.certificacion.banking.userinterfaces.CustomerLoginPage;
import co.edu.udea.certificacion.banking.userinterfaces.LoginPage;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class IniciarSesionComoCliente implements Task {

    private final String nombre;

    public IniciarSesionComoCliente(String nombre) {
        this.nombre = nombre;
    }

    public static IniciarSesionComoCliente llamado(String nombre) {
        return instrumented(IniciarSesionComoCliente.class, nombre);
    }

    @Override
    @Step("{0} inicia sesion como el cliente #nombre")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                DarClic.en(LoginPage.BTN_CUSTOMER_LOGIN),
                SeleccionarOpcion.deTexto(nombre).en(CustomerLoginPage.SELECT_USER),
                Esperar.unMomento(),
                DarClic.en(CustomerLoginPage.BTN_LOGIN),
                Esperar.unMomento());
    }
}
