package co.edu.udea.certificacion.banking.stepdefinitions;

import co.edu.udea.certificacion.banking.tasks.EliminarCliente;
import io.cucumber.java.en.When;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;

public class CustomerAdminStepDefinition {

    @When("{word} deletes the customer {string}")
    public void deletesTheCustomer(String actorName, String fullName) {
        theActorCalled(actorName).attemptsTo(EliminarCliente.llamado(fullName));
    }
}
