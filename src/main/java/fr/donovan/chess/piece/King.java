package fr.donovan.chess.piece;

import fr.donovan.chess.*;
import fr.donovan.chess.exception.InvalidMove;

import java.util.Objects;

public class King extends Piece {
    private boolean firstMove = true;
    public King(Color color) {
        super(color);
        if (Objects.equals(color, Color.WHITE)) {
            this.setImage("♚");
        }
        if (Objects.equals(color, Color.BLACK)) {
            this.setImage("♔");
        }
        this.setImage("K");
    }

    @Override
    public boolean move(Cell startCell, Cell endCell, Game game) throws InvalidMove {
//        int shiftColumn = Math.abs(startCell.getColumn() - endCell.getColumn());
//        int shiftLine = Math.abs(startCell.getLine() - endCell.getLine());
//        if (shiftLine > 1 || shiftColumn > 1) {
//            throw new InvalidMove(this);
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
        int shiftColumn = Math.abs(startCell.getColumn() - endCell.getColumn());
        int shiftLine = Math.abs(startCell.getLine() - endCell.getLine());
        int orientation = this.getColor() == Color.BLACK ? 7 : 0;
        if (shiftLine <= 1 && shiftColumn <= 1) {
            return super.moveValide(startCell, endCell, game, bool);
        }
        if (firstMove && endCell.getLine() == orientation && (endCell.getColumn() == 1 || endCell.getColumn() == 6)) {
            int columnRook = endCell.getColumn() == 6 ? 7 : 0;
            Cell cellRook = game.getBoard().get(orientation).get(columnRook);
            if (cellRook.getPiece() != null && cellRook.getPiece() instanceof Rook) {
                int[] listCellToBeEmpty = endCell.getColumn() == 6 ? new int[]{5} : new int[]{2, 3};
                boolean emptyPath = true;
                boolean checkedInPath = false;
                startCell.setPiece(null);
                for (int cellColumn : listCellToBeEmpty) {
                    emptyPath = game.getBoard().get(orientation).get(cellColumn).getPiece() == null && emptyPath;
                    checkedInPath = game.thisCellIsChecked(game.getBoard().get(orientation).get(cellColumn)) || checkedInPath;
                }
                startCell.setPiece(this);
                if (emptyPath && !checkedInPath) {
                    boolean moved = super.moveValide(startCell, endCell, game, bool);
                    if (moved && bool) {
                        int newColumnRook = endCell.getColumn() == 6 ? 5 : 2;
                        Cell newCellRook = game.getBoard().get(orientation).get(newColumnRook);
                        newCellRook.setPiece(cellRook.getPiece());
                        cellRook.setPiece(null);
                    }
                    return moved;
                }
                return false;
            }
            return false;
        }
        return false;
    }
}
