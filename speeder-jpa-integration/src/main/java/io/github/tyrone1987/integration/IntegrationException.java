package io.github.tyrone1987.integration;

/**
 * clase de exception personalizada
 */
public class IntegrationException extends Exception {

    /**
     * se lanza un error en las validaciones de las entidades
     * @param message mensaje de error para mostrar
     */
    public IntegrationException(String message) {
        super(message);
    }
}
