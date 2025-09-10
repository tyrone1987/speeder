/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package speeder.jpa.persistence;

/**
 *
 * @author gilberto
 */
public interface IEntity
{

    /**
     * Obtienen el id de la entidad, el id de una entidad
     * puede ser un numero entero autoincremental o un objeto UUID.
     * En el caso de los ids autoincrementales si este metodo devuelve null
     * significa que la entidad no se ha guardado en la base de datos.
     * @return Un Objeto que representa el identificador actual de la entidad en la base.
     */
    Object getId();
    
}
