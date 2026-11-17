package co.edu.udea.certificacion.banking.utils;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.text.Normalizer;

/** Utilidades de texto: comparar mensajes sin importar mayusculas, tildes ni espacios extra. */
public final class Texto {

    private Texto() { }

    public static String normalizar(String texto) {
        if (texto == null) {
            return "";
        }
        String sinTildes = Normalizer.normalize(texto, Normalizer.Form.NFD).replaceAll("\\p{M}", "");
        return sinTildes.toLowerCase().trim().replaceAll("\\s+", " ");
    }

    public static Matcher<String> contiene(String esperado) {
        return new TypeSafeMatcher<String>() {
            @Override
            protected boolean matchesSafely(String actual) {
                return normalizar(actual).contains(normalizar(esperado));
            }

            @Override
            public void describeTo(Description descripcion) {
                descripcion.appendText("un texto que contenga (sin importar mayusculas/tildes) ")
                        .appendValue(esperado);
            }
        };
    }
}
