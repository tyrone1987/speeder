
package speeder.jpa.nbm.actions;

import java.awt.event.ActionEvent;
import java.util.LinkedList;
import javax.swing.AbstractAction;
import speeder.jpa.data.model.DataModel;
import speeder.jpa.data.model.Entity;
import speeder.jpa.data.model.Enumerator;
import speeder.jpa.nbm.DataModelWraper;

public class AddEnumAction extends AbstractAction
{
    private DataModelWraper wraper;

    public AddEnumAction(DataModelWraper wraper)
    {
        super("Add enum");
        this.wraper = wraper;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(wraper.getDataModel().getEnums() == null)
        {
            wraper.getDataModel().setEnums(new LinkedList<Enumerator>());
        }
        wraper.getDataModel().getEnums().add(new Enumerator());
        wraper.dataModelChanged();
    }
}