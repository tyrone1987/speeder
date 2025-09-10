
package speeder.jpa.data.model;

public class FixedField extends AbsStrField
{
    @Override
    public DataType getType()
    {
        return DataType.FIXED;
    }

    @Override
    public String getJavaType()
    {
        return "String";
    }
}
