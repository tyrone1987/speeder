
package io.github.tyrone1987.data.model;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlTransient;

public class DoubleField extends NumberField
{
    private Double defaultValue;

    @XmlAttribute
    public Double getDefault()
    {
        return defaultValue;
    }

    public void setDefault(Double defaultValue)
    {
        this.defaultValue = defaultValue;
    }

    @Override
    public DataType getType()
    {
        return DataType.DOUBLE;
    }

    @XmlTransient
    public String getDefaultValueCode()
    {
        if(defaultValue != null)
        {
            return "new Double(" + defaultValue + ")";
        }
        return null;
    }

    @Override
    public String getJavaType()
    {
        return "Double";
    }
}
