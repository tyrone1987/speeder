
package speeder.jpa.nbm.actions;

import java.awt.event.ActionEvent;
import java.util.LinkedList;
import javax.swing.AbstractAction;
import speeder.jpa.data.model.EntityInterface;
import speeder.jpa.data.model.ImportPackage;
import speeder.jpa.nbm.DataModelWraper;

public class AddInterfaceAction extends AbstractAction
{
    private DataModelWraper wraper;

    public AddInterfaceAction(DataModelWraper wraper)
    {
        super("Add interface");
        this.wraper = wraper;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(wraper.getDataModel().getInterfaces() == null)
        {
            wraper.getDataModel().setInterfaces(new LinkedList<EntityInterface>());
        }
        wraper.getDataModel().getInterfaces().add(new EntityInterface());
        wraper.dataModelChanged();
    }
}
