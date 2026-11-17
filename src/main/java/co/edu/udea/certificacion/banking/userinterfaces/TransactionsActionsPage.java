package co.edu.udea.certificacion.banking.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;

public class TransactionsActionsPage {
    public static final Target BTN_RESET =
            Target.the("boton Reset").locatedBy("//button[@ng-click='reset()']");

    private TransactionsActionsPage() { }
}
