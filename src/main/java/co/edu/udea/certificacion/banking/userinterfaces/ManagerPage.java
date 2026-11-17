package co.edu.udea.certificacion.banking.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;

public class ManagerPage {
    public static final Target BTN_ADD_CUSTOMER =
            Target.the("boton Add Customer").locatedBy("//button[@ng-click='addCust()']");
    public static final Target BTN_OPEN_ACCOUNT =
            Target.the("boton Open Account").locatedBy("//button[@ng-click='openAccount()']");
    public static final Target BTN_CUSTOMERS =
            Target.the("boton Customers").locatedBy("//button[@ng-click='showCust()']");

    private ManagerPage() { }
}
