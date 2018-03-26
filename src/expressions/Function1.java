package expressions;

/**
 *
 * @author Aleksey
 */
public class Function1 extends Expression{
    public static final int REV = 1, SQRT = 2, LN = 3, LOG = 4, SIN = 5, 
            COS = 6, TG = 7, CTG = 8, EXP = 9;
    
    private static final String[] FUNC = {"rev", "sqrt", "ln", "log", "sin", 
        "cos", "tg", "ctg", "exp"};
    
    private int f;
    Expression a;
    
    public Function1(Expression arg1, int code){
        a = arg1;
        if(code < REV || code > FUNC.length)
            f = 0;
        else
            f = code;
    }
    
    public static Function1 tryParse(String s){
        for(int i = 0; i < FUNC.length; i++){
            if(s.startsWith(FUNC[i])){
                return new Function1(Parser.parse(s.substring(FUNC[i].length())), i + 1);
            }
        }
        return null;
    }
    
    @Override
    public double getValue() {
        if(f == REV)
            return -a.getValue();
        else if(f == SQRT)
            return Math.sqrt(a.getValue());
        else if(f == LN)
            return Math.log(a.getValue());
        else if(f == LOG)
            return Math.log10(a.getValue());
        else if(f == SIN)
            return Math.sin(a.getValue());
        else if(f == COS)
            return Math.cos(a.getValue());
        else if(f == TG)
            return Math.tan(a.getValue());
        else if(f == CTG)
            return 1/Math.tan(a.getValue());
        else if(f == EXP)
            return Math.exp(a.getValue());
        else 
            return Double.NaN;
        
    }

    @Override
    public String toString() {
        if(f == REV)
            return "-(" + a.toString() + ")";
        else
            return FUNC[f-1] + "(" + a.toString() + ")";
    }

    @Override
    public void setX(double x) {
        a.setX(x);
    }
    
    static public boolean isEndedWithFunc(String s){
        String s1 = s.toLowerCase();
        for(int i = 0; i < FUNC.length; i++){
            if(s1.endsWith(FUNC[i]))
                return true;
        }
        return false;
    }
    
}
