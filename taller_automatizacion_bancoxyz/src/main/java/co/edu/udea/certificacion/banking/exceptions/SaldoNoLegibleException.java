package co.edu.udea.certificacion.banking.exceptions;

public class SaldoNoLegibleException extends BancoException {
    public static final String MENSAJE = "No fue posible leer el saldo de la cuenta. Texto obtenido: ";
    public SaldoNoLegibleException(String textoObtenido, Throwable causa) {
        super(MENSAJE + "'" + textoObtenido + "'", causa);
    }
}
