import javax.swing.*;
import java.awt.*;

public class Pawn extends JButton {
    public Pawn(int x, int y, int w, int h, String s, Color color) {
        this.setBounds(x, y, w, h);
        this.setIcon(new ImageIcon(s));
        this.setOpaque(false);
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        this.setBackground(color);

    }
}
