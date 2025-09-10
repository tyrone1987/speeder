
package io.github.tyrone1987.generator.spi;

import io.github.tyrone1987.data.model.Enumerator;

public interface SrcCodeGenEnum extends SrcCodeGen
{
    void execute(SrcCodeGenProperties prop, Enumerator enumerator) throws Exception; 
}
