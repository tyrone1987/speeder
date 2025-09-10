package ${dataModel.package}.integration;

import ${dataModel.package}.persistence.*;

<#if dataModel.imports??>
    <#list dataModel.imports as i>
import ${i.packageName}.persiscence.*;
import ${i.packageName}.integration.*;
    </#list>
</#if>

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Path;
import speeder.jpa.integration.EntityQuery;
import speeder.jpa.integration.AbstractQuery;
import speeder.jpa.integration.QueryHelper;
import speeder.jpa.integration.QueryProvider;

/**
 * Esta clase permite realizar consultas sobre las entidades ${entity.name} de
 * una manera facil mediante una interfaz fluida (Fluent Interface)
 */
public class ${entity.name}Query extends AbstractQuery<${entity.name}> implements EntityQuery<${entity.name}>
{
    private Expression<Long> id;

    <#list entity.fields as f>
    <#if f.type != "RELATION">
    private Expression<${f.javaType}> ${f.name};
    <#else>
    private Path<${f.javaType}> ${f.name};
    </#if>

    </#list>
    /**
     * Crea una nueva instancia de esta clase con la cual realizar una consulta
     * a la base de datos sobre la entidad ${entity.name}.
     * @param entityManager El contexto de persistencia a usar
     * @return Un nuevo objeto ${entity.name}Query.
     */
    public static ${entity.name}Query filter(EntityManager entityManager)
    {
        CriteriaQuery q = entityManager.getCriteriaBuilder().createQuery();
        return new ${entity.name}Query(entityManager, q.from(${entity.name}.class), q, false);
    }

    /**
     * Crea una nueva instancia de esta clase con la cual realizar una consulta
     * a la base de datos sobre la entidad ${entity.name}.
     * @param entityManager El contexto de persistencia a usar
     * @return Un nuevo objeto ${entity.name}Query.
     */
    public static ${entity.name}Query search(EntityManager entityManager)
    {
        CriteriaQuery q = entityManager.getCriteriaBuilder().createQuery();
        return new ${entity.name}Query(entityManager, q.from(${entity.name}.class), q, true);
    }

    /**
     * Crea una nueva instancia de esta clase con la cual realizar una consulta
     * a la base de datos sobre la entidad ${entity.name}.
     * @param entityManager El contexto de persistencia a usar
     * @return Un nuevo objeto ${entity.name}Query.
     */
    public static ${entity.name}Query filter(EntityManager entityManager, ${entity.name}Filter filter)
    {
        ${entity.name}Query query = filter(entityManager);
        query.apply(filter);
        return query;
    }

    public void apply(${entity.name}Filter filter)
    {
        if(filter.getHasFilter())
        {
            if(filter.getFilterId())
            {
                id(filter.getId());
            }

            <#list entity.fields as f>
            if(filter.getFilter${f.name?cap_first}())
            {
                ${f.name}(filter.get${f.name?cap_first}());
            }

            <#if f.isTemporalType>
            if(filter.getFilter${f.name?cap_first}Range())
            {
                if(filter.get${f.name?cap_first}Range().getFrom() != null)
                {
                    ${f.name}AfterOrEqual(filter.get${f.name?cap_first}Range().getFrom());
                }

                if(filter.get${f.name?cap_first}Range().getTo() != null)
                {
                    ${f.name}BefordOrEqual(filter.get${f.name?cap_first}Range().getTo());
                }
            }

            <#elseif f.isNumericType>
            if(filter.getFilter${f.name?cap_first}Range())
            {
                if(filter.get${f.name?cap_first}Range().getFrom() != null)
                {
                    ${f.name}LessOrEqual(filter.get${f.name?cap_first}Range().getFrom());
                }

                if(filter.get${f.name?cap_first}Range().getTo() != null)
                {
                    ${f.name}GreaterOrEqual(filter.get${f.name?cap_first}Range().getTo());
                }
            }

            </#if>
            </#list>
        }
    }

