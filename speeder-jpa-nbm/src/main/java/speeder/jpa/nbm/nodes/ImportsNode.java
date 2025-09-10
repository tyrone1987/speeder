
package speeder.jpa.nbm.nodes;

import java.util.List;
import javax.swing.Action;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import speeder.jpa.data.model.DataModel;
import speeder.jpa.data.model.ImportPackage;
import speeder.jpa.nbm.DataModelChangeListener;
import speeder.jpa.nbm.DataModelWraper;
import speeder.jpa.nbm.actions.AddImportAction;
import speeder.jpa.nbm.actions.AddInterfaceAction;

public class ImportsNode extends AbstractNode implements DataModelChangeListener
{
    private DataModelWraper wraper;

    private DataModel dataModel;

    public ImportsNode(DataModelWraper wraper, DataModel dataModel)
    {
        super(Children.create(new ChildFactoryImpl(wraper, dataModel), true));
        this.wraper = wraper;
        this.dataModel = dataModel;
        this.wraper.addDataModelChangeListener(this);
        setName("imports");
        setIconBaseWithExtension("speeder/jpa/nbm/package.gif");
    }

    private static class ChildFactoryImpl extends ChildFactory<ImportPackage> implements DataModelChangeListener
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
        protected boolean createKeys(List<ImportPackage> list)
        {
            if(dataModel.getImports() != null)
            {
                List<ImportPackage> imports = dataModel.getImports();
                for (ImportPackage i : imports)
                {
                    list.add(i);
                }
            }
            return true;
        }

        @Override
        protected Node createNodeForKey(ImportPackage key)
        {
            return new ImportNode(wraper, key);
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
            new AddImportAction(wraper),
        };
    }

    @Override
    public void dataModelChanged()
    {
    }
}
