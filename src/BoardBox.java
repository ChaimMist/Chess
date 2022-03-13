import javax.swing.*;
import java.awt.*;

public class BoardBox extends JPanel {
    private int x;
    private int y;
    private int w;
    private int h;
    private Color color;
    boolean moveAble;

    public BoardBox(int x, int y, int w, int h, Color color){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.color = color;
        moveAble = false;

    }
    public void setColor(Color color){this.color = color;}
    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }


    public void paintComponent(Graphics g){
        g.setColor(color);
        g.fillRect(x, y, w, h);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, w, h);

    }


}