    /**
     * Constructor que crea una nueva instancia a partir de una consulta ya inicializada.
     * Este constructor no debe ser usado en cambio vease el metodo estatico find.
     * @param entityManager El contexto de persistencia a usar.
     * @param path El path a usar en la consulta.
     * @param provider La consulta a usar por esta instancia.
     */
    public ${entity.name}Query(EntityManager entityManager, Path<${entity.name}> path, CriteriaQuery query, boolean or)
    {
        super(entityManager, path, query, or);
    }

    /**
     * Constructor que crea una nueva instancia a partir de una consulta ya inicializada.
     * Este constructor no debe ser usado en cambio vease el metodo estatico find.
     * @param entityManager El contexto de persistencia a usar.
     * @param path El path a usar en la consulta.
     * @param provider La consulta a usar por esta instancia.
     */
    public ${entity.name}Query(EntityManager entityManager, Path<${entity.name}> path, QueryProvider provider)
    {
        super(entityManager, path, provider);
    }

    public Expression<Long> getIdPath()
    {
        if(id == null)
        {
            id = path.get(${entity.name}_.id);
        }
        return id;
    }

    <#list entity.fields as f>
    <#if f.type != "RELATION">
    public Expression<${f.javaType}> get${f.name?cap_first}Path()
    {
        if(${f.name} == null)
        {
            ${f.name} = path.get(${entity.name}_.${f.name});
        }
        return ${f.name};
    }
    <#else>
    public Path<${f.javaType}> get${f.name?cap_first}Path()
    {
        if(${f.name} == null)
        {
            ${f.name} = path.get(${entity.name}_.${f.name});
        }
        return ${f.name};
    }

    </#if>
    </#list>
    /**
     * Agrega un nuevo filtro para el campo id de la entidad.
     * @param value El valor por el que se debe filtrar el campo id
     * @return El objeto this
     */
    @Override
    public ${entity.name}Query id(Long... value)
    {
        QueryHelper.equal(entityManager, provider, getIdPath(), not, value);
        not = false;
        return this;
    }

    <#list entity.fields as f>
    /**
     * Agrega un nuevo filtro para el campo ${f.name} de la entidad.
     * @param value El valor por el que se debe filtrar el campo ${f.name}
     * @return El objeto this
     */
    public ${entity.name}Query ${f.name}(${f.javaType}... value)
    {
        <#if f.javaType == "String">
        QueryHelper.like(entityManager, provider, get${f.name?cap_first}Path(), not, value);
        <#else>
        QueryHelper.equal(entityManager, provider, get${f.name?cap_first}Path(), not, value);
        </#if>
        not = false;
        return this;
    }

    <#if f.javaType == "String">
    public ${entity.name}Query ${f.name}Sencitive(${f.javaType}... value)
    {
        QueryHelper.likeSencitive(entityManager, provider, get${f.name?cap_first}Path(), not, value);
        not = false;
        return this;
    }

    </#if>
    <#if f.isTemporalType >
    /**
     * Agrega un nuevo filtro para el campo ${f.name} de la entidad, mediante
     * el cual el valor de dicho campo debe estar entre el value1 y value2.
     * @param value1 Limite inferior del filtro.
     * @param value2 Limite superior del filtro.
     * @return El objeto this
     */
    public ${entity.name}Query ${f.name}Between(${f.javaType} value1, ${f.javaType} value2)
    {
        QueryHelper.between(entityManager, provider, get${f.name?cap_first}Path(), value1, value2, not);
        not = false;
        return this;
    }

    /**
     * Agrega un nuevo filtro para el campo ${f.name} de la entidad, mediante
     * el cual el valor de dicho campo debe ser posterior a value.
     * @param value El valor para la comparacion.
     * @return El objeto this
     */
    public ${entity.name}Query ${f.name}After(${f.javaType} value)
    {
        QueryHelper.greaterThan(entityManager, provider, get${f.name?cap_first}Path(), value, not);
        not = false;
        return this;
    }

