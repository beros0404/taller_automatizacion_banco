package co.edu.udea.certificacion.banking.exceptions;

public class CuentaNoEncontradaException extends BancoException {

    public CuentaNoEncontradaException(String moneda) {
        super("No se encontro una cuenta en la moneda: " + moneda);
    }
}
