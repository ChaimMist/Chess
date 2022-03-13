import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;


//"whitePieces/whiteKing.png"
public class Pieces extends JButton implements ActionListener {
    static Pawn[] pawns = new Pawn[16];
    static King[] kings = new King[2];
    static ArrayList<JButton> allPieces = new ArrayList<>();
    static ArrayList<JButton> allWhitePieces = new ArrayList<>();
    static ArrayList<JButton> allBlackPieces = new ArrayList<>();
    static ArrayList<int[]> moveAbleSpots = new ArrayList<>();
    static ArrayList<int[]> checkSpots = new ArrayList<>();
    static MovePiece movePiece = new MovePiece();
    static boolean piecePressed = false;
    static boolean checkMate = false;
    static boolean check = false;
    static JButton currentPressed;
    static int turns = 0;
    static JButton temp = new JButton();
    int whiteKillCounter = 1;
    int whiteKillCounterY = 1;
    int blackKillCounter = 1;
    int blackKillCounterY = 1;

    static King whiteKing;
    static King blackKing;
    Queen whiteQueen;
    Queen blackQueen;
    Pawn whitePawn;
    Pawn blackPawn;
    Bishop whiteBishop;
    Bishop whiteBishop2;
    Bishop blackBishop;
    Bishop blackBishop2;
    Knight whiteKnight;
    Knight whiteKnight2;
    Knight blackKnight;
    Knight blackKnight2;
    Rook whiteRook;
    Rook whiteRook2;
    Rook blackRook;
    Rook blackRook2;


