
package io.github.tyrone1987.data.model;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlTransient;
import java.math.BigDecimal;

public class DecimalField extends NumberField
{
    private BigDecimal defaultValue;

    @XmlAttribute
    public BigDecimal getDefault()
    {
        return defaultValue;
    }

    public void setDefault(BigDecimal defaultValue)
    {
        this.defaultValue = defaultValue;
    }

    @Override
    public DataType getType()
    {
        return DataType.DECIMAL;
    }

    @XmlTransient
    public String getDefaultValueCode()
    {
        if(defaultValue != null)
        {
            return "new BigDecimal(" + defaultValue + ")";
        }
        return null;
    }

    @Override
    public String getJavaType()
    {
        return "BigDecimal";
    }
}
