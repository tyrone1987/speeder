
package speeder.jpa.provider.persistence;

import java.util.HashMap;
import java.util.Map;
import speeder.jpa.data.model.Entity;
import speeder.jpa.generator.spi.*;

public class EntityMetaModelProvider implements SrcCodeGenEntity
{
    public void execute(SrcCodeGenProperties prop, Entity entity) throws Exception
    {
        SrcCodeGenTmplEng tp = SrcCodeGenUtils.findSrcTmplEngine(TmplEng.FREEMARKER);

        String genSrcRoot = prop.getGeneratedSourcesDir();
        String persistenceSrcDir = genSrcRoot + prop.getDataModel().getPackage().replace(".", "/") + "/persistence";
        String file = persistenceSrcDir + "/" + entity.getName() + "_.java";

        Map m = new HashMap();
        m.put("dataModel", prop.getDataModel());
        m.put("entity", entity);

        tp.generateSourceFile(
                "/speeder/jpa/persistence/generator/entity-meta-model.ftl", 
                m, 
                file);
    }

    public String getGeneratorName()
    {
        return "persistence";
    }
}
