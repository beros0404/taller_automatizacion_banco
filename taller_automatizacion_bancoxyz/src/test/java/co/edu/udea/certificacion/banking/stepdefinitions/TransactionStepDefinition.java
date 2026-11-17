package co.edu.udea.certificacion.banking.stepdefinitions;

import co.edu.udea.certificacion.banking.models.Movimiento;
import co.edu.udea.certificacion.banking.models.Transferencia;
import co.edu.udea.certificacion.banking.questions.MensajeDeTransaccion;
import co.edu.udea.certificacion.banking.questions.NumeroDeTransacciones;
import co.edu.udea.certificacion.banking.questions.SaldoActual;
import co.edu.udea.certificacion.banking.tasks.ConsultarTransacciones;
import co.edu.udea.certificacion.banking.tasks.Depositar;
import co.edu.udea.certificacion.banking.tasks.Retirar;
import co.edu.udea.certificacion.banking.tasks.SeleccionarCuenta;
import co.edu.udea.certificacion.banking.tasks.TransferirEntreCuentas;
import co.edu.udea.certificacion.banking.utils.Texto;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.Matchers.equalTo;

public class TransactionStepDefinition {

    private static final String INITIAL_BALANCE = "saldoInicial";

    // ---------- Given: precondiciones ----------

    @Given("{word} records the initial balance of the account")
    public void recordsTheInitialBalance(String actorName) {
        Actor actor = theActorCalled(actorName);
        actor.remember(INITIAL_BALANCE, actor.asksFor(SaldoActual.valor()));
    }

    @Given("{word} has at least {int} in the account")
    public void hasAtLeast(String actorName, int minimum) {
        Actor actor = theActorCalled(actorName);
        int balance = actor.asksFor(SaldoActual.valor());
        if (balance < minimum) {
            actor.attemptsTo(Depositar.elMovimiento(Movimiento.de(String.valueOf(minimum - balance))));
        }
        actor.remember(INITIAL_BALANCE, actor.asksFor(SaldoActual.valor()));
    }

    // ---------- When: acciones ----------

    @When("{word} deposits {string}")
    public void deposits(String actorName, String amount) {
        theActorCalled(actorName).attemptsTo(Depositar.elMovimiento(Movimiento.de(amount)));
    }

    @When("{word} tries to deposit without entering an amount")
    public void triesToDepositWithoutAnAmount(String actorName) {
        theActorCalled(actorName).attemptsTo(Depositar.sinIngresarMonto());
    }

    @When("{word} withdraws {string}")
    public void withdraws(String actorName, String amount) {
        theActorCalled(actorName).attemptsTo(Retirar.elMovimiento(Movimiento.de(amount)));
    }

    @When("{word} withdraws the entire balance")
    public void withdrawsTheEntireBalance(String actorName) {
        Actor actor = theActorCalled(actorName);
        int balance = actor.asksFor(SaldoActual.valor());
        actor.attemptsTo(Retirar.elMovimiento(Movimiento.de(String.valueOf(balance))));
    }

    @When("{word} tries to withdraw more than the balance")
    public void triesToWithdrawMoreThanTheBalance(String actorName) {
        Actor actor = theActorCalled(actorName);
        int balance = actor.asksFor(SaldoActual.valor());
        actor.attemptsTo(Retirar.elMovimiento(Movimiento.de(String.valueOf(balance + 1))));
    }

    @When("{word} transfers {int} from account {string} to account {string}")
    public void transfers(String actorName, int amount, String origin, String destination) {
        Actor actor = theActorCalled(actorName);
        rememberBalances(actor, origin, destination);
        actor.attemptsTo(TransferirEntreCuentas.conLaTransferencia(
                Transferencia.de(String.valueOf(amount), origin, destination)));
    }

    @When("{word} tries to transfer more than the balance from account {string} to account {string}")
    public void triesToTransferMoreThanTheBalance(String actorName, String origin, String destination) {
        Actor actor = theActorCalled(actorName);
        rememberBalances(actor, origin, destination);
        int originBalance = actor.recall("saldoOrigen");
        actor.attemptsTo(TransferirEntreCuentas.conLaTransferencia(
                Transferencia.de(String.valueOf(originBalance + 1), origin, destination)));
    }

