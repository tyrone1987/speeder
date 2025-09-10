
package io.github.tyrone1987.data.model;

import jakarta.xml.bind.annotation.XmlAttribute;

public class EnumeratorValue
{
    private String name;

    @XmlAttribute
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
