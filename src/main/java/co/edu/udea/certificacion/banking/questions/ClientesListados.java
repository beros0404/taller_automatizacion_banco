package co.edu.udea.certificacion.banking.questions;

import co.edu.udea.certificacion.banking.userinterfaces.CustomersPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

/** Cantidad de filas visibles en la lista de clientes (tras aplicar el filtro). */
public class ClientesListados implements Question<Integer> {

    private final String nombre;

    private ClientesListados(String nombre) {
        this.nombre = nombre;
    }

    /** Todas las filas visibles. */
    public static ClientesListados visibles() {
        return new ClientesListados(null);
    }

    /** Filas que contienen el nombre indicado. */
    public static ClientesListados llamados(String nombre) {
        return new ClientesListados(nombre);
    }

    @Override
    public Integer answeredBy(Actor actor) {
        if (nombre == null) {
            return CustomersPage.FILAS.resolveAllFor(actor).size();
        }
        return CustomersPage.FILA_CLIENTE.of(nombre).resolveAllFor(actor).size();
    }
}
