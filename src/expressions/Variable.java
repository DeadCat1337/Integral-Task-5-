package expressions;

/**
 *
 * @author Leha
 */
public class Variable extends Expression{
    double x, max, min;
    
    public Variable(double min, double max){
        if(max < min){
            this.max = min;
            this.min = max;
        } else {
            this.max = max;
            this.min = min;
        }
        
        x = this.min;
    }

    public void setX(double x) {
        if(x > max)
            this.x = max;
        else if(x < min)
            this.x = min;
        else
            this.x = x;
    }

    public void setMax(double max) {
        if(max < min)
            this.max = min;
        else
            this.max = max;
        
        if(x > max)
            x = max;
    }

    public void setMin(double min) {
        if(max < min)
            this.min = max;
        else
            this.min = min;
        
        if(x < min)
            x = min;
    }

    public double getX() {
        return x;
    }

    public double getMax() {
        return max;
    }

    public double getMin() {
        return min;
    }

    @Override
    public double getValue() {
        return x;
    }

    @Override
    public String toString() {
        return "x";
    }
}
