
package speeder.jpa.persistence;

public class RangeFilter<T>
{
    private T from;
    
    private T to;

    public T getFrom()
    {
        return from;
    }

    public void setFrom(T from)
    {
        this.from = from;
    }

    public T getTo()
    {
        return to;
    }

    public void setTo(T to)
    {
        this.to = to;
    }
}
