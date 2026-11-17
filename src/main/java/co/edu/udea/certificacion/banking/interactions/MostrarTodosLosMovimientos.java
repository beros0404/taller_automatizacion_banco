package co.edu.udea.certificacion.banking.interactions;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static net.serenitybdd.screenplay.Tasks.instrumented;

/**
 * Workaround para el defecto del extracto: la aplicacion deja startDate indefinido
 * y el filtro de fechas oculta todas las filas. Se fija el rango completo en el
 * scope de Angular y se fuerza un digest para que la tabla se renderice.
 *
 * No modifica transacciones: solo corrige el filtro de la vista.
 */
public class MostrarTodosLosMovimientos implements Interaction {

    public static MostrarTodosLosMovimientos delExtracto() {
        return instrumented(MostrarTodosLosMovimientos.class);
    }

    @Override
    @Step("{0} muestra todos los movimientos del extracto")
    public <T extends Actor> void performAs(T actor) {
        WebDriver driver = BrowseTheWeb.as(actor).getDriver();

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".tbStruct")));

        ((JavascriptExecutor) driver).executeScript(
                "var el = document.querySelector('.tbStruct');" +
                "var scope = angular.element(el).scope();" +
                "scope.startDate = new Date(0);" +
                "scope.end = new Date();" +
                "scope.$apply();"
        );
    }
}
