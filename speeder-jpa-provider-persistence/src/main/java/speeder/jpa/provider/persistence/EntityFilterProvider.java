
package io.github.tyrone1987.provider.persistence;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ServiceLoader;
import io.github.tyrone1987.data.model.Entity;
import io.github.tyrone1987.generator.spi.*;

public class EntityFilterProvider implements SrcCodeGenEntity
{
    @Override
    public void execute(SrcCodeGenProperties prop, Entity entity) throws Exception
    {
        SrcCodeGenTmplEng tp = SrcCodeGenUtils.findSrcTmplEngine(TmplEng.FREEMARKER);

        String genSrcRoot = prop.getGeneratedSourcesDir();
        String persistenceSrcDir = genSrcRoot + prop.getDataModel().getPackage().replace(".", "/") + "/persistence";
        String file = persistenceSrcDir + "/" + entity.getName() + "Filter.java";

        Map m = new HashMap();
        m.put("dataModel", prop.getDataModel());
        m.put("entity", entity);

        tp.generateSourceFile(
                "/speeder/jpa/persistence/generator/entity-filter.ftl", 
                m, 
                file);
    }

    @Override
    public String getGeneratorName()
    {
        return "persistence";
    }
}
