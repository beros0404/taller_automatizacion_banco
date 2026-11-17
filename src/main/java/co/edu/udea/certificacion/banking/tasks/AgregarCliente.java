package co.edu.udea.certificacion.banking.tasks;

import co.edu.udea.certificacion.banking.interactions.AceptarAlerta;
import co.edu.udea.certificacion.banking.interactions.DarClic;
import co.edu.udea.certificacion.banking.interactions.Esperar;
import co.edu.udea.certificacion.banking.interactions.EscribirTexto;
import co.edu.udea.certificacion.banking.models.Cliente;
import co.edu.udea.certificacion.banking.userinterfaces.AddCustomerPage;
import co.edu.udea.certificacion.banking.userinterfaces.ManagerPage;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.waits.WaitUntil;

import java.time.Duration;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class AgregarCliente implements Task {

    private final Cliente cliente;

    public AgregarCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public static AgregarCliente conDatos(Cliente cliente) {
        return instrumented(AgregarCliente.class, cliente);
    }

    /** Camino excepcional: envia el formulario vacio (no debe aparecer ninguna alerta). */
    public static Performable sinDiligenciar() {
        return Task.where("{0} intenta agregar un cliente sin diligenciar el formulario",
                DarClic.en(ManagerPage.BTN_ADD_CUSTOMER),
                WaitUntil.the(AddCustomerPage.INPUT_FIRST_NAME, isVisible())
                        .forNoMoreThan(Duration.ofSeconds(10)),
                DarClic.en(AddCustomerPage.BTN_SUBMIT),
                Esperar.unMomento());
    }

    @Override
    @Step("{0} agrega al cliente #cliente")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                DarClic.en(ManagerPage.BTN_ADD_CUSTOMER),
                WaitUntil.the(AddCustomerPage.INPUT_FIRST_NAME, isVisible())
                        .forNoMoreThan(Duration.ofSeconds(10)),
                EscribirTexto.elTexto(cliente.getNombre()).en(AddCustomerPage.INPUT_FIRST_NAME),
                EscribirTexto.elTexto(cliente.getApellido()).en(AddCustomerPage.INPUT_LAST_NAME),
                EscribirTexto.elTexto(cliente.getCodigoPostal()).en(AddCustomerPage.INPUT_POST_CODE),
                DarClic.en(AddCustomerPage.BTN_SUBMIT),
                AceptarAlerta.delNavegador(),
                Esperar.unMomento());
    }
}
