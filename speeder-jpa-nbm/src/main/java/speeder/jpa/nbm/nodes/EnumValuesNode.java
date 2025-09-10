
package speeder.jpa.nbm.nodes;

import java.util.List;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import speeder.jpa.data.model.Enumerator;
import speeder.jpa.data.model.EnumeratorValue;
import speeder.jpa.nbm.DataModelChangeListener;
import speeder.jpa.nbm.DataModelWraper;

public class EnumValuesNode extends AbstractNode implements DataModelChangeListener
{
    private DataModelWraper wraper;

    private Enumerator enumerator;

    public EnumValuesNode(DataModelWraper wraper, Enumerator enumerator)
    {
        super(Children.create(new ChildFactoryImpl(wraper, enumerator), true));
        this.wraper = wraper;
        this.enumerator = enumerator;
        this.wraper.addDataModelChangeListener(this);
        setName("values");
        setIconBaseWithExtension("speeder/jpa/nbm/constant.png");
    }

    private static class ChildFactoryImpl extends ChildFactory<EnumeratorValue> implements DataModelChangeListener
    {
        private DataModelWraper wraper;

        private Enumerator enumerator;

        public ChildFactoryImpl(DataModelWraper wraper, Enumerator enumerator)
        {
            this.enumerator = enumerator;
            this.wraper = wraper;
            this.wraper.addDataModelChangeListener(this);
        }

        @Override
        protected boolean createKeys(List<EnumeratorValue> list)
        {
            if(enumerator.getValues() != null)
            {
                List<EnumeratorValue> en = enumerator.getValues();
                for (EnumeratorValue e : en)
                {
                    list.add(e);
                }
            }
            return true;
        }

        @Override
        protected Node createNodeForKey(EnumeratorValue key)
        {
            return new EnumValueNode(wraper, key);
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
