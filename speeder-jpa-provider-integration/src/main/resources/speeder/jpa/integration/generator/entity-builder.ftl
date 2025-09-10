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
import speeder.jpa.integration.EntityBuilder;
import speeder.jpa.integration.AbstractBuilder;
import speeder.jpa.integration.EntityBuilderValidated;
import speeder.jpa.integration.IntegrationException;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Esta clase permite manipular un objeto ${entity.name} de manera facil mediante
 * una interfaz fluida (Fluent Interface)
 */
public class ${entity.name}Builder extends AbstractBuilder<${entity.name}> implements EntityBuilder<${entity.name}>
{
    /**
     * Crea un objeto ${entity.name}Builder, para crear un nuevo objeto vease el metodo
     * create(EntityManager)
     * @param entity El objeto ${entity.name} a manipular.
     * @param entityManager Referencia al entityManager de la aplicacion
     */
    public ${entity.name}Builder(EntityManager entityManager, ${entity.name} entity)
    {
        super(entityManager, entity);
    }

    <#list entity.fields as f>
    /**
     * Establece la propiedad ${f.name} y retorna una referencia al objeto this para
     * continuas modificaciones.
     * @param value Un objeto ${f.javaType} con el valor de la propiedad ${f.name} que se establecer.
     * @return Una referencia al objeto this para continuas modificaciones.
     */
    public ${entity.name}Builder ${f.name}(${f.javaType} value)
    {
        entity.set${f.name?cap_first}(value);
        return this;
    }

    /**
     * Obtiene la propiedad ${f.name} del objeto subyacente.
     * @return Un ${f.javaType} que representa el valor de la propiedad ${f.name}
     * del objeto subyacente.
     */
    public ${f.javaType} ${f.name}()
    {
        return entity.get${f.name?cap_first}();
    }

