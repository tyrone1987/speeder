package speeder.jpa.data.model;

import jakarta.xml.bind.annotation.*;

public abstract class Field {

    private String name;

    private Boolean required;

    private String column;

    private Boolean indexed;

    private Boolean unique;

    private String convert;

    private ValidatedType validated;

    @XmlAttribute
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlAttribute
    public String getColumn() {
        if (column == null) {
            return getName();
        }
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    @XmlTransient
    public abstract DataType getType();

    @XmlAttribute
    public Boolean getRequired() {
        if (required == null) {
            return false;
        }
        return required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }

    @XmlTransient
    public abstract String getJavaType();

    @XmlTransient
    public String getTemporalType() {
        DataType type = getType();

        switch (type) {
            case DATE:
                return "TemporalType.DATE";
            case DATETIME:
                return "TemporalType.TIMESTAMP";
            case TIMESTAMP:
                return "TemporalType.TIMESTAMP";
        }

        return null;
    }

    @XmlTransient
    public boolean getIsTemporalType() {
        return getType() == DataType.DATE || getType() == DataType.DATETIME || getType() == DataType.TIMESTAMP;
    }

    @XmlTransient
    public boolean getIsNumericType() {
        return getIsIntegerType() || getIsDecimalType();
    }

    @XmlTransient
    public boolean getIsIntegerType() {
        return getType() == DataType.BIGINT
                || getType() == DataType.SMALLINT
                || getType() == DataType.INTEGER
                || getType() == DataType.TINYINT;
    }

    @XmlTransient
    public boolean getIsDecimalType() {
        return getType() == DataType.DECIMAL
                || getType() == DataType.DOUBLE
                || getType() == DataType.FLOAT;
    }

    @XmlAttribute
    public Boolean getIndexed() {
        if (getUnique()) {
            return true;
        }

        if (indexed == null) {
            if (getType() == DataType.ENUM
                    || getType() == DataType.BOOLEAN
                    || getType() == DataType.FIXED
                    || getType() == DataType.RELATION) {
                return true;
            } else {
                return false;
            }
        }

        return indexed;
    }

    public void setIndexed(Boolean indexed) {
        this.indexed = indexed;
    }

    @XmlAttribute
    public Boolean getUnique() {
        if (unique == null) {
            return false;
        }
        return unique;
    }

    public void setUnique(Boolean unique) {
        this.unique = unique;
    }

    @XmlAttribute
    public String getConvert() {
        return convert;
    }

    public void setConvert(String convert) {
        this.convert = convert;
    }

    @XmlAttribute
    public ValidatedType getValidated() {
        if (null == validated) {
            return ValidatedType.NONE;
        }
        return validated;
    }

    public void setValidated(ValidatedType validated) {
        this.validated = validated;
    }

    @Override
    public String toString() {
        return "Field{" + "name=" + name + ", required=" + required + ", column=" + column + ", indexed=" + indexed + ", unique=" + unique + ", convert=" + convert + ", validated=" + validated + '}';
    }

}
