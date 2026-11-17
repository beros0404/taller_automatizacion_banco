package co.edu.udea.certificacion.banking.models;

/** Deposito o retiro por un monto. */
public class Movimiento {
    private final String monto;

    private Movimiento(String monto) { this.monto = monto; }

    public static Movimiento de(String monto) { return new Movimiento(monto); }

    public String getMonto() { return monto; }

    @Override
    public String toString() { return monto; }
}
