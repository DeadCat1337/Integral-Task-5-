package expressions;

/**
 *
 * @author Leha
 */
public class Operation extends Expression{
    public static final int PLUS = 1, MINUS = 2, MULT = 3, DIV = 4, POW = 5;
    private Expression a1, a2;
    private int o;
    
    public Operation(Expression arg1, Expression arg2, int code){
        a1 = arg1;
        a2 = arg2;
        if(code < PLUS || code > POW)
            o = 0;
        else
            o = code;
    }

    @Override
    public double getValue() {
        if(o == PLUS)
            return a1.getValue() + a2.getValue();
        else if(o == MINUS)
            return a1.getValue() - a2.getValue();
        else if(o == MULT)
            return a1.getValue() * a2.getValue();
        else if(o == DIV)
            return a1.getValue() / a2.getValue();
        else if(o == POW)
            return Math.pow(a1.getValue(), a2.getValue());
        else
            return 0;
    }

    @Override
    public String toString() {
        //return "(" + a1.toString() + ") OPERATION (" + a2.toString() + ")";
        if(o == PLUS)
            return "(" + a1.toString() + ")+(" + a2.toString() + ")";
        else if(o == MINUS)
            return "(" + a1.toString() + ")-(" + a2.toString() + ")";
        else if(o == MULT)
            return "(" + a1.toString() + ")*(" + a2.toString() + ")";
        else if(o == DIV)
            return "(" + a1.toString() + ")/(" + a2.toString() + ")";
        else if(o == POW)
            return "(" + a1.toString() + ")^(" + a2.toString() + ")";
        else
            return "";
    }
}
