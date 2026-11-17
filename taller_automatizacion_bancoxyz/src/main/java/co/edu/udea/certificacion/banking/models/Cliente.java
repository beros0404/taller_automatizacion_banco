package co.edu.udea.certificacion.banking.models;

/** Cliente financiero del banco. */
public class Cliente {
    private final String nombre;
    private final String apellido;
    private final String codigoPostal;

    private Cliente(String nombre, String apellido, String codigoPostal) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.codigoPostal = codigoPostal;
    }

    public static Cliente de(String nombre, String apellido, String codigoPostal) {
        return new Cliente(nombre, apellido, codigoPostal);
    }

    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public String getCodigoPostal() { return codigoPostal; }
    public String nombreCompleto() { return nombre + " " + apellido; }

    @Override
    public String toString() { return nombreCompleto(); }
}
