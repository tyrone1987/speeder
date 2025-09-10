package speeder.jpa.data.model;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlTransient;
import org.apache.commons.lang.WordUtils;

public class EnumField extends Field {

    private String enumType;

    private EnumStorage enumStorage;

    private String defaultValue;

    @Override
    public String getName() {
        if (super.getName() == null && getEnum() != null) {
            return WordUtils.uncapitalize(getEnum());
        }
        return super.getName();
    }


    @XmlAttribute(name = "enumType") // ← NOMBRE EXPLÍCITO
    public String getEnum() {
        return enumType;
    }

    public void setEnum(String enumType) {
        this.enumType = enumType;
    }

    @Override
    public DataType getType() {
        return DataType.ENUM;
    }

    @Override
    public String getJavaType() {
        System.out.println("DEBUG - EnumField: enumType=" + enumType
                + ", enumStorage=" + enumStorage
                + ", defaultValue=" + defaultValue);
        return ((EnumField) this).getEnum();
    }

    @XmlAttribute(name = "enumStorage") // ← ESPECIFICA el nombre del atributo XML
    public EnumStorage getEnumStorage() {
        if (enumStorage == null) {
            return EnumStorage.ORDINAL;
        }
        return enumStorage;
    }

    public void setEnumStorage(EnumStorage enumStorage) {
        this.enumStorage = enumStorage;
    }

    @XmlAttribute
    public String getDefault() {
        return defaultValue;
    }

    public void setDefault(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    @XmlTransient
    public String getDefaultValueCode() {
        if (defaultValue != null) {
            return getEnum() + "." + getDefault();
        }
        return null;
    }

    @Override
    public String toString() {
        return super.toString() + "EnumField{" + "enumType=" + enumType + ", enumStorage=" + enumStorage + ", defaultValue=" + defaultValue + '}';
    }

}
