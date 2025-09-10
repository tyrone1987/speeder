
package io.github.tyrone1987.integration;

import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;
import org.apache.commons.lang.StringUtils;

/**
 * Esta clase agrupa un conjunto de funciones de uso comun a la hora de crear
 * consultas de JPA usando la API de Criterios.
 */
public class QueryHelper
{
    /**
     * Crea una nueva condicion de igualda entre la expresion y los valores dados
     * concatenados mediante or esto es
     * expression = value1 or expression = value2 .....
     * y modifica el estado actual de las condiciones de un objeto QueryProvider
     * @param <T>
     * @param entityManager El EntityManager de JPA usado para construir las condiciones
     * @param provider Un objeto QueryProvider que represente el estado actual de las condiciones de la consulta.
     * @param expression La expresion que se usara como base de las comparaciones
     * @param not Determina si la condicion creada debe ser negada antes de ser agregada a la lista de condiciones de la consulta.
     * @param or Determina si la condicion creada debe ser enlasada mediante or (true) o mendia and (false)
     * @param value El valor o lista de valores que se usara como base de las comparaciones.
     */
    public static <T> void equal(EntityManager entityManager, QueryProvider provider, Expression<T> expression, boolean not, T... value)
    {
        Predicate equalPredicate = null;
        for (Object object : value)
        {
            if( equalPredicate == null )
                equalPredicate = entityManager.getCriteriaBuilder().equal(expression, object);
            else
                equalPredicate = entityManager.getCriteriaBuilder().or(equalPredicate, entityManager.getCriteriaBuilder().equal(expression, object));
        }

        if( not )
            equalPredicate = entityManager.getCriteriaBuilder().not(equalPredicate);
        addFilter(entityManager, provider, equalPredicate);
    }

    /**
     * 
     * @param entityManager El EntityManager de JPA usado para construir las condiciones
     * @param provider Un objeto QueryProvider que represente el estado actual de las condiciones de la consulta.
     * @param expression La expresion que se usara como base de las comparaciones
     * @param not Determina si la condicion creada debe ser negada antes de ser agregada a la lista de condiciones de la consulta.
     * @param or Determina si la condicion creada debe ser enlasada mediante or (true) o mendia and (false)
     * @param value El valor o lista de valores que se usara como base de las comparaciones.
     */
    public static void like(EntityManager entityManager, QueryProvider provider, Expression<String> expression, boolean not, String... value)
    {
        Predicate equalPredicate = null;
        
        for (String object : value)
        {
            Predicate current = null;
            if( object == null || !object.contains("%") )
                current = entityManager.getCriteriaBuilder().equal(entityManager.getCriteriaBuilder().upper(expression), StringUtils.upperCase(object));
            else
                current = entityManager.getCriteriaBuilder().like(entityManager.getCriteriaBuilder().upper(expression), StringUtils.upperCase(object));

            if( equalPredicate == null )
                equalPredicate = current;
            else
                equalPredicate = entityManager.getCriteriaBuilder().or(equalPredicate, current);
        }

        if( not )
            equalPredicate = entityManager.getCriteriaBuilder().not(equalPredicate);

        addFilter(entityManager, provider, equalPredicate);
    }

    /**
     * 
     * @param entityManager El EntityManager de JPA usado para construir las condiciones
     * @param provider Un objeto QueryProvider que represente el estado actual de las condiciones de la consulta.
     * @param expression La expresion que se usara como base de las comparaciones
     * @param not Determina si la condicion creada debe ser negada antes de ser agregada a la lista de condiciones de la consulta.
     * @param or Determina si la condicion creada debe ser enlasada mediante or (true) o mendia and (false)
     * @param value El valor o lista de valores que se usara como base de las comparaciones.
     */
    public static void likeSencitive(EntityManager entityManager, QueryProvider provider, Expression<String> expression, boolean not, String... value)
    {
        Predicate equalPredicate = null;
        
        for (String object : value)
        {
            
            Predicate current = null;
            if( object == null || !object.contains("%") )
                current = entityManager.getCriteriaBuilder().equal(expression, object);
            else
                current = entityManager.getCriteriaBuilder().like(expression, object);

            if( equalPredicate == null )
                equalPredicate = current;
            else
                equalPredicate = entityManager.getCriteriaBuilder().or(equalPredicate, current);
        }

        if( not )
            equalPredicate = entityManager.getCriteriaBuilder().not(equalPredicate);

        addFilter(entityManager, provider, equalPredicate);
    }

