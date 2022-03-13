import javax.swing.*;
import java.awt.*;
import java.util.Objects;


public class Board extends JPanel {
    public static BoardBox[][] boxes = new BoardBox[8][8];
    static Pieces pieces;
    ImageIcon boardImage = new ImageIcon("MenuImage/ChessBoard.jpg");
    Image image = boardImage.getImage();
    static JLabel turn = new JLabel("White's Turn");
    static JLabel checkMateLabel = new JLabel("Check Mate");
    static Color black = new Color(0x6A6359);
    static Color white = new Color(0xD7D5D2);
    boolean run = true;


    public Board() {

        this.setBounds(0, 0, 1920, 1080);
        this.setLayout(null);
        turn.setBounds(420, 10, 500, 70);
        turn.setFont(new Font("Dialog", Font.BOLD, 50));
        turn.setForeground(Color.WHITE);
        turn.setVisible(true);
        checkMateLabel.setVisible(false);
        checkMateLabel.setFont(new Font("Dialog", Font.BOLD, 100));
        checkMateLabel.setForeground(Color.RED);
        checkMateLabel.setBounds(250, 220, 700, 550);
        this.add(checkMateLabel);
        this.add(turn);
        createBoard();
        pieces = new Pieces();
        addPieces();
        this.addMouseListener(Pieces.movePiece);
        this.mainGameLoop();
    }

    public static BoardBox getBox(int x, int y) {
        for (int i = 1; i < 9; i++) {
            for (int j = 1; j < 9; j++) {
                if (boxes[i - 1][j - 1].getX() / 110 == x / 110 && boxes[i - 1][j - 1].getY() / 110 == y / 110) {
                    return boxes[i - 1][j - 1];
                }
            }
        }
        return null;
    }

    public static JButton getPieceByAxes(int x, int y) {
        for (JButton b : Pieces.allPieces) {
            if (b.getX() == x && b.getY() == y) {
                return b;
            }
        }
        return null;
    }

    public static void getMoveAbles(JButton x) {
        if (x instanceof Pawn) {

            getPawnMoveAbles(x.getX(), x.getY(), false, x.getBackground());
            return;
        }
        if (x instanceof King) {

            getKingMoveAbles(x.getX(), x.getY(), false);
            return;
        }
        if (x instanceof Queen) {
            getDiagonals(x.getX(), x.getY(), false);
            getHorizontalVertical(x.getX(), x.getY(), false);
            return;
        }
        if (x instanceof Rook) {
            getHorizontalVertical(x.getX(), x.getY(), false);
            return;
        }
        if (x instanceof Bishop) {
            getDiagonals(x.getX(), x.getY(), false);
            return;
        }
        if (x instanceof Knight) {
            getKnightMoveAbles(x.getX(), x.getY(), false);
        }
    }

    public static void setMoveAbleColors() {
        Color kColor = Pieces.getKingColor();
        for (int[] x : Pieces.moveAbleSpots) {
            if (x[0] >= 0 && x[1] >= 0 && x[0] <= 7 && x[1] <= 7) {
                if (!(hasPiece((x[0] + 1) * 110, (x[1] + 1) * 110))) {
                    boxes[x[0]][x[1]].setColor(Color.GREEN);
                    continue;
                }
                if (hasPiece((x[0] + 1) * 110, (x[1] + 1) * 110) && getPieceByAxes((x[0] + 1) * 110, (x[1] + 1) * 110).getBackground() != kColor)
                    boxes[x[0]][x[1]].setColor(Color.ORANGE);
            }
        }
        if (Pieces.check)
            boxes[Pieces.getKingX(kColor) / 110 - 1][Pieces.getKingY(kColor) / 110 - 1].setColor(Color.RED);
    }

    public static void paintKingCheck(Color kColor) {

        boxes[Pieces.getKingX(kColor) / 110 - 1][Pieces.getKingY(kColor) / 110 - 1].setColor(Color.RED);
    }

