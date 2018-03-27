package integral;

import expressions.Expression;
import java.util.ArrayList;

/**
 *
 * @author Leha
 */
public class IntegralManager {
    private int thnum;
    private double min, max;
    private Expression ex;
    
    private ArrayList<IntThread> ths_w, ths_r;
    
    public IntegralManager(Expression ex, double min, double max, int th){
        this.ex = ex;
        this.min = min;
        this.max = max;
        thnum = th;
        ths_w = new ArrayList<>();
        ths_r = new ArrayList<>();
        double li = (max - min)/th;
        for(int i = 0; i < th; i++){
            ths_w.add(new IntThread(ex, min + li*i, min + li*(1+i)));
        }
    }
    
    public double calculate(){
        double res = 0;
        while(!ths_w.isEmpty()){
            for(int i = 0; i < ths_w.size(); i++){
                if(ths_w.get(i).getState() == IntThread.READY){
                    res += ths_w.get(i).getResult();
                    if(Double.isNaN(res)){
                        return Double.NaN;
                    }
                    ths_r.add(ths_w.get(i));
                    ths_w.remove(i);
                } else if(ths_w.get(i).getState() == IntThread.ERROR){
                    System.err.println("ERROR");
                    return ths_w.get(i).getResult();
                }
            }
        }
        //System.out.println("RESULT:" + res);
        return res;
    }
    
    public void setMin(double min) {
        this.min = min;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public void setExpression(Expression ex) {
        this.ex = ex;
    }
    
    public void setThreadNumber(int num){
        thnum = num;
    }

    public double getMin() {
        return min;
    }

    public double getMax() {
        return max;
    }

    public Expression getExpression() {
        return ex;
    }
    
    public int getThreadNumber(){
        return thnum;
    }
    
}
