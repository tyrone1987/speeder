
package io.github.tyrone1987.provider.persistence;

import java.util.HashMap;
import java.util.Map;
import io.github.tyrone1987.data.model.Entity;
import io.github.tyrone1987.generator.spi.*;

public class EntityProvider implements SrcCodeGenEntity
{
    public void execute(SrcCodeGenProperties prop, Entity entity) throws Exception
    {
        SrcCodeGenTmplEng tp = SrcCodeGenUtils.findSrcTmplEngine(TmplEng.FREEMARKER);

        String genSrcRoot = prop.getGeneratedSourcesDir();
        String persistenceSrcDir = genSrcRoot + prop.getDataModel().getPackage().replace(".", "/") + "/persistence";
        String file = persistenceSrcDir + "/" + entity.getName() + ".java";

        Map m = new HashMap();
        m.put("dataModel", prop.getDataModel());
        m.put("entity", entity);
        boolean forObjectDb = "true".equalsIgnoreCase(prop.getConfig().get("forObjectDb"));
        m.put("forObjectDb", forObjectDb);

        tp.generateSourceFile(
                "/speeder/jpa/persistence/generator/entity.ftl",
                m,
                file);
    }

    public String getGeneratorName()
    {
        return "persistence";
    }
}
