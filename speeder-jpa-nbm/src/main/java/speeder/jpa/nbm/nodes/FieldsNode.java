
package speeder.jpa.nbm.nodes;

import java.util.List;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import speeder.jpa.data.model.EntitySpec;
import speeder.jpa.data.model.Field;
import speeder.jpa.nbm.DataModelChangeListener;
import speeder.jpa.nbm.DataModelWraper;

public class FieldsNode extends AbstractNode implements DataModelChangeListener
{
    private DataModelWraper wraper;

    private EntitySpec entity;

    public FieldsNode(DataModelWraper wraper, EntitySpec entity)
    {
        super(Children.create(new ChildFactoryImpl(wraper, entity), true));
        this.wraper = wraper;
        this.entity = entity;
        this.wraper.addDataModelChangeListener(this);
        setName("fields");
        setIconBaseWithExtension("speeder/jpa/nbm/fieldPublic.png");
    }

    private static class ChildFactoryImpl extends ChildFactory<Field> implements DataModelChangeListener
    {
        private DataModelWraper wraper;

        private EntitySpec entity;

        public ChildFactoryImpl(DataModelWraper wraper, EntitySpec entity)
        {
            this.entity = entity;
            this.wraper = wraper;
            this.wraper.addDataModelChangeListener(this);
        }

        @Override
        protected boolean createKeys(List<Field> list)
        {
            if(entity.getFields() != null)
            {
                List<Field> en = entity.getFields();
                for (Field e : en)
                {
                    list.add(e);
                }
            }
            return true;
        }

        @Override
        protected Node createNodeForKey(Field key)
        {
            return new FieldNode(wraper, key);
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