    /**
     * Agrega un nuevo filtro para el campo ${f.name} de la entidad, mediante
     * el cual el valor de dicho campo debe ser posterior o igual a value.
     * @param value El valor para la comparacion.
     * @return El objeto this
     */
    public ${entity.name}Query ${f.name}AfterOrEqual(${f.javaType} value)
    {
        QueryHelper.greaterThanOrEqualTo(entityManager, provider, get${f.name?cap_first}Path(), value, not);
        not = false;
        return this;
    }

    /**
     * Agrega un nuevo filtro para el campo ${f.name} de la entidad, mediante
     * el cual el valor de dicho campo debe ser anterior a value.
     * @param value El valor para la comparacion.
     * @return El objeto this
     */
    public ${entity.name}Query ${f.name}Beford(${f.javaType} value)
    {
        QueryHelper.lessThan(entityManager, provider, get${f.name?cap_first}Path(), value, not);
        not = false;
        return this;
    }

    /**
     * Agrega un nuevo filtro para el campo ${f.name} de la entidad, mediante
     * el cual el valor de dicho campo debe ser anterior o igual a value.
     * @param value El valor para la comparacion.
     * @return El objeto this
     */
    public ${entity.name}Query ${f.name}BefordOrEqual(${f.javaType} value)
    {
        QueryHelper.lessThanOrEqualTo(entityManager, provider, get${f.name?cap_first}Path(), value, not);
        not = false;
        return this;
    }

    </#if>
    <#if f.isNumericType >
    /**
     * Agrega un nuevo filtro para el campo ${f.name} de la entidad, mediante
     * el cual el valor de dicho campo debe ser mayor que value.
     * @param value El valor para la comparacion.
     * @return El objeto this
     */
    public ${entity.name}Query ${f.name}GreaterThan(${f.javaType} value)
    {
        QueryHelper.gt(entityManager, provider, get${f.name?cap_first}Path(), value, not);
        not = false;
        return this;
    }

    /**
     * Agrega un nuevo filtro para el campo ${f.name} de la entidad, mediante
     * el cual el valor de dicho campo debe ser mayor o igual que value.
     * @param value El valor para la comparacion.
     * @return El objeto this
     */
    public ${entity.name}Query ${f.name}GreaterOrEqual(${f.javaType} value)
    {
        QueryHelper.ge(entityManager, provider, get${f.name?cap_first}Path(), value, not);
        not = false;
        return this;
    }

    /**
     * Agrega un nuevo filtro para el campo ${f.name} de la entidad, mediante
     * el cual el valor de dicho campo debe ser menor que value.
     * @param value El valor para la comparacion.
     * @return El objeto this
     */
    public ${entity.name}Query ${f.name}LessThan(${f.javaType} value)
    {
        QueryHelper.lt(entityManager, provider, get${f.name?cap_first}Path(), value, not);
        not = false;
        return this;
    }

    /**
     * Agrega un nuevo filtro para el campo ${f.name} de la entidad, mediante
     * el cual el valor de dicho campo debe ser menor o igual que value.
     * @param value El valor para la comparacion.
     * @return El objeto this
     */
    public ${entity.name}Query ${f.name}LessOrEqual(${f.javaType} value)
    {
        QueryHelper.le(entityManager, provider, get${f.name?cap_first}Path(), value, not);
        not = false;
        return this;
    }

    </#if>
    <#if f.type == "RELATION">
    /**
     * Obtiene un objeto ${f.entity}Query con el cual se pueden consultar los campos
     * de los objetos ${f.entity} asociados con los objetos ${entity.name} mediante
     * el campo ${f.name}.
     * @return El objeto this
     */
    public ${f.entity}Query ${f.name}()
    {
        return new ${f.entity}Query(entityManager, get${f.name?cap_first}Path(), provider);
    }

