
package speeder.jpa.data.model;

import jakarta.xml.bind.annotation.*;

public class BooleanField extends Field
{
    private Boolean defaultValue;

    @XmlAttribute
    public Boolean isDefault()
    {
        return defaultValue;
    }

    public void setDefault(Boolean defaultValue)
    {
        this.defaultValue = defaultValue;
    }

    @Override
    public DataType getType()
    {
        return DataType.BOOLEAN;
    }

    @XmlTransient
    public String getDefaultValueCode()
    {
        if(defaultValue != null)
        {
            return defaultValue.toString();
        }
        return null;
    }

    @Override
    public String getJavaType()
    {
        return "Boolean";
    }
}
