
package io.github.tyrone1987.data.model;

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
