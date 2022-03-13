import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame implements ActionListener {
    static JFrame menu = new JFrame();
    ImageIcon menuImage = new ImageIcon("MenuImage/MenuImage.jpg");
    JButton playGame = new JButton("Play");
    public static Board chessBoard;
    JLabel menuLabelImage = new JLabel();
    public Menu(){
        menu.setSize(1920, 1080);
        menu.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        menu.setExtendedState(JFrame.MAXIMIZED_BOTH);
        menu.setLayout(null);
        menu.setTitle("Chess");
        menu.setLocationRelativeTo(null);
        menuLabelImage.setBounds(0,0,1920,1080);
        menuLabelImage.setIcon(menuImage);
        playGame.setBounds(835, 250, 250, 75);
        playGame.setBackground(new Color(0x15735C));
        playGame.addActionListener(this);
        menu.add(playGame);
        menu.add(menuLabelImage);
        menuLabelImage.setVisible(true);
        menu.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        String action = e.getActionCommand();
        if (action .equals("Play")){
            System.out.println("Play");
            chessBoard = new Board();
            menu.add(chessBoard);
            menuLabelImage.setVisible(false);
            playGame.setVisible(false);
            chessBoard.setVisible(true);

        }
    }
}