    public Pieces() {
        whiteKing = new King(Board.boxes[4][7].getX(), Board.boxes[3][7].getY(), 110, 110, "whitePieces/whiteKing.png", Color.WHITE);
        blackKing = new King(Board.boxes[4][0].getX(), Board.boxes[4][0].getY(), 110, 110, "blackPieces/blackKing.png", Color.BLACK);
        kings[0] = whiteKing;
        kings[1] = blackKing;
        allWhitePieces.add(whiteKing);
        allBlackPieces.add(blackKing);

        allPieces.add(whiteKing);
        allPieces.add(blackKing);
        whiteQueen = new Queen(Board.boxes[3][7].getX(), Board.boxes[3][7].getY(), 110, 110, "whitePieces/whiteQueen.png", Color.WHITE);
        blackQueen = new Queen(Board.boxes[3][0].getX(), Board.boxes[4][0].getY(), 110, 110, "blackPieces/blackQueen.png", Color.BLACK);
        allPieces.add(blackQueen);
        allPieces.add(whiteQueen);
        allWhitePieces.add(whiteQueen);
        allBlackPieces.add(blackQueen);
        whiteRook = new Rook(Board.boxes[0][7].getX(), Board.boxes[0][7].getY(), 110, 110, "whitePieces/whiteRook.png", Color.WHITE);
        whiteRook2 = new Rook(Board.boxes[7][7].getX(), Board.boxes[7][7].getY(), 110, 110, "whitePieces/whiteRook.png", Color.WHITE);
        blackRook = new Rook(Board.boxes[7][0].getX(), Board.boxes[7][0].getY(), 110, 110, "blackPieces/blackRook.png", Color.BLACK);
        blackRook2 = new Rook(Board.boxes[0][0].getX(), Board.boxes[0][0].getY(), 110, 110, "blackPieces/blackRook.png", Color.BLACK);
        allWhitePieces.add(whiteRook);
        allWhitePieces.add(whiteRook2);
        allBlackPieces.add(blackRook);
        allBlackPieces.add(blackRook2);
        allPieces.add(whiteRook);
        allPieces.add(whiteRook2);
        allPieces.add(blackRook2);
        allPieces.add(blackRook);
        whiteKnight = new Knight(Board.boxes[6][7].getX(), Board.boxes[6][7].getY(), 110, 110, "whitePieces/whiteKnight.png", Color.WHITE);
        whiteKnight2 = new Knight(Board.boxes[1][7].getX(), Board.boxes[1][7].getY(), 110, 110, "whitePieces/whiteKnight.png", Color.WHITE);
        blackKnight = new Knight(Board.boxes[6][0].getX(), Board.boxes[4][0].getY(), 110, 110, "blackPieces/blackKnight.png", Color.BLACK);
        blackKnight2 = new Knight(Board.boxes[1][0].getX(), Board.boxes[4][0].getY(), 110, 110, "blackPieces/blackKnight.png", Color.BLACK);
        allWhitePieces.add(whiteKnight);
        allWhitePieces.add(whiteKnight2);
        allBlackPieces.add(blackKnight);
        allBlackPieces.add(blackKnight2);
        allPieces.add(whiteKnight);
        allPieces.add(whiteKnight2);
        allPieces.add(blackKnight);
        allPieces.add(blackKnight2);
        whiteBishop = new Bishop(Board.boxes[5][7].getX(), Board.boxes[5][7].getY(), 110, 110, "whitePieces/whiteBishop.png", Color.WHITE);
        whiteBishop2 = new Bishop(Board.boxes[2][7].getX(), Board.boxes[2][7].getY(), 110, 110, "whitePieces/whiteBishop.png", Color.WHITE);
        blackBishop = new Bishop(Board.boxes[5][0].getX(), Board.boxes[4][0].getY(), 110, 110, "blackPieces/blackBishop.png", Color.BLACK);
        blackBishop2 = new Bishop(Board.boxes[2][0].getX(), Board.boxes[4][0].getY(), 110, 110, "blackPieces/blackBishop.png", Color.BLACK);
        allWhitePieces.add(whiteBishop);
        allWhitePieces.add(whiteBishop2);
        allBlackPieces.add(blackBishop);
        allBlackPieces.add(blackBishop2);
        allPieces.add(whiteBishop);
        allPieces.add(whiteBishop2);
        allPieces.add(blackBishop);
        allPieces.add(blackBishop2);
        for (int i = 0; i < 8; i++) {
            whitePawn = new Pawn(Board.boxes[i][6].getX(), Board.boxes[i][6].getY(), 110, 110, "whitePieces/whitePawn.png", Color.WHITE);
            allPieces.add(whitePawn);
            pawns[i] = whitePawn;
            allWhitePieces.add(whitePawn);
            blackPawn = new Pawn(Board.boxes[i][1].getX(), Board.boxes[i][1].getY(), 110, 110, "blackPieces/blackPawn.png", Color.BLACK);
            allPieces.add(blackPawn);
            pawns[i + 8] = blackPawn;
            allBlackPieces.add(blackPawn);

        }
        addMouseListener();
    }

