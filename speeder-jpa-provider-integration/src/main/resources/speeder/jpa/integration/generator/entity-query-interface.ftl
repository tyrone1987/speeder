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
import speeder.jpa.persistence.*;
import speeder.jpa.integration.*;

/**
 * Esta interface permite realizar consultas sobre las entidades ${entity.name} de
 * una manera facil mediante una interfaz fluida (Fluent Interface)
 */
public interface ${entity.name}Query extends EntityQuery<${entity.name}>
{
    <#list entity.fields as f>
    <#if f.type != "RELATION">
    public Expression<${f.javaType}> get${f.name?cap_first}Path();

    <#else>
    public Path<${f.javaType}> get${f.name?cap_first}Path();

    </#if>
    </#list>
    <#list entity.fields as f>
    /**
     * Agrega un nuevo filtro para el campo ${f.name} de la entidad.
     * @param value El valor por el que se debe filtrar el campo ${f.name}
     * @return El objeto this
     */
    public ${entity.name}Query ${f.name}(${f.javaType}... value);

    <#if f.javaType == "String">
    public ${entity.name}Query ${f.name}Sencitive(${f.javaType}... value);

    </#if>
    <#if f.isTemporalType >
    /**
     * Agrega un nuevo filtro para el campo ${f.name} de la entidad, mediante
     * el cual el valor de dicho campo debe estar entre el value1 y value2.
     * @param value1 Limite inferior del filtro.
     * @param value2 Limite superior del filtro.
     * @return El objeto this
     */
    public ${entity.name}Query ${f.name}Between(${f.javaType} value1, ${f.javaType} value2);

    /**
     * Agrega un nuevo filtro para el campo ${f.name} de la entidad, mediante
     * el cual el valor de dicho campo debe ser posterior a value.
     * @param value El valor para la comparacion.
     * @return El objeto this
     */
    public ${entity.name}Query ${f.name}After(${f.javaType} value);

    /**
     * Agrega un nuevo filtro para el campo ${f.name} de la entidad, mediante
     * el cual el valor de dicho campo debe ser posterior o igual a value.
     * @param value El valor para la comparacion.
     * @return El objeto this
     */
    public ${entity.name}Query ${f.name}AfterOrEqual(${f.javaType} value);

    /**
     * Agrega un nuevo filtro para el campo ${f.name} de la entidad, mediante
     * el cual el valor de dicho campo debe ser anterior a value.
     * @param value El valor para la comparacion.
     * @return El objeto this
     */
    public ${entity.name}Query ${f.name}Beford(${f.javaType} value);

    /**
     * Agrega un nuevo filtro para el campo ${f.name} de la entidad, mediante
     * el cual el valor de dicho campo debe ser anterior o igual a value.
     * @param value El valor para la comparacion.
     * @return El objeto this
     */
    public ${entity.name}Query ${f.name}BefordOrEqual(${f.javaType} value);

    </#if>
    <#if f.isNumericType >
    /**
     * Agrega un nuevo filtro para el campo ${f.name} de la entidad, mediante
     * el cual el valor de dicho campo debe ser mayor que value.
     * @param value El valor para la comparacion.
     * @return El objeto this
     */
    public ${entity.name}Query ${f.name}GreaterThan(${f.javaType} value);

    /**
     * Agrega un nuevo filtro para el campo ${f.name} de la entidad, mediante
     * el cual el valor de dicho campo debe ser mayor o igual que value.
     * @param value El valor para la comparacion.
     * @return El objeto this
     */
    public ${entity.name}Query ${f.name}GreaterOrEqual(${f.javaType} value);

    /**
     * Agrega un nuevo filtro para el campo ${f.name} de la entidad, mediante
     * el cual el valor de dicho campo debe ser menor que value.
     * @param value El valor para la comparacion.
     * @return El objeto this
     */
    public ${entity.name}Query ${f.name}LessThan(${f.javaType} value);

    /**
     * Agrega un nuevo filtro para el campo ${f.name} de la entidad, mediante
     * el cual el valor de dicho campo debe ser menor o igual que value.
     * @param value El valor para la comparacion.
     * @return El objeto this
     */
    public ${entity.name}Query ${f.name}LessOrEqual(${f.javaType} value);

    </#if>
    <#if f.type == "RELATION">
    /**
     * Obtiene un objeto ${f.entity}Query con el cual se pueden consultar los campos
     * de los objetos ${f.entity} asociados con los objetos ${entity.name} mediante
     * el campo ${f.name}.
     * @return El objeto this
     */
    public ${f.entity}Query ${f.name}();

    </#if>
    </#list>
    /**
     * Ejecuta la consulta y obtiene todos los valores del campo id.
     * @return Una lista con todos los valores del campo id obtenidos durante la consulta.
     */
    public List<Long> allId();

    /**
     * Ejecuta la consulta y obtiene todos los valores del campo id, para la pagina dada
     * usando el tamanio de pagina por defecto.
     * @param page Un entero positivo (mayor que 0) indicando el numero de la pagina a obtener.
     * @return Una lista con todos los valores del campo id obtenidos durante la consulta.
     */
    public List<Long> allId(int page);

    /**
     * Ejecuta la consulta y obtiene todos los valores del campo id, para la pagina dada
     * usando el tamanio de pagina dado.
     * @param page Un entero positivo (mayor que 0) indicando el numero de la pagina a obtener.
     * @param pageSize Un entero positivo (mayor que 0) indicando el tamanio de pagina a usar.
     * @return Una lista con todos los valores del campo id obtenidos durante la consulta.
     */
    public List<Long> allId(int page, int pageSize);

