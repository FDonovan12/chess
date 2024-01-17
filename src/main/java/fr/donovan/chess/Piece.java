package fr.donovan.chess;

import fr.donovan.chess.exception.*;

import java.util.ArrayList;
import java.util.Objects;

public abstract class Piece {
    protected Color color;
    protected String image;
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";

    public Color getColor() {
        return color;
    }

    public String getImage() {
        return image;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Piece() {
    }

    public Piece(Color color, String image) {
        this.color = color;
        this.image = image;
    }

    public Piece(Color color) {
        this.color = color;
    }

    public boolean move(Cell startCell, Cell endCell, Game game) throws InvalidMove {
        if (true) {//moveValide(startCell, endCell, game, true)) {
            endCell.setPiece(startCell.getPiece());
            startCell.setPiece(null);
            return true;
        }

        throw new InvalidMove();
    }

    public boolean moveValide(Cell startCell, Cell endCell, Game game, Boolean bool) {
//        System.out.println(" moveValide de piece");
        if (bool) {
            game.resetCase();
        }
        boolean isNull = endCell.getPiece() == null || startCell.getPiece() == null;
//        MainChess.printMove(startCell, endCell, "moveValide " + isNull);
//        System.out.println(" isNull " + isNull);
        if (isNull || !Objects.equals(endCell.getPiece().getColor(), startCell.getPiece().getColor())) {
            Piece endCellPiece = endCell.getPiece();
//            MainChess.printMove(startCellChecked, endCellChecked, "move valide is checked");
            endCell.setPiece(startCell.getPiece());
            startCell.setPiece(null);
//            game.setCurrentPlayer(game.getCurrentPlayer() == Color.WHITE ? Color.BLACK : Color.WHITE);
            boolean ischecked = game.isChecked();
//            game.setCurrentPlayer(game.getCurrentPlayer() == Color.WHITE ? Color.BLACK : Color.WHITE);
            startCell.setPiece(endCell.getPiece());
            endCell.setPiece(endCellPiece);
            if (ischecked) {
                return false;
            }
            return true;
        }
        return false;
    }

    public void possibleMove(Cell startCell, Game game) {
        for (ArrayList<Cell> line : game.getBoard()) {
            for (Cell endCell : line) {
//                printMove(startCell, endCell, "possibleMove Piece");
                if (moveValide(startCell, endCell, game, false)) {
                    endCell.setColor(Color.POSSIBLE);
                }
            }
        }
    }

    public static boolean isOnPath(Cell startCell, Game game, int line, int column, boolean pieceOnPath) {
        Cell cellOnPath = game.getBoard().get(line).get(column);
        pieceOnPath = cellOnPath.getPiece() != null || pieceOnPath;
//        if(pieceOnPath) {
//            System.out.println("isOnPath " + cellOnPath.getLine() + " " + cellOnPath.getColumn() + " " + cellOnPath.getPiece());
//        }
        return pieceOnPath;
    }

    public static boolean isPieceOnPathRook(Cell startCell, Cell endCell, Game game, int shiftColumn, int shiftLine, boolean pieceOnPath) {
        if (shiftColumn == 0) {
            if (shiftLine > 0) {
                for (int line = startCell.getLine() - 1; line > endCell.getLine(); line--) {
                    pieceOnPath = isOnPath(startCell, game, line, startCell.getColumn(), pieceOnPath);
                }
            } else {
                for (int line = startCell.getLine() + 1; line < endCell.getLine(); line++) {
                    pieceOnPath = isOnPath(startCell, game, line, startCell.getColumn(), pieceOnPath);
                }
            }
        }
        if (shiftLine == 0) {
            if (shiftColumn > 0) {
                for (int column = startCell.getColumn() - 1; column > endCell.getColumn(); column--) {
                    pieceOnPath = isOnPath(startCell, game, startCell.getLine(), column, pieceOnPath);
                }
            } else {
                for (int column = startCell.getColumn() + 1; column < endCell.getColumn(); column++) {
                    pieceOnPath = isOnPath(startCell, game, startCell.getLine(), column, pieceOnPath);
                }
            }
        }
        return pieceOnPath;
    }

    public static boolean isPieceOnPathBishop(Cell startCell, Game game, int shiftColumn, int shiftLine, boolean pieceOnPath) {
        if (Math.abs(shiftColumn) == Math.abs(shiftLine)) {
            if (shiftLine > 0 && shiftColumn > 0) {
                for (int i = 1; i < Math.abs(shiftLine); i++) {
                    pieceOnPath = isOnPath(startCell, game, startCell.getLine() - i, startCell.getColumn() - i, pieceOnPath);
                }
            }
            if (shiftLine < 0 && shiftColumn > 0) {
                for (int i = 1; i < Math.abs(shiftLine); i++) {
                    pieceOnPath = isOnPath(startCell, game, startCell.getLine() + i, startCell.getColumn() - i, pieceOnPath);
                }
            }
            if (shiftLine < 0 && shiftColumn < 0) {
                for (int i = 1; i < Math.abs(shiftLine); i++) {
                    pieceOnPath = isOnPath(startCell, game, startCell.getLine() + i, startCell.getColumn() + i, pieceOnPath);
                }
            }
            if (shiftLine > 0 && shiftColumn < 0) {
                for (int i = 1; i < Math.abs(shiftLine); i++) {
                    pieceOnPath = isOnPath(startCell, game, startCell.getLine() - i, startCell.getColumn() + i, pieceOnPath);
                }
            }
        }
        return pieceOnPath;
    }

    @Override
    public String toString() {
        return this.getImage();
    }

}