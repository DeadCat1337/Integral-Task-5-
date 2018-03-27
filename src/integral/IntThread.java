package integral;

import expressions.Expression;

/**
 *
 * @author Leha
 */
public class IntThread implements Runnable{
    public static final int FREE = 0, WORKING = 1, READY = 2, ERROR = 3;
    public static final int ITR = 100;
    private int state;
    
    private double min, max;
    private Expression ex;
    
    private double res;
    private double len;
    private int i;
    
    public IntThread(Expression ex, double min, double max){
        this.ex = ex;
        this.min = min;
        this.max = max;
        len = (max - min)/ITR;
        Thread th = new Thread(this);
        th.start();
        start();
    }
    
    public void start(){
        res = 0;
        i = 0;
        state = WORKING;
    }
    
    public int getState(){
        return state;
    }
    
    public double getResult(){
        switch (state) {
            case READY:
                state = FREE;
                return res;
            case ERROR:
                return res;
            default:
                return 0;
        }
    }
    
    @Override
    public void run() {
        while(state == WORKING){
            if(i < ITR){
                res += (ex.getValue(min + len*i) + ex.getValue(min + len*(i + 1)))*len/2;
                if(Double.isNaN(res)){
                    res = Double.NaN;
                    state = ERROR;
                }
                if(Double.isInfinite(res)){
                    state = ERROR;
                }
            }
            i++;
            if(i >= ITR){
                state = READY;
                System.out.println("READY [" + min + "; " + max + "]: " + res);
            }
        }
    }
    
}
