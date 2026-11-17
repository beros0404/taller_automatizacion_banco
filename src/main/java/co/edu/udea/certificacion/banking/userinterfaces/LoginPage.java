package co.edu.udea.certificacion.banking.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;

public class LoginPage {
    public static final Target BTN_CUSTOMER_LOGIN =
            Target.the("boton Customer Login").locatedBy("//button[@ng-click='customer()']");
    public static final Target BTN_MANAGER_LOGIN =
            Target.the("boton Bank Manager Login").locatedBy("//button[@ng-click='manager()']");
    public static final Target BTN_HOME =
            Target.the("boton Home").locatedBy("//button[@ng-click='home()']");

    private LoginPage() { }
}
