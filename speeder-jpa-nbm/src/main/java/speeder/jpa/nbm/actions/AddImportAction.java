
package speeder.jpa.nbm.actions;

import java.awt.event.ActionEvent;
import java.util.LinkedList;
import javax.swing.AbstractAction;
import speeder.jpa.data.model.DataModel;
import speeder.jpa.data.model.Enumerator;
import speeder.jpa.data.model.ImportPackage;
import speeder.jpa.nbm.DataModelWraper;

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
