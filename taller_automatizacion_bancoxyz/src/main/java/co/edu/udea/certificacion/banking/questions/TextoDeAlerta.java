package co.edu.udea.certificacion.banking.questions;

import co.edu.udea.certificacion.banking.utils.Constantes;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

/** Ultimo texto de alerta que el actor leyo y acepto. */
public class TextoDeAlerta implements Question<String> {

    public static TextoDeAlerta ultima() {
        return new TextoDeAlerta();
    }

    @Override
    public String answeredBy(Actor actor) {
        String texto = actor.recall(Constantes.ALERTA_ULTIMA);
        return texto == null ? "" : texto;
    }
}
