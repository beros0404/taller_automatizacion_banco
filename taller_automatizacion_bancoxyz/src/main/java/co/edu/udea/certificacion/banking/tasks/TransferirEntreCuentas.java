package co.edu.udea.certificacion.banking.tasks;

import co.edu.udea.certificacion.banking.models.Movimiento;
import co.edu.udea.certificacion.banking.models.Transferencia;
import co.edu.udea.certificacion.banking.questions.MensajeDeTransaccion;
import co.edu.udea.certificacion.banking.utils.Texto;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

import static net.serenitybdd.screenplay.Tasks.instrumented;

/**
 * El portal XYZ Bank no ofrece una opcion de transferencia. Se modela como operacion
 * de negocio: retiro en la cuenta origen y, solo si fue exitoso, deposito del mismo
 * monto en la cuenta destino.
 */
public class TransferirEntreCuentas implements Task {

    private final Transferencia transferencia;

    public TransferirEntreCuentas(Transferencia transferencia) {
        this.transferencia = transferencia;
    }

    public static TransferirEntreCuentas conLaTransferencia(Transferencia transferencia) {
        return instrumented(TransferirEntreCuentas.class, transferencia);
    }

    @Override
    @Step("{0} transfiere #transferencia")
    public <T extends Actor> void performAs(T actor) {
        Movimiento movimiento = Movimiento.de(transferencia.getMonto());
        actor.attemptsTo(
                SeleccionarCuenta.numero(transferencia.getCuentaOrigen()),
                Retirar.elMovimiento(movimiento));
        String resultado = actor.asksFor(MensajeDeTransaccion.mostrado());
        if (Texto.normalizar(resultado).contains("transaction successful")) {
            actor.attemptsTo(
                    SeleccionarCuenta.numero(transferencia.getCuentaDestino()),
                    Depositar.elMovimiento(movimiento));
        }
    }
}
