
package io.github.tyrone1987.data.model;

import jakarta.xml.bind.annotation.XmlAttribute;

public abstract class NumberField extends Field
{
    private NumberSignStrategy sign;

    @XmlAttribute
    public NumberSignStrategy getSign()
    {
        return sign;
    }

    public void setSign(NumberSignStrategy sign)
    {
        this.sign = sign;
    }
}
