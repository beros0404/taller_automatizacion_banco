package co.edu.udea.certificacion.banking.stepdefinitions;

import co.edu.udea.certificacion.banking.questions.AvisoDeCuentaPendiente;
import co.edu.udea.certificacion.banking.questions.MonedaDeLaCuenta;
import co.edu.udea.certificacion.banking.questions.NombreDeBienvenida;
import co.edu.udea.certificacion.banking.questions.NumeroDeCuentas;
import co.edu.udea.certificacion.banking.questions.PantallaDeLoginDelCliente;
import co.edu.udea.certificacion.banking.tasks.CerrarSesion;
import co.edu.udea.certificacion.banking.tasks.ReiniciarHistorial;
import co.edu.udea.certificacion.banking.tasks.RetirarSinMonto;
import co.edu.udea.certificacion.banking.utils.Texto;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

/** Pasos sobre el resumen de la cuenta del cliente, el cierre de sesion y el historial. */
public class CustomerAccountStepDefinition {

    @When("{word} logs out")
    public void logsOut(String actorName) {
        theActorCalled(actorName).attemptsTo(CerrarSesion.delCliente());
    }

    @When("{word} resets the transaction history")
    public void resetsTheTransactionHistory(String actorName) {
        theActorCalled(actorName).attemptsTo(ReiniciarHistorial.delaCuenta());
    }

    @When("{word} tries to withdraw without entering an amount")
    public void triesToWithdrawWithoutAnAmount(String actorName) {
        theActorCalled(actorName).attemptsTo(RetirarSinMonto.enLaCuenta());
    }

    @Then("the welcome message shows {string}")
    public void theWelcomeMessageShows(String customerName) {
        theActorInTheSpotlight().should(
                seeThat(NombreDeBienvenida.mostrado(), Texto.contiene(customerName)));
    }

    @Then("the account currency is {string}")
    public void theAccountCurrencyIs(String currency) {
        theActorInTheSpotlight().should(
                seeThat(MonedaDeLaCuenta.seleccionada(), Texto.contiene(currency)));
    }

    @Then("the account selector lists {int} account(s)")
    public void theAccountSelectorListsAccounts(int expected) {
        theActorInTheSpotlight().should(
                seeThat(NumeroDeCuentas.disponibles(), equalTo(expected)));
    }

    @Then("the portal tells the customer to open an account first")
    public void thePortalTellsTheCustomerToOpenAnAccount() {
        theActorInTheSpotlight().should(
                seeThat(AvisoDeCuentaPendiente.mostrado(), Texto.contiene("Please open an account with us")));
    }

    @Then("the customer is back at the customer login screen")
    public void theCustomerIsBackAtTheLoginScreen() {
        theActorInTheSpotlight().should(
                seeThat(PantallaDeLoginDelCliente.visible(), is(true)));
    }
}
