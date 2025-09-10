
package speeder.jpa.integration;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import java.util.LinkedList;
import java.util.List;

/**
 * Esta clase facilita la creacion de consultas es usada por las clases
 * Query para almacenar el estado de las condiciones de la consulta en un momento 
 * dado.
 */
public class QueryProvider 
{
    private CriteriaQuery query;

    private List<Predicate> predicateList = new LinkedList<Predicate>();
    
    private boolean or;

    private EntityManager em;

    /**
     * Este constructor es el unico disponible para la clase, 
     * es necesario especificar la consulta de criterios y las condiciones
     * actuales de la misma.
     * @param query
     * @param predicate 
     */
    public QueryProvider(CriteriaQuery query, boolean  or, EntityManager em) 
    {
        this.query = query;
        this.or = or;
        this.em = em;
    }
    
    /**
     * Obtiene el predicado (condiciones) de la consulta.
     * @return Un objeto Predicate con las condiciones actuales de la consulta.
     */
    private Predicate createPredicate() 
    {
        if(or)
            return em.getCriteriaBuilder().or(createPredicateArray());
        else
            return em.getCriteriaBuilder().and(createPredicateArray());
    }
    
    private Predicate[] createPredicateArray()
    {
        Predicate[] p = new Predicate[predicateList.size()];
        return predicateList.toArray(p);
    }

    /**
     * Obtene la consulta de criterios actual
     * @return Un objeto CriteriaQuery que representa la consulta de criterios actual.
     */
    public CriteriaQuery getQuery() 
    {
        query.where(createPredicate());
        return query;
    }

    /**
     * Establece la consulta de criterios actual
     * @param query Un objeto CriteriaQuery que representa la consulta de criterios actual.
     */
    public void setQuery(CriteriaQuery query) 
    {
        this.query = query;
    }

    public void addPredicate(Predicate p)
    {
        predicateList.add(p);
    }
}
