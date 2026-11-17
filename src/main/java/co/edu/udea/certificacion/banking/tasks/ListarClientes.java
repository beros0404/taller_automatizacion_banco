package co.edu.udea.certificacion.banking.tasks;

import co.edu.udea.certificacion.banking.interactions.DarClic;
import co.edu.udea.certificacion.banking.interactions.Esperar;
import co.edu.udea.certificacion.banking.interactions.EscribirTexto;
import co.edu.udea.certificacion.banking.userinterfaces.CustomersPage;
import co.edu.udea.certificacion.banking.userinterfaces.ManagerPage;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Clear;
import net.serenitybdd.screenplay.waits.WaitUntil;

import java.time.Duration;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

/** Abre el listado de clientes y, opcionalmente, lo filtra con el buscador. */
public class ListarClientes implements Task {

    private final String filtro;

    public ListarClientes(String filtro) {
        this.filtro = filtro;
    }

    public static ListarClientes registrados() {
        return instrumented(ListarClientes.class, "");
    }

    public static ListarClientes filtradosPor(String texto) {
        return instrumented(ListarClientes.class, texto);
    }

    @Override
    @Step("{0} consulta la lista de clientes (filtro: '#filtro')")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                DarClic.en(ManagerPage.BTN_CUSTOMERS),
                WaitUntil.the(CustomersPage.INPUT_SEARCH, isVisible())
                        .forNoMoreThan(Duration.ofSeconds(10)),
                Esperar.unMomento(),
                Clear.field(CustomersPage.INPUT_SEARCH),
                EscribirTexto.elTexto(filtro).en(CustomersPage.INPUT_SEARCH),
                Esperar.unMomento());
    }
}
