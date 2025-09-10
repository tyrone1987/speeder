
package io.github.tyrone1987.nbm.actions;

import java.awt.event.ActionEvent;
import java.util.LinkedList;
import javax.swing.AbstractAction;
import io.github.tyrone1987.data.model.DataModel;
import io.github.tyrone1987.data.model.Entity;
import io.github.tyrone1987.data.model.Enumerator;
import io.github.tyrone1987.nbm.DataModelWraper;

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