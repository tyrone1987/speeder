
package speeder.jpa.data.model;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlTransient;

public class TinyIntField extends NumberField
{
    private Byte defaultValue;

    @XmlAttribute
    public Byte getDefault()
    {
        return defaultValue;
    }

    public void setDefault(Byte defaultValue)
    {
        this.defaultValue = defaultValue;
    }

    @Override
    public DataType getType()
    {
        return DataType.TINYINT;
    }

    @XmlTransient
    public String getDefaultValueCode()
    {
        if(defaultValue != null)
        {
            return "(byte)" + defaultValue;
        }
        return null;
    }

    @Override
    public String getJavaType()
    {
        return "Byte";
    }
}