    /**
     * 
     * @param <T>
     * @param entityManager El EntityManager de JPA usado para construir las condiciones
     * @param provider Un objeto QueryProvider que represente el estado actual de las condiciones de la consulta.
     * @param expression La expresion que se usara como base de las comparaciones
     * @param value1 El valor inferior de la conparacion
     * @param value2 El valor superior de la conparacion
     * @param not Determina si la condicion creada debe ser negada antes de ser agregada a la lista de condiciones de la consulta.
     * @param or Determina si la condicion creada debe ser enlasada mediante or (true) o mendia and (false)
     */
    public static <T extends Comparable<? super T>> void between(EntityManager entityManager, QueryProvider provider, Expression<? extends T> expression, T value1, T value2, boolean not)
    {
        Predicate betweenPredicate = entityManager.getCriteriaBuilder().between(expression, value1, value2);
        if( not )
            betweenPredicate = entityManager.getCriteriaBuilder().not(betweenPredicate);
        addFilter(entityManager, provider, betweenPredicate);
    }

    /**
     * 
     * @param <Y>
     * @param entityManager El EntityManager de JPA usado para construir las condiciones
     * @param provider Un objeto QueryProvider que represente el estado actual de las condiciones de la consulta.
     * @param x La expresion que se usara como base de la comparacion
     * @param y El valor que se usara como base de la comparacion
     * @param not Determina si la condicion creada debe ser negada antes de ser agregada a la lista de condiciones de la consulta.
     * @param or Determina si la condicion creada debe ser enlasada mediante or (true) o mendia and (false)
     */
    public static <Y extends Comparable<? super Y>> void greaterThan(EntityManager entityManager, QueryProvider provider, Expression<? extends Y> x, Y y, boolean not)
    {
        Predicate predicate = entityManager.getCriteriaBuilder().greaterThan(x, y);
        if( not )
            predicate = entityManager.getCriteriaBuilder().not(predicate);
        addFilter(entityManager, provider, predicate);
    }

    /**
     * 
     * @param <Y>
     * @param entityManager El EntityManager de JPA usado para construir las condiciones
     * @param provider Un objeto QueryProvider que represente el estado actual de las condiciones de la consulta.
     * @param x La expresion que se usara como base de la comparacion
     * @param y El valor que se usara como base de la comparacion
     * @param not Determina si la condicion creada debe ser negada antes de ser agregada a la lista de condiciones de la consulta.
     * @param or Determina si la condicion creada debe ser enlasada mediante or (true) o mendia and (false)
     */
    public static <Y extends Comparable<? super Y>> void greaterThanOrEqualTo(EntityManager entityManager, QueryProvider provider, Expression<? extends Y> x, Y y, boolean not)
    {
        Predicate predicate = entityManager.getCriteriaBuilder().greaterThanOrEqualTo(x, y);
        if( not )
            predicate = entityManager.getCriteriaBuilder().not(predicate);
        addFilter(entityManager, provider, predicate);
    }

    /**
     * 
     * @param <Y>
     * @param entityManager El EntityManager de JPA usado para construir las condiciones
     * @param provider Un objeto QueryProvider que represente el estado actual de las condiciones de la consulta.
     * @param x La expresion que se usara como base de la comparacion
     * @param y El valor que se usara como base de la comparacion
     * @param not Determina si la condicion creada debe ser negada antes de ser agregada a la lista de condiciones de la consulta.
     * @param or Determina si la condicion creada debe ser enlasada mediante or (true) o mendia and (false)
     */
    public static <Y extends Comparable<? super Y>> void lessThan(EntityManager entityManager, QueryProvider provider, Expression<? extends Y> x, Y y, boolean not)
    {
        Predicate predicate = entityManager.getCriteriaBuilder().lessThan(x, y);
        if( not )
            predicate = entityManager.getCriteriaBuilder().not(predicate);
        addFilter(entityManager, provider, predicate);
    }

