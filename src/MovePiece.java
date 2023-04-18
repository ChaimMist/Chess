import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MovePiece extends JPanel implements MouseListener {
    static boolean originalFlip = true;

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
            if (Pieces.isMoveAbleAt((x / 110) - 1, (y / 110) - 1)) {
                if (checkIfNeedReturn(Pieces.currentPressed.getBounds(), x, y)) {
                    Pieces.piecePressed = false;
                    Board.resetMoveAbleColors();
                    return;
                }
                if (Pieces.currentPressed instanceof FirstMove && ((FirstMove) Pieces.currentPressed).getFirstMove())
                    ((FirstMove) Pieces.currentPressed).setFirstMove(false);
                if (Pieces.currentPressed instanceof Pawn) {
                    if (Board.boardFlip) {
                        if (Pieces.currentPressed.getY() == 110) {
                            transformPawn(Pieces.currentPressed);
                        }
                    }
                    if (!Board.boardFlip) {
                        if (Pieces.currentPressed.getBackground().equals(Color.WHITE) && Pieces.currentPressed.getY() == 110) {
                            transformPawn(Pieces.currentPressed);
                        }
                        if (Pieces.currentPressed.getBackground().equals(Color.BLACK) && Pieces.currentPressed.getY() == 880) {
                            transformPawn(Pieces.currentPressed);
                        }
                    }
                }

                Pieces.check = Board.checkCheckFromKing(Pieces.getOtherKingColor(Pieces.getKingColor()));
                Pieces.piecePressed = false;
                Pieces.turns += 1;
                Pieces.pawnMovement *= -1;
                Pieces.moveAbleSpots.clear();
                if (Board.boardFlip)
                    flipBoard();
            }
        }

        Board.resetMoveAbleColors();
        if (Pieces.check) {
            Board.paintKingCheck(Pieces.getKingColor());
        }
        Pieces.moveAbleSpots.clear();
    }

    public void transformPawn(JButton currentPressed) {
        String[] x = new String[]{"Queen", "Rook", "Knight", "Bishop"};
        String newPiece = (String) JOptionPane.showInputDialog(Menu.chessBoard, "Choose new piece", "Pieces", JOptionPane.QUESTION_MESSAGE, new ImageIcon("MenuImage/chessLogo.png"), x, x[0]);
        Pieces.allPieces.remove(currentPressed);
        if (newPiece == null) {
            newPiece = ("Queen");
        }
        switch (newPiece) {
            case "Queen": {
                Queen f;
                if (currentPressed.getBackground().equals(Color.WHITE)) {
                    f = new Queen(currentPressed.getX(), currentPressed.getY(), 110, 110, "whitePieces/whiteQueen.png", Color.WHITE);
                    Pieces.allWhitePieces.add(f);
                } else {
                    f = new Queen(currentPressed.getX(), currentPressed.getY(), 110, 110, "blackPieces/blackQueen.png", Color.BLACK);
                    Pieces.allBlackPieces.add(f);
                }
                Menu.chessBoard.add(f);
                f.addActionListener(Board.pieces);
                Pieces.allPieces.add(f);
                break;
            }
            case "Rook": {
                Rook f;
                if (currentPressed.getBackground().equals(Color.WHITE)) {
                    f = new Rook(currentPressed.getX(), currentPressed.getY(), 110, 110, "whitePieces/whiteRook.png", Color.WHITE);
                    Pieces.allWhitePieces.add(f);
                } else {
                    f = new Rook(currentPressed.getX(), currentPressed.getY(), 110, 110, "blackPieces/blackRook.png", Color.BLACK);
                    Pieces.allBlackPieces.add(f);
                }
                Menu.chessBoard.add(f);
                f.addActionListener(Board.pieces);
                Pieces.allPieces.add(f);
                break;
            }
            case "Knight": {
                Knight f;
                if (currentPressed.getBackground().equals(Color.WHITE)) {
                    f = new Knight(currentPressed.getX(), currentPressed.getY(), 110, 110, "whitePieces/whiteKnight.png", Color.WHITE);
                    Pieces.allWhitePieces.add(f);
                } else {
                    f = new Knight(currentPressed.getX(), currentPressed.getY(), 110, 110, "blackPieces/blackKnight.png", Color.BLACK);
                    Pieces.allBlackPieces.add(f);
                }
                Menu.chessBoard.add(f);
                f.addActionListener(Board.pieces);
                Pieces.allPieces.add(f);
                break;
            }
            case "Bishop": {
                Bishop f;
                if (currentPressed.getBackground().equals(Color.WHITE)) {
                    f = new Bishop(currentPressed.getX(), currentPressed.getY(), 110, 110, "whitePieces/whiteBishop.png", Color.WHITE);
                    Pieces.allWhitePieces.add(f);
                } else {
                    f = new Bishop(currentPressed.getX(), currentPressed.getY(), 110, 110, "blackPieces/blackBishop.png", Color.BLACK);
                    Pieces.allBlackPieces.add(f);
                }
                Menu.chessBoard.add(f);
                f.addActionListener(Board.pieces);
                Pieces.allPieces.add(f);
            }
        }
        currentPressed.setVisible(false);
    }


    public static void flipBoard() {
        new Thread(() -> {
            for (JButton piece : Pieces.allPieces) {
                piece.setBounds(piece.getX(), 990 - piece.getY(), 110, 110);
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            originalFlip = !originalFlip;
        }).start();
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

