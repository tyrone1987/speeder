
package speeder.jpa.nbm.nodes;

import java.util.List;
import javax.swing.Action;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import speeder.jpa.data.model.DataModel;
import speeder.jpa.data.model.Enumerator;
import speeder.jpa.nbm.DataModelChangeListener;
import speeder.jpa.nbm.DataModelWraper;
import speeder.jpa.nbm.actions.AddEnumAction;
import speeder.jpa.nbm.actions.AddImportAction;

public class EnumsNode extends AbstractNode implements DataModelChangeListener
{
    private DataModelWraper wraper;

    private DataModel dataModel;

    public EnumsNode(DataModelWraper wraper, DataModel dataModel)
    {
        super(Children.create(new ChildFactoryImpl(wraper, dataModel), true));
        this.wraper = wraper;
        this.dataModel = dataModel;
        this.wraper.addDataModelChangeListener(this);
        setName("enums");
        setIconBaseWithExtension("speeder/jpa/nbm/enum.gif");
    }


    private static class ChildFactoryImpl extends ChildFactory<Enumerator> implements DataModelChangeListener
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
        protected boolean createKeys(List<Enumerator> list)
        {
            if(dataModel.getEnums() != null)
            {
                List<Enumerator> en = dataModel.getEnums();
                for (Enumerator e : en)
                {
                    list.add(e);
                }
            }
            return true;
        }

        @Override
        protected Node createNodeForKey(Enumerator key)
        {
            return new EnumNode(wraper, key);
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
            new AddEnumAction(wraper),
        };
    }

    @Override
    public void dataModelChanged()
    {
    }
}
