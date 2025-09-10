
package io.github.tyrone1987.generator.spi;

import io.github.tyrone1987.data.model.EntityInterface;

public interface SrcCodeGenInterface extends SrcCodeGen
{
    void execute(SrcCodeGenProperties prop, EntityInterface entityInterface) throws Exception;
}
