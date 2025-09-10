
package io.github.tyrone1987.generator.spi;

import io.github.tyrone1987.data.model.Entity;

public interface SrcCodeGenEntity extends SrcCodeGen
{
    void execute(SrcCodeGenProperties prop, Entity entity) throws Exception; 
}
