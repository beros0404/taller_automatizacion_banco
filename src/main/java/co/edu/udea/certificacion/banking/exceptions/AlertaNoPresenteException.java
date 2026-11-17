package co.edu.udea.certificacion.banking.exceptions;

public class AlertaNoPresenteException extends BancoException {
    public static final String MENSAJE = "Se esperaba una alerta del navegador y no aparecio";
    public AlertaNoPresenteException(Throwable causa) { super(MENSAJE, causa); }
}
