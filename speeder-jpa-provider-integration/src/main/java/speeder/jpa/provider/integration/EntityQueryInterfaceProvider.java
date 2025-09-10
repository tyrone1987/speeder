
package io.github.tyrone1987.provider.integration;

import java.util.HashMap;
import java.util.Map;
import io.github.tyrone1987.data.model.EntityInterface;
import io.github.tyrone1987.generator.spi.*;

public class EntityQueryInterfaceProvider implements SrcCodeGenInterface
{
    public void execute(SrcCodeGenProperties prop, EntityInterface entityInterface) throws Exception
    {
        SrcCodeGenTmplEng tp = SrcCodeGenUtils.findSrcTmplEngine(TmplEng.FREEMARKER);

        String genSrcRoot = prop.getGeneratedSourcesDir();
        String persistenceSrcDir = genSrcRoot + prop.getDataModel().getPackage().replace(".", "/") + "/integration";
        String file = persistenceSrcDir + "/" + entityInterface.getName() + "Query.java";

        Map m = new HashMap();
        m.put("dataModel", prop.getDataModel());
        m.put("entity", entityInterface);

        tp.generateSourceFile(
                "/speeder/jpa/integration/generator/entity-query-interface.ftl",
                m,
                file);
    }

    public String getGeneratorName()
    {
        return "integration";
    }
}
