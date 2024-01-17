package fr.donovan.chess.piece;

import fr.donovan.chess.*;
import fr.donovan.chess.exception.InvalidMove;

import java.util.Objects;

public class Knight extends Piece {
    public Knight(Color color) {
        super(color);
        if (Objects.equals(color, Color.WHITE)) {
            this.setImage("♞");
        }
        if (Objects.equals(color, Color.BLACK)) {
            this.setImage("♘");
        }
        this.setImage("N");
    }

    @Override
    public boolean move(Cell startCell, Cell endCell, Game game) throws InvalidMove {
//        int shiftColumn = Math.abs(startCell.getColumn() - endCell.getColumn());
//        int shiftLine = Math.abs(startCell.getLine() - endCell.getLine());
//        if (shiftColumn + shiftLine == 3 && shiftColumn != 0 && shiftLine != 0) {
//            return super.move(startCell, endCell, game);
//        }
//        throw new InvalidMove(this);
//        System.out.println(" Deplacement invalide pour le cavalier");
//        return false;
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
        int shiftColumn = Math.abs(startCell.getColumn() - endCell.getColumn());
        int shiftLine = Math.abs(startCell.getLine() - endCell.getLine());
        if (shiftColumn + shiftLine == 3 && shiftColumn != 0 && shiftLine != 0) {
            return super.moveValide(startCell, endCell, game, bool);
        }
//        throw new InvalidMove(this);
        return false;
    }
}
