<#list dataModel.entitys as entity>
DROP VIEW IF EXISTS ${entity.name}View;
</#list>

<#list dataModel.entitys as entity>
CREATE VIEW ${entity.name}View
AS
    SELECT
        ${entity.table}.id as id
        <#list entity.fields as f>
        <#if f.type != "RELATION">
        ,${entity.table}.${f.column} as ${f.name}
        </#if>
        </#list>
        <#list entity.relations as r>
        <#assign relEntity = dataModel.entitysMap[r.entity] />
        ,${entity.table}.${r.column} as ${r.name}
        <#list relEntity.fields as f>
        ,${r.name}.${f.column} as ${r.name}_${f.name}
        </#list>
        </#list>
        <#list entity.relations as r>
        <#assign relEntity = dataModel.entitysMap[r.entity] />
        <#list relEntity.relations as r1>
        <#assign relEntity1 = dataModel.entitysMap[r1.entity] />
        <#list relEntity1.fields as f>
        ,${r.name}_${r1.name}.${f.column} as ${r.name}_${r1.name}_${f.name}
        </#list>
        </#list>
        </#list>
    FROM
        ${entity.table}
        <#list entity.relations as r>
        <#assign relEntity = dataModel.entitysMap[r.entity] />
        LEFT JOIN ${relEntity.table} AS ${r.name} ON ${entity.table}.${r.column} = ${r.name}.id
        </#list>
        <#list entity.relations as r>
        <#assign relEntity = dataModel.entitysMap[r.entity] />
        <#list relEntity.relations as r1>
        <#assign relEntity1 = dataModel.entitysMap[r1.entity] />
        LEFT JOIN ${relEntity1.table} AS ${r.name}_${r1.name} ON ${r.name}.${r1.column} = ${r.name}_${r1.name}.id
        </#list>
        </#list>
;

</#list>