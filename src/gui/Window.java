package gui;

import expressions.*;
import integral.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author Leha
 */
public class Window extends JFrame implements ActionListener{
    JPanel opt;
    JTextField t_func;
    
    JLabel l_min, l_max, l_ths, l_ans;
    JTextField t_min, t_max, t_ths;
    
    JButton b_ok, b_int;
    
    Graph g;
    
    Expression ex;
    
    public Window(){
        super("Integral");
        
        ex = null;
        
        initW();
        initComp();
        
        repaint();
    }
    
    private void initW(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(900, 600);
        setLocationRelativeTo(null);
        //setResizable(false);
        setVisible(true);
    }
    
    private void initComp(){
        t_func = new JTextField("sin(x)/x");
        t_func.setLocation(10, 10);
        t_func.setSize(getContentPane().getWidth() - 20, 30);
        t_func.setFont(t_func.getFont().deriveFont((float)24));
        add(t_func);
        
        opt = new JPanel();
        opt.setLayout(null);
        opt.setLocation(0, t_func.getHeight() + t_func.getY() + 10);
        opt.setSize(180, getContentPane().getHeight() - 
                t_func.getHeight() - t_func.getY());
        add(opt);
        
        b_ok = new JButton("OK");
        b_ok.setLocation(10, 0);
        b_ok.setSize(opt.getWidth() - 10, 25);
        opt.add(b_ok);
        
        l_min = new JLabel("Min:");
        l_min.setLocation(10, b_ok.getHeight() + b_ok.getY() + 50);
        l_min.setSize(opt.getWidth() - 20 - 95, 20);
        opt.add(l_min);
        
        l_max = new JLabel("Max:");
        l_max.setLocation(10, l_min.getHeight() + l_min.getY() + 10);
        l_max.setSize(l_min.getWidth(), 20);
        opt.add(l_max);
        
        l_ths = new JLabel("Threads:");
        l_ths.setLocation(10, l_max.getHeight() + l_max.getY() + 10);
        l_ths.setSize(l_max.getWidth(), 20);
        opt.add(l_ths);
        
        t_min = new JTextField();
        t_min.setLocation(20 + l_min.getWidth(), l_min.getY());
        t_min.setSize(opt.getWidth() - 20 - l_min.getWidth(), 25);
        opt.add(t_min);
        
        t_max = new JTextField();
        t_max.setLocation(t_min.getX(), l_max.getY());
        t_max.setSize(t_min.getSize());
        opt.add(t_max);
        
        t_ths = new JTextField();
        t_ths.setLocation(t_max.getX(), l_ths.getY());
        t_ths.setSize(t_max.getSize());
        opt.add(t_ths);
        
        b_int = new JButton("Integrate");
        b_int.setLocation(10, l_ths.getHeight() + l_ths.getY() + 10);
        b_int.setSize(b_ok.getSize());
        opt.add(b_int);
        
        l_ans = new JLabel("Ans:");
        l_ans.setLocation(10, b_int.getHeight() + b_int.getY() + 10);
        l_ans.setSize(b_int.getWidth(), 20);
        opt.add(l_ans);
        
        g = new Graph(ex);
        g.setLocation(opt.getWidth() + 10, t_func.getHeight() + t_func.getY() + 10);
        g.setSize(getContentPane().getWidth() - opt.getWidth() - 20, 
                getContentPane().getHeight() - t_func.getHeight() - t_func.getY() - 20);
        add(g);
        
        t_func.addActionListener(this);
        b_ok.addActionListener(this);
        b_int.addActionListener(this);
    }
    
    public void calculate(){
        ex = Parser.parse(t_func.getText());
        if(ex == null)
            return;
        g.setExpression(ex);
        System.err.println(ex.toString());
        System.err.println("RESULT | X = 0 : " + ex.getValue());
        System.err.println("done");
        repaint();
    }
    
    public void integrate(){
        if(ex != null){
            double min, max;
            int ths;
            try{
                min = Double.parseDouble(t_min.getText());
                max = Double.parseDouble(t_max.getText());
                ths = Integer.parseInt(t_ths.getText());
                double res = new IntegralManager(ex, min, max, ths).calculate();
                if(Double.isFinite(res)){
                    l_ans.setText("Ans: " + (double)Math.round(res*100000)/100000);
                } else {
                    l_ans.setText("Ans: " + res);
                }
            }catch(NumberFormatException | NullPointerException exc1){
                l_ans.setText("Ans: NaN");
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        calculate();
        
        if(e.getSource() == b_int){
            integrate();
        }
    }
    
}
