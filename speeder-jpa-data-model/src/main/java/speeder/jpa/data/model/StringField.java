
package io.github.tyrone1987.data.model;

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
