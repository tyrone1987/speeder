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
public class ${entity.name}Filter extends AbstractFilter implements Serializable, EntityFilter
{
    /**
     * Determina si se debe filtrar por el id de la entidad.
     * @return true El el filtro se aplicara en el campo id de la entidad, false en caso contrario.
     */
    private boolean filterId = false;

    /**
     * Determina el valor a usar cuando se filtre por el campo id de la entidad
     * @return El valor por el cual se debe filtrar el campo id
     */
    private Long id;

    <#list entity.fields as f>
    /**
     * Determina el valor a usar cuando se filtre por el campo ${f.name} de la entidad
     * @return El valor por el cual se debe filtrar el campo ${f.name}
     */
    private ${f.javaType} ${f.name};

    /**
     * Determina si se debe filtrar por el ${f.name} de la entidad.
     * @return true Si el filtro se aplicara en el campo ${f.name} de la entidad, false en caso contrario.
     */
    private boolean filter${f.name?cap_first} = false;

    <#if f.isTemporalType || f.isNumericType>
    /**
     * Determina el rango de valores a usar cuando se filtre por el campo ${f.name} de la entidad
     * @return El rango de valores por el cual se debe filtrar el campo ${f.name}
     */
    private RangeFilter<${f.javaType}> ${f.name}Range = new RangeFilter<${f.javaType}>();

    /**
     * Determina si se debe filtrar por el rango de valores de ${f.name} de la entidad.
     * @return true Si el filtro se aplicara en el campo rango de valores de ${f.name} de la entidad, false en caso contrario.
     */
    private boolean filter${f.name?cap_first}Range = false;
    
    </#if>
    </#list>
    /**
     * Constructor por defecto
     */
    public ${entity.name}Filter()
    {
    }

    /**
     * Determina si se debe filtrar por el id de la entidad
     * @return true El el filtro se aplicara en el campo id de la entidad, false en caso contrario.
     */
    @Override
    public boolean getFilterId()
    {
        return this.filterId;
    }

    /**
     * Especifica si se debe filtrar por el id de la entidad.
     * @return true El el filtro se aplicara en el campo id de la entidad, false en caso contrario.
     */
    @Override
    public void setfilterId(boolean filterId)
    {
        this.filterId = filterId;
    }

    /**
     * Determina el valor a usar cuando se filtre por el campo id de la entidad
     * @return El valor por el cual se debe filtrar el campo id
     */
    @Override
    public Long getId()
    {
        return this.id;
    }

    /**
     * Especifica el valor a usar cuando se filtre por el campo id de la entidad
     * @param id El valor por el cual se debe filtrar el campo id
     */
    @Override
    public void setId(Long id)
    {
        this.id = id;
    }

    <#list entity.fields as f>
    /**
     * Determina el valor a usar cuando se filtre por el campo ${f.name} de la entidad
     * @return El valor por el cual se debe filtrar el campo ${f.name}
     */
    public ${f.javaType} get${f.name?cap_first}()
    {
        return this.${f.name};
    }

    /**
     * Establece el valor a usar cuando se filtre por el campo ${f.name} de la entidad
     * @param ${f.name} El valor por el cual se debe filtrar el campo ${f.name}
     */
    public void set${f.name?cap_first}(${f.javaType} ${f.name})
    {
        this.${f.name} = ${f.name};
    }

    /**
     * Determina si se debe filtrar por el campo ${f.name} de la entidad.
     * @return true El el filtro se aplicara en el campo id de la entidad, false en caso contrario.
     */
    public boolean getFilter${f.name?cap_first}()
    {
        return this.filter${f.name?cap_first};
    }

    /**
     * Establece si se debe filtrar por el campo ${f.name} de la entidad.
     * @param filter${f.name?cap_first} true El el filtro se aplicara en el campo ${f.name} de la entidad, false en caso contrario.
     */
    public void setFilter${f.name?cap_first}(boolean filter${f.name?cap_first})
    {
        this.filter${f.name?cap_first} = filter${f.name?cap_first};
    }

    <#if f.isTemporalType || f.isNumericType>
    /**
     * Determina el rango de valores a usar cuando se filtre por el campo ${f.name} de la entidad
     * @return El rango de valores por el cual se debe filtrar el campo ${f.name}
     */
    public RangeFilter<${f.javaType}> get${f.name?cap_first}Range()
    {
        return this.${f.name}Range;
    }

    /**
     * Determina si se debe filtrar por el rango de valores del campo ${f.name} de la entidad.
     * @return true Si el filtro se aplicara en el rango de valores del campo ${f.name} de la entidad, false en caso contrario.
     */
    public boolean getFilter${f.name?cap_first}Range()
    {
        return this.filter${f.name?cap_first}Range;
    }

    /**
     * Establece si se debe filtrar por el campo ${f.name} de la entidad.
     * @param filter${f.name?cap_first} true Si el filtro se aplicara en el rango de valores del campo ${f.name} de la entidad, false en caso contrario.
     */
    public void setFilter${f.name?cap_first}Range(boolean filter${f.name?cap_first}Range)
    {
        this.filter${f.name?cap_first}Range = filter${f.name?cap_first}Range;
    }

    </#if>
    </#list>
    /**
     * Determina si el objeto actual establece algun filtro
     * @return true El objeto actual establece al menos un filtro para la consulta, 
     * false El objeto actual no establece ningun filtro para la consulta
     */
    @Override
    public boolean getHasFilter()
    {
        return filterId
            <#list entity.fields as f>
            <#if f.isTemporalType || f.isNumericType>
            || filter${f.name?cap_first}Range
            </#if>
            || filter${f.name?cap_first}<#if !f_has_next>;</#if>
            </#list>
    }
}