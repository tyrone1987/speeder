
package speeder.jpa.nbm.nodes;

import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import speeder.jpa.data.model.Field;
import speeder.jpa.nbm.DataModelChangeListener;
import speeder.jpa.nbm.DataModelWraper;

public class FieldNode extends AbstractNode implements DataModelChangeListener
{
    private DataModelWraper wraper;

    private Field field;

    public FieldNode(DataModelWraper wraper, Field field)
    {
        super(Children.LEAF);
        this.wraper = wraper;
        this.field = field;
        this.wraper.addDataModelChangeListener(this);
        setName(field.getName());
        setIconBaseWithExtension("speeder/jpa/nbm/fieldPublic.png");
    }

    @Override
    public String getHtmlDisplayName()
    {
        return field.getName() + " <font color=\"AAAAAA\">" + field.getType() + "</font>";
    }

    @Override
    public void dataModelChanged()
    {
    }
}
