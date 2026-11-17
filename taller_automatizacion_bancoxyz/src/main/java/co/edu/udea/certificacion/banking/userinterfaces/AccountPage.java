package co.edu.udea.certificacion.banking.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;

public class AccountPage {
    public static final Target LBL_WELCOME =
            Target.the("nombre del cliente").locatedBy("//span[contains(@class,'fontBig')]");
    public static final Target SELECT_ACCOUNT =
            Target.the("lista de cuentas").locatedBy("#accountSelect");
    public static final Target LBL_ACCOUNT_NUMBER =
            Target.the("numero de cuenta").locatedBy("(//div[@ng-hide='noAccount']//strong)[1]");
    public static final Target LBL_BALANCE =
            Target.the("saldo de la cuenta").locatedBy("(//div[@ng-hide='noAccount']//strong)[2]");
    public static final Target BTN_TRANSACTIONS =
            Target.the("boton Transactions").locatedBy("//button[@ng-click='transactions()']");
    public static final Target BTN_DEPOSIT =
            Target.the("boton Deposit").locatedBy("//button[@ng-click='deposit()']");
    public static final Target BTN_WITHDRAWL =
            Target.the("boton Withdrawl").locatedBy("//button[@ng-click='withdrawl()']");
    public static final Target INPUT_AMOUNT =
            Target.the("campo monto").locatedBy("//input[@ng-model='amount']");
    public static final Target BTN_SUBMIT_AMOUNT =
            Target.the("boton confirmar monto").locatedBy("//form//button[@type='submit']");
    public static final Target LBL_MESSAGE =
            Target.the("mensaje de la transaccion").locatedBy("//span[@ng-show='message']");
    public static final Target BTN_LOGOUT =
            Target.the("boton Logout").locatedBy("//button[@ng-click='logout()']");

    private AccountPage() { }
}