    @When("{word} views the transaction history")
    public void viewsTheTransactionHistory(String actorName) {
        theActorCalled(actorName).attemptsTo(ConsultarTransacciones.delaCuenta());
    }

    // ---------- Then: validaciones (Questions) ----------

    @Then("the portal shows the message {string}")
    public void thePortalShowsTheMessage(String message) {
        theActorInTheSpotlight().should(
                seeThat(MensajeDeTransaccion.mostrado(), Texto.contiene(message)));
    }

    @Then("the account balance increases by {int}")
    public void theBalanceIncreasesBy(int amount) {
        Actor actor = theActorInTheSpotlight();
        int initial = actor.recall(INITIAL_BALANCE);
        actor.should(seeThat(SaldoActual.valor(), equalTo(initial + amount)));
    }

    @Then("the account balance decreases by {int}")
    public void theBalanceDecreasesBy(int amount) {
        Actor actor = theActorInTheSpotlight();
        int initial = actor.recall(INITIAL_BALANCE);
        actor.should(seeThat(SaldoActual.valor(), equalTo(initial - amount)));
    }

    @Then("the account balance stays the same")
    public void theBalanceStaysTheSame() {
        Actor actor = theActorInTheSpotlight();
        int initial = actor.recall(INITIAL_BALANCE);
        actor.should(seeThat(SaldoActual.valor(), equalTo(initial)));
    }

    @Then("the account balance is {int}")
    public void theBalanceIs(int expected) {
        theActorInTheSpotlight().should(seeThat(SaldoActual.valor(), equalTo(expected)));
    }

    @Then("the transaction history lists {int} movement(s)")
    public void theHistoryListsMovements(int expected) {
        theActorInTheSpotlight().should(
                seeThat(NumeroDeTransacciones.registradas(), equalTo(expected)));
    }

    @Then("both account balances reflect the transfer of {int}")
    public void bothBalancesReflectTheTransfer(int amount) {
        Actor actor = theActorInTheSpotlight();
        String origin = actor.recall("cuentaOrigen");
        String destination = actor.recall("cuentaDestino");
        int originBalance = actor.recall("saldoOrigen");
        int destinationBalance = actor.recall("saldoDestino");
        actor.attemptsTo(SeleccionarCuenta.numero(origin));
        actor.should(seeThat(SaldoActual.valor(), equalTo(originBalance - amount)));
        actor.attemptsTo(SeleccionarCuenta.numero(destination));
        actor.should(seeThat(SaldoActual.valor(), equalTo(destinationBalance + amount)));
    }

    @Then("the transfer is rejected and both account balances stay the same")
    public void theTransferIsRejected() {
        Actor actor = theActorInTheSpotlight();
        actor.should(seeThat(MensajeDeTransaccion.mostrado(), Texto.contiene("Transaction Failed")));
        String origin = actor.recall("cuentaOrigen");
        String destination = actor.recall("cuentaDestino");
        int originBalance = actor.recall("saldoOrigen");
        int destinationBalance = actor.recall("saldoDestino");
        actor.attemptsTo(SeleccionarCuenta.numero(origin));
        actor.should(seeThat(SaldoActual.valor(), equalTo(originBalance)));
        actor.attemptsTo(SeleccionarCuenta.numero(destination));
        actor.should(seeThat(SaldoActual.valor(), equalTo(destinationBalance)));
    }

    // ---------- Apoyo ----------

    private void rememberBalances(Actor actor, String origin, String destination) {
        actor.attemptsTo(SeleccionarCuenta.numero(origin));
        actor.remember("saldoOrigen", actor.asksFor(SaldoActual.valor()));
        actor.attemptsTo(SeleccionarCuenta.numero(destination));
        actor.remember("saldoDestino", actor.asksFor(SaldoActual.valor()));
        actor.remember("cuentaOrigen", origin);
        actor.remember("cuentaDestino", destination);
    }
}
