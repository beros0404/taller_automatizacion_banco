package co.edu.udea.certificacion.banking.models;

/** Cuenta que el gerente abre para un cliente (titular + moneda). */
public class Cuenta {
    private final String titular;
    private final String moneda;

    private Cuenta(String titular, String moneda) {
        this.titular = titular;
        this.moneda = moneda;
    }

    public static Cuenta de(String titular, String moneda) {
        return new Cuenta(titular, moneda);
    }

    public String getTitular() { return titular; }
    public String getMoneda() { return moneda; }

    @Override
    public String toString() { return moneda + " de " + titular; }
}
