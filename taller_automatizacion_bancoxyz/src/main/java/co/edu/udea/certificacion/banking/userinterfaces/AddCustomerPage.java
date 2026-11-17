package co.edu.udea.certificacion.banking.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;

public class AddCustomerPage {
    public static final Target INPUT_FIRST_NAME =
            Target.the("campo First Name").locatedBy("//input[@ng-model='fName']");
    public static final Target INPUT_LAST_NAME =
            Target.the("campo Last Name").locatedBy("//input[@ng-model='lName']");
    public static final Target INPUT_POST_CODE =
            Target.the("campo Post Code").locatedBy("//input[@ng-model='postCd']");
    public static final Target BTN_SUBMIT =
            Target.the("boton Add Customer (submit)").locatedBy("//form//button[@type='submit']");

    private AddCustomerPage() { }
}