    public static boolean isMoveAbleAt(int x, int y) {
        for (int[] p : moveAbleSpots) {
            if (p[0] == x && p[1] == y) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Board.resetMoveAbleColors();
        JButton temp = ((JButton) e.getSource());
        System.out.println("Activated actionPerformed~");
        if (temp.getX() > 990)
            return;
        if (temp.getBackground() != getKingColor() && !piecePressed) {
            return;
        }
        if (checkMate) {
            System.out.println("checkMate");
            return;
        }
        if (Board.checkCheckFromKing(getKingColor())) {
            check = true;
            Board.paintKingCheck(getKingColor());
            if (Board.checkCheckMate(getKingColor())) {
                checkMate = true;
                return;
            }
        } else
            check = false;
        if (!piecePressed || currentPressed != null && currentPressed.getBackground().equals(((JButton) e.getSource()).getBackground())) {
            if (e.getSource() instanceof Pawn) {
                checkSpots.clear();
                setPawnMoveAbles((Pawn) e.getSource());
            }
            if (e.getSource() instanceof Queen) {
                moveAbleSpots.clear();
                checkSpots.clear();
                setQueenMoveAbles((Queen) e.getSource());
            }
            if (e.getSource() instanceof Bishop) {
                moveAbleSpots.clear();
                checkSpots.clear();
                setBishopMoveAbles((Bishop) e.getSource());
            }
            if (e.getSource() instanceof Rook) {
                moveAbleSpots.clear();
                checkSpots.clear();
                setRookMoveAbles((Rook) e.getSource());
            }
            if (e.getSource() instanceof Knight) {
                moveAbleSpots.clear();
                checkSpots.clear();
                setKnightMoveAbles((Knight) e.getSource());
            }
            if (e.getSource() instanceof King) {
                moveAbleSpots.clear();
                checkSpots.clear();
                setKingMoveAbles((King) e.getSource());
            }
            Board.setMoveAbleColors();
        }
        if (piecePressed && !(Objects.requireNonNull(currentPressed).getBackground().equals(((JButton) e.getSource()).getBackground()))) {
            if (Board.checkCheckFromKing(getKingColor())) {
                if (Board.checkCheckMate(getKingColor())) {
                    checkMate = true;
                    return;
                }
            }
            if (isMoveAbleAt((((JButton) e.getSource()).getX() / 110) - 1, (((JButton) e.getSource()).getY() / 110) - 1)) {
                temp = (JButton) e.getSource();
                int x = currentPressed.getX();
                int y = currentPressed.getY();
                currentPressed.setBounds(temp.getX(), temp.getY(), 110, 110);
                int x2 = temp.getX();
                int y2 = temp.getY();
                setKilledSpot(temp);
                if (checkIfNeedReturn(currentPressed.getBackground(), x, y)) {
                    temp.setBounds(x2, y2, 110, 110);
                    Board.resetMoveAbleColors();
                    Board.paintKingCheck(getKingColor());
                    allPieces.add(temp);
                    if (temp.getBackground() == Color.WHITE)
                        allWhitePieces.add(temp);
                    else
                        allBlackPieces.add(temp);
                    return;
                }

                moveAbleSpots.clear();
                piecePressed = false;
                Board.resetMoveAbleColors();
                if (Board.checkCheckFromKing(Pieces.getOtherKingColor(getKingColor()))) {
                    check = true;
                        if (Board.checkCheckMate(Pieces.getOtherKingColor(getKingColor()))) {
                            System.out.println("Check Mate");
                            checkMate = true;
                    }
                    Board.paintKingCheck(getOtherKingColor(getKingColor()));
                }
                turns++;
                return;
            }
            Board.resetMoveAbleColors();
            Board.paintKingCheck(getOtherKingColor(getKingColor()));

            return;
        }
        piecePressed = true;
        currentPressed = temp;
    }

    private void setKilledSpot(JButton temp) {
        if (temp.getBackground() == Color.WHITE) {
            if (whiteKillCounter % 7 == 0) {
                whiteKillCounter = 1;
                whiteKillCounterY++;
            }
            temp.setBounds(whiteKillCounter * 110 + 990, 110 * whiteKillCounterY, 110, 110);
            whiteKillCounter++;
            temp.setVisible(true);
            allWhitePieces.remove(temp);
        }
        if (temp.getBackground() == Color.BLACK) {
            if (blackKillCounter % 7 == 0) {
                blackKillCounter = 1;
                blackKillCounterY += 1;
            }
            temp.setBounds(blackKillCounter * 110 + 990, 110 * -blackKillCounterY + 990, 110, 110);
            blackKillCounter++;
            temp.setVisible(true);
            allBlackPieces.remove(temp);
        }
        allPieces.remove(temp);
        moveAbleSpots.clear();
    }

    public static Color getKingColor() {
        if (turns % 2 == 0)
            return Color.WHITE;
        else return Color.BLACK;
    }

    private boolean checkIfNeedReturn(Color background, int x, int y) {
        if (Board.checkCheckFromKing(background) && currentPressed != null) {
            temp.setBounds(currentPressed.getBounds());
            currentPressed.setBounds(x, y, 110, 110);
            return true;
        }
        return false;
    }

    private void setBishopMoveAbles(Bishop bishop) {
        moveAbleSpots.clear();
        Board.getDiagonals(bishop.getX(), bishop.getY(), false);
    }

    private void setRookMoveAbles(Rook rook) {
        moveAbleSpots.clear();
        Board.getHorizontalVertical(rook.getX(), rook.getY(), false);
    }

    private void setQueenMoveAbles(Queen queen) {
        moveAbleSpots.clear();
        int currentCubeX = queen.getX();
        int currentCubeY = queen.getY();
        Board.getDiagonals(currentCubeX, currentCubeY, false);
        Board.getHorizontalVertical(currentCubeX, currentCubeY, false);
    }

    private void setKnightMoveAbles(Knight knight) {
        moveAbleSpots.clear();
        Board.getKnightMoveAbles(knight.getX(), knight.getY(), false);
    }

    public static void setPawnMoveAbles(Pawn pawn) {
        moveAbleSpots.clear();
        Board.getPawnMoveAbles(pawn.getX(), pawn.getY(), false, pawn.getBackground());
    }

    private void setKingMoveAbles(King king) {
        moveAbleSpots.clear();
        Board.getKingMoveAbles(king.getX(), king.getY(), false);
    }

    public static int getKingX(Color color) {
        if (color == Color.WHITE)
            return whiteKing.getX();
        else return blackKing.getX();
    }

    public static int getKingY(Color color) {
        if (color == Color.WHITE)
            return whiteKing.getY();
        else return blackKing.getY();
    }

    public static void printMoveAbleSpots() {
        for (int[] a : moveAbleSpots) {
            System.out.println(a[0] + " : " + a[1]);
        }
    }

    private static ArrayList<JButton> getArrayOfPieceTypeAndColor(Color kingColor) {
        if (kingColor == Color.WHITE)
            return allWhitePieces;
        else
            return allBlackPieces;
    }

    private void addMouseListener() {
        for (JButton b : allPieces) {
            b.addActionListener(this);
        }
    }

    public boolean checkForSave(Color kingColor) {
        ArrayList<JButton> pieces = getArrayOfPieceTypeAndColor(kingColor);
        int x, y;
        int x1 = 0, y1 = 0;
        JButton b = null;
        ArrayList<int[]> tempMoveAbles = new ArrayList<>();
        tempMoveAbles.addAll(moveAbleSpots);
        for (JButton i : pieces) {
            System.out.println(i);
            moveAbleSpots.clear();
            Board.getMoveAbles(i);
            x = i.getX();
            y = i.getY();
            for (int[] j : moveAbleSpots) {
                if (j[0] < 0 || j[1] < 0 || j[0] > 7 || j[1] > 7) {
                    continue;
                }
                System.out.println("In J for Loop. Trying : " + j[0] + " " + j[1]);
                b = null;
                if (Board.hasPiece((j[0] + 1) * 110, (j[1] + 1) * 110)) {
                    b = Board.getPieceByAxes((j[0] + 1) * 110, (j[1] + 1) * 110);
                    if (Objects.requireNonNull(b).getBackground() != i.getBackground()) {
                        x1 = b.getX();
                        y1 = b.getY();
                        System.out.println("Has Piece at: " + x1 + " " + y1);
                    } else
                        continue;
                }
                if (b != null) {
                    b.setBounds(-1000, -1000, 0, 0);
                }
                //(j[0] + 1) * 110, (j[1] + 1) * 110,
                i.setBounds((j[0] + 1) * 110, (j[1] + 1) * 110, 110, 110);
                if (!Board.checkCheckFromKing(kingColor)) {
                    System.out.println("Not check if move : " + i.getClass() + " To: " + j[0] + " " + j[1]);
                    i.setBounds(x, y, 110, 110);
                    if (b != null) {
                        b.setBounds(x1, y1, 110, 110);
                    }
                    moveAbleSpots.clear();
                    moveAbleSpots.addAll(tempMoveAbles);
                    return true;
                }
                i.setBounds(x, y, 110, 110);
                if (b != null) {
                    b.setBounds(x1, y1, 110, 110);
                }
            }
        }
        return false;
    }

    public static Color getOtherKingColor(Color kingColor) {
        if (kingColor == Color.WHITE)
            return Color.BLACK;
        else
            return Color.WHITE;
    }
}
