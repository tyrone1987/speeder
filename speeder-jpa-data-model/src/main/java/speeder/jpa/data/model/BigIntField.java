
package speeder.jpa.data.model;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlTransient;

public class BigIntField extends NumberField
{
    private Long defaultValue;

    @XmlAttribute
    public Long getDefault()
    {
        return defaultValue;
    }

    public void setDefault(Long defaultValue)
    {
        this.defaultValue = defaultValue;
    }

    @Override
    public DataType getType()
    {
        return DataType.BIGINT;
    }

    @XmlTransient
    public String getDefaultValueCode()
    {
        if(defaultValue != null)
        {
            return "new Long(" + defaultValue + ")";
        }
        return null;
    }

    @Override
    public String getJavaType()
    {
        return "Long";
    }
}
