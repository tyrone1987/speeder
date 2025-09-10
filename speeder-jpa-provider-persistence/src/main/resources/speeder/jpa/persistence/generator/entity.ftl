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
import jakarta.validation.constraints.*;
import speeder.jpa.persistence.*;

<#if entity.converters?? >
import org.eclipse.persistence.annotations.Convert;
import org.eclipse.persistence.annotations.Converter;
    <#list entity.converters as c>
import ${c.package}.${c.className};
    </#list>
</#if>

import jakarta.xml.bind.annotation.XmlRootElement;

<#if forObjectDb>
import jakarta.jdo.annotations.Index;
import jakarta.jdo.annotations.Unique;
</#if>

/**
 * Clase para la entidad ${entity.name}
 * Nombre tabla SQL: ${entity.table}
 */
@XmlRootElement
@Entity
@Table(name = "${entity.table}")
<#if entity.converters?? >
<#list entity.converters as c>
@Converter(name="${c.name}",converterClass=${c.className}.class)
</#list>
</#if>
public class ${entity.name} extends AbstractEntity implements Serializable, IEntity
{
    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    <#list entity.fields as f>
    <#if f.validated??>
        <#if f.validated == "NONULL" >
    @NotNull(message="El campo ${f.name} no puede ser nulo")
        </#if> 
        <#if f.validated == "NULL" >
    @Null(message="El campo ${f.name} tiene que ser nulo")
        </#if>   
    </#if>    
    <#if f.type == "TEXT" > 
    @Lob       
    </#if>
    <#if f.type == "BOOLEAN" >
        <#if f.validated??>
            <#if f.validated == "ASSERT_FALSE" >
    @AssertFalse(message="El campo ${f.name} tiene que ser falso")
            </#if> 
            <#if f.validated == "ASSERT_TRUE" >
    @AssertTrue(message="El campo ${f.name} tiene que ser verdadero")
            </#if>
        </#if>          
    </#if>
    <#if f.type == "DATE" || f.type == "DATETIME" || f.type == "TIMESTAMP">
    @Temporal(${f.temporalType})
        <#if f.validated??>
            <#if f.validated == "PAST" >
    @Past(message="El campo ${f.name} tiene que contener una fecha menor que la fecha actual")
            </#if>
            <#if f.validated == "FUTURE" >
    @Future(message="El campo ${f.name} tiene que contener una fecha mayor que la fecha actual")
            </#if>
        </#if>
    </#if>
    <#if f.type == "RELATION" >
    @JoinColumn(name="${f.column}", referencedColumnName="id")
    @ManyToOne
    <#else>
    @Column(name="${f.column}")
    <#if f.type == "ENUM" >
    @Enumerated(EnumType.${f.enumStorage})
    </#if>
    </#if>
    <#if f.convert??>
    @Convert("${f.convert}")
    </#if>
    <#if forObjectDb>
    <#if f.indexed??>
    @Index<#if f.unique>(unique="true")</#if>
    </#if>
    </#if>
    private ${f.javaType} ${f.name};

    </#list>
    /**
     * Constructor por defecto para esta entidad.
     */
    public ${entity.name}()
    {
    }

    /**
     * Constructor de la entidad a partir de su identificador,
     * @param id El identificador para esta entidad.
     */
    public ${entity.name}(Long id)
    {
        this.id = id;
    }

    /**
     * Obtiene el identificador de la entidad.
     * @return Un objeto Long que representa el identificador de la entidad.
     */
    @Override
    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    <#list entity.fields as f>
    /**
     * Obtiene el campo ${f.name}
     * @return Un objeto ${f.javaType} que representa el valor del campo ${f.name}.
     */
    public ${f.javaType} get${f.name?cap_first}()
    {
        return this.${f.name};
    }

    /**
     * Establece el campo ${f.name}
     * @param ${f.name} Un objeto ${f.javaType} que representa el valor del campo ${f.name}.
     */
    public void set${f.name?cap_first}(${f.javaType} ${f.name})
    {
        <#if f.type == "STRING" || f.type == "FIXED" >
        <#if f.trim || f.strCase != "NONE" >
        if(${f.name} != null)
        {
            <#if f.trim >
            ${f.name} = ${f.name}.trim();
            </#if>
            <#if f.strCase == "UPPER" >
            ${f.name} = ${f.name}.toUpperCase();
            <#elseif f.strCase == "LOWER" >
            ${f.name} = ${f.name}.toLowerCase();
            </#if>
        }
        </#if>
        </#if>
        this.${f.name} = ${f.name};
    }

    </#list>
    <#if entity.toStringField?? >
    /**
     * Este metodo devuelve la propiedad ${entity.toStringField}
     * @return String El valor de la propiedad ${entity.toStringField}
     */
    @Override
    public String toString()
    {
        return get${entity.toStringField?cap_first}();
    }

    </#if>
    /**
     * Crea un nuevo objeto ${entity.name} 
     * @return Una referencia al nuevo objeto ${entity.name} creado.
     */
    public static ${entity.name} createNew()
    {
        ${entity.name} newEntity = new ${entity.name}();
        <#list entity.fields as f>
        <#if f.default?? >
        newEntity.set${f.name?cap_first}(${f.defaultValueCode});
        </#if>
        </#list>
        return newEntity;
    }
}