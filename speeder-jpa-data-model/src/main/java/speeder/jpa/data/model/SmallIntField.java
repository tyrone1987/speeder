
package io.github.tyrone1987.data.model;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlTransient;

public class SmallIntField extends NumberField
{
    private Short defaultValue;

    @XmlAttribute
    public Short getDefault()
    {
        return defaultValue;
    }

    public void setDefault(Short defaultValue)
    {
        this.defaultValue = defaultValue;
    }

    @Override
    public DataType getType()
    {
        return DataType.SMALLINT;
    }

    @XmlTransient
    public String getDefaultValueCode()
    {
        if(defaultValue != null)
        {
            return "(short)" + defaultValue;
        }
        return null;
    }

    @Override
    public String getJavaType()
    {
        return "Short";
    }
}
