package co.edu.udea.certificacion.banking.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;

public class TransactionsPage {
    public static final Target FILAS =
            Target.the("filas de transacciones").locatedBy("//table//tbody/tr");
    public static final Target BTN_BACK =
            Target.the("boton Back").locatedBy("//button[@ng-click='back()']");

    private TransactionsPage() { }
}
