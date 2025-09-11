
package io.github.tyrone1987.provider.persistence;

import java.util.HashMap;
import java.util.Map;
import io.github.tyrone1987.data.model.EntityInterface;
import io.github.tyrone1987.generator.spi.*;

/**
* Clase para modelar los filtro de cada entidad
*/
public class EntityFilterInterfaceProvider implements SrcCodeGenInterface
{
    @Override
    public void execute(SrcCodeGenProperties prop, EntityInterface entityInterface) throws Exception
    {
        SrcCodeGenTmplEng tp = SrcCodeGenUtils.findSrcTmplEngine(TmplEng.FREEMARKER);

        String genSrcRoot = prop.getGeneratedSourcesDir();
        String persistenceSrcDir = genSrcRoot + prop.getDataModel().getPackage().replace(".", "/") + "/persistence";
        String file = persistenceSrcDir + "/" + entityInterface.getName() + "Filter.java";

        Map m = new HashMap();
        m.put("dataModel", prop.getDataModel());
        m.put("entity", entityInterface);

        tp.generateSourceFile(
                "/io/github/tyrone1987/persistence/generator/entity-filter-interface.ftl", 
                m, file);
    }

    @Override
    public String getGeneratorName()
    {
        return "persistence";
    }
}