    <#if f.isNumericType >
    /**
     * Agrega la cantidad dada a la propiedad ${f.name} y retorna una referencia al objeto this para
     * continuas modificaciones. ${f.javaType}
     * @param value El valor ${f.javaType} a agregar a la propiedad ${f.name}.
     * @return Una referencia al objeto this para continuas modificaciones.
     */
    public ${entity.name}Builder add${f.name?cap_first}(${f.javaType} value)
    {
        <#if f.javaType == 'BigDecimal'>
        entity.set${f.name?cap_first}(entity.get${f.name?cap_first}().add(value));
        <#else>
        entity.set${f.name?cap_first}(<#if f.javaType == "Short" || f.javaType == "Byte">(${f.javaType?uncap_first})(</#if>entity.get${f.name?cap_first}() + value<#if f.javaType == "Short" || f.javaType == "Byte">)</#if>);
        </#if>
        return this;
    }

    </#if>
    <#if f.type == "RELATION">
    /**
     * Obtiene un objeto ${entity.name}Builder para manipular la entidad 
     * del campo ${f.name}.
     * @return Un nuevo objeto ${entity.name}Builder con el valor del campo ${f.name}.
     */
    public ${f.entity}Builder ${f.name}Builder()
    {
        return ${f.entity}Builder.wrap(entityManager, entity.get${f.name?cap_first}());
    }

    /**
     * Sustituye la entidad actual ${f.name} de la relacion por la misma entidad
     * obtenida de la Base de Datos y retorna una referencia al objeto this para
     * continuas modificaciones.
     * @return Una referencia al objeto this para continuas modificaciones.
     */
    public ${entity.name}Builder refresh${f.name?cap_first}()
    {
        if( entity.get${f.name?cap_first}() == null || entity.get${f.name?cap_first}().getId() == null )
        {
            return null;
        }
        ${f.entity} value = entityManager.find(${f.entity}.class, entity.get${f.name?cap_first}().getId());
        entityManager.refresh(value);
        entity.set${f.name?cap_first}(value);
        return this;
    }

    /**
     * Guarda el objeto ${f.entity} de la relacion ${f.name} en la base de datos.
     * @return Una referencia al objeto this para continuas modificaciones.
     */
    public ${entity.name}Builder save${f.name?cap_first}()
    {
        if(entity.get${f.name?cap_first}() != null)
        {
            ${f.name}Builder().save();
        }
        return this;
    }

    /**
     * Valida el objeto ${f.entity} de la relacion ${f.name} de manera que cumpla con 
     * las reglas del modelo para campos requeridos y el tamaño de las cadenas de texto.
     * @return Una referencia al objeto this para continuas modificaciones.
     */
    public ${entity.name}Builder check${f.name?cap_first}() throws IntegrationException
    {
        if(entity.get${f.name?cap_first}() != null)
        {
            ${f.name}Builder().check();
        }
        return this;
    }

    /**
     * Elimina el objeto ${f.entity} de la relacion ${f.name} de la base de datos.
     * @return Una referencia al objeto this para continuas modificaciones.
     */
    public ${entity.name}Builder delete${f.name?cap_first}()
    {
        if(entity.get${f.name?cap_first}() != null)
        {
            ${f.name}Builder().delete();
        }
        return this;
    }

    /**
     * Elimina el objeto ${f.entity} de la relacion ${f.name} de la base de datos,
     * y establece el valor de la relacion a null en la entidad interna.
     * @return Una referencia al objeto this para continuas modificaciones.
     */
    public ${entity.name}Builder delete${f.name?cap_first}ToNull()
    {
        delete${f.name?cap_first}();
        ${f.name}(null);
        return this;
    }

    </#if>
    </#list>
    /**
     * Guarda el objeto ${entity.name} interno en la base de datos.
     * @return Una referencia al objeto this para continuas modificaciones.
     */
    @Override
    public ${entity.name}Builder save()
    {
        return (${entity.name}Builder)super.save();
    }

    /**
     * Valida la entidad interna de manera que cumpla con las reglas del modelo
     * para campos requeridos y el tamaño de las cadenas de texto.
     * @return Una referencia al objeto this para continuas modificaciones.
     */
    @Override
    public ${entity.name}Builder check() throws IntegrationException
    {
        <#list entity.fields as f>
        <#if f.required>
        if(entity.get${f.name?cap_first}() == null)
        {
            throw new IntegrationException("No se especifico un valor para el campo ${f.name}.");
        }
        </#if>

        <#if f.type == "STRING">
        <#if f.notEmpty >
        if(StringUtils.isEmpty(entity.get${f.name?cap_first}()))
        {
            throw new IntegrationException("No se especifico un valor para el campo ${f.name}.");
        }        
        </#if>

        if(StringUtils.length(entity.get${f.name?cap_first}()) > ${f.length?string("0")} )
        {
            throw new IntegrationException("El campo ${f.name} es demasiado largo, el valor no debe exceder los ${f.length?string("0")} caracteres.");
        }
        </#if>

        <#if f.unique>
        if(${entity.name}Query.filter(entityManager).${f.name}(entity.get${f.name?cap_first}()).not().id(entity.getId()).count() > 0)        
        {
            throw new IntegrationException("El valor del campo ${f.name} ya existe.");
        }
        </#if>
        
        <#if f.sign?? >
        <#if f.sign == "UNSIGNED">
        if(entity.get${f.name?cap_first}() != null && entity.get${f.name?cap_first}() < 0)
        {
            throw new IntegrationException("El valor del campo ${f.name} no puede ser menor que cero.");
        }        
        </#if>
        </#if>
        <#if f.validated?? >
        <#if f.validated == "EMAIL">
        if(!entity.get${f.name?cap_first}().isEmpty() && !EntityBuilderValidated.validateEmail(entity.get${f.name?cap_first}()))
        {
            throw new IntegrationException("El correo no es valido.");
        }        
        </#if>
        </#if>
        </#list>
        return this;
    }

    /**
     * Determina cuando el objeto esta siendo referenciado
     * por otros registros de la base de datos tengan o no la opcion ON DELETE en RESTRICT.
     * @return true El objeto esta siendo referenciado por otros registros, 
     * false en caso contrario.
     */
    public boolean isBeingReferencedAll()
    {
        <#list dataModel.entitys as refEntity>
        <#list refEntity.fields as refField>
        <#if refField.type == "RELATION" && refField.entity == entity.name>
        if( ${refEntity.name}Query.filter(entityManager)
                .${refField.name}(getEntity()).count() > 0)
        {
            return true;
        }

        </#if>
        </#list>
        </#list>
        return false;
    }

    /**
     * Determina cuando el objeto esta siendo referenciado
     * por otros registros que no tiene la opcion ON DELETE distinta de RESTRICT
     * y por tanto intentar eliminar este registro causaria un error de integridad
     * referencial en la base de datos.
     * @return true El objeto esta siendo referenciado por otros registros, 
     * false en caso contrario.
     */
    public boolean isBeingReferenced()
    {
        <#list dataModel.entitys as refEntity>
        <#list refEntity.fields as refField>
        <#if refField.type == "RELATION" && refField.entity == entity.name>
        <#if refField.onDelete == "RESTRICT">
        if( ${refEntity.name}Query.filter(entityManager)
                .${refField.name}(getEntity()).count() > 0)
        {
            return true;
        }

        </#if>
        </#if>
        </#list>
        </#list>
        return false;
    }

    /**
     * Lanza un error cuando el objeto esta siendo referenciado
     * por otros registros que no tiene la opcion ON DELETE distinta de RESTRICT
     * y por tanto intentar eliminar este registro causaria un error de integridad
     * referencial en la base de datos.
     */
    public ${entity.name}Builder checkIsBeingReferenced() throws IntegrationException
    {
        if(isBeingReferenced())
        {
            throw new IntegrationException("No se puede eliminar el porque existen datos asociados con el mismo.");
        }

        return this;
    }

    /**
     * Elimina el objeto ${entity.name} interno de la base de datos.
     * @return Una referencia al objeto this para continuas modificaciones.
     */
    @Override
    public ${entity.name}Builder delete()
    {
        return (${entity.name}Builder)super.delete();
    }

    /**
     * Determina cuando el objeto ${entity.name} interno existe en la base de datos o no.
     * @return true Si el objeto ${entity.name} interno existe en la base de datos, false en caso contrario.
     */
    @Override
    public boolean existsInDataBase()
    {
        return super.existsInDataBase();
    }

    /**
     * Permite encontrar un objeto ${entity.name} en la base de datos dado por su id.
     * @param entityManager Una referencia al contexto de persistencia a usar.
     * @param id El identificador del objeto a encontrar.
     * @return Un nuevo objeto ${entity.name}Builder que puede ser usado para continuas modificaciones,
     * o null si no se puede encontrar ninguna entidad ${entity.name} por el id especificado.
     */
    public static ${entity.name}Builder find(EntityManager entityManager, Long id)
    {
        if(id == null)
        {
            return null;
        }
        ${entity.name} entity = entityManager.find(${entity.name}.class, id);
        if( entity != null )
            return new ${entity.name}Builder(entityManager, entity);
        return null;
    }

    /**
     * Determina cuando el objeto ${entity.name} dado existe en la base de datos o no.
     * @param entityManager Una referencia al contexto de persistencia a usar.
     * @param entity El objeto entidad a buscar.
     * @return true Si el objeto ${entity.name} dado existe en la base de datos, false en caso contrario.
     */
    public static boolean existsInDataBase(EntityManager entityManager, ${entity.name} entity)
    {
        if(entity.getId() == null)
        {
            return false;
        }
        return entityManager.find(${entity.name}.class, entity.getId()) != null;
    }

    /**
     * Determina cuando el objeto ${entity.name} dado existe en la base de datos o no.
     * @param entityManager Una referencia al contexto de persistencia a usar.
     * @param id El identificador del objeto a buscar.
     * @return true Si el objeto ${entity.name} dado existe en la base de datos, false en caso contrario.
     */
    public static boolean existsInDataBase(EntityManager entityManager, Long id)
    {
        if(id == null)
        {
            return false;
        }
        return entityManager.find(${entity.name}.class, id) != null;
    }

    /**
     * Crea un nuevo objeto ${entity.name} y devuelve un objeto ${entity.name}Builder que permite realizar modificaciones a la entidad creada.
     * @param entityManager Una referencia al contexto de persistencia a usar.
     * @return Un nuevo objeto ${entity.name}Builder que puede ser usado para continuas modificaciones.
     */
    public static ${entity.name}Builder create(EntityManager entityManager)
    {
        return new ${entity.name}Builder(entityManager, ${entity.name}.createNew());
    }

    /**
     * Encapsula un objeto ${entity.name} con un objeto ${entity.name}Builder 
     * que permite realizar modificaciones a la entidad encapsulada.
     * @param entityManager Una referencia al contexto de persistencia a usar.
     * @param entity El objeto ${entity.name} a emcapsular.
     * @return Un nuevo objeto ${entity.name}Builder que puede ser usado para continuas modificaciones.
     */
    public static ${entity.name}Builder wrap(EntityManager entityManager, ${entity.name} entity)
    {
        if( entity != null )
        {
            return new ${entity.name}Builder(entityManager, entity);
        }
        return null;
    }

    /**
     * Desencapsula un objeto ${entity.name}Builder obteniendo el objeto ${entity.name} subyacente.
     * @param entityManager Una referencia al contexto de persistencia a usar.
     * @param builder El objeto ${entity.name}Builder a desencapsular.
     * @return 
     */
    public static ${entity.name} unwrap(EntityManager entityManager, ${entity.name}Builder builder)
    {
        if( builder != null )
        {
            return builder.getEntity();
        }
        return null;
    }
}