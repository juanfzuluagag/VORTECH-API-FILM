package es.com.vortech.film.message;

import lombok.Getter;

import java.nio.charset.StandardCharsets;

import static java.nio.charset.StandardCharsets.ISO_8859_1;

public class GeneralMessage {
    public static String INFO_AUTOGEN = "ID es autogenerado, tipo entero";

    public enum GeneralMsg{
            NO_FOUND_ACTORSIDS("No se encuentra actorsId"),
            NOT_FOUND_MOVIEID("Película no encontrada: %s"),
            EMPTY_ACTORSIDS("ActorsId no puede estar vacio"),
            VERIFY_ACTORS_ID("Veriicar los IDs de actores enviados: %s"),
            VERIFY_FIELDS("Verificar los campos suministrados");

            @Getter
            private String message;

            GeneralMsg(String message) {
                this.message = new String(message.getBytes(ISO_8859_1), StandardCharsets.UTF_8);
            }
        }
}
