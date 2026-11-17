package co.edu.udea.certificacion.banking.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;

public class OpenAccountPage {
    public static final Target SELECT_CUSTOMER =
            Target.the("lista de clientes").locatedBy("#userSelect");
    public static final Target SELECT_CURRENCY =
            Target.the("lista de monedas").locatedBy("#currency");
    public static final Target BTN_PROCESS =
            Target.the("boton Process").locatedBy("//button[normalize-space()='Process']");

    private OpenAccountPage() { }
}
