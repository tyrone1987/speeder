package ${dataModel.package}.persistence;

<#if dataModel.imports??>
    <#list dataModel.imports as i>
import ${i.packageName}.persiscence.*;
    </#list>
</#if>

import java.io.Serializable;
import java.util.Date;
import jakarta.persistence.*;
import java.math.BigDecimal;
import io.github.tyrone1987.persistence.*;

/**
 * Inteface para la entidad ${entity.name}
 */
public interface ${entity.name} extends IEntity
{
    <#list entity.fields as f>
    /**
     * Obtiene el campo ${f.name}
     * @return Un objeto ${f.javaType} que representa el valor del campo ${f.name}.
     */
    public ${f.javaType} get${f.name?cap_first}();

    /**
     * Establece el campo ${f.name}
     * @param ${f.name} Un objeto ${f.javaType} que representa el valor del campo ${f.name}.
     */
    public void set${f.name?cap_first}(${f.javaType} ${f.name});

    </#list>
}