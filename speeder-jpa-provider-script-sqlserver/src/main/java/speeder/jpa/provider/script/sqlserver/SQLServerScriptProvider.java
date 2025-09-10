
package speeder.jpa.provider.script.sqlserver;

import java.util.HashMap;
import java.util.Map;
import speeder.jpa.generator.spi.SrcCodeGenDataModel;
import speeder.jpa.generator.spi.SrcCodeGenTmplEng;
import speeder.jpa.generator.spi.SrcCodeGenProperties;
import speeder.jpa.generator.spi.SrcCodeGenUtils;
import speeder.jpa.generator.spi.TmplEng;

public class SQLServerScriptProvider implements SrcCodeGenDataModel
{
    @Override
    public void execute(SrcCodeGenProperties prop) throws Exception
    {
        SrcCodeGenTmplEng tp = SrcCodeGenUtils.findSrcTmplEngine(TmplEng.FREEMARKER);

        String genResRoot = prop.getGeneratedResourcesDir();
        String scriptsResDir = genResRoot + "META-INF/sqlscripts";
        String file2k5 = scriptsResDir + "/" + "sqlserver-2k5-script.sql";
        String file2k8 = scriptsResDir + "/" + "sqlserver-2k8-script.sql";

        Map m = new HashMap();
        m.put("dataModel", prop.getDataModel());

        tp.generateSourceFile(
                "/speeder/jpa/sqlserver/generator/sqlserver-2k5-script.ftl",
                m,
                file2k5);

        tp.generateSourceFile(
                "/speeder/jpa/sqlserver/generator/sqlserver-2k8-script.ftl",
                m,
                file2k8);


        String file = scriptsResDir + "/" + "sqlserver-views.sql";
        tp.generateSourceFile(
                "/speeder/jpa/sqlserver/generator/sqlserver-views.ftl",
                m,
                file);
    }

    @Override
    public String getGeneratorName()
    {
        return "sqlserver";
    }
}
