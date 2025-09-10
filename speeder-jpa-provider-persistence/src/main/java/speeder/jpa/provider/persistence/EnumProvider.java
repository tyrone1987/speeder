
package speeder.jpa.provider.persistence;

import java.util.HashMap;
import java.util.Map;
import speeder.jpa.data.model.Enumerator;
import speeder.jpa.generator.spi.*;

public class EnumProvider implements SrcCodeGenEnum
{
    public void execute(SrcCodeGenProperties prop, Enumerator enumerator) throws Exception
    {
        SrcCodeGenTmplEng tp = SrcCodeGenUtils.findSrcTmplEngine(TmplEng.FREEMARKER);

        String genSrcRoot = prop.getGeneratedSourcesDir();
        String persistenceSrcDir = genSrcRoot + prop.getDataModel().getPackage().replace(".", "/") + "/persistence";
        String file = persistenceSrcDir + "/" + enumerator.getName() + ".java";

        Map m = new HashMap();
        m.put("dataModel", prop.getDataModel());
        m.put("enum", enumerator);

        tp.generateSourceFile(
                "/speeder/jpa/persistence/generator/enum.ftl",
                m,
                file);
    }

    public String getGeneratorName()
    {
        return "persistence";
    }
}
