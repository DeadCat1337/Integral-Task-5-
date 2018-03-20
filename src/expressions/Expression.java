package expressions;

/**
 *
 * @author Leha
 */
public abstract class Expression {
    public abstract double getValue();
    
    @Override
    public abstract String toString();
    
    public abstract void setX(double x);
}
