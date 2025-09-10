
package io.github.tyrone1987.generator.spi;

public interface SrcCodeGenDataModel extends SrcCodeGen
{
    void execute(SrcCodeGenProperties prop) throws Exception; 
}
