
package speeder.jpa.nbm;

import java.util.LinkedList;
import java.util.List;
import speeder.jpa.data.model.DataModel;

public class DataModelWraper
{
    private List<DataModelChangeListener> listeners = new LinkedList<DataModelChangeListener>();

    private DataModel dataModel;

    public DataModelWraper(DataModel dataModel)
    {
        this.dataModel = dataModel;
    }
    
    public void addDataModelChangeListener(DataModelChangeListener l)
    {
        listeners.add(l);
    }
    
    public void dataModelChanged()
    {
        for (DataModelChangeListener dataModelChangeListener : listeners)
        {
            dataModelChangeListener.dataModelChanged();
        }
    }

    public DataModel getDataModel()
    {
        return dataModel;
    }
}
