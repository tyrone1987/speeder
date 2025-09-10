
package speeder.jpa.nbm.nodes;

import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import speeder.jpa.data.model.Converter;
import speeder.jpa.nbm.DataModelChangeListener;
import speeder.jpa.nbm.DataModelWraper;

public class ConverterNode extends AbstractNode implements DataModelChangeListener
{
    private DataModelWraper wraper;

    private Converter converter;

    public ConverterNode(DataModelWraper wraper, Converter converter)
    {
        super(Children.LEAF);
        this.wraper = wraper;
        this.converter = converter;
        this.wraper.addDataModelChangeListener(this);
        setName(converter.getName());
        setIconBaseWithExtension("speeder/jpa/nbm/class.png");
    }

    @Override
    public void dataModelChanged()
    {
    }
}
