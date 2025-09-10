
package io.github.tyrone1987.provider.script.mysql;

import java.util.HashMap;
import java.util.Map;
import io.github.tyrone1987.generator.spi.*;

public class MySQLScriptProvider implements SrcCodeGenDataModel
{
    public void execute(SrcCodeGenProperties prop) throws Exception
    {
        SrcCodeGenTmplEng tp = SrcCodeGenUtils.findSrcTmplEngine(TmplEng.FREEMARKER);

        String genResRoot = prop.getGeneratedResourcesDir();
        String scriptsResDir = genResRoot + "META-INF/sqlscripts";

        Map m = new HashMap();
        m.put("dataModel", prop.getDataModel());

        String file = scriptsResDir + "/" + "mysql-script.sql";
        tp.generateSourceFile(
                "/speeder/jpa/mysql/generator/mysql-script.ftl", 
                m, 
                file);

        file = scriptsResDir + "/" + "mysql-views.sql";
        tp.generateSourceFile(
                "/speeder/jpa/mysql/generator/mysql-views.ftl", 
                m, 
                file);
    }

    public String getGeneratorName()
    {
        return "mysql";
    }
}
