
package speeder.jpa.data.model;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlTransient;

public class IntegerField extends NumberField
{
    private Integer defaultValue;

    @XmlAttribute
    public Integer getDefault()
    {
        return defaultValue;
    }

    public void setDefault(Integer defaultValue)
    {
        this.defaultValue = defaultValue;
    }

    @Override
    public DataType getType()
    {
        return DataType.INTEGER;
    }

    @XmlTransient
    public String getDefaultValueCode()
    {
        if(defaultValue != null)
        {
            return "new Integer(" + defaultValue + ")";
        }
        return null;
    }

    @Override
    public String getJavaType()
    {
        return "Integer";
    }
}
