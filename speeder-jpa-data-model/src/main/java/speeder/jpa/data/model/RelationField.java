
package speeder.jpa.data.model;

import jakarta.xml.bind.annotation.XmlAttribute;
import org.apache.commons.lang.WordUtils;

public class RelationField extends Field
{
    private String entity;
    
    private RefIntegAction onDelete;

    @Override
    public String getName()
    {
        if(super.getName() == null && getEntity() != null)
        {
            return WordUtils.uncapitalize(getEntity());
        }
        return super.getName();
    }
    
    @XmlAttribute
    public String getEntity()
    {
        return entity;
    }

    public void setEntity(String entity)
    {
        this.entity = entity;
    }

    @Override
    public DataType getType()
    {
        return DataType.RELATION;
    }

    @Override
    public String getJavaType()
    {
        return ((RelationField)this).getEntity();
    }

    @XmlAttribute
    public RefIntegAction getOnDelete()
    {
        if(onDelete == null)
        {
            if(getRequired())
            {
                return RefIntegAction.RESTRICT;
            }
            else
            {
                return RefIntegAction.SETNULL;
            }
        }
        return onDelete;
    }

    public void setOnDelete(RefIntegAction onDelete)
    {
        this.onDelete = onDelete;
    }
}
