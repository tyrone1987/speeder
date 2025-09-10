package ${dataModel.package}.persistence;

<#if dataModel.imports??>
    <#list dataModel.imports as i>
import ${i.packageName}.persiscence.*;
    </#list>
</#if>

import jakarta.persistence.metamodel.*;
import java.util.Date;
import java.math.BigDecimal;

/**
 * StaticMetamodel para la entidad ${entity.name} que facilita la creacion de 
 * consultas de JPA.
 */
@StaticMetamodel(${entity.name}.class)
public class ${entity.name}_
{
    /**
     * SingularAttribute del campo id.
     */
    public static volatile SingularAttribute<${entity.name}, Long> id;

    <#list entity.fields as f>
    /**
     * SingularAttribute del campo ${f.name}.
     */
    public static volatile SingularAttribute<${entity.name}, ${f.javaType}> ${f.name};

    </#list>
}