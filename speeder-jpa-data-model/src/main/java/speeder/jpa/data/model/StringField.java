
package speeder.jpa.data.model;

public class StringField extends AbsStrField
{
    @Override
    public DataType getType()
    {
        return DataType.STRING;
    }

    @Override
    public String getJavaType()
    {
        return "String";
    }
}