    public static void resetMoveAbleColors() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (i % 2 == 0) {
                    if (j % 2 == 0)
                        boxes[i][j].setColor(white);
                    else
                        boxes[i][j].setColor(black);
                } else {
                    if (j % 2 == 0)
                        boxes[i][j].setColor(black);
                    else
                        boxes[i][j].setColor(white);
                }
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                boxes[i][j].paintComponent(g);
            }
        }
    }

    private void createBoard() {
        for (int i = 1; i < 9; i++) {
            for (int j = 1; j < 9; j++) {
                createBoardButtons(i, j);
            }
        }
    }

    private void createBoardButtons(int i, int j) {
        BoardBox box;
        if (i % 2 == 0) {
            if (j % 2 == 0)
                box = new BoardBox(i * 110, j * 110, 110, 110, white);
            else
                box = new BoardBox(i * 110, j * 110, 110, 110, black);
        } else {
            if (j % 2 == 0)
                box = new BoardBox(i * 110, j * 110, 110, 110, black);
            else
                box = new BoardBox(i * 110, j * 110, 110, 110, white);
        }
        boxes[i - 1][j - 1] = box;
    }

    private void addPieces() {
        this.add(Pieces.whiteKing);
        this.add(Pieces.blackKing);
        this.add(pieces.blackQueen);
        this.add(pieces.whiteQueen);
        this.add(pieces.blackBishop);
        this.add(pieces.blackBishop2);
        this.add(pieces.whiteBishop);
        this.add(pieces.whiteBishop2);
        this.add(pieces.whiteRook);
        this.add(pieces.whiteRook2);
        this.add(pieces.whiteKnight);
        this.add(pieces.whiteKnight2);
        this.add(pieces.blackRook);
        this.add(pieces.blackRook2);
        this.add(pieces.blackKnight);
        this.add(pieces.blackKnight2);

        for (int i = 0; i < Pieces.pawns.length; i++) {
            this.add(Pieces.pawns[i]);
        }
    }

    public static int[] getBoxSpotIndex(BoardBox b) {
        int[] indexes = new int[2];
        indexes[0] = (b.getX() / 110) - 1;
        indexes[1] = (b.getY() / 110) - 1;
        return indexes;
    }

    public static boolean hasPiece(int x, int y) {
        for (JButton b : Pieces.allPieces) {
            if (b.getX() == x && b.getY() == y) {
                return true;
            }
        }
        return false;
    }

    public static void getDiagonals(int x, int y, boolean forCheck) {
        int[] index = new int[2];
        int counter = 0;
        int plus = 1;
        for (int t = 0; t < 2; t++) {
            if (t == 1) {
                counter = 0;
                plus = -1;
            }
            index[0] = ((x / 110) - 1);
            index[1] = ((y / 110) - 1);
            //Checking Up - Left And Right
            for (int i = index[0]; i >= 0; i--) {
                index[0] = i;
                if (counter != 0 && hasPiece((i + 1) * 110, (index[1] + 1) * 110)) {
                    if (forCheck) {
                        Pieces.checkSpots.add(new int[]{index[0], index[1]});
                    } else {
                        Pieces.moveAbleSpots.add(new int[]{index[0], index[1]});
                    }
                    break;
                }
                if (forCheck) {
                    Pieces.checkSpots.add(new int[]{index[0], index[1]});
                } else {
                    Pieces.moveAbleSpots.add(new int[]{index[0], index[1]});
                }
                if (index[1] < 8 && index[1] > -1)
                    index[1] -= plus;
                counter++;
            }
            index[0] = ((x / 110) - 1);
            index[1] = ((y / 110) - 1);
            counter = 0;
            //Checking Down - Left and right
            for (int i = index[0]; i <= 7; i++) {
                index[0] = i;
                if (counter != 0 && hasPiece((i + 1) * 110, (index[1] + 1) * 110)) {
                    if (forCheck) {
                        Pieces.checkSpots.add(new int[]{index[0], index[1]});
                    } else {
                        Pieces.moveAbleSpots.add(new int[]{index[0], index[1]});
                    }

                    break;
                }
                if (forCheck) {
                    Pieces.checkSpots.add(new int[]{index[0], index[1]});
                } else {
                    Pieces.moveAbleSpots.add(new int[]{index[0], index[1]});
                }
                if (index[1] < 8 && index[1] > -1)
                    index[1] -= plus;
                counter++;
            }
        }
    }

    public static void getHorizontalVertical(int x, int y, boolean forCheck) {
        int[] index = new int[2];
        index[0] = (x / 110) - 1;
        index[1] = (y / 110) - 1;
        for (int i = index[1] - 1; i >= 0; i -= 1) {
            index[1] = i;
            if (forCheck) {
                Pieces.checkSpots.add(new int[]{index[0], index[1]});
            } else {
                Pieces.moveAbleSpots.add(new int[]{index[0], index[1]});
            }
            if (hasPiece((index[0] + 1) * 110, (index[1] + 1) * 110)) {
                break;
            }
        }
        index[1] = (y / 110) - 1;
        for (int i = index[1] + 1; i <= 7; i++) {
            index[1] = i;
            if (forCheck) {
                Pieces.checkSpots.add(new int[]{index[0], index[1]});
            } else {
                Pieces.moveAbleSpots.add(new int[]{index[0], index[1]});
            }
            if (hasPiece((index[0] + 1) * 110, (index[1] + 1) * 110)) {
                break;
            }
        }
        index[0] = (x / 110) - 1;
        index[1] = (y / 110) - 1;
        for (int i = index[0] - 1; i >= 0; i -= 1) {
            index[0] = i;
            if (forCheck) {
                Pieces.checkSpots.add(new int[]{index[0], index[1]});
            } else {
                Pieces.moveAbleSpots.add(new int[]{index[0], index[1]});
            }
            if (hasPiece((index[0] + 1) * 110, (index[1] + 1) * 110)) {
                break;
            }
        }
        index[0] = (x / 110) - 1;
        index[1] = (y / 110) - 1;
        for (int i = index[0] + 1; i <= 7; i += 1) {
            index[0] = i;
            if (forCheck) {
                Pieces.checkSpots.add(new int[]{index[0], index[1]});
            } else {
                Pieces.moveAbleSpots.add(new int[]{index[0], index[1]});
            }
            if (hasPiece((index[0] + 1) * 110, (index[1] + 1) * 110)) {
                break;
            }
        }
    }

    public static void getKnightMoveAbles(int x, int y, boolean forCheck) {
        int[] index = new int[2];
        index[0] = ((x / 110) - 1);
        index[1] = ((y / 110) - 1);
        if (index[0] < 6) {
            if (forCheck) {
                Pieces.checkSpots.add(new int[]{index[0] + 2, index[1] - 1});
                Pieces.checkSpots.add(new int[]{index[0] + 2, index[1] + 1});
            } else {
                Pieces.moveAbleSpots.add(new int[]{index[0] + 2, index[1] - 1});
                Pieces.moveAbleSpots.add(new int[]{index[0] + 2, index[1] + 1});
            }
        }
        if (forCheck) {
            Pieces.checkSpots.add(new int[]{index[0] - 2, index[1] + 1});
            Pieces.checkSpots.add(new int[]{index[0] - 2, index[1] - 1});
        } else {
            Pieces.moveAbleSpots.add(new int[]{index[0] - 2, index[1] + 1});
            Pieces.moveAbleSpots.add(new int[]{index[0] - 2, index[1] - 1});

        }
        if (index[1] < 6) {
            if (forCheck) {
                Pieces.checkSpots.add(new int[]{index[0] + 1, index[1] + 2});
                Pieces.checkSpots.add(new int[]{index[0] - 1, index[1] + 2});
            } else {
                Pieces.moveAbleSpots.add(new int[]{index[0] + 1, index[1] + 2});
                Pieces.moveAbleSpots.add(new int[]{index[0] - 1, index[1] + 2});
            }
        }
        if (index[1] > 1) {
            if (forCheck) {
                Pieces.checkSpots.add(new int[]{index[0] + 1, index[1] - 2});
                Pieces.checkSpots.add(new int[]{index[0] - 1, index[1] - 2});
            } else {
                Pieces.moveAbleSpots.add(new int[]{index[0] + 1, index[1] - 2});
                Pieces.moveAbleSpots.add(new int[]{index[0] - 1, index[1] - 2});
            }
        }
    }

    public static void getPawnMoveAbles(int x, int y, boolean forCheck, Color pawnColor) {
        Pieces.checkSpots.clear();
        int[] index = new int[2];
        index[0] = ((x / 110) - 1);
        index[1] = ((y / 110) - 1);
        if (forCheck) {
            if (pawnColor == Color.BLACK) {
                Pieces.checkSpots.add(new int[]{index[0] + 1, index[1] + 1});
                Pieces.checkSpots.add(new int[]{index[0] - 1, index[1] + 1});
            }
            if (pawnColor == Color.WHITE) {
                Pieces.checkSpots.add(new int[]{index[0] - 1, index[1] - 1});
                Pieces.checkSpots.add(new int[]{index[0] + 1, index[1] - 1});
            }
        } else {
            if (pawnColor == Color.BLACK) {

                if (hasPiece((index[0] + 2) * 110, (index[1] + 2) * 110) && (getPieceByAxes((index[0] + 2) * 110, (index[1] + 2) * 110)).getBackground() != pawnColor) {
                    Pieces.moveAbleSpots.add(new int[]{index[0] + 1, index[1] + 1});
                }

                if (hasPiece((index[0]) * 110, (index[1] + 2) * 110) && (getPieceByAxes((index[0]) * 110, (index[1] + 2) * 110).getBackground() != pawnColor)) {
                    Pieces.moveAbleSpots.add(new int[]{index[0] - 1, index[1] + 1});
                }

                if (index[1] == 1 && !hasPiece((index[0] + 1) * 110, (index[1] + 3) * 110)) {
                    Pieces.moveAbleSpots.add(new int[]{index[0], index[1] + 2});
                }

                if (!(hasPiece((index[0] + 1) * 110, (index[1] + 2) * 110))) {
                    Pieces.moveAbleSpots.add(new int[]{index[0], index[1] + 1});
                }
            }
            if (pawnColor == Color.WHITE) {

                if (hasPiece((index[0]) * 110, (index[1]) * 110) && (getPieceByAxes((index[0]) * 110, (index[1]) * 110).getBackground() != pawnColor)) {
                    Pieces.moveAbleSpots.add(new int[]{index[0] - 1, index[1] - 1});
                }

                if (hasPiece((index[0] + 2) * 110, (index[1]) * 110) && (getPieceByAxes((index[0] + 2) * 110, (index[1]) * 110).getBackground() != pawnColor)) {
                    Pieces.moveAbleSpots.add(new int[]{index[0] + 1, index[1] - 1});
                }

                if (index[1] == 6 && !hasPiece((index[0] + 1) * 110, (index[1] - 1) * 110)) {
                    Pieces.moveAbleSpots.add(new int[]{index[0], index[1] - 2});
                }

                if (!(hasPiece((index[0] + 1) * 110, index[1] * 110))) {
                    Pieces.moveAbleSpots.add(new int[]{index[0], index[1] - 1});
                }
            }
        }
    }

    public static void getKingMoveAbles(int x, int y, boolean forCheck) {
        Pieces.checkSpots.clear();
        int[] index = new int[2];
        index[0] = ((x / 110) - 1);
        index[1] = ((y / 110) - 1);
        if (forCheck) {
            Pieces.checkSpots.add(new int[]{index[0] + 1, index[1] + 1});
            Pieces.checkSpots.add(new int[]{index[0] - 1, index[1] - 1});
            Pieces.checkSpots.add(new int[]{index[0] + 1, index[1]});
            Pieces.checkSpots.add(new int[]{index[0] - 1, index[1]});
            Pieces.checkSpots.add(new int[]{index[0], index[1] + 1});
            Pieces.checkSpots.add(new int[]{index[0], index[1] - 1});
            Pieces.checkSpots.add(new int[]{index[0] + 1, index[1] - 1});
            Pieces.checkSpots.add(new int[]{index[0] - 1, index[1] + 1});
        }
        Pieces.moveAbleSpots.add(new int[]{index[0] + 1, index[1] + 1});
        Pieces.moveAbleSpots.add(new int[]{index[0] - 1, index[1] - 1});
        Pieces.moveAbleSpots.add(new int[]{index[0] + 1, index[1]});
        Pieces.moveAbleSpots.add(new int[]{index[0] - 1, index[1]});
        Pieces.moveAbleSpots.add(new int[]{index[0], index[1] + 1});
        Pieces.moveAbleSpots.add(new int[]{index[0], index[1] - 1});
        Pieces.moveAbleSpots.add(new int[]{index[0] + 1, index[1] - 1});
        Pieces.moveAbleSpots.add(new int[]{index[0] - 1, index[1] + 1});
    }

    public static boolean checkCheck(int x, int y, Color pieceColor) {
        getDiagonals(x, y, true);
        for (int[] b : Pieces.checkSpots) {
            if (hasPiece((b[0] + 1) * 110, (b[1] + 1) * 110) && ((getPieceByAxes((b[0] + 1) * 110, (b[1] + 1) * 110) instanceof Bishop || getPieceByAxes((b[0] + 1) * 110, (b[1] + 1) * 110) instanceof Queen) && (Objects.requireNonNull(getPieceByAxes((b[0] + 1) * 110, (b[1] + 1) * 110)).getBackground() != pieceColor))) {

                return true;
            }
        }
        Pieces.checkSpots.clear();
        getHorizontalVertical(x, y, true);
        for (int[] b : Pieces.checkSpots) {
            if (hasPiece((b[0] + 1) * 110, (b[1] + 1) * 110) && ((getPieceByAxes((b[0] + 1) * 110, (b[1] + 1) * 110) instanceof Rook || getPieceByAxes((b[0] + 1) * 110, (b[1] + 1) * 110) instanceof Queen) && (Objects.requireNonNull(getPieceByAxes((b[0] + 1) * 110, (b[1] + 1) * 110)).getBackground() != pieceColor))) {

                return true;
            }
        }
        Pieces.checkSpots.clear();
        getKnightMoveAbles(x, y, true);
        for (int[] b : Pieces.checkSpots) {
            if (hasPiece((b[0] + 1) * 110, (b[1] + 1) * 110) && ((getPieceByAxes((b[0] + 1) * 110, (b[1] + 1) * 110) instanceof Knight)) && (Objects.requireNonNull(getPieceByAxes((b[0] + 1) * 110, (b[1] + 1) * 110)).getBackground() != pieceColor)) {

                return true;
            }
        }
        Pieces.checkSpots.clear();
        getPawnKillAbles(x, y, pieceColor);
        for (int[] b : Pieces.checkSpots) {
            if (hasPiece((b[0] + 1) * 110, (b[1] + 1) * 110) && ((getPieceByAxes((b[0] + 1) * 110, (b[1] + 1) * 110) instanceof Pawn)) && (Objects.requireNonNull(getPieceByAxes((b[0] + 1) * 110, (b[1] + 1) * 110)).getBackground() != pieceColor)) {
                return true;
            }
        }
        Pieces.checkSpots.clear();
        getKingMoveAbles(x, y, true);
        for (int[] b : Pieces.checkSpots) {
            if (hasPiece((b[0] + 1) * 110, (b[1] + 1) * 110) && ((getPieceByAxes((b[0] + 1) * 110, (b[1] + 1) * 110) instanceof King) && (Objects.requireNonNull(getPieceByAxes((b[0] + 1) * 110, (b[1] + 1) * 110)).getBackground() != pieceColor))) {

                Pieces.checkSpots.clear();
                return true;
            }
        }
        return false;
    }

    public static boolean checkCheckMate(Color kingColor) {
        if (!pieces.checkForSave(kingColor)) {

            Pieces.checkMate = true;
        }
        return false;
    }

    public static boolean checkCheckFromKing(Color pieceColor) {
        int x = 0;
        int y = 0;

        for (King king : Pieces.kings) {
            if (king.getBackground() == pieceColor) {
                x = king.getX();
                y = king.getY();
            }
        }
        Pieces.checkSpots.clear();
        getDiagonals(x, y, true);
        for (int[] b : Pieces.checkSpots) {
            if (hasPiece((b[0] + 1) * 110, (b[1] + 1) * 110) && ((getPieceByAxes((b[0] + 1) * 110, (b[1] + 1) * 110) instanceof Bishop || getPieceByAxes((b[0] + 1) * 110, (b[1] + 1) * 110) instanceof Queen) && (Objects.requireNonNull(getPieceByAxes((b[0] + 1) * 110, (b[1] + 1) * 110)).getBackground() != pieceColor))) {

                Pieces.checkSpots.clear();
                return true;
            }
        }
        Pieces.checkSpots.clear();
        getHorizontalVertical(x, y, true);
        for (int[] b : Pieces.checkSpots) {
            if (hasPiece((b[0] + 1) * 110, (b[1] + 1) * 110) && ((getPieceByAxes((b[0] + 1) * 110, (b[1] + 1) * 110) instanceof Rook || getPieceByAxes((b[0] + 1) * 110, (b[1] + 1) * 110) instanceof Queen) && (Objects.requireNonNull(getPieceByAxes((b[0] + 1) * 110, (b[1] + 1) * 110)).getBackground() != pieceColor))) {
                Pieces.checkSpots.clear();

                return true;
            }
        }
        Pieces.checkSpots.clear();
        getKnightMoveAbles(x, y, true);
        for (int[] b : Pieces.checkSpots) {
            if (hasPiece((b[0] + 1) * 110, (b[1] + 1) * 110) && ((getPieceByAxes((b[0] + 1) * 110, (b[1] + 1) * 110) instanceof Knight)) && (Objects.requireNonNull(getPieceByAxes((b[0] + 1) * 110, (b[1] + 1) * 110)).getBackground() != pieceColor)) {
                Pieces.checkSpots.clear();

                return true;
            }
        }
        Pieces.checkSpots.clear();
        getPawnKillAbles(x, y, pieceColor);
        for (int[] b : Pieces.checkSpots) {
            if (hasPiece((b[0] + 1) * 110, (b[1] + 1) * 110) && ((getPieceByAxes((b[0] + 1) * 110, (b[1] + 1) * 110) instanceof Pawn)) && (Objects.requireNonNull(getPieceByAxes((b[0] + 1) * 110, (b[1] + 1) * 110)).getBackground() != pieceColor)) {
                return true;
            }
        }
        Pieces.checkSpots.clear();
        Color kingColor = Pieces.getKingColor();
        getKingMoveAbles(Pieces.getKingX(kingColor), Pieces.getKingY(kingColor), true);
        for (int[] b : Pieces.checkSpots) {
            if (hasPiece((b[0] + 1) * 110, (b[1] + 1) * 110) && ((getPieceByAxes((b[0] + 1) * 110, (b[1] + 1) * 110) instanceof King) && (Objects.requireNonNull(getPieceByAxes((b[0] + 1) * 110, (b[1] + 1) * 110)).getBackground() != pieceColor))) {
                return true;
            }
        }
        return false;
    }

    private static void getPawnKillAbles(int x, int y, Color color) {
        int[] index = new int[2];
        index[0] = ((x / 110) - 1);
        index[1] = ((y / 110) - 1);
        if (color.equals(Color.WHITE)) {
            Pieces.checkSpots.add(new int[]{index[0] + 1, index[1] - 1});
            Pieces.checkSpots.add(new int[]{index[0] - 1, index[1] - 1});
            return;
        }
        Pieces.checkSpots.add(new int[]{index[0] - 1, index[1] + 1});
        Pieces.checkSpots.add(new int[]{index[0] + 1, index[1] + 1});
    }

    private void mainGameLoop() {
        new Thread(() -> {
            while (run) {
                if (Pieces.checkMate) {
                    checkMateLabel.setVisible(true);
                    run = false;
                }
                if (Pieces.turns % 2 == 0)
                    turn.setText("White's Turn");
                else
                    turn.setText("Black's Turn");

                repaint();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
