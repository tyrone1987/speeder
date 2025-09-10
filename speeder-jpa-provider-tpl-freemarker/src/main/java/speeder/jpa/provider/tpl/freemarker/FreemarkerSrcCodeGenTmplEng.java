
package speeder.jpa.provider.tpl.freemarker;

import freemarker.template.Configuration;
import freemarker.template.Template;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import speeder.jpa.generator.spi.SrcCodeGenTmplEng;
import speeder.jpa.generator.spi.TmplEng;

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

        InputStream is = getClass().getResourceAsStream(template);
        InputStreamReader isr = new InputStreamReader(is);
        Template tmp = new Template(template, isr, cf);
        isr.close();
        is.close();

        FileWriter fw = new FileWriter(f);
        tmp.process(data, fw);
        fw.close();
    }

    public TmplEng getEngine()
    {
        return TmplEng.FREEMARKER;
    }
}
