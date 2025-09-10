
package speeder.jpa.nbm.nodes;

import java.util.List;
import javax.swing.Action;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import speeder.jpa.data.model.DataModel;
import speeder.jpa.data.model.EntityInterface;
import speeder.jpa.nbm.DataModelChangeListener;
import speeder.jpa.nbm.DataModelWraper;
import speeder.jpa.nbm.actions.AddInterfaceAction;

public class IntefacesNode extends AbstractNode implements DataModelChangeListener
{
    private DataModelWraper wraper;

    private DataModel dataModel;

    public IntefacesNode(DataModelWraper wraper, DataModel dataModel)
    {
        super(Children.create(new ChildFactoryImpl(wraper, dataModel), true));
        this.wraper = wraper;
        this.dataModel = dataModel;
        this.wraper.addDataModelChangeListener(this);
        setName("interfaces");
        setIconBaseWithExtension("speeder/jpa/nbm/interface.gif");
    }

    private static class ChildFactoryImpl extends ChildFactory<EntityInterface> implements DataModelChangeListener
    {
        private DataModelWraper wraper;

        private DataModel dataModel;

        public ChildFactoryImpl(DataModelWraper wraper, DataModel dataModel)
        {
            this.dataModel = dataModel;
            this.wraper = wraper;
            this.wraper.addDataModelChangeListener(this);
        }

        @Override
        protected boolean createKeys(List<EntityInterface> list)
        {
            if(dataModel.getInterfaces() != null)
            {
                List<EntityInterface> interf = dataModel.getInterfaces();
                for (EntityInterface i : interf)
                {
                    list.add(i);
                }
            }
            return true;
        }

        @Override
        protected Node createNodeForKey(EntityInterface key)
        {
            return new InterfaceNode(wraper, key);
        }

        @Override
        public void dataModelChanged()
        {
            refresh(true);
        }
    }

    @Override
    public Action[] getActions(boolean context)
    {
        return new Action[]
        {
            new AddInterfaceAction(wraper),
        };
    }

    @Override
    public void dataModelChanged()
    {
    }
}
