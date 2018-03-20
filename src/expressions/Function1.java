package expressions;

/**
 *
 * @author Aleksey
 */
public class Function1 extends Expression{
    public static final int REV = 1, SQRT = 2, LN = 3, LOG = 4, SIN = 5, 
            COS = 6, TG = 7, CTG = 8;
    
    private int f;
    Expression a;
    
    public Function1(Expression arg1, int code){
        a = arg1;
        if(code < REV || code > CTG)
            f = 0;
        else
            f = code;
    }
    
    
    @Override
    public double getValue() {
        if(f == REV)
            return -a.getValue();
        else
            return 0;
    }

    @Override
    public String toString() {
        if(f == REV)
            return "-(" + a.toString() + ")";
        return null;
    }

    @Override
    public void setX(double x) {
        a.setX(x);
    }
    
}
