/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package io.github.tyrone1987.integration;

import io.github.tyrone1987.persistence.IEntity;

/**
 *
 * @author gilberto
 * @author Tyrone for new Version
 * @param <T> entidad de la que depende esta interfaz
 */
public interface EntityBuilder<T extends IEntity>
{

    /**
     * Valida la entidad interna de manera que cumpla con las reglas del modelo
     * para campos requeridos y el tama�o de las cadenas de texto.
     * @return Una referencia al objeto this para continuas modificaciones.
     * @throws io.github.tyrone1987.integration.IntegrationException error relacionado en alguna validacion
     */
    AbstractBuilder<T> check() throws IntegrationException;

    /**
     * Elimina el objeto T interno de la base de datos.
     * @return Una referencia al objeto this para continuas modificaciones.
     */
    AbstractBuilder<T> delete();

    /**
     * Determina cuando el objeto T interno existe en la base de datos o no.
     * @return true Si el objeto T interno existe en la base de datos, false en caso contrario.
     */
    boolean existsInDataBase();

    /**
     * Obtiene el objeto T interno.
     * @return Una referencia al objeto T interno
     */
    T getEntity();

    /**
     * Resfresca el objeto T interno.
     * @return Una referencia al objeto T interno
     */
    T refresh();

    /**
     * Guarda el objeto T interno en la base de datos.
     * @return Una referencia al objeto this para continuas modificaciones.
     */
    AbstractBuilder<T> save();
    
}
