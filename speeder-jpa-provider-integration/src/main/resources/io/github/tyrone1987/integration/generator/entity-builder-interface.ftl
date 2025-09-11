package ${dataModel.package}.integration;

import ${dataModel.package}.persistence.*;

<#if dataModel.imports??>
    <#list dataModel.imports as i>
import ${i.packageName}.persiscence.*;
import ${i.packageName}.integration.*;
    </#list>
</#if>

import jakarta.persistence.EntityManager;
import org.apache.commons.lang.StringUtils;
import java.math.BigDecimal;
import java.util.Date;
import io.github.tyrone1987.persistence.*;
import io.github.tyrone1987.integration.*;

/**
 * Esta clase permite manipular un objeto ${entity.name} de manera facil mediante
 * una interfaz fluida (Fluent Interface)
 */
public interface ${entity.name}Builder extends EntityBuilder<${entity.name}>
{
    <#list entity.fields as f>
    /**
     * Establece la propiedad ${f.name} y retorna una referencia al objeto this para
     * continuas modificaciones.
     * @param value Un objeto ${f.javaType} con el valor de la propiedad ${f.name} que se establecer.
     * @return Una referencia al objeto this para continuas modificaciones.
     */
    public ${entity.name}Builder ${f.name}(${f.javaType} value);

    /**
     * Obtiene la propiedad ${f.name} del objeto subyacente.
     * @return Un ${f.javaType} que representa el valor de la propiedad ${f.name}
     * del objeto subyacente.
     */
    public ${f.javaType} ${f.name}();

    <#if f.isNumericType >
    /**
     * Agrega la cantidad dada a la propiedad ${f.name} y retorna una referencia al objeto this para
     * continuas modificaciones.
     * @param value El valor ${f.javaType} a agregar a la propiedad ${f.name}.
     * @return Una referencia al objeto this para continuas modificaciones.
     */
    public ${entity.name}Builder add${f.name?cap_first}(${f.javaType} value);

    </#if>
    <#if f.type == "RELATION">
    /**
     * Obtiene un objeto ${entity.name}Builder para manipular la entidad 
     * del campo ${f.name}.
     * @return Un nuevo objeto ${entity.name}Builder con el valor del campo ${f.name}.
     */
    public ${f.entity}Builder ${f.name}Builder();

    /**
     * Sustituye la entidad actual ${f.name} de la relacion por la misma entidad
     * obtenida de la Base de Datos y retorna una referencia al objeto this para
     * continuas modificaciones.
     * @return Una referencia al objeto this para continuas modificaciones.
     */
    public ${entity.name}Builder refresh${f.name?cap_first}();

    /**
     * Guarda el objeto ${f.entity} de la relacion ${f.name} en la base de datos.
     * @return Una referencia al objeto this para continuas modificaciones.
     */
    public ${entity.name}Builder save${f.name?cap_first}();

    /**
     * Valida el objeto ${f.entity} de la relacion ${f.name} de manera que cumpla con 
     * las reglas del modelo para campos requeridos y el tamaño de las cadenas de texto.
     * @return Una referencia al objeto this para continuas modificaciones.
     */
    public ${entity.name}Builder check${f.name?cap_first}() throws IntegrationException;

    /**
     * Elimina el objeto ${f.entity} de la relacion ${f.name} de la base de datos.
     * @return Una referencia al objeto this para continuas modificaciones.
     */
    public ${entity.name}Builder delete${f.name?cap_first}();

    /**
     * Elimina el objeto ${f.entity} de la relacion ${f.name} de la base de datos,
     * y establece el valor de la relacion a null en la entidad interna.
     * @return Una referencia al objeto this para continuas modificaciones.
     */
    public ${entity.name}Builder delete${f.name?cap_first}ToNull();

    </#if>
    </#list>
}