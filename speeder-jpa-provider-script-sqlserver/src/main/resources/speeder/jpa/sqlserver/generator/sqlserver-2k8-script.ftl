<#assign refIntegMap = {} />
<#assign refIntegMap = refIntegMap + { "SETNULL": "SET NULL"} />
<#assign refIntegMap = refIntegMap + { "RESTRICT": "RESTRICT"} />
<#assign refIntegMap = refIntegMap + { "CASCADE": "CASCADE"} />
<#assign refIntegMap = refIntegMap + { "NOACTION": "NO ACTION"} />

<#assign typesMap = {} />
<#assign typesMap = typesMap + { "BOOLEAN": "BIT"} />
<#assign typesMap = typesMap + { "TINYINT": "TINYINT"} />
<#assign typesMap = typesMap + { "SMALLINT": "SMALLINT"} />
<#assign typesMap = typesMap + { "INTEGER": "INT"} />
<#assign typesMap = typesMap + { "BIGINT": "BIGINT"} />
<#assign typesMap = typesMap + { "FLOAT": "REAL"} />
<#assign typesMap = typesMap + { "DOUBLE": "DECIMAL"} />
<#assign typesMap = typesMap + { "DECIMAL": "DECIMAL"} />
<#assign typesMap = typesMap + { "FIXED": "NCHAR"} />
<#assign typesMap = typesMap + { "STRING": "NVARCHAR"} />
<#assign typesMap = typesMap + { "TEXT": "NTEXT"} />
<#assign typesMap = typesMap + { "DATE": "DATETIME"} />
<#assign typesMap = typesMap + { "DATETIME": "DATETIME"} />
<#assign typesMap = typesMap + { "TIMESTAMP": "TIMESTAMP"} />
<#assign typesMap = typesMap + { "ENUM_ORDINAL": "SMALLINT"} />
<#assign typesMap = typesMap + { "ENUM_STRING": "NVARCHAR(255)"} />
<#assign typesMap = typesMap + { "RELATION": "BIGINT"} />

<#list dataModel.entitys as entity>
CREATE TABLE [dbo].[${entity.table}]
(
    [id] BIGINT NOT NULL IDENTITY (1, 1),
    <#list entity.fields as f>
    <#if f.type != "ENUM">
        <#assign type = f.type />
    <#else>
        <#assign type = f.type + "_" + f.enumStorage />
    </#if>
    [${f.column}] ${typesMap[type]}<#if f.type == "FIXED" || f.type == "STRING">(${f.length?string("0")})</#if><#if f.required> NOT NULL</#if>,
    </#list>
    PRIMARY KEY ([id])
);

GO

</#list>
<#list dataModel.entitys as entity>
<#list entity.fields as f>
<#if f.indexed>
CREATE <#if f.unique>UNIQUE</#if> INDEX idx_${entity.table}_${f.column}
    ON ${entity.table} ( ${f.column} )
;

GO

</#if>
</#list>
</#list>
<#list dataModel.entitys as entity>
<#list entity.fields as f>
<#if f.type == "RELATION">
ALTER TABLE [dbo].[${entity.table}]
    ADD CONSTRAINT [FK_${entity.table}_${f.column}]
        FOREIGN KEY ([${f.column}])
        REFERENCES [${dataModel.entitysMap[f.entity].table}] ([id])
        ON DELETE ${refIntegMap[f.onDelete]} ON UPDATE NO ACTION
;

GO

</#if>
</#list>
</#list>
