import javax.swing.*;
import java.awt.*;

public class King extends JButton implements FirstMove{
    private boolean firstMove = true;
    public King(int x, int y, int w, int h, String s, Color color) {
        this.setBounds(x, y, w, h);
        this.setIcon( new ImageIcon(s));
        this.setOpaque(false);
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        this.setBackground(color);
    }

    @Override
    public boolean getFirstMove() {
        return firstMove;
    }

    @Override
    public void setFirstMove(boolean f) {
    firstMove = f;
    }

}


