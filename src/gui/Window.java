package gui;

import expressions.*;
import integral.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
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
    JButton b_ok;
    
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
        opt = new JPanel();
        opt.setLayout(null);
        opt.setLocation(0, 0);
        opt.setSize(getContentPane().getWidth(), 100);
        add(opt);
        
        t_func = new JTextField();
        t_func.setLocation(10, 10);
        t_func.setSize(opt.getWidth() - 20, 30);
        t_func.setFont(t_func.getFont().deriveFont((float)24));
        opt.add(t_func);
        
        b_ok = new JButton("OK");
        b_ok.setLocation(10, t_func.getHeight() + t_func.getY() + 10);
        b_ok.setSize(180, 30);
        opt.add(b_ok);
        
        g = new Graph();
        g.setLocation(10, opt.getHeight() + 10);
        g.setSize(opt.getWidth() - 20, getContentPane().getHeight() - opt.getHeight() - 20);
        add(g);
        
        
        t_func.addActionListener(this);
        b_ok.addActionListener(this);
    }
    
    public void calculate(){
        ex = Parser.parse(t_func.getText());
        if(ex == null)
            return;
        
        System.err.println(ex.toString());
        
        System.err.println("RESULT | X = 0 : " + ex.getValue());
        
        System.err.println("done");
        
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        calculate();
    }
    
    public class Graph extends JButton{
        
        private double gx = 0, gy = 0, scale = 10;
        
        public Graph(){
            super();
        }
        
        
        @Override
        public void paint(Graphics g){
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, getWidth(), getHeight());
            g.setColor(Color.BLACK);
            g.drawLine((int)(gx*scale + getWidth()/2), 0, (int)(gx*scale + getWidth()/2), getHeight());
            g.drawLine(0, (int)(gy*scale + getHeight()/2), getWidth(), (int)(gy*scale + getHeight()/2));
            g.setColor(Color.red);
            if(ex != null){
                int vs[] = new int[getWidth()];
                for(int i = 0; i < vs.length; i++){
                    ex.setX(((double)(i) - vs.length/2)/scale + gx);
                    //System.out.println("i = " + i + ": X = " + ((double)(i) - vs.length/2)/scale + gx);
                    vs[i] = (int)(-(ex.getValue() - gy)*scale + getHeight()/2);
                    if(i >= 1){
                        g.drawLine(i-1, vs[i-1], i, vs[i]);
                    }
                    
                    //System.out.println("Y = " + (int)(-(ex.getValue() - gy)*scale + getHeight()/2));
                }
                
            }
            
        }
        
    }
    
}
