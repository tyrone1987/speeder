
package speeder.jpa.nbm.nodes;

import java.util.List;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import speeder.jpa.data.model.Entity;
import speeder.jpa.data.model.Enumerator;
import speeder.jpa.nbm.DataModelChangeListener;
import speeder.jpa.nbm.DataModelWraper;

public class EnumNode extends AbstractNode implements DataModelChangeListener
{
    private DataModelWraper wraper;

    private Enumerator enumer;

    public EnumNode(DataModelWraper wraper, Enumerator enumer)
    {
        super(Children.create(new ChildFactoryImpl(wraper, enumer), true));
        this.wraper = wraper;
        this.enumer = enumer;
        this.wraper.addDataModelChangeListener(this);
        setName(enumer.getName());
        setIconBaseWithExtension("speeder/jpa/nbm/enum.gif");
    }
    
    private static class ChildFactoryImpl extends ChildFactory implements DataModelChangeListener
    {
        private DataModelWraper wraper;

        private Enumerator enumer;

        public ChildFactoryImpl(DataModelWraper wraper, Enumerator enumer)
        {
            this.enumer = enumer;
            this.wraper = wraper;
            this.wraper.addDataModelChangeListener(this);
        }

        @Override
        protected boolean createKeys(List list)
        {
            list.add(new EnumValuesNode(wraper, enumer));
            return true;
        }

        @Override
        protected Node createNodeForKey(Object key)
        {
            return (Node)key;
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
