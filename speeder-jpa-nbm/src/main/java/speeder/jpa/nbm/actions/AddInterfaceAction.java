
package io.github.tyrone1987.nbm.actions;

import java.awt.event.ActionEvent;
import java.util.LinkedList;
import javax.swing.AbstractAction;
import io.github.tyrone1987.data.model.EntityInterface;
import io.github.tyrone1987.data.model.ImportPackage;
import io.github.tyrone1987.nbm.DataModelWraper;

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
