package expressions;

/**
 *
 * @author Leha
 */
public class Number extends Expression{

    double v;
    
    public Number(double v){
        this.v = v;
    }
    
    @Override
    public double getValue() {
        return v;
    }

    @Override
    public String toString() {
        return "" + v;
    }
    
}
