package co.edu.udea.certificacion.banking.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;

public class CustomerLoginPage {
    public static final Target SELECT_USER =
            Target.the("lista de clientes").locatedBy("#userSelect");
    public static final Target BTN_LOGIN =
            Target.the("boton Login").locatedBy("//button[normalize-space()='Login']");

    private CustomerLoginPage() { }
}
