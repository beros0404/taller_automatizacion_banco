package co.edu.udea.certificacion.banking.stepdefinitions;

import co.edu.udea.certificacion.banking.models.Cliente;
import co.edu.udea.certificacion.banking.models.Cuenta;
import co.edu.udea.certificacion.banking.questions.ClientesListados;
import co.edu.udea.certificacion.banking.tasks.AbrirCuenta;
import co.edu.udea.certificacion.banking.tasks.AgregarCliente;
import co.edu.udea.certificacion.banking.tasks.ListarClientes;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;

public class ManagerStepDefinition {

    @When("{word} adds the customer {string} {string} with post code {string}")
    public void addsTheCustomer(String actorName, String firstName, String lastName, String postCode) {
        Cliente cliente = Cliente.de(firstName, lastName, postCode);
        theActorCalled(actorName).attemptsTo(AgregarCliente.conDatos(cliente));
    }

    @When("{word} tries to add a customer without filling in the form")
    public void triesToAddACustomerWithoutFillingInTheForm(String actorName) {
        theActorCalled(actorName).attemptsTo(AgregarCliente.sinDiligenciar());
    }

    @When("{word} opens a {string} account for the customer {string}")
    public void opensAnAccountForTheCustomer(String actorName, String currency, String customerName) {
        Cuenta cuenta = Cuenta.de(customerName, currency);
        theActorCalled(actorName).attemptsTo(AbrirCuenta.conLaCuenta(cuenta));
    }

    @When("{word} views the customers list")
    public void viewsTheCustomersList(String actorName) {
        theActorCalled(actorName).attemptsTo(ListarClientes.registrados());
    }

    @When("{word} searches the customers list for {string}")
    public void searchesTheCustomersListFor(String actorName, String text) {
        theActorCalled(actorName).attemptsTo(ListarClientes.filtradosPor(text));
    }

    @Then("the customers list includes {string}")
    public void theCustomersListIncludes(String fullName) {
        theActorInTheSpotlight().should(
                seeThat(ClientesListados.llamados(fullName), greaterThan(0)));
    }

    @Then("the customers list does not include {string}")
    public void theCustomersListDoesNotInclude(String fullName) {
        theActorInTheSpotlight().should(
                seeThat(ClientesListados.llamados(fullName), equalTo(0)));
    }

    @Then("the customers list shows {int} result(s)")
    public void theCustomersListShowsResults(int expected) {
        theActorInTheSpotlight().should(
                seeThat(ClientesListados.visibles(), equalTo(expected)));
    }
}
