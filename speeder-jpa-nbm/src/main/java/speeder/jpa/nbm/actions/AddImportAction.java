
package io.github.tyrone1987.nbm.actions;

import java.awt.event.ActionEvent;
import java.util.LinkedList;
import javax.swing.AbstractAction;
import io.github.tyrone1987.data.model.DataModel;
import io.github.tyrone1987.data.model.Enumerator;
import io.github.tyrone1987.data.model.ImportPackage;
import io.github.tyrone1987.nbm.DataModelWraper;

public class AddImportAction extends AbstractAction
{
    private DataModelWraper wraper;

    public AddImportAction(DataModelWraper wraper)
    {
        super("Add import");
        this.wraper = wraper;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(wraper.getDataModel().getImports() == null)
        {
            wraper.getDataModel().setImports(new LinkedList<ImportPackage>());
        }
        wraper.getDataModel().getImports().add(new ImportPackage());
        wraper.dataModelChanged();
    }
}
