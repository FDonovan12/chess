package fr.donovan.chess.piece;

import fr.donovan.chess.*;
import fr.donovan.chess.exception.InvalidMove;

import java.util.ArrayList;
import java.util.Objects;

public class Rook extends Piece {
    public Rook(Color color) {
        super(color);
        if (Objects.equals(color, Color.WHITE)) {
            this.setImage("♜");
        }
        if (Objects.equals(color, Color.BLACK)) {
            this.setImage("♖");
        }
        this.setImage("R");
    }

    @Override
    public boolean move(Cell startCell, Cell endCell, Game game) throws InvalidMove {
//        int shiftColumn = (startCell.getColumn() - endCell.getColumn());
//        int shiftLine = (startCell.getLine() - endCell.getLine());
//        boolean pieceOnPath = false;
//        if (shiftLine == 0 || shiftColumn == 0) {
//            pieceOnPath = isPieceOnPathRook(startCell, endCell, game, shiftColumn, shiftLine, pieceOnPath);
//        } else {
//            throw new InvalidMove(this);
//        }
//        if (pieceOnPath) {
//            throw new InvalidMove(true);
//        }
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
        if(bool) {
            game.resetCase();
        }
        int shiftColumn = (startCell.getColumn() - endCell.getColumn());
        int shiftLine = (startCell.getLine() - endCell.getLine());
        boolean pieceOnPath = false;
        if (shiftLine == 0 || shiftColumn == 0) {
            pieceOnPath = isPieceOnPathRook(startCell, endCell, game, shiftColumn, shiftLine, pieceOnPath);
        } else {
            return false;
        }
        if (pieceOnPath) {
            return false;
        }
        return super.moveValide(startCell, endCell, game, bool);
    }
//    @Override
//    public void possibleMove(Cell startCell, GameRepository game) {
//        for (ArrayList<Cell> line : game.getBoard()) {
//            for (Cell endCell : line) {
//                if (moveValide(startCell, endCell, game, false)) {
//                    endCell.setColor(Color.POSSIBLE);
//                }
//            }
//        }
//    }
}
