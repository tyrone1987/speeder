
package speeder.jpa.data.model;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlTransient;

public abstract class AbsStrField extends Field
{
    private Boolean trim;

    private StrCaseStrategy strCase;

    private Integer length;

    private String defaultValue;

    private Boolean notEmpty;

    @XmlAttribute
    public Integer getLength()
    {
        if(length == null)
        {
            length = 255;
        }
        return length;
    }

    public void setLength(Integer length)
    {
        this.length = length;
    }

    @XmlAttribute
    public StrCaseStrategy getStrCase()
    {
        if(strCase == null)
        {
            return StrCaseStrategy.NONE;
        }
        return strCase;
    }

    public void setStrCase(StrCaseStrategy strCase)
    {
        this.strCase = strCase;
    }

    @XmlAttribute
    public boolean getTrim()
    {
        if(trim == null)
        {
            if(getType() == DataType.STRING)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        return trim;
    }

    public void setTrim(boolean trim)
    {
        this.trim = trim;
    }

    @XmlAttribute
    public String getDefault()
    {
        return defaultValue;
    }

    public void setDefault(String defaultValue)
    {
        this.defaultValue = defaultValue;
    }

    @XmlTransient
    public String getDefaultValueCode()
    {
        if(defaultValue != null)
        {
            return "\"" + this.defaultValue + "\"";
        }
        return null;
    }

    @XmlAttribute
    public boolean isNotEmpty()
    {
        if(notEmpty == null)
        {
            return getRequired();
        }
        return notEmpty;
    }

    public void setNotEmpty(boolean notEmpty)
    {
        this.notEmpty = notEmpty;
    }
}
