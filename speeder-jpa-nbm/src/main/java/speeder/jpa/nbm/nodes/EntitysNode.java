
package speeder.jpa.nbm.nodes;

import java.util.List;
import javax.swing.Action;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import speeder.jpa.data.model.DataModel;
import speeder.jpa.data.model.Entity;
import speeder.jpa.nbm.DataModelChangeListener;
import speeder.jpa.nbm.DataModelWraper;
import speeder.jpa.nbm.actions.AddEntityAction;
import speeder.jpa.nbm.actions.AddEnumAction;
import speeder.jpa.nbm.actions.AddImportAction;
import speeder.jpa.nbm.actions.AddInterfaceAction;

public class EntitysNode extends AbstractNode implements DataModelChangeListener
{
    private DataModelWraper wraper;

    private DataModel dataModel;

    public EntitysNode(DataModelWraper wraper, DataModel dataModel)
    {
        super(Children.create(new ChildFactoryImpl(wraper, dataModel), true));
        this.wraper = wraper;
        this.dataModel = dataModel;
        this.wraper.addDataModelChangeListener(this);
        setName("entitys");
        setIconBaseWithExtension("speeder/jpa/nbm/class.gif");
    }

    private static class ChildFactoryImpl extends ChildFactory<Entity> implements DataModelChangeListener
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
        protected boolean createKeys(List<Entity> list)
        {
            if(dataModel.getEntitys() != null)
            {
                List<Entity> entitys = dataModel.getEntitys();
                for (Entity entity : entitys)
                {
                    list.add(entity);
                }
            }
            return true;
        }

        @Override
        protected Node createNodeForKey(Entity key)
        {
            return new EntityNode(wraper, key);
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
            new AddEntityAction(wraper),
        };
    }

    @Override
    public void dataModelChanged()
    {
    }
}