    /**
     * Ejecuta la consulta y obtiene todos los valores distintos del campo id.
     * @return Una lista con todos los valores del campo id obtenidos durante la consulta.
     */
    public List<Long> distinctId();

    /**
     * Ejecuta la consulta y obtiene todos los valores distintos del campo id, para la pagina dada
     * usando el tamanio de pagina por defecto.
     * @param page Un entero positivo (mayor que 0) indicando el numero de la pagina a obtener.
     * @return Una lista con todos los valores del campo id obtenidos durante la consulta.
     */
    public List<Long> distinctId(int page);

    /**
     * Ejecuta la consulta y obtiene todos los valores distintos del campo id, para la pagina dada
     * usando el tamanio de pagina dado.
     * @param page Un entero positivo (mayor que 0) indicando el numero de la pagina a obtener.
     * @param pageSize Un entero positivo (mayor que 0) indicando el tamanio de pagina a usar.
     * @return Una lista con todos los valores del campo id obtenidos durante la consulta.
     */
    public List<Long> distinctId(int page, int pageSize);

    /**
     * Ejecuta la consulta y obtiene el primer valor del campo id.
     * @return El primer valor del campo id encontrado al ejecutar la consulta o bien
     * null si no se encontro ningun registro.
     */
    public Long firstId();

    <#list entity.fields as f>
    /**
     * Ejecuta la consulta y obtiene todos los valores del campo ${f.name}.
     * @return Una lista con todos los valores del campo ${f.name} obtenidos durante la consulta.
     */
    public List<${f.javaType}> all${f.name?cap_first}();

    /**
     * Ejecuta la consulta y obtiene todos los valores del campo ${f.name}, para la pagina dada
     * usando el tamanio de pagina por defecto.
     * @param page Un entero positivo (mayor que 0) indicando el numero de la pagina a obtener.
     * @return Una lista con todos los valores del campo ${f.name} obtenidos durante la consulta.
     */
    public List<${f.javaType}> all${f.name?cap_first}(int page);

    /**
     * Ejecuta la consulta y obtiene todos los valores del campo ${f.name}, para la pagina dada
     * usando el tamanio de pagina dado.
     * @param page Un entero positivo (mayor que 0) indicando el numero de la pagina a obtener.
     * @param pageSize Un entero positivo (mayor que 0) indicando el tamanio de pagina a usar.
     * @return Una lista con todos los valores del campo ${f.name} obtenidos durante la consulta.
     */
    public List<${f.javaType}> all${f.name?cap_first}(int page, int pageSize);

    <#if f.type != "long_string" >
    /**
     * Ejecuta la consulta y obtiene todos los valores distintos del campo ${f.name}.
     * @return Una lista con todos los valores del campo ${f.name} obtenidos durante la consulta.
     */
    public List<${f.javaType}> distinct${f.name?cap_first}();

    /**
     * Ejecuta la consulta y obtiene todos los valores distintos del campo ${f.name}, para la pagina dada
     * usando el tamanio de pagina por defecto.
     * @param page Un entero positivo (mayor que 0) indicando el numero de la pagina a obtener.
     * @return Una lista con todos los valores del campo ${f.name} obtenidos durante la consulta.
     */
    public List<${f.javaType}> distinct${f.name?cap_first}(int page);

    /**
     * Ejecuta la consulta y obtiene todos los valores distintos del campo ${f.name}, para la pagina dada
     * usando el tamanio de pagina dado.
     * @param page Un entero positivo (mayor que 0) indicando el numero de la pagina a obtener.
     * @param pageSize Un entero positivo (mayor que 0) indicando el tamanio de pagina a usar.
     * @return Una lista con todos los valores del campo ${f.name} obtenidos durante la consulta.
     */
    public List<${f.javaType}> distinct${f.name?cap_first}(int page, int pageSize);

    </#if>
    /**
     * Ejecuta la consulta y obtiene el primer valor del campo ${f.name}.
     * @return El primer valor del campo ${f.name} encontrado al ejecutar la consulta o bien
     * null si no se encontro ningun registro.
     */
    public ${f.javaType} first${f.name?cap_first}();

    <#if f.isNumericType >
    /**
     * Ejecuta la consulta y obtiene la suma de todos los valores del campo ${f.name}.
     * @return La suma de todos los valores del campo ${f.name}.
     */
    public ${f.javaType} sum${f.name?cap_first}();

    </#if>
    <#if f.isTemporalType || f.isNumericType >
    /**
     * Ejecuta la consulta y obtiene el minimo valor del campo ${f.name} entontrado.
     * @return El valor minimo del campo ${f.name} o null si no se encontro ningun registro.
     */
    public ${f.javaType} min${f.name?cap_first}();

    /**
     * Ejecuta la consulta y obtiene el maximo valor del campo ${f.name} entontrado.
     * @return El valor maximo del campo ${f.name} o null si no se encontro ningun registro.
     */
    public ${f.javaType} max${f.name?cap_first}();

    </#if>
    </#list>
    <#list entity.fields as f>
    /**
     * Ordena los registros ascendentemente por el campo ${f.name}
     * @return El objeto this.
     */
    public ${entity.name}Query asc${f.name?cap_first}();

    /**
     * Ordena los registros descendentemente por el campo ${f.name}
     * @return El objeto this.
     */
    public ${entity.name}Query desc${f.name?cap_first}();

    </#list>
}