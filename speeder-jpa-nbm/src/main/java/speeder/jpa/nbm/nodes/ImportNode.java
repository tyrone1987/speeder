
package speeder.jpa.nbm.nodes;

import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import speeder.jpa.data.model.Entity;
import speeder.jpa.data.model.ImportPackage;
import speeder.jpa.nbm.DataModelChangeListener;
import speeder.jpa.nbm.DataModelWraper;

public class ImportNode extends AbstractNode implements DataModelChangeListener
{
    private DataModelWraper wraper;

    private ImportPackage importPackage;

    public ImportNode(DataModelWraper wraper, ImportPackage importPackage)
    {
        super(Children.LEAF);
        this.wraper = wraper;
        this.importPackage = importPackage;
        this.wraper.addDataModelChangeListener(this);
        setName(importPackage.getPackageName());
        setIconBaseWithExtension("speeder/jpa/nbm/package.gif");
    }

    @Override
    public void dataModelChanged()
    {
    }
}
