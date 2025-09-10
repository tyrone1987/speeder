
package io.github.tyrone1987.nbm.nodes;

import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import io.github.tyrone1987.data.model.Entity;
import io.github.tyrone1987.data.model.ImportPackage;
import io.github.tyrone1987.nbm.DataModelChangeListener;
import io.github.tyrone1987.nbm.DataModelWraper;

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
