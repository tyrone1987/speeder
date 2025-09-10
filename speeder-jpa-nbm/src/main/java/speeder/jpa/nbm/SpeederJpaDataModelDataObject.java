
package io.github.tyrone1987.nbm;

import java.io.IOException;
import org.netbeans.core.spi.multiview.MultiViewElement;
import org.netbeans.core.spi.multiview.text.MultiViewEditorElement;
import org.openide.filesystems.FileObject;
import org.openide.loaders.DataObjectExistsException;
import org.openide.loaders.MultiDataObject;
import org.openide.loaders.MultiFileLoader;
import org.openide.util.Lookup;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.TopComponent;

public class SpeederJpaDataModelDataObject extends MultiDataObject
{
    public SpeederJpaDataModelDataObject(FileObject pf, MultiFileLoader loader) throws DataObjectExistsException, IOException
    {
        super(pf, loader);
        registerEditor("text/speeder-jpa-data-model+xml", true);
    }

    @Override
    protected int associateLookup()
    {
        return 1;
    }

    @MultiViewElement.Registration(displayName = "#LBL_SpeederJpaDataModel_EDITOR",
    iconBase = "speeder/jpa/nbm/speeder16.png",
    mimeType = "text/speeder-jpa-data-model+xml",
    persistenceType = TopComponent.PERSISTENCE_ONLY_OPENED,
    preferredID = "SpeederJpaDataModel",
    position = 1000)
    @Messages("LBL_SpeederJpaDataModel_EDITOR=Source")
    public static MultiViewEditorElement createEditor(Lookup lkp)
    {
        return new MultiViewEditorElement(lkp);
    }
}
