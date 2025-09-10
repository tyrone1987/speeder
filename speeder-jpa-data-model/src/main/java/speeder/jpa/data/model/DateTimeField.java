
package io.github.tyrone1987.data.model;

public class DateTimeField extends Field
{
    @Override
    public DataType getType()
    {
        return DataType.DATETIME;
    }

    @Override
    public String getJavaType()
    {
        return "Date";
    }
}
