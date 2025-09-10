
package speeder.jpa.nbm.nodes;

import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import speeder.jpa.data.model.EnumeratorValue;
import speeder.jpa.data.model.Field;
import speeder.jpa.nbm.DataModelChangeListener;
import speeder.jpa.nbm.DataModelWraper;

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
