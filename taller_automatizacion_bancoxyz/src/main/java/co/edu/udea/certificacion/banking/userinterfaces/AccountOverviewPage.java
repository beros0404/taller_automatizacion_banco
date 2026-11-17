package co.edu.udea.certificacion.banking.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;

/** Datos de resumen de la cuenta del cliente (moneda, lista de cuentas, aviso de cuenta pendiente). */
public class AccountOverviewPage {
    public static final Target LBL_CURRENCY =
            Target.the("moneda de la cuenta").locatedBy("(//div[@ng-hide='noAccount']//strong)[3]");
    public static final Target OPCIONES_CUENTA =
            Target.the("opciones de la lista de cuentas").locatedBy("//select[@id='accountSelect']/option");
    public static final Target LBL_NO_ACCOUNT =
            Target.the("aviso de cliente sin cuenta").locatedBy("//span[@ng-show='noAccount']");

    private AccountOverviewPage() { }
}
