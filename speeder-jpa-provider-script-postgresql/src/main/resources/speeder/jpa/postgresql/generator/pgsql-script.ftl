<#assign refIntegMap = {} />
<#assign refIntegMap = refIntegMap + { "SETNULL": "SET NULL"} />
<#assign refIntegMap = refIntegMap + { "RESTRICT": "RESTRICT"} />
<#assign refIntegMap = refIntegMap + { "CASCADE": "CASCADE"} />
<#assign refIntegMap = refIntegMap + { "NOACTION": "NO ACTION"} />

<#assign typesMap = {} />
<#assign typesMap = typesMap + { "BOOLEAN": "BOOLEAN"} />
<#assign typesMap = typesMap + { "TINYINT": "SMALLINT"} />
<#assign typesMap = typesMap + { "SMALLINT": "SMALLINT"} />
<#assign typesMap = typesMap + { "INTEGER": "INTEGER"} />
<#assign typesMap = typesMap + { "BIGINT": "BIGINT"} />
<#assign typesMap = typesMap + { "FLOAT": "REAL"} />
<#assign typesMap = typesMap + { "DOUBLE": "DOUBLE"} />
<#assign typesMap = typesMap + { "DECIMAL": "DECIMAL"} />
<#assign typesMap = typesMap + { "FIXED": "CHAR"} />
<#assign typesMap = typesMap + { "STRING": "VARCHAR"} />
<#assign typesMap = typesMap + { "TEXT": "TEXT"} />
<#assign typesMap = typesMap + { "DATE": "DATE"} />
<#assign typesMap = typesMap + { "DATETIME": "TIMESTAMP"} />
<#assign typesMap = typesMap + { "TIMESTAMP": "TIMESTAMP"} />
<#assign typesMap = typesMap + { "ENUM_ORDINAL": "SMALLINT"} />
<#assign typesMap = typesMap + { "ENUM_STRING": "VARCHAR(255)"} />
<#assign typesMap = typesMap + { "RELATION": "BIGINT"} />

<#list dataModel.entitys as entity>
CREATE TABLE ${entity.table}
(
    id BIGINT NOT NULL,
    <#list entity.fields as f>
    <#if f.type != "ENUM">
        <#assign type = f.type />
    <#else>
        <#assign type = f.type + "_" + f.enumStorage />
    </#if>
    ${f.column} ${typesMap[type]}<#if f.type == "FIXED" || f.type == "STRING">(${f.length?string("0")})</#if><#if f.required> NOT NULL</#if>,
    </#list>
    CONSTRAINT ${entity.table}_pkey PRIMARY KEY (id)
)
WITH (OIDS = FALSE);

</#list>
<#list dataModel.entitys as entity>
<#list entity.fields as f>
<#if f.indexed>
CREATE <#if f.unique>UNIQUE</#if> INDEX idx_${entity.table}_${f.column}
    ON ${entity.table} ( ${f.column} )
;

</#if>
</#list>
</#list>
<#list dataModel.entitys as entity>
<#list entity.fields as f>
<#if f.type == "RELATION">
ALTER TABLE ${entity.table}
    ADD CONSTRAINT fk_${entity.table}_${f.column}
        FOREIGN KEY (${f.column})
        REFERENCES ${dataModel.entitysMap[f.entity].table} (id) MATCH SIMPLE
        ON DELETE ${refIntegMap[f.onDelete]} ON UPDATE RESTRICT
;

</#if>
</#list>
</#list>