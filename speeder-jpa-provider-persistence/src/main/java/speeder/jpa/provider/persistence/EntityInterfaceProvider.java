
package io.github.tyrone1987.provider.persistence;

import java.util.HashMap;
import java.util.Map;
import io.github.tyrone1987.data.model.EntityInterface;
import io.github.tyrone1987.generator.spi.*;

public class EntityInterfaceProvider implements SrcCodeGenInterface
{

    public void execute(SrcCodeGenProperties prop, EntityInterface entityInterface) throws Exception
    {
        SrcCodeGenTmplEng tp = SrcCodeGenUtils.findSrcTmplEngine(TmplEng.FREEMARKER);

        String genSrcRoot = prop.getGeneratedSourcesDir();
        String persistenceSrcDir = genSrcRoot + prop.getDataModel().getPackage().replace(".", "/") + "/persistence";
        String file = persistenceSrcDir + "/" + entityInterface.getName() + ".java";

        Map m = new HashMap();
        m.put("dataModel", prop.getDataModel());
        m.put("entity", entityInterface);
        m.put("forObjectDb", false);

        tp.generateSourceFile(
                "/speeder/jpa/persistence/generator/entity-interface.ftl", 
                m, 
                file);
    }

    public String getGeneratorName()
    {
        return "persistence";
    }
}
