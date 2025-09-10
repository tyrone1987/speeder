
package io.github.tyrone1987.nbm.nodes;

import java.util.List;
import javax.swing.Action;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import io.github.tyrone1987.data.model.DataModel;
import io.github.tyrone1987.data.model.Entity;
import io.github.tyrone1987.nbm.DataModelChangeListener;
import io.github.tyrone1987.nbm.DataModelWraper;
import io.github.tyrone1987.nbm.actions.AddEntityAction;
import io.github.tyrone1987.nbm.actions.AddEnumAction;
import io.github.tyrone1987.nbm.actions.AddImportAction;
import io.github.tyrone1987.nbm.actions.AddInterfaceAction;

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
