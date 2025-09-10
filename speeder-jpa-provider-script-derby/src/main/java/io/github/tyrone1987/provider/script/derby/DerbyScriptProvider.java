
package io.github.tyrone1987.provider.script.derby;

import java.util.HashMap;
import java.util.Map;
import io.github.tyrone1987.generator.spi.*;

public class DerbyScriptProvider implements SrcCodeGenDataModel
{
    @Override
    public void execute(SrcCodeGenProperties prop) throws Exception
    {
        SrcCodeGenTmplEng tp = SrcCodeGenUtils.findSrcTmplEngine(TmplEng.FREEMARKER);

        String genResRoot = prop.getGeneratedResourcesDir();
        String scriptsResDir = genResRoot + "META-INF/sqlscripts";
        String file = scriptsResDir + "/" + "derby-script.sql";

        Map m = new HashMap();
        m.put("dataModel", prop.getDataModel());

        tp.generateSourceFile(
                "/speeder/jpa/derby/generator/derby-script.ftl", 
                m, 
                file);

        file = scriptsResDir + "/" + "derby-views.sql";
        tp.generateSourceFile(
                "/speeder/jpa/derby/generator/derby-views.ftl", 
                m, 
                file);
    }

    @Override
    public String getGeneratorName()
    {
        return "derby";
    }
}
