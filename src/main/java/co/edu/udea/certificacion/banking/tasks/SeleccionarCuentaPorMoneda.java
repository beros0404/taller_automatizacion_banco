package co.edu.udea.certificacion.banking.tasks;

import co.edu.udea.certificacion.banking.exceptions.CuentaNoEncontradaException;
import co.edu.udea.certificacion.banking.interactions.Esperar;
import co.edu.udea.certificacion.banking.userinterfaces.AccountOverviewPage;
import co.edu.udea.certificacion.banking.userinterfaces.AccountPage;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.SelectFromOptions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import static net.serenitybdd.screenplay.Tasks.instrumented;

/**
 * Selecciona la cuenta cuya moneda coincide con la indicada, sin depender
 * de numeros de cuenta hardcodeados.
 */
public class SeleccionarCuentaPorMoneda implements Task {

    private final String moneda;

    public SeleccionarCuentaPorMoneda(String moneda) {
        this.moneda = moneda;
    }

    public static SeleccionarCuentaPorMoneda laMoneda(String moneda) {
        return instrumented(SeleccionarCuentaPorMoneda.class, moneda);
    }

    @Override
    @Step("{0} selecciona la cuenta en #moneda")
    public <T extends Actor> void performAs(T actor) {
        String monedaActual = AccountOverviewPage.LBL_CURRENCY.resolveFor(actor).getText().trim();
        if (monedaActual.equalsIgnoreCase(moneda)) {
            return;
        }

        WebDriver driver = BrowseTheWeb.as(actor).getDriver();
        int totalOpciones = new Select(driver.findElement(By.id("accountSelect")))
                .getOptions()
                .size();

        for (int index = 0; index < totalOpciones; index++) {
            actor.attemptsTo(
                    SelectFromOptions.byIndex(index).from(AccountPage.SELECT_ACCOUNT),
                    Esperar.unMomento());

            monedaActual = AccountOverviewPage.LBL_CURRENCY.resolveFor(actor).getText().trim();
            if (monedaActual.equalsIgnoreCase(moneda)) {
                return;
            }
        }

        throw new CuentaNoEncontradaException(moneda);
    }
}
