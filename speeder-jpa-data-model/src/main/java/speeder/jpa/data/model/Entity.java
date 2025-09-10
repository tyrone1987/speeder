
package io.github.tyrone1987.data.model;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlElements;
import java.util.List;

public class Entity extends EntitySpec
{
    private List<Converter> converters;

    private String table;

    @XmlAttribute
    public String getTable()
    {
        if(table == null)
        {
            return getName();
        }
        return table;
    }

    public void setTable(String table)
    {
        this.table = table;
    }

    @XmlElementWrapper(name = "converters")
    @XmlElements({
        @XmlElement(name="converter",type=Converter.class)
    })
    public List<Converter> getConverters()
    {
        return converters;
    }

    public void setConverters(List<Converter> converters)
    {
        this.converters = converters;
    }
}
