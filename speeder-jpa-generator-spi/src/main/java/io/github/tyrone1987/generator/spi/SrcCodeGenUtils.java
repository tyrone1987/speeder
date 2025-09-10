
package io.github.tyrone1987.generator.spi;

import java.util.Iterator;
import java.util.ServiceLoader;

public class SrcCodeGenUtils
{
    public static SrcCodeGenTmplEng findSrcTmplEngine(TmplEng engineTemplate)
    {
        Iterator<SrcCodeGenTmplEng> services = ServiceLoader.load(SrcCodeGenTmplEng.class).iterator();
        while (services.hasNext())
        {
            SrcCodeGenTmplEng tp = services.next();
            if (tp.getEngine() == engineTemplate)
            {
                return tp;
            }
        }
        return null;
    }
}
