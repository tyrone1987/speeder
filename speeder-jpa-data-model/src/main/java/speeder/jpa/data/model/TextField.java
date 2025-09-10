
package speeder.jpa.data.model;

public class TextField extends Field
{
    @Override
    public DataType getType()
    {
        return DataType.TEXT;
    }

    @Override
    public String getJavaType()
    {
        return "String";
    }
}
