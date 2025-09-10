
package io.github.tyrone1987.nbm;

import java.util.LinkedList;
import java.util.List;
import io.github.tyrone1987.data.model.DataModel;

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
