
package io.github.tyrone1987.nbm.nodes;

import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import io.github.tyrone1987.data.model.Converter;
import io.github.tyrone1987.nbm.DataModelChangeListener;
import io.github.tyrone1987.nbm.DataModelWraper;

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
