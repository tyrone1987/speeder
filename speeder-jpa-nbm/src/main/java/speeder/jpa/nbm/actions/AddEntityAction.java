
package speeder.jpa.nbm.actions;

import java.awt.event.ActionEvent;
import java.util.LinkedList;
import javax.swing.AbstractAction;
import speeder.jpa.data.model.DataModel;
import speeder.jpa.data.model.Entity;
import speeder.jpa.data.model.EntityInterface;
import speeder.jpa.nbm.DataModelWraper;

public class AddEntityAction extends AbstractAction
{
    private DataModelWraper wraper;

    public AddEntityAction(DataModelWraper wraper)
    {
        super("Add entity");
        this.wraper = wraper;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(wraper.getDataModel().getEntitys() == null)
        {
            wraper.getDataModel().setEntitys(new LinkedList<Entity>());
        }
        wraper.getDataModel().getEntitys().add(new Entity());
        wraper.dataModelChanged();
    }
}
