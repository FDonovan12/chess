package fr.donovan.chess.piece;

import fr.donovan.chess.*;
import fr.donovan.chess.exception.InvalidMove;

import java.util.Objects;

public class Bishop extends Piece {
    public Bishop(Color color) {
        super(color);
        if (Objects.equals(color, Color.WHITE)) {
            this.setImage("♝");
        }
        if (Objects.equals(color, Color.BLACK)) {
            this.setImage("♗");
        }
        this.setImage("B");
    }

    @Override
    public boolean move(Cell startCell, Cell endCell, Game game) throws InvalidMove {
//        int shiftColumn = (startCell.getColumn() - endCell.getColumn());
//        int shiftLine = (startCell.getLine() - endCell.getLine());
//        boolean pieceOnPath = false;
//        if (Math.abs(shiftColumn) == Math.abs(shiftLine)) {
//            pieceOnPath = isPieceOnPathBishop(startCell, game, shiftColumn, shiftLine, pieceOnPath);
//        } else {
//            throw new InvalidMove(this);
//        }
//        if (pieceOnPath) {
//            throw new InvalidMove(true);
//        }
//        return super.move(startCell, endCell, game);
        if (moveValide(startCell, endCell, game, true)) {
            return super.move(startCell, endCell, game);
        }
        throw new InvalidMove(this);
    }

    @Override
    public boolean moveValide(Cell startCell, Cell endCell, Game game, Boolean bool) {
        if(bool) {
            game.resetCase();
        }
        int shiftColumn = (startCell.getColumn() - endCell.getColumn());
        int shiftLine = (startCell.getLine() - endCell.getLine());
        boolean pieceOnPath = false;
        if (Math.abs(shiftColumn) == Math.abs(shiftLine)) {
            pieceOnPath = isPieceOnPathBishop(startCell, game, shiftColumn, shiftLine, pieceOnPath);
        } else {
            return false;
        }
        if (pieceOnPath) {
            return false;
        }
        return super.moveValide(startCell, endCell, game, bool);
    }


}
