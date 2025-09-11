package ${dataModel.package}.persistence;

import java.io.Serializable;

/**
 * Esta clase representa el enumerado ${enum.name}
 */
public enum ${enum.name} implements Serializable
{
    <#list enum.values as value>
    ${value.name}<#if value_has_next >,<#else>;</#if>
    </#list>
}