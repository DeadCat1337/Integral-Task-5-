package gui;

import expressions.Expression;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import javax.swing.JButton;

public class Graph extends JButton implements MouseMotionListener, 
        MouseWheelListener, MouseListener, KeyListener {

    private static final double SCALE_CHANGE = 1.2;
    
    private static final Color GRID_COLOR = Color.BLACK, GRAPH_COLOR = Color.RED,
            BACK_COLOR = Color.WHITE, TICK_COLOR = Color.BLUE;
    
    private static final int PRES = 10000;
    
    private double gx = 0, gy = 0, scale = 50;
    private Expression ex;
    
    
    
    private int nX, nY;
    
    public Graph(Expression ex) {
        super();
        this.ex = ex;
        
        addMouseListener(this);
        addMouseWheelListener(this);
        addMouseMotionListener(this);
        addKeyListener(this);
    }

    public void setExpression(Expression ex){
        this.ex = ex;
    }
    
    @Override
    public void paint(Graphics g) {
        g.setColor(BACK_COLOR);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(GRID_COLOR);
        g.drawLine((int) (gx * scale + getWidth() / 2), 0, 
                (int) (gx * scale + getWidth() / 2), getHeight());
        g.drawLine(0, (int) (gy * scale + getHeight() / 2), 
                getWidth(), (int) (gy * scale + getHeight() / 2));
        
        g.setColor(TICK_COLOR);
        g.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));
        double len = calcDistX();
        double dl = (gy * scale + getHeight() / 2) % len;
        double v = 1;
        if(gx * scale + getWidth() / 2 < 5){
            for (int i = 0; i < 10; i++) {
                g.drawLine(0, (int)(len * i + dl), 10, (int)(len * i + dl));
                v = (getHeight()/2 - (len * i + dl))/scale + gy;
                v = (double)Math.round(v*PRES)/PRES;
                g.drawString("" + v, 1, (int)(len * i + dl)-3);
                
            }
        } else if(gx * scale + getWidth() / 2 > getWidth() - 5){
            for (int i = 0; i < 10; i++) {
                g.drawLine(getWidth() - 10, (int)(len * i + dl), 
                        getWidth(), (int)(len * i + dl));
                v = (getHeight()/2 - (len * i + dl))/scale + gy;
                v = (double)Math.round(v*PRES)/PRES;
                g.drawString("" + v, getWidth() - ("" + v).length()*g.getFont().
                        getSize(), (int)(len * i + dl)-3);
            }
        }else if(gx * scale + getWidth() / 2 > getWidth() - ("" + v).length()*
                g.getFont().getSize()){
            for (int i = 0; i < 10; i++) {
                g.drawLine((int) (gx * scale + getWidth() / 2) - 5, 
                        (int)(len * i + dl), 
                        (int) (gx * scale + getWidth() / 2) + 5, 
                        (int)(len * i + dl));
                v = (getHeight()/2 - (len * i + dl))/scale + gy;
                v = (double)Math.round(v*PRES)/PRES;
                g.drawString("" + v, getWidth() - ("" + v).length()*g.getFont().
                        getSize(), (int)(len * i + dl)-3);
            }
        } else {
            for (int i = 0; i < 10; i++) {
                g.drawLine((int) (gx * scale + getWidth() / 2) - 5, 
                        (int)(len * i + dl), 
                        (int) (gx * scale + getWidth() / 2) + 5, 
                        (int)(len * i + dl));
                v = (getHeight()/2 - (len * i + dl))/scale + gy;
                v = (double)Math.round(v*PRES)/PRES;
                g.drawString("" + v, (int) (gx * scale + getWidth() / 2), 
                        (int)(len * i + dl)-3);
            }
        }
        
        len = calcDistY();
        dl = (gx * scale + getWidth() / 2) % len;
        if(gy * scale + getHeight() / 2 < g.getFont().getSize()){
            for (int i = -1; i < 10; i++) {
                g.drawLine((int)(len * i + dl), 0, (int)(len * i + dl), 10);
                v = (getWidth()/2 - (len * i + dl))/scale + gx;
                v = (double)Math.round(v*PRES)/PRES;
                g.drawString("" + v, (int)(len * i + dl), 
                        g.getFont().getSize());
            }
        } else if(gy * scale + getHeight() / 2 > getHeight()- 5){
            for (int i = -1; i < 10; i++) {
                g.drawLine((int)(len * i + dl), getHeight() - 10, 
                        (int)(len * i + dl), getHeight());
                v = (getWidth()/2 - (len * i + dl))/scale + gx;
                v = (double)Math.round(v*PRES)/PRES;
                g.drawString("" + v, (int)(len * i + dl), 
                        getHeight() - 5);
            }
        }else {
            for (int i = -1; i < 10; i++) {
                g.drawLine((int)(len * i + dl), 
                        (int) (gy * scale + getHeight() / 2) - 5,  
                        (int)(len * i + dl),
                        (int) (gy * scale + getHeight() / 2) + 5);
                v = (getWidth()/2 - (len * i + dl))/scale + gx;
                v = (double)Math.round(v*PRES)/PRES;
                g.drawString("" + v, (int)(len * i + dl), 
                        (int) (gy * scale + getHeight() / 2) - 3);
            }
        }
        
        g.setColor(GRAPH_COLOR);
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

    private double calcDistX(){
        return (double)(getHeight() - 1)/9;
    }

    private double calcDistY(){
        return (double)(getWidth()- 1)/9;
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

    @Override
    public void keyTyped(KeyEvent ke) {
        if(ke.getKeyChar() == ' '){
            gx = 0;
            gy = 0;
            scale = 50;
        }
    }

    @Override
    public void keyPressed(KeyEvent ke) {
    }

    @Override
    public void keyReleased(KeyEvent ke) {
    }

}
