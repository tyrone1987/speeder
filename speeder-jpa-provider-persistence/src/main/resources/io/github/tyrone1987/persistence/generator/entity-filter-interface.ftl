package ${dataModel.package}.persistence;

<#if dataModel.imports??>
    <#list dataModel.imports as i>
import ${i.packageName}.persiscence.*;
    </#list>
</#if>

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;
import io.github.tyrone1987.persistence.*;

/**
 * Clase utilitaria para creacion de filtros personalizados por el usuario
 * para consultar datos de la entidad ${entity.name}.
 */
public interface ${entity.name}Filter extends EntityFilter
{
    <#list entity.fields as f>
    /**
     * Determina el valor a usar cuando se filtre por el campo ${f.name} de la entidad
     * @return El valor por el cual se debe filtrar el campo ${f.name}
     */
    public ${f.javaType} get${f.name?cap_first}();

    /**
     * Establece el valor a usar cuando se filtre por el campo ${f.name} de la entidad
     * @param ${f.name} El valor por el cual se debe filtrar el campo ${f.name}
     */
    public void set${f.name?cap_first}(${f.javaType} ${f.name});

    /**
     * Determina si se debe filtrar por el campo ${f.name} de la entidad.
     * @return true El el filtro se aplicara en el campo id de la entidad, false en caso contrario.
     */
    public boolean getFilter${f.name?cap_first}();

    /**
     * Establece si se debe filtrar por el campo ${f.name} de la entidad.
     * @param filter${f.name?cap_first} true El el filtro se aplicara en el campo id de la entidad, false en caso contrario.
     */
    public void setFilter${f.name?cap_first}(boolean filter${f.name?cap_first});

    </#list>
}