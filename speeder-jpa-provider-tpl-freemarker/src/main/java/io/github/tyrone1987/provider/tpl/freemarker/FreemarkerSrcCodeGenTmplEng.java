
package io.github.tyrone1987.provider.tpl.freemarker;

import freemarker.template.Configuration;
import freemarker.template.Template;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import io.github.tyrone1987.generator.spi.SrcCodeGenTmplEng;
import io.github.tyrone1987.generator.spi.TmplEng;

public class FreemarkerSrcCodeGenTmplEng implements SrcCodeGenTmplEng
{
    private Configuration cf = new Configuration();

    @Override
    public void generateSourceFile(String template, Object data, String file) throws Exception
    {
        File f = new File(file);

        System.out.println("Generando " + f.getName());

        if (f.exists())
        {
            f.delete();
            f.createNewFile();
        }
        else
        {
            f.getParentFile().mkdirs();
            f.createNewFile();
        }

        Template tmp;
        try (InputStream is = getClass().getResourceAsStream(template)) {
            InputStreamReader isr = new InputStreamReader(is);
            tmp = new Template(template, isr, cf);
            isr.close();
        }

        try (FileWriter fw = new FileWriter(f)) {
            tmp.process(data, fw);
        }
    }

    public TmplEng getEngine()
    {
        return TmplEng.FREEMARKER;
    }
}
