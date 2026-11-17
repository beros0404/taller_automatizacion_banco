package co.edu.udea.certificacion.banking.utils;

/** Constantes compartidas por toda la automatizacion. */
public final class Constantes {

    public static final String URL_BANCO =
            "https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login";
    /** Clave con la que el actor recuerda el ultimo texto de alerta del navegador. */
    public static final String ALERTA_ULTIMA = "textoAlerta";
    /** Pausa entre pasos (ms). Configurable con -Dpausa.ms=1500 */
    public static final int PAUSA_MS = Integer.getInteger("pausa.ms", 1000);

    private Constantes() { }
}
