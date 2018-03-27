package gui;

import expressions.Expression;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import javax.swing.JButton;

public class Graph extends JButton implements MouseMotionListener, 
        MouseWheelListener, MouseListener {

    private static final double SCALE_CHANGE = 1.2;
    
    private double gx = 0, gy = 0, scale = 10;
    private Expression ex;
    
    
    
    private int nX, nY;
    
    public Graph(Expression ex) {
        super();
        this.ex = ex;
        
        addMouseListener(this);
        addMouseWheelListener(this);
        addMouseMotionListener(this);
    }

    public void setExpression(Expression ex){
        this.ex = ex;
    }
    
    @Override
    public void paint(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.BLACK);
        g.drawLine((int) (gx * scale + getWidth() / 2), 0, (int) (gx * scale + getWidth() / 2), getHeight());
        g.drawLine(0, (int) (gy * scale + getHeight() / 2), getWidth(), (int) (gy * scale + getHeight() / 2));
        g.setColor(Color.red);
        if (ex != null) {
            int vs[] = new int[getWidth()];
            for (int i = 0; i < vs.length; i++) {
                ex.setX(((double) (i) - vs.length / 2) / scale - gx);
                //System.out.println("i = " + i + ": X = " + ((double)(i) - vs.length/2)/scale + gx);
                //vs[i] = (int) (-(ex.getValue() - gy) * scale + getHeight() / 2);
                if(!Double.isNaN(ex.getValue())){
                    vs[i] = (int) (-(ex.getValue() - gy) * scale + getHeight() / 2);
                } else {
                    vs[i] = -1;
                }
                if (i >= 1) {
                    if(vs[i] > 0 && vs[i-1] > 0){
                        g.drawLine(i - 1, vs[i - 1], i, vs[i]);
                        //System.out.println("i = " + i + ": Y = " + vs[i]);
                    }
                }
                //System.out.println("Y = " + (int)(-(ex.getValue() - gy)*scale + getHeight()/2));
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        //setLocation(getX() + e.getPoint().x - nX, getY());
        gx += (e.getX() - nX)/scale;
        gy += (e.getY() - nY)/scale;
        nX = e.getX();
        nY = e.getY();
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if(e.getWheelRotation() > 0){
            scale /= SCALE_CHANGE;
        } else {
            scale *= SCALE_CHANGE;
        }
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        nX = e.getX();
        nY = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