    /**
     * 
     * @param <Y>
     * @param entityManager El EntityManager de JPA usado para construir las condiciones
     * @param provider Un objeto QueryProvider que represente el estado actual de las condiciones de la consulta.
     * @param x La expresion que se usara como base de la comparacion
     * @param y El valor que se usara como base de la comparacion
     * @param not Determina si la condicion creada debe ser negada antes de ser agregada a la lista de condiciones de la consulta.
     * @param or Determina si la condicion creada debe ser enlasada mediante or (true) o mendia and (false)
     */
    public static <Y extends Comparable<? super Y>> void lessThanOrEqualTo(EntityManager entityManager, QueryProvider provider, Expression<? extends Y> x, Y y, boolean not)
    {
        Predicate predicate = entityManager.getCriteriaBuilder().lessThanOrEqualTo(x, y);
        if( not )
            predicate = entityManager.getCriteriaBuilder().not(predicate);
        addFilter(entityManager, provider, predicate);
    }

    /**
     * 
     * @param entityManager El EntityManager de JPA usado para construir las condiciones
     * @param provider Un objeto QueryProvider que represente el estado actual de las condiciones de la consulta.
     * @param x La expresion que se usara como base de la comparacion
     * @param y El valor que se usara como base de la comparacion
     * @param not Determina si la condicion creada debe ser negada antes de ser agregada a la lista de condiciones de la consulta.
     * @param or Determina si la condicion creada debe ser enlasada mediante or (true) o mendia and (false)
     */
    public static void gt(EntityManager entityManager, QueryProvider provider, Expression<? extends Number> x, Number y, boolean not)
    {
        Predicate predicate = entityManager.getCriteriaBuilder().gt(x, y);
        if( not )
            predicate = entityManager.getCriteriaBuilder().not(predicate);
        addFilter(entityManager, provider, predicate);
    }

    /**
     * 
     * @param entityManager El EntityManager de JPA usado para construir las condiciones
     * @param provider Un objeto QueryProvider que represente el estado actual de las condiciones de la consulta.
     * @param x La expresion que se usara como base de la comparacion
     * @param y El valor que se usara como base de la comparacion
     * @param not Determina si la condicion creada debe ser negada antes de ser agregada a la lista de condiciones de la consulta.
     * @param or Determina si la condicion creada debe ser enlasada mediante or (true) o mendia and (false)
     */
    public static void ge(EntityManager entityManager, QueryProvider provider, Expression<? extends Number> x, Number y, boolean not)
    {
        Predicate predicate = entityManager.getCriteriaBuilder().ge(x, y);
        if( not )
            predicate = entityManager.getCriteriaBuilder().not(predicate);
        addFilter(entityManager, provider, predicate);
    }

    /**
     * 
     * @param entityManager El EntityManager de JPA usado para construir las condiciones
     * @param provider Un objeto QueryProvider que represente el estado actual de las condiciones de la consulta.
     * @param x La expresion que se usara como base de la comparacion
     * @param y El valor que se usara como base de la comparacion
     * @param not Determina si la condicion creada debe ser negada antes de ser agregada a la lista de condiciones de la consulta.
     * @param or Determina si la condicion creada debe ser enlasada mediante or (true) o mendia and (false)
     */
    public static void lt(EntityManager entityManager, QueryProvider provider, Expression<? extends Number> x, Number y, boolean not)
    {
        Predicate predicate = entityManager.getCriteriaBuilder().lt(x, y);
        if( not )
            predicate = entityManager.getCriteriaBuilder().not(predicate);
        addFilter(entityManager, provider, predicate);
    }

