
package speeder.jpa.provider.persistence;

import java.util.HashMap;
import java.util.Map;
import speeder.jpa.data.model.EntityInterface;
import speeder.jpa.generator.spi.*;

public class EntityFilterInterfaceProvider implements SrcCodeGenInterface
{
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
                "/speeder/jpa/persistence/generator/entity-filter-interface.ftl", 
                m, file);
    }

    public String getGeneratorName()
    {
        return "persistence";
    }
}
