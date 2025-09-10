
package speeder.jpa.data.model;

public class TimestampField extends Field
{
    @Override
    public DataType getType()
    {
        return DataType.TIMESTAMP;
    }

    @Override
    public String getJavaType()
    {
        return "Date";
    }
}
