
package io.github.tyrone1987.data.model;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlTransient;

public class FloatField extends NumberField
{
    private Float defaultValue;

    @XmlAttribute
    public Float getDefault()
    {
        return defaultValue;
    }

    public void setDefault(Float defaultValue)
    {
        this.defaultValue = defaultValue;
    }

    @Override
    public DataType getType()
    {
        return DataType.FLOAT;
    }
    
    @XmlTransient
    public String getDefaultValueCode()
    {
        if(defaultValue != null)
        {
            return "new Float(" + defaultValue + ")";
        }
        return null;
    }

    @Override
    public String getJavaType()
    {
        return "Float";
    }
}
