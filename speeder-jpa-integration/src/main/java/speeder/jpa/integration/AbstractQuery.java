package speeder.jpa.integration;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Path;
import java.lang.reflect.Method;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import speeder.jpa.persistence.IEntity;

/**
 * Esta clase permite realizar consultas sobre las entidades T de
 * una manera facil mediante una interfaz fluida (Fluent Interface)
 */
public abstract class AbstractQuery<T extends IEntity> implements EntityQuery<T>
{
    /**
     * El objeto path del cual se tomaran los campos de la consulta para esta instancia
     */
    protected Path<T> path;

    /**
     * La consulta de criterios de JPA a usar por esta instancia.
     */
    protected QueryProvider provider;

    /**
     * El contexto de persistencia a usar.
     */
    protected EntityManager entityManager;

    /**
     * Bandera que determina cuando el siguiente filtro a ser incluido en la consulta debe ser negado.
     */
    protected boolean not = false;

    /**
     * Constructor que crea una nueva instancia a partir de una consulta ya inicializada.
     * Este constructor no debe ser usado en cambio vease el metodo estatico find.
     * @param entityManager El contexto de persistencia a usar.
     * @param path El path a usar en la consulta.
     * @param provider La consulta a usar por esta instancia.
     */
    public AbstractQuery(EntityManager entityManager, Path<T> path, CriteriaQuery query, boolean or)
    {
        this.path = path;
        this.provider = new QueryProvider(query, or, entityManager);
        this.entityManager = entityManager;
    }

    /**
     * Constructor que crea una nueva instancia a partir de una consulta ya inicializada.
     * Este constructor no debe ser usado en cambio vease el metodo estatico find.
     * @param entityManager El contexto de persistencia a usar.
     * @param path El path a usar en la consulta.
     * @param provider La consulta a usar por esta instancia.
     */
    public AbstractQuery(EntityManager entityManager, Path<T> path, QueryProvider provider)
    {
        this.path = path;
        this.provider = provider;
        this.entityManager = entityManager;
    }

    /**
     * Obtiene la cantidad de registros que se obtendran mediante esta consulta.
     * @return El objeto this
     */
    @Override
    public Long count()
    {
        provider.getQuery().select(entityManager.getCriteriaBuilder().count(path));
        provider.getQuery().distinct(true);
        return QueryHelper.getSingleResult(entityManager, (CriteriaQuery<Long>)provider.getQuery());
    }

    /**
     * Ejecuta la consulta y obtiene todos los registros.
     * @return Una lista con todos los objetos obtenidos durante la consulta.
     */
    @Override
    public List<T> all()
    {
        provider.getQuery().select(path);
        provider.getQuery().distinct(false);
        return QueryHelper.getResultListUnlimit(entityManager, (CriteriaQuery<T>)provider.getQuery());
    }

    /**
     * Ejecuta la consulta y obtiene todos los registros, para la pagina dada
     * usando el tamanio de pagina por defecto.
     * @param page Un entero positivo (mayor que 0) indicando el numero de la pagina a obtener.
     * @return Una lista con todos los objetos obtenidos durante la consulta.
     */
    @Override
    public List<T> all(int page)
    {
        provider.getQuery().select(path);
        provider.getQuery().distinct(false);
        return QueryHelper.getResultListPage(entityManager, (CriteriaQuery<T>)provider.getQuery(), page, 100);
    }

    /**
     * Ejecuta la consulta y obtiene todos los registros, para la pagina dada
     * usando el tamanio de pagina dado.
     * @param page Un entero positivo (mayor que 0) indicando el numero de la pagina a obtener.
     * @param pageSize Un entero positivo (mayor que 0) indicando el tamanio de pagina a usar.
     * @return Una lista con todos los objetos obtenidos durante la consulta.
     */
    @Override
    public List<T> all(int page, int pageSize)
    {
        provider.getQuery().select(path);
        provider.getQuery().distinct(false);
        return QueryHelper.getResultListPage(entityManager, (CriteriaQuery<T>)provider.getQuery(), page, pageSize);
    }

    /**
     * Ejecuta la consulta y obtiene todos los registros distintos.
     * @return Una lista con todos los objetos obtenidos durante la consulta.
     */
    @Override
    public List<T> distinct()
    {
        provider.getQuery().select(path);
        provider.getQuery().distinct(true);
        return QueryHelper.getResultListUnlimit(entityManager, (CriteriaQuery<T>)provider.getQuery());
    }

    /**
     * Ejecuta la consulta y obtiene todos los registros distintos, para la pagina dada
     * usando el tamanio de pagina por defecto.
     * @param page Un entero positivo (mayor que 0) indicando el numero de la pagina a obtener.
     * @return Una lista con todos los objetos obtenidos durante la consulta.
     */
    @Override
    public List<T> distinct(int page)
    {
        provider.getQuery().select(path);
        provider.getQuery().distinct(true);
        return QueryHelper.getResultListPage(entityManager, (CriteriaQuery<T>)provider.getQuery(), page, 100);
    }

    /**
     * Ejecuta la consulta y obtiene todos los registros distintos, para la pagina dada
     * usando el tamanio de pagina dado.
     * @param page Un entero positivo (mayor que 0) indicando el numero de la pagina a obtener.
     * @param pageSize Un entero positivo (mayor que 0) indicando el tamanio de pagina a usar.
     * @return Una lista con todos los objetos obtenidos durante la consulta.
     */
    @Override
    public List<T> distinct(int page, int pageSize)
    {
        provider.getQuery().select(path);
        provider.getQuery().distinct(true);
        return QueryHelper.getResultListPage(entityManager, (CriteriaQuery<T>)provider.getQuery(), page, pageSize);
    }

    /**
     * Ejecuta la consulta y obtiene el primer registro.
     * @return El primer registro encontrado al ejecutar la consulta o bien
     * null si no se encontro ningun registro.
     */
    @Override
    public T first()
    {
        provider.getQuery().select(path);
        return QueryHelper.getSingleResult(entityManager, (CriteriaQuery<T>)provider.getQuery());
    }

    /**
     * Permite hacer la negacion del siguiente filtro.
     * @return El objeto this.
     */
    @Override
    public AbstractQuery<T> not()
    {
        not = true;
        return this;
    }

    @Override
    public AbstractQuery<T> asc(String field)
    {
        return orderBy(field, "asc");
    }

    @Override
    public AbstractQuery<T> desc(String field)
    {
        return orderBy(field, "desc");
    }

    @Override
    public AbstractQuery<T> orderBy(String field, String orderType)
    {
        if(field != null && !StringUtils.isBlank(field))
        {
            String[] f = field.split("\\.");
            Object ob = this;
            for (int i = 0; i < f.length - 1; i++) 
            {
                try 
                {
                    Method m = ob.getClass().getMethod(f[i]);
                    ob = m.invoke(ob);
                }
                catch (Exception ex) 
                {
                    Logger.getLogger(AbstractQuery.class.getName()).log(Level.SEVERE, null, ex);
                    break;
                }
            }

            try 
            {
                Method m = ob.getClass().getMethod(orderType + WordUtils.capitalize(f[f.length-1]));
                ob = m.invoke(ob);
            }
            catch (Exception ex) 
            {
                Logger.getLogger(AbstractQuery.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return this;
    }
}
