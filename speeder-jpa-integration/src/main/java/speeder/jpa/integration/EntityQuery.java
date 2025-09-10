/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package io.github.tyrone1987.integration;

import java.util.List;
import io.github.tyrone1987.persistence.AbstractEntity;
import io.github.tyrone1987.persistence.IEntity;

/**
 *
 * @author gilberto
 */
public interface EntityQuery<T extends IEntity>
{

    /**
     * Ejecuta la consulta y obtiene todos los registros.
     * @return Una lista con todos los objetos obtenidos durante la consulta.
     */
    List<T> all();

    /**
     * Ejecuta la consulta y obtiene todos los registros, para la pagina dada
     * usando el tamanio de pagina por defecto.
     * @param page Un entero positivo (mayor que 0) indicando el numero de la pagina a obtener.
     * @return Una lista con todos los objetos obtenidos durante la consulta.
     */
    List<T> all(int page);

    /**
     * Ejecuta la consulta y obtiene todos los registros, para la pagina dada
     * usando el tamanio de pagina dado.
     * @param page Un entero positivo (mayor que 0) indicando el numero de la pagina a obtener.
     * @param pageSize Un entero positivo (mayor que 0) indicando el tamanio de pagina a usar.
     * @return Una lista con todos los objetos obtenidos durante la consulta.
     */
    List<T> all(int page, int pageSize);

    /**
     * Ejecuta la consulta y obtiene todos los valores del campo id.
     * @return Una lista con todos los valores del campo id obtenidos durante la consulta.
     */
    List<Long> allId();

    /**
     * Ejecuta la consulta y obtiene todos los valores del campo id, para la pagina dada
     * usando el tamanio de pagina por defecto.
     * @param page Un entero positivo (mayor que 0) indicando el numero de la pagina a obtener.
     * @return Una lista con todos los valores del campo id obtenidos durante la consulta.
     */
    List<Long> allId(int page);

    /**
     * Ejecuta la consulta y obtiene todos los valores del campo id, para la pagina dada
     * usando el tamanio de pagina dado.
     * @param page Un entero positivo (mayor que 0) indicando el numero de la pagina a obtener.
     * @param pageSize Un entero positivo (mayor que 0) indicando el tamanio de pagina a usar.
     * @return Una lista con todos los valores del campo id obtenidos durante la consulta.
     */
    List<Long> allId(int page, int pageSize);

    AbstractQuery<T> asc(String field);

    /**
     * Obtiene la cantidad de registros que se obtendran mediante esta consulta.
     * @return El objeto this
     */
    Long count();

    AbstractQuery<T> desc(String field);

    /**
     * Ejecuta la consulta y obtiene todos los registros distintos.
     * @return Una lista con todos los objetos obtenidos durante la consulta.
     */
    List<T> distinct();

    /**
     * Ejecuta la consulta y obtiene todos los registros distintos, para la pagina dada
     * usando el tamanio de pagina por defecto.
     * @param page Un entero positivo (mayor que 0) indicando el numero de la pagina a obtener.
     * @return Una lista con todos los objetos obtenidos durante la consulta.
     */
    List<T> distinct(int page);

    /**
     * Ejecuta la consulta y obtiene todos los registros distintos, para la pagina dada
     * usando el tamanio de pagina dado.
     * @param page Un entero positivo (mayor que 0) indicando el numero de la pagina a obtener.
     * @param pageSize Un entero positivo (mayor que 0) indicando el tamanio de pagina a usar.
     * @return Una lista con todos los objetos obtenidos durante la consulta.
     */
    List<T> distinct(int page, int pageSize);

    /**
     * Ejecuta la consulta y obtiene todos los valores distintos del campo id.
     * @return Una lista con todos los valores del campo id obtenidos durante la consulta.
     */
    List<Long> distinctId();

    /**
     * Ejecuta la consulta y obtiene todos los valores distintos del campo id, para la pagina dada
     * usando el tamanio de pagina por defecto.
     * @param page Un entero positivo (mayor que 0) indicando el numero de la pagina a obtener.
     * @return Una lista con todos los valores del campo id obtenidos durante la consulta.
     */
    List<Long> distinctId(int page);

    /**
     * Ejecuta la consulta y obtiene todos los valores distintos del campo id, para la pagina dada
     * usando el tamanio de pagina dado.
     * @param page Un entero positivo (mayor que 0) indicando el numero de la pagina a obtener.
     * @param pageSize Un entero positivo (mayor que 0) indicando el tamanio de pagina a usar.
     * @return Una lista con todos los valores del campo id obtenidos durante la consulta.
     */
    List<Long> distinctId(int page, int pageSize);

    /**
     * Ejecuta la consulta y obtiene el primer registro.
     * @return El primer registro encontrado al ejecutar la consulta o bien
     * null si no se encontro ningun registro.
     */
    T first();

    /**
     * Ejecuta la consulta y obtiene el primer valor del campo id.
     * @return El primer valor del campo id encontrado al ejecutar la consulta o bien
     * null si no se encontro ningun registro.
     */
    Long firstId();

    /**
     * Agrega un nuevo filtro para el campo id de la entidad.
     * @param value El valor por el que se debe filtrar el campo id
     * @return El objeto this
     */
    AbstractQuery<T> id(Long... value);

    /**
     * Permite hacer la negacion del siguiente filtro.
     * @return El objeto this.
     */
    AbstractQuery<T> not();

    AbstractQuery<T> orderBy(String field, String orderType);
    
}
