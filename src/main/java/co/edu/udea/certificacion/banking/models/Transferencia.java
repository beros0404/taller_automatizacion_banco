package co.edu.udea.certificacion.banking.models;

/** Transferencia entre dos cuentas del mismo cliente. */
public class Transferencia {
    private final String monto;
    private final String cuentaOrigen;
    private final String cuentaDestino;

    private Transferencia(String monto, String cuentaOrigen, String cuentaDestino) {
        this.monto = monto;
        this.cuentaOrigen = cuentaOrigen;
        this.cuentaDestino = cuentaDestino;
    }

    public static Transferencia de(String monto, String cuentaOrigen, String cuentaDestino) {
        return new Transferencia(monto, cuentaOrigen, cuentaDestino);
    }

    public String getMonto() { return monto; }
    public String getCuentaOrigen() { return cuentaOrigen; }
    public String getCuentaDestino() { return cuentaDestino; }

    @Override
    public String toString() { return monto + " de " + cuentaOrigen + " a " + cuentaDestino; }
}
