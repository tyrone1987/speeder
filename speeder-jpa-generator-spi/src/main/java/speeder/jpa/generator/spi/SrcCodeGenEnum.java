
package speeder.jpa.generator.spi;

import speeder.jpa.data.model.Enumerator;

public interface SrcCodeGenEnum extends SrcCodeGen
{
    void execute(SrcCodeGenProperties prop, Enumerator enumerator) throws Exception; 
}
