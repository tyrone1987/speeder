
package speeder.jpa.nbm.nodes;

import java.util.List;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import speeder.jpa.data.model.EntityInterface;
import speeder.jpa.nbm.DataModelChangeListener;
import speeder.jpa.nbm.DataModelWraper;

public class InterfaceNode extends AbstractNode implements DataModelChangeListener
{
    private DataModelWraper wraper;

    private EntityInterface interf;

    public InterfaceNode(DataModelWraper wraper, EntityInterface interf)
    {
        super(Children.create(new ChildFactoryImpl(wraper, interf), true));
        this.wraper = wraper;
        this.interf = interf;
        this.wraper.addDataModelChangeListener(this);
        setName(interf.getName());
        setIconBaseWithExtension("speeder/jpa/nbm/interface.gif");
    }

    private static class ChildFactoryImpl extends ChildFactory implements DataModelChangeListener
    {
        private DataModelWraper wraper;

        private EntityInterface entity;

        public ChildFactoryImpl(DataModelWraper wraper, EntityInterface entity)
        {
            this.entity = entity;
            this.wraper = wraper;
            this.wraper.addDataModelChangeListener(this);
        }

        @Override
        protected boolean createKeys(List list)
        {
            list.add(new FieldsNode(wraper, entity));
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
