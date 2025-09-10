
package io.github.tyrone1987.generator.spi;

public interface SrcCodeGenTmplEng
{
    public void generateSourceFile(String template, Object data, String file) throws Exception;
    
    public TmplEng getEngine();
}
