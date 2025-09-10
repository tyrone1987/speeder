
package speeder.jpa.data.model;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlElements;
import jakarta.xml.bind.annotation.XmlTransient;
import java.util.LinkedList;
import java.util.List;

public class EntitySpec
{
    private String name;

    private List<Field> fields;

    @XmlAttribute
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    @XmlElementWrapper(name = "fields")
    @XmlElements({
        @XmlElement(name="bigint",type=BigIntField.class),
        @XmlElement(name="boolean",type=BooleanField.class),
        @XmlElement(name="date",type=DateField.class),
        @XmlElement(name="datetime",type=DateTimeField.class),
        @XmlElement(name="decimal",type=DecimalField.class),
        @XmlElement(name="double",type=DoubleField.class),
        @XmlElement(name="fixed",type=FixedField.class),
        @XmlElement(name="float",type=FloatField.class),
        @XmlElement(name="integer",type=IntegerField.class),
        @XmlElement(name="relation",type=RelationField.class),
        @XmlElement(name="smallint",type=SmallIntField.class),
        @XmlElement(name="string",type=StringField.class),
        @XmlElement(name="text",type=TextField.class),
        @XmlElement(name="timestamp",type=TimestampField.class),
        @XmlElement(name="tinyint",type=TinyIntField.class),
        @XmlElement(name="enum",type=EnumField.class)
    })
    public List<Field> getFields()
    {
        return fields;
    }

    public void setFields(List<Field> fields)
    {
        this.fields = fields;
    }
    
    @XmlTransient
    public List<RelationField> getRelations()
    {
        return findFieldsByType(RelationField.class);
    }
    
    @XmlTransient
    public List<BigIntField> getBigInts()
    {
        return findFieldsByType(BigIntField.class);
    }
    
    @XmlTransient
    public List<BooleanField> getBooleans()
    {
        return findFieldsByType(BooleanField.class);
    }
    
    @XmlTransient
    public List<DateField> getDates()
    {
        return findFieldsByType(DateField.class);
    }

    @XmlTransient
    public List<DateTimeField> getDateTimes()
    {
        return findFieldsByType(DateTimeField.class);
    }

    @XmlTransient
    public List<DecimalField> getDecimals()
    {
        return findFieldsByType(DecimalField.class);
    }
    
    @XmlTransient
    public List<DoubleField> getDoubles()
    {
        return findFieldsByType(DoubleField.class);
    }

    @XmlTransient
    public List<EnumField> getEnums()
    {
        return findFieldsByType(EnumField.class);
    }

    @XmlTransient
    public List<FixedField> getFixeds()
    {
        return findFieldsByType(FixedField.class);
    }
    
    @XmlTransient
    public List<FloatField> getFloats()
    {
        return findFieldsByType(FloatField.class);
    }
    
    @XmlTransient
    public List<IntegerField> getIntegers()
    {
        return findFieldsByType(IntegerField.class);
    }

    @XmlTransient
    public List<SmallIntField> getSmallInts()
    {
        return findFieldsByType(SmallIntField.class);
    }
    
    @XmlTransient
    public List<StringField> getStrings()
    {
        return findFieldsByType(StringField.class);
    }

    @XmlTransient
    public List<TextField> getTexts()
    {
        return findFieldsByType(TextField.class);
    }
    
    @XmlTransient
    public List<TimestampField> getTimestamps()
    {
        return findFieldsByType(TimestampField.class);
    }

    @XmlTransient
    public List<TinyIntField> getTinyInts()
    {
        return findFieldsByType(TinyIntField.class);
    }

    public <T extends Field> List<T> findFieldsByType(Class<T> cls)
    {
        List<T> lst = new LinkedList<T>();
        for(Field f :getFields())
        {
            if(f.getClass().isAssignableFrom(cls))
            {
                lst.add((T)f);
            }
        }
        return lst;
    }
}
