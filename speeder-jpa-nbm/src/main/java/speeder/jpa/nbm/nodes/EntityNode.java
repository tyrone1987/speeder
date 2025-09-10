
package io.github.tyrone1987.nbm.nodes;

import java.util.List;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import io.github.tyrone1987.data.model.Entity;
import io.github.tyrone1987.nbm.DataModelChangeListener;
import io.github.tyrone1987.nbm.DataModelWraper;

public class EntityNode extends AbstractNode implements DataModelChangeListener
{
    private DataModelWraper wraper;

    private Entity entity;

    public EntityNode(DataModelWraper wraper, Entity entity)
    {
        super(Children.create(new ChildFactoryImpl(wraper, entity), true));
        this.wraper = wraper;
        this.entity = entity;
        this.wraper.addDataModelChangeListener(this);
        setName(entity.getName());
        setIconBaseWithExtension("speeder/jpa/nbm/class.gif");
    }

    private static class ChildFactoryImpl extends ChildFactory implements DataModelChangeListener
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
        protected boolean createKeys(List list)
        {
            list.add(new FieldsNode(wraper, entity));
            list.add(new ConvertersNode(wraper, entity));
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
