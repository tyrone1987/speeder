
package speeder.jpa.nbm.nodes;

import java.util.List;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import speeder.jpa.data.model.Converter;
import speeder.jpa.data.model.Entity;
import speeder.jpa.nbm.DataModelChangeListener;
import speeder.jpa.nbm.DataModelWraper;

public class ConvertersNode extends AbstractNode implements DataModelChangeListener
{
    private DataModelWraper wraper;

    private Entity entity;

    public ConvertersNode(DataModelWraper wraper, Entity entity)
    {
        super(Children.create(new ChildFactoryImpl(wraper, entity), true));
        this.wraper = wraper;
        this.entity = entity;
        this.wraper.addDataModelChangeListener(this);
        setName("converters");
        setIconBaseWithExtension("speeder/jpa/nbm/class.png");
    }

    private static class ChildFactoryImpl extends ChildFactory<Converter> implements DataModelChangeListener
    {
        private DataModelWraper wraper;

        private Entity entity;

        public ChildFactoryImpl(DataModelWraper wraper, Entity entity)
        {
            this.entity = entity;
            this.wraper = wraper;
            this.wraper.addDataModelChangeListener(this);
        }

        @Override
        protected boolean createKeys(List<Converter> list)
        {
            if(entity.getConverters() != null)
            {
                List<Converter> en = entity.getConverters();
                for (Converter e : en)
                {
                    list.add(e);
                }
            }
            return true;
        }

        @Override
        protected Node createNodeForKey(Converter key)
        {
            return new ConverterNode(wraper, key);
        }

        @Override
        public void dataModelChanged()
        {
            refresh(true);
        }
    }

    @Override
    public void dataModelChanged()
    {
    }
}
