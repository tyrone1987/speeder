
package io.github.tyrone1987.data.model;

import jakarta.xml.bind.annotation.XmlAttribute;

public class Converter
{
    private String name;

    private String className;

    private String packageName;

    @XmlAttribute
    public String getClassName()
    {
        return className;
    }

    public void setClassName(String className)
    {
        this.className = className;
    }

    @XmlAttribute
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    @XmlAttribute
    public String getPackage()
    {
        return packageName;
    }

    public void setPackage(String packageName)
    {
        this.packageName = packageName;
    }
}
