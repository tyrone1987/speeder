
package io.github.tyrone1987.persistence;

import jakarta.persistence.MappedSuperclass;

/**
 * Esta clase representa una entidad abstracta, presenta las propiedades y metodo basicos
 * de todas las entidades que componen el modelo de datos de la aplicacion.
 */
@MappedSuperclass
public abstract class AbstractEntity implements IEntity
{

    /**
     * hashCode del objeto actual, El hashCode es calculado mediante el id de 
     * la entidad, si id == null entonces se usara la funcion hashCode de la clase Object
     * @return Un entero que representa el hashCode de la entidad.
     */
    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (getId() != null ? getId().hashCode() : super.hashCode());
        return hash;
    }

    /**
     * Determina si la entidad es igual la entidad object dada en el argumento
     * La comparacion de las dos entidades se hace a partir de sus ids, el objeto
     * debe ser una clase de la misma clase que la entidad de otro modo se devolvera 
     * false.
     * @param object El objeto a comparar con la entidad.
     * @return true, El objeto object es el mismo objeto que la entidad actual o bien 
     * tiene el mismo id y es de la misma clase, false en caso contratio.
     */    
    @Override
    public boolean equals(Object object)
    {
        if( ! (object instanceof AbstractEntity) )
        {
            return false;
        }

        if (!(getClass().isInstance(object)))
        {
            return false;
        }

        AbstractEntity other = (AbstractEntity) object;
        if( (this.getId() == null && other.getId() == null) )
        {
            return super.equals(object);
        }

        if( this.getId() == null || other.getId() == null )
        {
            return false;
        }

        return this.getId().equals(other.getId());
    }
}
