package co.edu.udea.certificacion.banking.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;

public class CustomersActionsPage {
    /** Usar con .of("Nombre", "Apellido"). */
    public static final Target BTN_DELETE =
            Target.the("boton Delete del cliente {0} {1}")
                    .locatedBy("//table//tbody/tr[td[1][normalize-space()='{0}'] and td[2][normalize-space()='{1}']]"
                            + "//button[normalize-space()='Delete']");

    private CustomersActionsPage() { }
}
