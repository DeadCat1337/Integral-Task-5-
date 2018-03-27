package expressions;

/**
 *
 * @author Leha
 */
public abstract class Expression {
    public abstract double getValue();
    
    public abstract double getValue(double x);
    
    @Override
    public abstract String toString();
    
    public abstract void setX(double x);
}