    /**
     * 
     * @param entityManager El EntityManager de JPA usado para construir las condiciones
     * @param provider Un objeto QueryProvider que represente el estado actual de las condiciones de la consulta.
     * @param x La expresion que se usara como base de la comparacion
     * @param y El valor que se usara como base de la comparacion
     * @param not Determina si la condicion creada debe ser negada antes de ser agregada a la lista de condiciones de la consulta.
     * @param or Determina si la condicion creada debe ser enlasada mediante or (true) o mendia and (false)
     */
    public static void le(EntityManager entityManager, QueryProvider provider, Expression<? extends Number> x, Number y, boolean not)
    {
        Predicate predicate = entityManager.getCriteriaBuilder().lt(x, y);
        if( not )
            predicate = entityManager.getCriteriaBuilder().not(predicate);
        addFilter(entityManager, provider, predicate);
    }

    /**
     * 
     * @param entityManager El EntityManager de JPA usado para construir las condiciones
     * @param provider Un objeto QueryProvider que represente el estado actual de las condiciones de la consulta.
     * @param equalPredicate La condicion a agregar a la lista de condiciones actuales de la consulta.
     * @param or Determina si la condicion dada debe ser enlasada mediante or (true) o mendia and (false)
     */
    private static void addFilter(EntityManager entityManager, QueryProvider provider, Predicate equalPredicate)
    {
        provider.addPredicate(equalPredicate);
    }

    /**
     * 
     * @param <T>
     * @param entityManager El EntityManager de JPA usado para construir las condiciones
     * @param query La consulta a ejecutar
     * @return Una lista de entidades que representa los registros obtenidos mendiante la ejecucion de la consulta.
     */
    public static <T> List<T> getResultList(EntityManager entityManager, CriteriaQuery<T> query)
    {
        TypedQuery<T> typed = entityManager.createQuery(query);

        typed.setFirstResult(0);
        typed.setMaxResults(100);

        return typed.getResultList();
    }

    /**
     * 
     * @param <T>
     * @param em El EntityManager de JPA usado para construir las condiciones
     * @param query La consulta a ejecutar
     * @param startResult El primer registro a ser devuelto debe ser >= 0 o sera ignorado
     * @param maxResults El maximo numero de resultados debe ser >= 0 o sera ignorado
     * @return Una lista de entidades que representa los registros obtenidos mendiante la ejecucion de la consulta.
     */
    public static <T> List<T> getResultList(EntityManager em, CriteriaQuery<T> query, int startResult, int maxResults)
    {
        TypedQuery<T> typed = em.createQuery(query);

        if( startResult >= 0)
            typed.setFirstResult(startResult);
        if( maxResults >= 0)
            typed.setMaxResults(maxResults);

        return typed.getResultList();
    }

    /**
     * 
     * @param <T>
     * @param em El EntityManager de JPA usado para construir las condiciones
     * @param query La consulta a ser ejecutada
     * @param page La pagina de resultados a devolver
     * @param pageSize El tama�o de una pagina de resultados
     * @return Una lista de entidades que representa los registros obtenidos mendiante la ejecucion de la consulta.
     */
    public static <T> List<T> getResultListPage(EntityManager em, CriteriaQuery<T> query, int page, int pageSize)
    {
        return getResultList(em, query, (page - 1) * pageSize, pageSize );
    }

    /**
     * 
     * @param <T>
     * @param em El EntityManager de JPA usado para construir las condiciones
     * @param query
     * @return Una lista de entidades que representa los registros obtenidos mendiante la ejecucion de la consulta.
     */
    public static <T> List<T> getResultListUnlimit(EntityManager em, CriteriaQuery<T> query)
    {
        return getResultList(em, query, -1, -1 );
    }

    /**
     * 
     * @param <T>
     * @param em El EntityManager de JPA usado para construir las condiciones
     * @param query La consulta a ser ejecutada
     * @return El valor del primer registro encontrado o bien null si ninguno fue encontrado.
     */
    public static <T> T getSingleResult(EntityManager em, CriteriaQuery<T> query)
    {
        TypedQuery<T> typed = em.createQuery(query);

        typed.setFirstResult(0);
        typed.setMaxResults(1);

        List<T> t = typed.getResultList();
        if( t.isEmpty() )
        {
            return null;
        }

        return t.get(0);
    }
}
