package expressions;

/**
 *
 * @author Leha
 */
public class Param extends Expression{
    double a;
    String name;
    
    public Param(double a, String name){
        this.a = a;
        this.name = name;
    }

    public void setA(double a) {
        this.a = a;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getA() {
        return a;
    }

    public String getName() {
        return name;
    }
    
    @Override
    public double getValue() {
        return a;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public void setX(double x) {
    }
    
}
