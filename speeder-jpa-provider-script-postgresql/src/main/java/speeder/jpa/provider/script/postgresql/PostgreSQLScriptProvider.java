
package speeder.jpa.provider.script.postgresql;

import java.util.HashMap;
import java.util.Map;
import speeder.jpa.generator.spi.*;

public class PostgreSQLScriptProvider implements SrcCodeGenDataModel
{
    public void execute(SrcCodeGenProperties prop) throws Exception
    {
        SrcCodeGenTmplEng tp = SrcCodeGenUtils.findSrcTmplEngine(TmplEng.FREEMARKER);

        String genResRoot = prop.getGeneratedResourcesDir();
        String scriptsResDir = genResRoot + "META-INF/sqlscripts";
        String file = scriptsResDir + "/" + "postgresql-script.sql";

        Map m = new HashMap();
        m.put("dataModel", prop.getDataModel());

        tp.generateSourceFile(
                "/speeder/jpa/postgresql/generator/pgsql-script.ftl", 
                m, 
                file);

        file = scriptsResDir + "/" + "postgresql-views.sql";
        tp.generateSourceFile(
                "/speeder/jpa/postgresql/generator/pgsql-views.ftl", 
                m, 
                file);
    }

    public String getGeneratorName()
    {
        return "postgresql";
    }
}
