
package speeder.jpa.generator.spi;

import speeder.jpa.data.model.Entity;

public interface SrcCodeGenEntity extends SrcCodeGen
{
    void execute(SrcCodeGenProperties prop, Entity entity) throws Exception; 
}
