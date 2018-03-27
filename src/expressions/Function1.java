package expressions;

/**
 *
 * @author Aleksey
 */
public class Function1 extends Expression{
    public static final int REV = 1, SQRT = 2, LN = 3, LOG = 4, SIN = 5, 
            COS = 6, TG = 7, CTG = 8, EXP = 9, SIGN = 10, ABS = 11;
    
    private static final String[] FUNC = {"rev", "sqrt", "ln", "log", "sin", 
        "cos", "tg", "ctg", "exp", "sign", "abs"};
    
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
        switch (f) {
            case REV:
                return -a.getValue();
            case SQRT:
                return Math.sqrt(a.getValue());
            case LN:
                return Math.log(a.getValue());
            case LOG:
                return Math.log10(a.getValue());
            case SIN:
                return Math.sin(a.getValue());
            case COS:
                return Math.cos(a.getValue());
            case TG:
                return Math.tan(a.getValue());
            case CTG:
                return 1 / Math.tan(a.getValue());
            case EXP:
                return Math.exp(a.getValue());
            case SIGN:
                if (a.getValue() > 0) {
                    return 1;
                } else if (a.getValue() < 0) {
                    return -1;
                } else {
                    return 0;
                }
            case ABS:
                return Math.abs(a.getValue());
            default:
                return Double.NaN;
        }
    }

    @Override
    public double getValue(double x) {
        switch (f) {
            case REV:
                return -a.getValue(x);
            case SQRT:
                return Math.sqrt(a.getValue(x));
            case LN:
                return Math.log(a.getValue(x));
            case LOG:
                return Math.log10(a.getValue(x));
            case SIN:
                return Math.sin(a.getValue(x));
            case COS:
                return Math.cos(a.getValue(x));
            case TG:
                return Math.tan(a.getValue(x));
            case CTG:
                return 1 / Math.tan(a.getValue(x));
            case EXP:
                return Math.exp(a.getValue(x));
            case SIGN:
                if (a.getValue(x) > 0) {
                    return 1;
                } else if (a.getValue(x) < 0) {
                    return -1;
                } else {
                    return 0;
                }
            case ABS:
                return Math.abs(a.getValue(x));
            default:
                return Double.NaN;
        }
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
