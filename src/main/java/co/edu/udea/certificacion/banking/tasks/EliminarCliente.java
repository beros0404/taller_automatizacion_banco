package co.edu.udea.certificacion.banking.tasks;

import co.edu.udea.certificacion.banking.interactions.DarClic;
import co.edu.udea.certificacion.banking.interactions.Esperar;
import co.edu.udea.certificacion.banking.userinterfaces.CustomersActionsPage;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

import static net.serenitybdd.screenplay.Tasks.instrumented;

/** El gerente busca al cliente en la lista y lo elimina con el boton Delete de su fila. */
public class EliminarCliente implements Task {

    private final String nombreCompleto;

    public EliminarCliente(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public static EliminarCliente llamado(String nombreCompleto) {
        return instrumented(EliminarCliente.class, nombreCompleto);
    }

    @Override
    @Step("{0} elimina al cliente #nombreCompleto")
    public <T extends Actor> void performAs(T actor) {
        String[] partes = nombreCompleto.split(" ", 2);
        String nombre = partes[0];
        String apellido = partes.length > 1 ? partes[1] : "";
        actor.attemptsTo(
                ListarClientes.filtradosPor(nombre),
                DarClic.en(CustomersActionsPage.BTN_DELETE.of(nombre, apellido)),
                ListarClientes.registrados(),
                Esperar.unMomento());
    }
}
