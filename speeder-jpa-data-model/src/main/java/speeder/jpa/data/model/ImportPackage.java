

package speeder.jpa.data.model;

import jakarta.xml.bind.annotation.XmlAttribute;

public class ImportPackage
{
    private String packageName;

    @XmlAttribute(name="package")
    public String getPackageName()
    {
        return packageName;
    }

    public void setPackageName(String packageName)
    {
        this.packageName = packageName;
    }
}
