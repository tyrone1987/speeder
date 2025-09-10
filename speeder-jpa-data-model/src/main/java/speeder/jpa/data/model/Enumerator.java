
package io.github.tyrone1987.data.model;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlElements;
import java.util.List;

public class Enumerator
{
    private String name;

    private List<EnumeratorValue> values;

    @XmlAttribute
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    @XmlElementWrapper
    @XmlElements({
        @XmlElement(name="value",type=EnumeratorValue.class)
    })
    public List<EnumeratorValue> getValues()
    {
        return values;
    }

    public void setValues(List<EnumeratorValue> values)
    {
        this.values = values;
    }
}
