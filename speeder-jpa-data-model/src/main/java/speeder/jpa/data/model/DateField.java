
package speeder.jpa.data.model;

public class DateField extends Field
{
    @Override
    public DataType getType()
    {
        return DataType.DATE;
    }

    @Override
    public String getJavaType()
    {
        return "Date";
    }
}
