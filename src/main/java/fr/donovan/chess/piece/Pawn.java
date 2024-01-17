package fr.donovan.chess.piece;

import fr.donovan.chess.*;
import fr.donovan.chess.exception.InvalidMove;

import java.util.Objects;

import static fr.donovan.chess.MainChess.printMove;

public class Pawn extends Piece {
    private boolean firstMove = true;
    public Pawn(Color color) {
        super(color);
        if (Objects.equals(color, Color.WHITE)) {
            this.setImage("♟");
        }
        if (Objects.equals(color, Color.BLACK)) {
            this.setImage("♙");
        }
        this.setImage("P");
    }

    @Override
    public boolean move(Cell startCell, Cell endCell, Game game) throws InvalidMove {
//        int shiftColumn = Math.abs(startCell.getColumn() - endCell.getColumn());
//        int shiftLine = Math.abs(startCell.getLine() - endCell.getLine());
//        if (firstMove && shiftLine == 2 && shiftColumn == 0 && endCell.getPiece() == null) {
//            firstMove = false;
//            return super.move(startCell, endCell, game);
//
//        }
//        if (shiftLine == 1 && shiftColumn == 0 && endCell.getPiece() == null) {
//            firstMove = false;
//            return super.move(startCell, endCell, game);
//        }
//        if (shiftLine == 1 && shiftColumn == 1 && endCell.getPiece() != null) {
//            boolean moved = super.move(startCell, endCell, game);
//            firstMove = false;
//            return moved;
//
//        }
//        throw new InvalidMove(this);
        if (moveValide(startCell, endCell, game, true)) {
            firstMove = false;
            boolean moved = super.move(startCell, endCell, game);
            int orientation = this.getColor() == Color.BLACK ? 0 : 7;
            if(moved && endCell.getLine() == orientation) {
                Queen queen = new Queen(this.getColor());
                endCell.setPiece(queen);
            }
            return moved;
        }
        throw new InvalidMove(this);
    }

    @Override
    public boolean moveValide(Cell startCell, Cell endCell, Game game, Boolean bool) {
        if(bool) {
            game.resetCase();
        }
        int shiftColumn = Math.abs(startCell.getColumn() - endCell.getColumn());
        int orientation = this.getColor() == Color.BLACK ? -1 : 1;
        int shiftLine = (endCell.getLine() - startCell.getLine());
        if (firstMove && shiftLine*orientation == 2 && shiftColumn == 0 && endCell.getPiece() == null) {
            return super.moveValide(startCell, endCell, game, bool);

        }
        if (shiftLine*orientation == 1 && shiftColumn == 0 && endCell.getPiece() == null) {
            return super.moveValide(startCell, endCell, game, bool);
        }
        if (shiftLine*orientation == 1 && shiftColumn == 1 && endCell.getPiece() != null) {
            return super.moveValide(startCell, endCell, game, bool);

        }
        return false;
    }
}
