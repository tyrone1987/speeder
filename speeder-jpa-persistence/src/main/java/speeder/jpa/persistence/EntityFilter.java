/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package speeder.jpa.persistence;

/**
 *
 * @author gilberto
 */
public interface EntityFilter
{

    /**
     * Determina si se debe filtrar por el id de la entidad.
     * @return true El el filtro se aplicara en el campo id de la entidad, false en caso contrario.
     */
    boolean getFilterId();

    /**
     * Determina si el objeto actual establece algun filtro
     * @return true El objeto actual establece al menos un filtro para la consulta,
     * false El objeto actual no establece ningun filtro para la consulta
     */
    boolean getHasFilter();

    /**
     * Determina el valor a usar cuando se filtre por el campo id de la entidad
     * @return El valor por el cual se debe filtrar el campo id
     */
    Long getId();

    /**
     * Establece el valor a usar cuando se filtre por el campo id de la entidad
     * @param id El valor por el cual se debe filtrar el campo id
     */
    void setId(Long id);

    /**
     * Establece si se debe filtrar por el id de la entidad
     * @param filterId true El el filtro se aplicara en el campo id de la entidad, false en caso contrario.
     */
    void setfilterId(boolean filterId);
    
}