    </#if>
    </#list>
    /**
     * Ejecuta la consulta y obtiene todos los valores del campo id.
     * @return Una lista con todos los valores del campo id obtenidos durante la consulta.
     */
    @Override
    public List<Long> allId()
    {
        provider.getQuery().select(path.get(${entity.name}_.id));
        provider.getQuery().distinct(false);
        return QueryHelper.getResultListUnlimit(entityManager, (CriteriaQuery<Long>)provider.getQuery());
    }

    /**
     * Ejecuta la consulta y obtiene todos los valores del campo id, para la pagina dada
     * usando el tamanio de pagina por defecto.
     * @param page Un entero positivo (mayor que 0) indicando el numero de la pagina a obtener.
     * @return Una lista con todos los valores del campo id obtenidos durante la consulta.
     */
    @Override
    public List<Long> allId(int page)
    {
        provider.getQuery().select(path.get(${entity.name}_.id));
        provider.getQuery().distinct(false);
        return QueryHelper.getResultListPage(entityManager, (CriteriaQuery<Long>)provider.getQuery(), page, 100);
    }

    /**
     * Ejecuta la consulta y obtiene todos los valores del campo id, para la pagina dada
     * usando el tamanio de pagina dado.
     * @param page Un entero positivo (mayor que 0) indicando el numero de la pagina a obtener.
     * @param pageSize Un entero positivo (mayor que 0) indicando el tamanio de pagina a usar.
     * @return Una lista con todos los valores del campo id obtenidos durante la consulta.
     */
    @Override
    public List<Long> allId(int page, int pageSize)
    {
        provider.getQuery().select(path.get(${entity.name}_.id));
        provider.getQuery().distinct(false);
        return QueryHelper.getResultListPage(entityManager, (CriteriaQuery<Long>)provider.getQuery(), page, pageSize);
    }

    /**
     * Ejecuta la consulta y obtiene todos los valores distintos del campo id.
     * @return Una lista con todos los valores del campo id obtenidos durante la consulta.
     */
    @Override
    public List<Long> distinctId()
    {
        provider.getQuery().select(path.get(${entity.name}_.id));
        provider.getQuery().distinct(true);
        return QueryHelper.getResultListUnlimit(entityManager, (CriteriaQuery<Long>)provider.getQuery());
    }

    /**
     * Ejecuta la consulta y obtiene todos los valores distintos del campo id, para la pagina dada
     * usando el tamanio de pagina por defecto.
     * @param page Un entero positivo (mayor que 0) indicando el numero de la pagina a obtener.
     * @return Una lista con todos los valores del campo id obtenidos durante la consulta.
     */
    @Override
    public List<Long> distinctId(int page)
    {
        provider.getQuery().select(path.get(${entity.name}_.id));
        provider.getQuery().distinct(true);
        return QueryHelper.getResultListPage(entityManager, (CriteriaQuery<Long>)provider.getQuery(), page, 100);
    }

    /**
     * Ejecuta la consulta y obtiene todos los valores distintos del campo id, para la pagina dada
     * usando el tamanio de pagina dado.
     * @param page Un entero positivo (mayor que 0) indicando el numero de la pagina a obtener.
     * @param pageSize Un entero positivo (mayor que 0) indicando el tamanio de pagina a usar.
     * @return Una lista con todos los valores del campo id obtenidos durante la consulta.
     */
    @Override
    public List<Long> distinctId(int page, int pageSize)
    {
        provider.getQuery().select(path.get(${entity.name}_.id));
        provider.getQuery().distinct(true);
        return QueryHelper.getResultListPage(entityManager, (CriteriaQuery<Long>)provider.getQuery(), page, pageSize);
    }

    /**
     * Ejecuta la consulta y obtiene el primer valor del campo id.
     * @return El primer valor del campo id encontrado al ejecutar la consulta o bien
     * null si no se encontro ningun registro.
     */
    @Override
    public Long firstId()
    {
        provider.getQuery().select(path.get(${entity.name}_.id));
        return QueryHelper.getSingleResult(entityManager, (CriteriaQuery<Long>)provider.getQuery());
    }

