package co.edu.udea.certificacion.banking.stepdefinitions;

import co.edu.udea.certificacion.banking.questions.AlertaPresente;
import co.edu.udea.certificacion.banking.questions.TextoDeAlerta;
import co.edu.udea.certificacion.banking.tasks.AbrirElBanco;
import co.edu.udea.certificacion.banking.tasks.IniciarSesionComoCliente;
import co.edu.udea.certificacion.banking.tasks.IniciarSesionComoGerente;
import co.edu.udea.certificacion.banking.tasks.VolverAlInicio;
import co.edu.udea.certificacion.banking.utils.Texto;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.Matchers.is;

/** Pasos comunes del portal. Aqui se define el actor: theActorCalled(nombre). */
public class PortalStepDefinition {

    @Given("{word} opens the bank portal")
    public void opensTheBankPortal(String actorName) {
        theActorCalled(actorName).attemptsTo(AbrirElBanco.enLaPaginaPrincipal());
    }

    @Given("{word} logs in as customer {string}")
    public void logsInAsCustomer(String actorName, String customerName) {
        theActorCalled(actorName).attemptsTo(IniciarSesionComoCliente.llamado(customerName));
    }

    @Given("{word} logs in as bank manager")
    public void logsInAsBankManager(String actorName) {
        theActorCalled(actorName).attemptsTo(IniciarSesionComoGerente.delBanco());
    }

    @When("{word} goes back to the home screen")
    public void goesBackToTheHomeScreen(String actorName) {
        theActorCalled(actorName).attemptsTo(VolverAlInicio.delPortal());
    }

    @Then("the system shows the alert {string}")
    public void theSystemShowsTheAlert(String message) {
        theActorInTheSpotlight().should(seeThat(TextoDeAlerta.ultima(), Texto.contiene(message)));
    }

    @Then("the system shows no alert")
    public void theSystemShowsNoAlert() {
        theActorInTheSpotlight().should(seeThat(AlertaPresente.enPantalla(), is(false)));
    }
}
