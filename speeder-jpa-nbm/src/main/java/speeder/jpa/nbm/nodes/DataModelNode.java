
package io.github.tyrone1987.nbm.nodes;

import java.util.List;
import javax.swing.Action;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import io.github.tyrone1987.data.model.DataModel;
import io.github.tyrone1987.nbm.DataModelChangeListener;
import io.github.tyrone1987.nbm.DataModelWraper;
import io.github.tyrone1987.nbm.actions.AddEntityAction;
import io.github.tyrone1987.nbm.actions.AddEnumAction;
import io.github.tyrone1987.nbm.actions.AddImportAction;
import io.github.tyrone1987.nbm.actions.AddInterfaceAction;

public class DataModelNode extends AbstractNode implements DataModelChangeListener
{
    private DataModelWraper wraper;

    private DataModel dataModel;

    public DataModelNode(DataModelWraper wraper, DataModel dataModel)
    {
        super(Children.create(new ChildFactoryImpl(wraper, dataModel), true));
        this.wraper = wraper;
        this.dataModel = dataModel;
        this.wraper.addDataModelChangeListener(this);
        setName(dataModel.getPackage());
        setIconBaseWithExtension("speeder/jpa/nbm/database.gif");
    }
    
    public DataModel getDataModel()
    {
        return dataModel;
    }

    public void setDataModel(DataModel dataModel)
    {
        this.dataModel = dataModel;
    }

    private static class ChildFactoryImpl extends ChildFactory implements DataModelChangeListener
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
        protected boolean createKeys(List list)
        {
            list.add(new ImportsNode(wraper, dataModel));
            list.add(new IntefacesNode(wraper, dataModel));
            list.add(new EntitysNode(wraper, dataModel));
            list.add(new EnumsNode(wraper, dataModel));
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
    public Action[] getActions(boolean context)
    {
        return new Action[]
        {
            new AddInterfaceAction(wraper),
            new AddImportAction(wraper),
            new AddEntityAction(wraper),
            new AddEnumAction(wraper)
        };
    }

    @Override
    public void dataModelChanged()
    {
    }
}
