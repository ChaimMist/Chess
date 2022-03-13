import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MovePiece extends JPanel implements MouseListener {

    public MovePiece() {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    private int[] getClicked(MouseEvent e) {
        int[] cord = new int[2];
        cord[0] = e.getX();
        cord[1] = e.getY();
        return cord;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("Activated mousePressed~");
        if (Pieces.checkMate) {
            return;
        }
        if (Board.checkCheckFromKing(Pieces.getKingColor())) {
            if (Board.checkCheckMate(Pieces.getKingColor())) {
                Pieces.checkMate = true;
                return;
            }
        }

        if (Pieces.piecePressed && inBoardBounds(e)) {
            int x = (Board.getBox(getClicked(e)[0], getClicked(e)[1])).getX();
            int y = (Board.getBox(getClicked(e)[0], getClicked(e)[1])).getY();
            System.out.println("Clicked Point: " + ((x / 110) - 1) + " " + ((y / 110) - 1));
            if (Pieces.isMoveAbleAt((x / 110) - 1, (y / 110) - 1)) {
                if (checkIfNeedReturn(Pieces.currentPressed.getBounds(), x, y)) {
                    Pieces.piecePressed = false;
                    Board.resetMoveAbleColors();
                    return;
                }
                Pieces.check = Board.checkCheckFromKing(Pieces.getOtherKingColor(Pieces.getKingColor()));
                Pieces.piecePressed = false;
                Pieces.turns += 1;
            }
        }
        Board.resetMoveAbleColors();
        if (Pieces.check){
            Board.paintKingCheck(Pieces.getKingColor());
        }
        Pieces.moveAbleSpots.clear();
    }

    public static boolean checkIfNeedReturn(Rectangle bounds, int x, int y) {
        Pieces.currentPressed.setBounds(x, y, 110, 110);
        if (Board.checkCheckFromKing(Pieces.getKingColor())) {
            Pieces.currentPressed.setBounds(bounds.getBounds());
            return true;
        }
        return false;
    }


    private boolean inBoardBounds(MouseEvent e) {
        return e.getX() < 990 && e.getX() > 109 && e.getY() < 990 && e.getY() > 109;
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

