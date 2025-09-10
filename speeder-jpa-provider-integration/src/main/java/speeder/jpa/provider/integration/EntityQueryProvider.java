
package io.github.tyrone1987.provider.integration;

import java.util.HashMap;
import java.util.Map;
import io.github.tyrone1987.data.model.Entity;
import io.github.tyrone1987.generator.spi.*;

public class EntityQueryProvider implements SrcCodeGenEntity
{
    public void execute(SrcCodeGenProperties prop, Entity entity) throws Exception
    {
        SrcCodeGenTmplEng tp = SrcCodeGenUtils.findSrcTmplEngine(TmplEng.FREEMARKER);

        String genSrcRoot = prop.getGeneratedSourcesDir();
        String persistenceSrcDir = genSrcRoot + prop.getDataModel().getPackage().replace(".", "/") + "/integration";
        String file = persistenceSrcDir + "/" + entity.getName() + "Query.java";

        Map m = new HashMap();
        m.put("dataModel", prop.getDataModel());
        m.put("entity", entity);

        tp.generateSourceFile(
                "/speeder/jpa/integration/generator/entity-query.ftl",
                m,
                file);
    }

    public String getGeneratorName()
    {
        return "integration";
    }
}
