package co.edu.udea.certificacion.banking.stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

/**
 * Prepara el escenario. OnlineCast entrega a cada actor la habilidad BrowseTheWeb
 * (su propio navegador); al terminar se baja el telon y se cierra el navegador.
 */
public class HooksStepDefinition {

    @Before
    public void prepararElEscenario() {
        OnStage.setTheStage(new OnlineCast());
    }

    @After
    public void cerrarElTelon() {
        OnStage.drawTheCurtain();
    }
}
