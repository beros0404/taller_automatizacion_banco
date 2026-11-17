package co.edu.udea.certificacion.banking.exceptions;

/**
 * Error (no falla funcional) de la automatizacion: no se pudo acceder al sitio,
 * no se encontro un elemento, etc. Una FALLA es que el negocio no hizo lo esperado
 * y la reporta una Question; un ERROR se lanza como excepcion.
 */
public class BancoException extends RuntimeException {
    public BancoException(String mensaje) { super(mensaje); }
    public BancoException(String mensaje, Throwable causa) { super(mensaje, causa); }
}
