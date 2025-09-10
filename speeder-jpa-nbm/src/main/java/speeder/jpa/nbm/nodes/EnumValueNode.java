
package io.github.tyrone1987.nbm.nodes;

import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import io.github.tyrone1987.data.model.EnumeratorValue;
import io.github.tyrone1987.data.model.Field;
import io.github.tyrone1987.nbm.DataModelChangeListener;
import io.github.tyrone1987.nbm.DataModelWraper;

public class EnumValueNode extends AbstractNode implements DataModelChangeListener
{
    private DataModelWraper wraper;

    private EnumeratorValue val;

    public EnumValueNode(DataModelWraper wraper, EnumeratorValue val)
    {
        super(Children.LEAF);
        this.wraper = wraper;
        this.val = val;
        this.wraper.addDataModelChangeListener(this);
        setName(val.getName());
        setIconBaseWithExtension("speeder/jpa/nbm/constant.png");
    }

    @Override
    public void dataModelChanged()
    {
    }
}
