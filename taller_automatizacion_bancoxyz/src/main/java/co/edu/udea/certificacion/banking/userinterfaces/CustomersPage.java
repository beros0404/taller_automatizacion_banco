package co.edu.udea.certificacion.banking.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;

public class CustomersPage {
    public static final Target INPUT_SEARCH =
            Target.the("buscador de clientes").locatedBy("//input[@ng-model='searchCustomer']");
    public static final Target FILAS =
            Target.the("filas de clientes").locatedBy("//table//tbody/tr");
    public static final Target FILA_CLIENTE =
            Target.the("fila del cliente {0}")
                    .locatedBy("//table//tbody/tr[contains(normalize-space(.), '{0}')]");

    private CustomersPage() { }
}
