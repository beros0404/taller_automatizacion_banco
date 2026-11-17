package co.edu.udea.certificacion.banking.tasks;

import co.edu.udea.certificacion.banking.userinterfaces.LoginPage;
import co.edu.udea.certificacion.banking.utils.Constantes;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

/**
 * Abre el portal y deja la aplicacion en el estado semilla.
 *
 * La aplicacion guarda clientes, cuentas y transacciones en localStorage. Para que
 * cada escenario sea independiente se eliminan esas claves y se recarga la pagina;
 * la propia aplicacion vuelve a crear los cinco clientes de ejemplo.
 */
public class AbrirElBanco implements Task {

    private static final String LIMPIAR_ESTADO =
            "['User','Account','Transaction','CurrentUser','CurrentAccount'," +
            "'maxUserId','maxAccountNo'].forEach(function(k){ localStorage.removeItem(k); });";

    public static AbrirElBanco enLaPaginaPrincipal() {
        return instrumented(AbrirElBanco.class);
    }

    @Override
    @Step("{0} abre el portal del Banco XYZ y restablece los datos de ejemplo")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Open.url(Constantes.URL_BANCO));

        WebDriver driver = BrowseTheWeb.as(actor).getDriver();
        ((JavascriptExecutor) driver).executeScript(LIMPIAR_ESTADO);
        driver.navigate().refresh();

        actor.attemptsTo(
                WaitUntil.the(LoginPage.BTN_CUSTOMER_LOGIN, isVisible())
                        .forNoMoreThan(Duration.ofSeconds(20))
        );
    }
}
