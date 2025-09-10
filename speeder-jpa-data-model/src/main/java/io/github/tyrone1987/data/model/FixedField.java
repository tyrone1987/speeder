
package io.github.tyrone1987.data.model;

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
