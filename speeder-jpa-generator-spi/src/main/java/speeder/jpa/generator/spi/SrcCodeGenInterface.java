
package speeder.jpa.generator.spi;

import speeder.jpa.data.model.EntityInterface;

public interface SrcCodeGenInterface extends SrcCodeGen
{
    void execute(SrcCodeGenProperties prop, EntityInterface entityInterface) throws Exception;
}