    <#list entity.fields as f>
    /**
     * Ejecuta la consulta y obtiene todos los valores del campo ${f.name}.
     * @return Una lista con todos los valores del campo ${f.name} obtenidos durante la consulta.
     */
    public List<${f.javaType}> all${f.name?cap_first}()
    {
        provider.getQuery().select(path.get(${entity.name}_.${f.name}));
        provider.getQuery().distinct(false);
        return QueryHelper.getResultListUnlimit(entityManager, (CriteriaQuery<${f.javaType}>)provider.getQuery());
    }

    /**
     * Ejecuta la consulta y obtiene todos los valores del campo ${f.name}, para la pagina dada
     * usando el tamanio de pagina por defecto.
     * @param page Un entero positivo (mayor que 0) indicando el numero de la pagina a obtener.
     * @return Una lista con todos los valores del campo ${f.name} obtenidos durante la consulta.
     */
    public List<${f.javaType}> all${f.name?cap_first}(int page)
    {
        provider.getQuery().select(path.get(${entity.name}_.${f.name}));
        provider.getQuery().distinct(false);
        return QueryHelper.getResultListPage(entityManager, (CriteriaQuery<${f.javaType}>)provider.getQuery(), page, 100);
    }

    /**
     * Ejecuta la consulta y obtiene todos los valores del campo ${f.name}, para la pagina dada
     * usando el tamanio de pagina dado.
     * @param page Un entero positivo (mayor que 0) indicando el numero de la pagina a obtener.
     * @param pageSize Un entero positivo (mayor que 0) indicando el tamanio de pagina a usar.
     * @return Una lista con todos los valores del campo ${f.name} obtenidos durante la consulta.
     */
    public List<${f.javaType}> all${f.name?cap_first}(int page, int pageSize)
    {
        provider.getQuery().select(path.get(${entity.name}_.${f.name}));
        provider.getQuery().distinct(false);
        return QueryHelper.getResultListPage(entityManager, (CriteriaQuery<${f.javaType}>)provider.getQuery(), page, pageSize);
    }

    <#if f.type != "long_string" >
    /**
     * Ejecuta la consulta y obtiene todos los valores distintos del campo ${f.name}.
     * @return Una lista con todos los valores del campo ${f.name} obtenidos durante la consulta.
     */
    public List<${f.javaType}> distinct${f.name?cap_first}()
    {
        provider.getQuery().select(path.get(${entity.name}_.${f.name}));
        provider.getQuery().distinct(true);
        return QueryHelper.getResultListUnlimit(entityManager, (CriteriaQuery<${f.javaType}>)provider.getQuery());
    }

    /**
     * Ejecuta la consulta y obtiene todos los valores distintos del campo ${f.name}, para la pagina dada
     * usando el tamanio de pagina por defecto.
     * @param page Un entero positivo (mayor que 0) indicando el numero de la pagina a obtener.
     * @return Una lista con todos los valores del campo ${f.name} obtenidos durante la consulta.
     */
    public List<${f.javaType}> distinct${f.name?cap_first}(int page)
    {
        provider.getQuery().select(path.get(${entity.name}_.${f.name}));
        provider.getQuery().distinct(true);
        return QueryHelper.getResultListPage(entityManager, (CriteriaQuery<${f.javaType}>)provider.getQuery(), page, 100);
    }

    /**
     * Ejecuta la consulta y obtiene todos los valores distintos del campo ${f.name}, para la pagina dada
     * usando el tamanio de pagina dado.
     * @param page Un entero positivo (mayor que 0) indicando el numero de la pagina a obtener.
     * @param pageSize Un entero positivo (mayor que 0) indicando el tamanio de pagina a usar.
     * @return Una lista con todos los valores del campo ${f.name} obtenidos durante la consulta.
     */
    public List<${f.javaType}> distinct${f.name?cap_first}(int page, int pageSize)
    {
        provider.getQuery().select(path.get(${entity.name}_.${f.name}));
        provider.getQuery().distinct(true);
        return QueryHelper.getResultListPage(entityManager, (CriteriaQuery<${f.javaType}>)provider.getQuery(), page, pageSize);
    }

    </#if>
    /**
     * Ejecuta la consulta y obtiene el primer valor del campo ${f.name}.
     * @return El primer valor del campo ${f.name} encontrado al ejecutar la consulta o bien
     * null si no se encontro ningun registro.
     */
    public ${f.javaType} first${f.name?cap_first}()
    {
        provider.getQuery().select(path.get(${entity.name}_.${f.name}));
        return QueryHelper.getSingleResult(entityManager, (CriteriaQuery<${f.javaType}>)provider.getQuery());
    }

    <#if f.isNumericType >
    /**
     * Ejecuta la consulta y obtiene la suma de todos los valores del campo ${f.name}.
     * @return La suma de todos los valores del campo ${f.name}.
     */
    public ${f.javaType} sum${f.name?cap_first}()
    {
        provider.getQuery().select(entityManager.getCriteriaBuilder().sum(path.get(${entity.name}_.${f.name})));
        provider.getQuery().distinct(false);
        return QueryHelper.getSingleResult(entityManager, (CriteriaQuery<${f.javaType}>)provider.getQuery());
    }

    </#if>
    <#if f.isTemporalType || f.isNumericType >
    /**
     * Ejecuta la consulta y obtiene el minimo valor del campo ${f.name} entontrado.
     * @return El valor minimo del campo ${f.name} o null si no se encontro ningun registro.
     */
    public ${f.javaType} min${f.name?cap_first}()
    {
        provider.getQuery().select(entityManager.getCriteriaBuilder().least(path.get(${entity.name}_.${f.name})));
        return QueryHelper.getSingleResult(entityManager, (CriteriaQuery<${f.javaType}>)provider.getQuery());
    }

    /**
     * Ejecuta la consulta y obtiene el maximo valor del campo ${f.name} entontrado.
     * @return El valor maximo del campo ${f.name} o null si no se encontro ningun registro.
     */
    public ${f.javaType} max${f.name?cap_first}()
    {
        provider.getQuery().select(entityManager.getCriteriaBuilder().greatest(path.get(${entity.name}_.${f.name})));
        return QueryHelper.getSingleResult(entityManager, (CriteriaQuery<${f.javaType}>)provider.getQuery());
    }

    </#if>
    </#list>
    /**
     * Permite hacer la negacion del siguiente filtro.
     * @return El objeto this.
     */
    @Override
    public ${entity.name}Query not()
    {
        return (${entity.name}Query)super.not();
    }

    <#list entity.fields as f>
    /**
     * Ordena los registros ascendentemente por el campo ${f.name}
     * @return El objeto this.
     */    
    public ${entity.name}Query asc${f.name?cap_first}()
    {
        provider.getQuery().orderBy( entityManager.getCriteriaBuilder().asc( path.get(${entity.name}_.${f.name}) ));
        return this;
    }

    /**
     * Ordena los registros descendentemente por el campo ${f.name}
     * @return El objeto this.
     */
    public ${entity.name}Query desc${f.name?cap_first}()
    {
        provider.getQuery().orderBy( entityManager.getCriteriaBuilder().desc( path.get(${entity.name}_.${f.name}) ));
        return this;
    }

    </#list>
    @Override
    public ${entity.name}Query asc(String field)
    {
        return (${entity.name}Query)super.asc(field);
    }

    @Override
    public ${entity.name}Query desc(String field)
    {
        return (${entity.name}Query)super.desc(field);
    }

    @Override
    public ${entity.name}Query orderBy(String field, String orderType)
    {
        return (${entity.name}Query)super.orderBy(field, orderType);
    }
}